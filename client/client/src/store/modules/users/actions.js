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
    updateUser(context, data) {
        userService.updateUser(data.user, data.username)
        .then(response => {
            var user = JSON.parse(localStorage.getItem('user'));
            user.name = response.data.properties.userName;
            user.username = response.data.properties.userUsername;
            user.email = response.data.properties.userEmail;

            localStorage.setItem('user', JSON.stringify(user));
            context.commit('setUser', user);

            if (user.username !== data.username) {  //temos que revalidar o jwt, para tal é necessário novo login
                const loginData = {
                    username: user.username,
                    password: data.user.password
                };
                authService.login(loginData)
                    .then(response => {
                        context.commit('login', response);
                    })
                    .catch(error => {
                        console.log(error) //aqui tenho acesso ao objecto do erro com as informaçoes  
                    });
            }
        })
        .catch(error => {
            console.log(error) //aqui tenho acesso ao objecto do erro com as informaçoes  
        });
    },
    deleteUser(context, data) {
        userService.deleteUser(data)
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