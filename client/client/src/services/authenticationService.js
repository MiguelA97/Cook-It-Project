import axios from 'axios';

const LOGIN_URL = "https://localhost:8443/v1/users/login";

async function login(user) {
    const response = await axios.post(LOGIN_URL, user);
    if (response.data.token) {
        localStorage.setItem('user', JSON.stringify(response.data));
    }

    return response.data;
}

function logout() {
    localStorage.removeItem('user');    
}

export default { login, logout };