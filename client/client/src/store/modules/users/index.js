import mutations from './mutations.js';
import actions from './actions.js';
import getters from './getters.js';

export default {
    namespaced: true,
    state() {
        return {
            user: {
                    id: null,
                    username: '',
                    email: '',
                    name: '',
                    recipesList: ''
                }
        }   
    },
    mutations,
    actions,
    getters
}