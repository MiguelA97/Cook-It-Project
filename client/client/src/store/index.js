import { createStore } from 'vuex';
import createPersistedState from "vuex-persistedstate";

import usersModule from './modules/users/index.js';
import recipesModule from './modules/recipes/index.js';

const store = createStore({
    plugins: [createPersistedState()],
    modules: {
        user: usersModule,
        recipes: recipesModule
    }
});

export default store;