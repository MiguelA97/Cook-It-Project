import recipeAPIService from '../../../services/recipeAPIService.js'
import userRecipesListsService from '../../../services/userRecipesListsService.js'
import recipeService from '../../../services/recipeService.js'
import authService from '../../../services/authenticationService.js'

export default {
    async searchRecipes(context, data) {   
        await recipeAPIService.searchRecipes(data.toSearch, data.filter.diet, data.filter.intolerances, data.filter.type, data.filter.cuisine)
            .then(response => {
                const recipes = [];

                response.data.forEach(recipe => {
                    const newRecipe = {
                        id: recipe.id,
                        image: recipe.image,
                        title: recipe.title
                    };
                    recipes.push(newRecipe);
                });

                context.commit('setRecipes', recipes);
            })
            .catch(error => {
                console.log(error.response) //aqui tenho acesso ao objecto do erro com as informaçoes  
            });
    },
    async getRecipeDetails(context, recipeId) {
        await recipeAPIService.getRecipeInformation(recipeId)
            .then(response => {
                const recipe = {
                    apiId: response.data.apiId,
                    image: response.data.image,
                    name: response.data.title,
                    dairyFree: response.data.dairyFree,
                    glutenFree: response.data.glutenFree,
                    vegan: response.data.vegan,
                    vegetarian: response.data.vegetarian,
                    instructions: response.data.instructions,
                    readyInMinutes: response.data.readyInMinutes,
                    servings: response.data.servings,
                    summary: response.data.summary,
                    ingredientDetailsList: response.data.ingredients
                };

                context.commit('setRecipe', recipe);
            })
            .catch(error => {
                console.log(error.response) //aqui tenho acesso ao objecto do erro com as informaçoes  
            });
    },
    addUserRecipeList(context, data) {
        userRecipesListsService.addUserRecipeList(data.username, data.formData)
            .then(response => {
                data = {
                    id: response.data.properties.userRecipeListId,
                    userId: response.data.properties.userId,
                    name: response.data.properties.userRecipeListName,
                    description: response.data.properties.userRecipeListDescription,
                    visibility: response.data.properties.userRecipeListVisibility,
                    recipes: response.data.properties.userRecipeListRecipes
                }
                context.commit('addUserRecipeList', data)
            })
            .catch(error => {
                console.log(error.response) //aqui tenho acesso ao objecto do erro com as informaçoes  
            })
    },
    getUserRecipeListsByUsername(context, username) {
        userRecipesListsService.getUserRecipeListsByUsername(username)
            .then(response => {
                const recipeLists = [];
    
                response.data.forEach(recipeList => {
                    const newRecipeList = {
                        id: recipeList.properties.userRecipeListId,
                        userId: recipeList.properties.userId,
                        name: recipeList.properties.userRecipeListName,
                        description: recipeList.properties.userRecipeListDescription,
                        visibility: recipeList.properties.userRecipeListVisibility,
                        recipes: recipeList.properties.userRecipeListRecipes       
                    };
                    recipeLists.push(newRecipeList);
                });

                context.commit('setUserRecipeLists', recipeLists)
            })
            .catch(error => {
                if (error.status === 401) {     //quando auth expira, fazer logout!
                    authService.logout();
                }
                console.log(error.response) //aqui tenho acesso ao objecto do erro com as informaçoes  
            })
    },
    updateUserRecipeList(context, data) {
        userRecipesListsService.updateUserRecipeList(data.username, data.idUrl, data.formData)
            .then()
            .catch(error => {
                console.log(error.response) //aqui tenho acesso ao objecto do erro com as informaçoes  
            })
    },
    deleteUserRecipeList(context, data) {
        userRecipesListsService.deleteUserRecipeList(data.username, data.listId)
            .then(context.commit('deleteUserRecipeList', data.index))
            .catch(error => {
                console.log(error.response) //aqui tenho acesso ao objecto do erro com as informaçoes 
            });
    },
    getRecipesByUserRecipeListId(context, data) {
        recipeService.getRecipesByUserRecipeListId(data.username, data.listId)
            .then(response => {
                const recipes = [];

                response.data.forEach(recipe => {
                    const newRecipe = {
                        id: recipe.properties.recipeId,
                        image: recipe.properties.recipeImage,
                        name: recipe.properties.recipeName,
                        readyInMinutes: recipe.properties.recipeReadyInMinutes,
                        instructions: recipe.properties.recipeInstructions,
                        servings: recipe.properties.recipeServings,
                        dairyFree: recipe.properties.recipeDairyFree,
                        glutenFree: recipe.properties.recipeGlutenFree,
                        vegan: recipe.properties.recipeVegan,
                        vegetarian: recipe.properties.recipeVegetarian,
                        ingredientDetailsList: recipe.properties.recipeIngredientDetailsList
                    };
                    recipes.push(newRecipe);
                });

                context.commit('setRecipes', recipes);
            })
            .catch(error => {
                console.log(error.response) //aqui tenho acesso ao objecto do erro com as informaçoes  
            });
    },
    addRecipe(context, data) {
        recipeService.addRecipe(data.username, data.listId, data.recipe)
            .then()
            .catch(error => {
                console.log(error.response)     //aqui tenho acesso ao objecto do erro com as informaçoes  
            })
    },
    updateRecipe(context, data) {
        console.log(context + data)
    },
    deleteRecipe(context, data) {
        recipeService.deleteRecipe(data.username, data.listId, data.recipeId)
            .then(context.commit('deleteRecipe', data.index))       
            .catch(error => {
                console.log(error.response)     //aqui tenho acesso ao objecto do erro com as informaçoes  
            })
    }
};