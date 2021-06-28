import axios from 'axios';
import authHeader from './authHeader';

const GET_UPDATE_DELETE_USER_URL = "http://localhost:8081/v1/users/:username";
const CREATE_USER_URL = "http://localhost:8081/v1/users";

async function getUser(username) {
    const url = GET_UPDATE_DELETE_USER_URL.replace(":username", username);
    const response = await axios.get(url);
    return response; 
}

async function addUser(user) {
    const response = await axios.post(CREATE_USER_URL, user);
    return response;
}

async function updateUser(user, username) {
    const url = GET_UPDATE_DELETE_USER_URL.replace(":username", username);
    const response = await axios.patch(url, user, { headers: authHeader() });
    return response; 
}

async function deleteUser(username) {
    const url = GET_UPDATE_DELETE_USER_URL.replace(":username", username);
    const response = await axios.delete(url, { headers: authHeader() });
    return response; 
}

export default { getUser, addUser, updateUser, deleteUser };