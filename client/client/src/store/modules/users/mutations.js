export default {
    login(state, user) {
        state.loggedIn = true;
        state.user = user;
    },
    logout(state) {
        state.loggedIn = false;
        state.user = null;
    },
    setUser(state, user) {
        state.user = user;
    },
    setUserRecipeLists(state, payload) {
        state.recipeLists = payload;
    },
    setUserRecipeListIndex(state, payload) {
        state.recipeListIndex = payload;
    },
    setIngredients(state, payload) {
        state.ingredients = payload;
    },
    addIngredient(state, payload) {
        state.ingredients.push(payload);
    },
    deleteIngredient(state, payload) {
        state.ingredients.splice(payload, 1);
    }
};