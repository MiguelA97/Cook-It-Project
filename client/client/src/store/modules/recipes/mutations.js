export default {
    searchRecipes(state, payload) {
        state.recipes.push(payload);
    },
    setRecipes(state, payload) {
      state.recipes = payload;
    },
    setRecipe(state, payload) {
      state.recipe = payload;
    }
};