import axios from 'axios';
import authHeader from './authHeader';

const UPDATE_DELETE_INGREDIENT_DETAILS_URL = "https://localhost:8443/v1/users/:username/lists/:listId/recipes/:recipeId/ingredients/:ingredientDetailsId";
const CREATE_INGREDIENT_DETAILS_URL = "https://localhost:8443/v1/users/:username/lists/:listId/recipes/:recipeId/ingredients";

async function updateIngredientDetails(username, listId, recipeId, ingredientDetailsId, ingredientDetails) {
    const url = UPDATE_DELETE_INGREDIENT_DETAILS_URL.replace(":username", username).replace(":listId", listId).replace(":recipeId", recipeId).replace(":ingredientDetailsId", ingredientDetailsId);
    const response = await axios.patch(url, ingredientDetails, { headers: authHeader() });
    return response; 
}

async function addIngredientDetails(username, listId, recipeId, ingredientDetails) {
    const url = CREATE_INGREDIENT_DETAILS_URL.replace(":username", username).replace(":listId", listId).replace(":recipeId", recipeId);
    const response = await axios.post(url, ingredientDetails, { headers: authHeader() });
    return response; 
}

async function deleteIngredientDetails(username, listId, recipeId, ingredientDetailsId) {
    const url = UPDATE_DELETE_INGREDIENT_DETAILS_URL.replace(":username", username).replace(":listId", listId).replace(":recipeId", recipeId).replace(":ingredientDetailsId", ingredientDetailsId);
    const response = await axios.delete(url, { headers: authHeader() });
    return response; 
}

export default { updateIngredientDetails, addIngredientDetails, deleteIngredientDetails };