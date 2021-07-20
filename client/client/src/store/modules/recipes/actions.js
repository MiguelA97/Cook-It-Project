import recipeAPIService from '../../../services/recipeAPIService.js'
import userRecipesListsService from '../../../services/userRecipesListsService.js'
import recipeService from '../../../services/recipeService.js'
import ingredientDetailsService from '../../../services/ingredientDetailsService.js'
import authService from '../../../services/authenticationService.js'

export default {
    async searchRecipes(context, data) {   
        let intolerances = '';
        if (data.filter.intolerances !== null) {
            data.filter.intolerances.forEach(intolerance => intolerances += intolerance + ',');
        }
        await recipeAPIService.searchRecipes(data.toSearch, data.filter.diet, intolerances, data.filter.type, data.filter.cuisine)
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
                let summary = recipe.summary.replace(/(<([^>]+)>)/gi, "");
                let instrucions = recipe.instructions.replace(/(<([^>]+)>)/gi, "");
                recipe.summary = summary;
                recipe.instructions = instrucions;

                context.commit('setRecipe', recipe);
            })
            .catch(error => {
                console.log(error.response) //aqui tenho acesso ao objecto do erro com as informaçoes  
            });
    },
    addUserRecipeList(context, data) {
        userRecipesListsService.addUserRecipeList(data.username, data.formData)
            .then(response => {
                let recipeList = {
                    id: response.data.properties.userRecipeListId,
                    userId: response.data.properties.userId,
                    name: response.data.properties.userRecipeListName,
                    description: response.data.properties.userRecipeListDescription,
                    visibility: response.data.properties.userRecipeListVisibility,
                    recipes: response.data.properties.userRecipeListRecipes
                }
                context.commit('addUserRecipeList', recipeList)
                data.vm.$notify("List created!");
            })
            .catch(error => {
                data.vm.$notify(error.response.data.detail);
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
            .then(response => {
                console.log(response)
                data.vm.$notify("The list was updated!");
            })
            .catch(error => {
                data.vm.$notify(error.response.data.detail);
            })
    },
    deleteUserRecipeList(context, data) {
        userRecipesListsService.deleteUserRecipeList(data.username, data.listId)
            .then(response => {
                console.log(response)
                context.commit('deleteUserRecipeList', data.index)
                data.vm.$notify("List deleted!");
            })
            .catch(error => {
                data.vm.$notify(error.response.data.detail);
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
            .then(response => {
                console.log(response)
                data.vm.$notify("Recipe created and added to list!");
            })
            .catch(error => {
                data.vm.$notify(error.response.data.detail);
            })
    },
    updateRecipe(context, data) {
        recipeService.updateRecipe(data.username, data.listId, data.recipeId, data.recipe)
            .then(response => {
                const recipe = {
                    apiId: response.data.properties.apiId,
                    id: response.data.properties.recipeId,
                    image: response.data.properties.recipeImage,
                    name: response.data.properties.recipeName,
                    readyInMinutes: response.data.properties.recipeReadyInMinutes,
                    instructions: response.data.properties.recipeInstructions,
                    servings: response.data.properties.recipeServings,
                    dairyFree: response.data.properties.recipeDairyFree,
                    glutenFree: response.data.properties.recipeGlutenFree,
                    vegan: response.data.properties.recipeVegan,
                    vegetarian: response.data.properties.recipeVegetarian,
                    ingredientDetailsList: response.data.properties.recipeIngredientDetailsList
                };

                context.commit('setRecipe', recipe);
                data.vm.$notify("This recipe has been updated!");
            })
            .catch(error => {
                data.vm.$notify(error.response.data.detail);
            })
    },
    deleteRecipe(context, data) {
        recipeService.deleteRecipe(data.username, data.listId, data.recipeId)
            .then(response => {
                console.log(response)
                context.commit('deleteRecipe', data.index)
                data.vm.$notify("Recipe deleted!");
            })       
            .catch(error => {
                data.vm.$notify(error.response.data.detail);
            })
    },
    updateIngredientDetails(context, data) {
        ingredientDetailsService.updateIngredientDetails(data.username, data.idUrl, data.recipeId, data.ingredientDetailsId, data.formData)
            .then(response => {
                console.log(response)
                context.commit('setRecipeIngredientDetails', data.ingredients);
                data.vm.$notify("The ingredient was updated!");
            })
            .catch(error => {
                data.vm.$notify(error.response.data.detail);
            })
    },
    addIngredientDetails(context, data) {
        ingredientDetailsService.addIngredientDetails(data.username, data.idUrl, data.recipeId, data.formData)
            .then(response => {
                console.log(response)
                const ingredientDetails = {
                    aisle: response.data.properties.ingredientDetailsAisle,
                    amount: response.data.properties.ingredientDetailsAmount,
                    id: response.data.properties.ingredientDetailsId,
                    apiId: response.data.properties.ingredientDetailsApidId,
                    image: response.data.properties.ingredientDetailsImage,
                    ingredientName: response.data.properties.ingredientDetailsName,
                    recipeId: response.data.properties.recipeId,
                    unit: response.data.properties.ingredientDetailsUnit
                };
                context.commit('addRecipeIngredientDetails', ingredientDetails)
                data.vm.$notify("The ingredient was added!");
            })
            .catch(error => {
                data.vm.$notify(error.response.data.detail);
            })
    },
    deleteIngredientDetails(context, data) {
        ingredientDetailsService.deleteIngredientDetails(data.username, data.idUrl, data.recipeId,  data.ingredientDetailsId)
            .then(response => {
                console.log(response)
                context.commit('setRecipeIngredientDetails', data.ingredients);
                data.vm.$notify("The ingredient was deleted!");
            })
            .catch(error => {
                data.vm.$notify(error.response.data.detail);
            })
    }
};