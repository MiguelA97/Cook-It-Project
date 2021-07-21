import userService from '../../../services/userService.js'
import authService from '../../../services/authenticationService.js'

export default {
    getUser(context, data) {
        userService.getUser(data)
            .then(response => {
                const recipeLists = [];
    
                response.data.properties.userRecipeLists.forEach(recipeList => {
                    const newRecipeList = {
                        id: recipeList.idUrl,
                        name: recipeList.listName,
                        description: recipeList.description,
                        recipes: recipeList.recipes       
                    };
                    if (recipeList.visibility === 'public')
                        recipeLists.push(newRecipeList);
                });
                context.commit('setUserRecipeLists', recipeLists);
            })
            .catch(error => {
                console.log(error.response) //aqui tenho acesso ao objecto do erro com as informaçoes  
            });
    },
    addUser(context, data) {
        userService.addUser(data.data)
            .then(response => {
                console.log(response)
                data.vm.$notify("User account created!");
                data.vm.$router.replace('/login');
            })
            .catch(error => {
                data.vm.$notify(error.response.data.detail);
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
                data.vm.$notify("User account updated!");

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
                data.vm.$notify(error.response.data.detail);
            });
    },
    deleteUser(context, data) {
        userService.deleteUser(data)
            .then(response => {
                console.log(response)
            })
            .catch(error => {
                console.log(error) //aqui tenho acesso ao objecto do erro com as informaçoes  
            });
    },
    getUserIngredients(context, data) {
        userService.getUser(data)
            .then(response => {
                context.commit('setIngredients', response.data.properties.userIngredients);
            })
            .catch(error => {
                console.log(error) //aqui tenho acesso ao objecto do erro com as informaçoes  
            });
    },
    addUserIngredient(context, data) {
        userService.addUserIngredient(data.username, data.ingredient)
            .then(response => {
                console.log(response)
                context.commit('addIngredient', data.ingredient)
                data.vm.$notify("The ingredient, " + data.ingredient + " was added to the pantry!")
            })
            .catch(error => {
                data.vm.$notify(error.response.data.detail);
            });
    },
    deleteUserIngredient(context, data) {
        userService.deleteUserIngredient(data.username, data.ingredient)
            .then(response => {
                console.log(response)
                context.commit('deleteIngredient', data.index)
                data.vm.$notify("The ingredient, " + data.ingredient + " was deleted from the pantry!");   
            }) 
            .catch(error => {
                data.vm.$notify(error.response.data.detail);
            });
    },
    login(context, data) {
        authService.login(data.formData)
            .then(response => {
                context.commit('login', response);
                data.vm.$notify("Welcome, " + data.formData.username + "!");
                data.vm.$router.replace('/');
            })
            .catch(error => {
                data.vm.$notify(error.response.data.detail);
            });
    },
    logout(context) {
        authService.logout();
        context.commit('logout');
    }
};