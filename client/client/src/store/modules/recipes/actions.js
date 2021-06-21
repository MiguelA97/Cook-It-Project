import recipeAPIService from '../../../services/recipeAPIService.js'

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

            console.log(response)

            context.commit('setRecipes', recipes);
        })
        .catch(error => {
            console.log(error.response) //aqui tenho acesso ao objecto do erro com as informaçoes  
        });
    },
    getRecipeDetails(context, recipeId) {
        recipeAPIService.getRecipeInformation(recipeId)
        .then(response => {
            const recipe = {
                id: response.data.properties.
                image: response.data.properties.
                title: response.data.properties.
            };

            console.log(response)

            context.commit('setRecipe', recipe);
        })
        .catch(error => {
            console.log(error.response) //aqui tenho acesso ao objecto do erro com as informaçoes  
        });
    }
};