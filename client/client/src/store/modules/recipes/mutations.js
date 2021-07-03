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
      state.recipeLists.filter(list => list.id != payload);
    },
    setUserRecipeLists(state, payload) {
      state.recipeLists = payload;
    }
};