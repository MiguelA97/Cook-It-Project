import userService from '../../../services/userService.js'
import authService from '../../../services/authenticationService.js'

export default {
    addUser(context, data) {
        userService.addUser(data)
            .then()
            .catch(error => {
                console.log(error) //aqui tenho acesso ao objecto do erro com as informaçoes  
            });
    },
    login(context, data) {
        authService.login(data)
            .then(response => {
                context.commit('login', response);
            })
            .catch(error => {
                console.log(error) //aqui tenho acesso ao objecto do erro com as informaçoes  
            });
    },
    logout(context) {
        authService.logout();
        context.commit('logout');
    }
};