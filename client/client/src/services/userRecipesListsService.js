import axios from 'axios';

const GET_CREATE_USER_RECIPES_LIST_URL = "http://localhost:8081/v1/users/:username/lists";
const UPDATE_DELETE_USER_RECIPES_LIST_URL = "http://localhost:8081/v1/users/:username/lists/:idUrl";

async function getUserRecipeListsByUsername(username) {
    const url = GET_CREATE_USER_RECIPES_LIST_URL.replace(":username", username);
    const response = await axios.get(url);
    return response; 
}

async function addUserRecipeList(username, userRecipeList) {
    const url = GET_CREATE_USER_RECIPES_LIST_URL.replace(":username", username);
    const response = await axios.post(url, userRecipeList);
    return response;
}

async function updateUserRecipeList(username, idUrl, userRecipeList) {
    var url = UPDATE_DELETE_USER_RECIPES_LIST_URL.replace(":username", username);
    url = url.replace(":idUrl", idUrl);
    const response = await axios.patch(url, userRecipeList);
    return response; 
}

async function deleteUserRecipeList(username, idUrl) {
    var url = UPDATE_DELETE_USER_RECIPES_LIST_URL.replace(":username", username);
    url = url.replace(":idUrl", idUrl);
    const response = await axios.delete(url);
    return response; 
}

export default { getUserRecipeListsByUsername, addUserRecipeList, updateUserRecipeList, deleteUserRecipeList };