package isel.leic.ps.service.implementations;

import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.exceptions.EntityNotFoundException;
import isel.leic.ps.model.outputModel.jsonObjects.*;
import isel.leic.ps.service.APIService;
import isel.leic.ps.utils.RestrictionUtils;
import isel.leic.ps.utils.ValidationsUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class APIServiceImpl implements APIService {

    /** API request access data */
    private String HEADERS_KEY = "x-rapidapi-key";
    private String HEADERS_HOST = "x-rapidapi-host";
    @Value("${api.key}")
    private String API_KEY;
    private String API_HOST = "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com";

    /** API requests URL's */
    private String searchRecipesUrl = "https://" + API_HOST + "/recipes/searchComplex?limitLicense=true";
    private String summarizeRecipeUrl = "https://" + API_HOST + "/recipes/#?recipeId;/summary";
    private String getRecipeInformationUrl = "https://" + API_HOST + "/recipes/#?recipeId;/information";

    private final RestTemplate restTemplate;
    private final MessageSource messageSource;

    public APIServiceImpl(RestTemplateBuilder restTemplateBuilder, MessageSource messageSource) {
        this.restTemplate = restTemplateBuilder.build();
        this.messageSource = messageSource;
    }

    @Override
    public List<RecipeObject> searchRecipes(String query, String number, String offset, String diet, String intolerances, String type, String cuisine, String ingredients) throws EntityException {
        ValidationsUtils.validateSearchRecipesNumber(number);
        ValidationsUtils.validateSearchRecipesOffset(offset);
        ValidationsUtils.validateOptionalParameters(diet, RestrictionUtils.API_ACCEPTED_DIETS, "diet");
        ValidationsUtils.validateOptionalParameters(intolerances, RestrictionUtils.API_ACCEPTED_INTOLERANCES, "intolerances");
        ValidationsUtils.validateOptionalParameters(type, RestrictionUtils.API_ACCEPTED_TYPES, "type");
        ValidationsUtils.validateOptionalParameters(cuisine, RestrictionUtils.API_ACCEPTED_CUISINES, "cuisine");


        HttpEntity<String> entity = setHeaders();
        String url = searchRecipesUrl + "&number=" + number + "&offset=" + offset;
        //optional parameters url
        if (!query.isEmpty()) url += "&query=" + query;
        else if (!ingredients.isEmpty()) url += "&includeIngredients=" + ingredients;
        if (!diet.isEmpty()) url += "&diet=" + diet;
        if (!intolerances.isEmpty()) url += "&intolerances=" + intolerances;
        if (!type.isEmpty()) url += "&type=" + type;
        if (!cuisine.isEmpty()) url += "&cuisine=" + cuisine;
        ResponseEntity<SearchRecipesObject> searchRecipesObject = restTemplate.exchange(url, HttpMethod.GET, entity, SearchRecipesObject.class);

        List<RecipeObject> recipeObjects = new ArrayList<>();
        for (RecipeObject recipeObject : searchRecipesObject.getBody().getResults())
            recipeObjects.add(new RecipeObject(recipeObject.getId(), recipeObject.getTitle(), recipeObject.getImage()));

        return recipeObjects;
    }

    @Override
    public RecipeInformationObject getRecipeInformation(int recipeId) throws EntityException, EntityNotFoundException {
        ValidationsUtils.validateRecipeId(recipeId);

        HttpEntity<String> entity = setHeaders();
        String summaryUrl = summarizeRecipeUrl.replaceAll("#\\?.*?;", recipeId + "");                   // replaces #?recipeId; with recipeId in the url
        String recipeInformationUrl = getRecipeInformationUrl.replaceAll("#\\?.*?;", recipeId + "");    // replaces #?recipeId; with recipeId in the url

        ResponseEntity<RecipeInformationObject> recipeInformationObject;
        try {
            recipeInformationObject = restTemplate.exchange(recipeInformationUrl, HttpMethod.GET, entity, RecipeInformationObject.class);
        } catch (RestClientException e) {
            throw new EntityNotFoundException(messageSource.getMessage("recipe_information_Not_Exist", new Object[]{recipeId}, Locale.ENGLISH));
        }

        ResponseEntity<SummarizeRecipeObject> summarizeRecipeObject = restTemplate.exchange(summaryUrl, HttpMethod.GET, entity, SummarizeRecipeObject.class);
        recipeInformationObject.getBody().setSummary(summarizeRecipeObject.getBody().getSummary());

        return recipeInformationObject.getBody();
    }

    /**
     * @return HttpEntity<String> with the correct headers.
     */
    private HttpEntity<String> setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HEADERS_KEY, API_KEY);
        headers.set(HEADERS_HOST, API_HOST);
        return new HttpEntity<>("", headers);
    }
}