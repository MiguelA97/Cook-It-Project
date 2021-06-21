import axios from 'axios';

const SEARCH_RECIPES_URL = "http://localhost:8081/v1/recipes/search?";
const GET_RECIPE_INFORMATION_URL = "http://localhost:8081/v1/recipes/:recipeId/information";

async function searchRecipes(toSearch, diet, intolerances, type, cuisine) {
    var url = SEARCH_RECIPES_URL;
    if (toSearch.includes(","))
        url += "includeIngredients=" + toSearch;
    else url += "query=" + toSearch;
    if (diet !== undefined) url += "&diet=" + diet;
    if (intolerances !== undefined) url += "&intolerances=" + intolerances;
    if (type !== undefined) url += "&type=" + type;
    if (cuisine !== undefined) url += "&cuisine=" + cuisine;

    const response = await axios.get(url);
    return response; 
}

async function getRecipeInformation(recipeId) {
    const url = GET_RECIPE_INFORMATION_URL.replace(":recipeId", recipeId);
    const response = await axios.get(url);
    return response;
}

export default { searchRecipes, getRecipeInformation };