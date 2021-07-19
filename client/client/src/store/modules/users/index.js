import mutations from './mutations.js';
import actions from './actions.js';
import getters from './getters.js';

export default {
    namespaced: true,
    state() {
        return {
            loggedIn: JSON.parse(localStorage.getItem('user')) === null ? false : true,
            user: JSON.parse(localStorage.getItem('user')),
            recipeLists: [],
            recipeListIndex: null,
            ingredients: []
        }   
    },
    mutations,
    actions,
    getters
}