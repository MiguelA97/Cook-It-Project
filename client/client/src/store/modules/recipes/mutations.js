export default {
    setRecipes(state, payload) {
      state.recipes = payload;
    },
    setRecipe(state, payload) {
      state.recipe = payload;
    },
    addUserRecipeList(state, payload) {
      state.recipeLists.push(payload);
    },
    deleteUserRecipeList(state, payload) {
      state.recipeLists.splice(payload, 1);
    },
    setUserRecipeLists(state, payload) {
      state.recipeLists = payload;
    },
    setUserRecipeList(state, payload) {
      state.recipeList = payload;
    },
    deleteRecipe(state, payload) {
      state.recipes.splice(payload, 1);
    },
    setRecipeIngredientDetails(state, payload) {
      state.recipe.ingredientDetailsList = payload;
    },
    addRecipeIngredientDetails(state, payload) {
      state.recipe.ingredientDetailsList.push(payload);
    },
    deleteRecipeIngredientDetails(state, payload) {
      state.recipe.ingredientDetailsList.splice(payload, 1);
    }
};