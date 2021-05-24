package isel.leic.ps.service.implementations;

import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.model.outputModel.jsonObjects.RecipeObject;
import isel.leic.ps.model.outputModel.jsonObjects.SearchRecipesObject;
import isel.leic.ps.service.APIService;
import isel.leic.ps.utils.ValidationsUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class APIServiceImpl implements APIService {

    /** API request access data */
    private String HEADERS_KEY = "x-rapidapi-key";
    private String HEADERS_HOST = "x-rapidapi-host";
    @Value("${api.key}")
    private String API_KEY;
    private String API_HOST = "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com";

    /** API requests URL's */
    private String searchRecipesUrl = "https://" + API_HOST + "/recipes/search?query=";

    private final RestTemplate restTemplate;

    public APIServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<RecipeObject> searchRecipes(String query) throws EntityException {
        ValidationsUtils.validateSearchRecipesQuery(query);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HEADERS_KEY, API_KEY);
        headers.set(HEADERS_HOST, API_HOST);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        String url = searchRecipesUrl + query;
        ResponseEntity<SearchRecipesObject> searchRecipesObject = restTemplate.exchange(url, HttpMethod.GET, entity, SearchRecipesObject.class);

        List<RecipeObject> recipeObjects = new ArrayList<>();
        for (RecipeObject recipeObject : searchRecipesObject.getBody().getResults())
            recipeObjects.add(new RecipeObject(recipeObject.getId(), recipeObject.getTitle(), recipeObject.getReadyInMinutes(), recipeObject.getServings(), recipeObject.getImage()));

        return recipeObjects;
    }
}
