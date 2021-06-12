import { createStore } from 'vuex';

import usersModule from './modules/users/index.js';
import recipesModule from './modules/recipes/index.js';

const store = createStore({
    modules: {
        user: usersModule,
        recipes: recipesModule
    }
});

export default store;