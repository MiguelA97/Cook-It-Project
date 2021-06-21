import userService from '../../../services/userService.js'

export default {
    addUser(context, data) {
        userService.addUser(data)
            .then(response => {
                data = {
                    id: response.data.properties.userId,
                    username: response.data.properties.userUsername,
                    email: response.data.properties.userEmail,
                    name: response.data.properties.userName,
                }
                context.commit('addUser', data);
            })
            .catch(error => {
                console.log(error.response) //aqui tenho acesso ao objecto do erro com as informaçoes  
            });
    }
};