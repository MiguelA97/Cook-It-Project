import { createRouter, createWebHistory } from 'vue-router';

import HomePage from './pages/HomePage.vue'
import NotFound from './pages/NotFound.Vue'
import UserLogin from './pages/users/UserLogin.vue'
import UserRegistration from './pages/users/UserRegistration.vue'
import UserDetails from './pages/users/UserDetails.vue'
import RecipesList from './pages/recipes/RecipesList.vue'
import RecipeDetails from './pages/recipes/RecipeDetails.vue'
import RecipeListsList from './pages/recipes/RecipeListsList.vue'
import UserRecipeListRecipesList from './pages/recipes/UserRecipeListRecipesList.vue'
import AddRecipe from './pages/recipes/AddRecipe.vue'
import EditRecipeForm from './pages/recipes/EditRecipeForm.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', component: HomePage },
        { path: '/login', component: UserLogin },
        { path: '/register', component: UserRegistration },
        { path: '/recipes', component: RecipesList },
        { path: '/recipes/:id', component: RecipeDetails, props: true },
        { path: '/profile/:username/details', component: UserDetails, props: true },
        { path: '/profile/:username/recipeLists', component: RecipeListsList },
        { path: '/profile/:username/recipeLists/:idUrl/recipes', component: UserRecipeListRecipesList },
        { path: '/profile/:username/recipeLists/:idUrl/recipes/:idRecipe/editRecipe', component: EditRecipeForm },
        { path: '/recipe/addRecipe', component: AddRecipe },
        { path: '/:notFound(.*)', component: NotFound }
    ]
});

export default router;