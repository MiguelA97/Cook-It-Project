import axios from 'axios';
import authHeader from './authHeader';

const GET_UPDATE_DELETE_RECIPE_URL = "https://localhost:8443/v1/users/:username/lists/:listId/recipes/:recipeId";
const CREATE_RECIPE_URL = "https://localhost:8443/v1/users/:username/lists/:listId/recipes";

async function getRecipeById(username, listId, recipeId) {
    const url = GET_UPDATE_DELETE_RECIPE_URL.replace(":username", username).replace(":listId", listId).replace(":recipeId", recipeId);
    const response = await axios.get(url, { headers: authHeader() });
    return response; 
}

async function getRecipesByUserRecipeListId(username, listId) {
    const url = CREATE_RECIPE_URL.replace(":username", username).replace(":listId", listId);
    const response = await axios.get(url, { headers: authHeader() });
    return response; 
}

async function addRecipe(username, listId, recipe) {
    const url = CREATE_RECIPE_URL.replace(":username", username).replace(":listId", listId);
    const response = await axios.post(url, recipe, { headers: authHeader() });
    return response;
}

async function updateRecipe(username, listId, recipeId, recipe) {
    const url = GET_UPDATE_DELETE_RECIPE_URL.replace(":username", username).replace(":listId", listId).replace(":recipeId", recipeId);
    const response = await axios.patch(url, recipe, { headers: authHeader() });
    return response; 
}

async function deleteRecipe(username, listId, recipeId) {
    const url = GET_UPDATE_DELETE_RECIPE_URL.replace(":username", username).replace(":listId", listId).replace(":recipeId", recipeId);
    const response = await axios.delete(url, { headers: authHeader() });
    return response; 
}

export default { getRecipeById, getRecipesByUserRecipeListId, addRecipe, updateRecipe, deleteRecipe };