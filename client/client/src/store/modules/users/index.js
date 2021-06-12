import mutations from './mutations.js';
import actions from './actions.js';
import getters from './getters.js';

export default {
    namespaced: true,
    state() {
        return {
            user: [
                {
                    id: 1,
                    username: 'MiguelA97',
                    email: 'miguelachega@hotmail.com',
                    password: 'password',
                    name: 'Miguel'
                }
            ]
        }   
    },
    mutations,
    actions,
    getters
}