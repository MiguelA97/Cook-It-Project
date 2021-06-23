import recipeAPIService from '../../../services/recipeAPIService.js'
import userRecipesListsService from '../../../services/userRecipesListsService.js'

export default {
    searchRecipes(context, toSearch, diet, intolerances, type, cuisine) {
        recipeAPIService.searchRecipes(toSearch, diet, intolerances, type, cuisine)
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
    getRecipeDetails(context, recipeId) {
        recipeAPIService.getRecipeInformation(recipeId)
        .then(response => {
            console.log(response)
            const recipe = {
                apiId: response.data.apiId,
                image: response.data.image,
                title: response.data.title,
                dairyFree: response.data.dairyFree,
                glutenFree: response.data.glutenFree,
                vegan: response.data.vegan,
                vegetarian: response.data.vegetarian,
                instructions: response.data.instructions,
                readyInMinutes: response.data.readyInMinutes,
                servings: response.data.servings,
                summary: response.data.summary,
                ingredients: response.data.ingredients
            };

            context.commit('setRecipe', recipe);
        })
        .catch(error => {
            console.log(error.response) //aqui tenho acesso ao objecto do erro com as informaçoes  
        });
    },
    addUserRecipeList(context, data, username) {
        userRecipesListsService.addUserRecipeList(username, data)
        .then(response => {
            data = {
                id: response.data.properties.userRecipeListId,
                userId: response.data.properties.userId,
                name: response.data.properties.userRecipeListName,
                description: response.data.properties.userRecipeListDescription,
                visibility: response.data.properties.userRecipeListVisibility,
                recipes: response.data.properties.userRecipeListRecipes
            }
            console.log(data)
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
                    id: recipeList.userRecipeListId,
                    userId: recipeList.userId,
                    name: recipeList.userRecipeListName,
                    description: recipeList.userRecipeListDescription,
                    visibility: recipeList.userRecipeListVisibility,
                    recipes: recipeList.userRecipeListRecipes       //possivelmente deve ser alterado para o objecto com todos os parametros de uma receita
                };
                recipeLists.push(newRecipeList);
            });

            context.commit('setUserRecipeLists', recipeLists)
        })
        .catch(error => {
            console.log(error.response) //aqui tenho acesso ao objecto do erro com as informaçoes  
        })
    }
};