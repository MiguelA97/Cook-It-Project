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
import UserRecipeLists from './pages/users recipes/UserRecipeLists.vue'
import UserRecipesList from './pages/users recipes/UserRecipesList.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', component: HomePage },
        { path: '/login', component: UserLogin },
        { path: '/register', component: UserRegistration },
        { path: '/recipes', component: RecipesList },
        { path: '/recipes/:id', component: RecipeDetails },
        { path: '/profile/:username/details', component: UserDetails },
        { path: '/profile/:username/recipesLists', component: RecipeListsList },
        { path: '/profile/:username/recipesLists/:idUrl/recipes', name: "recipes", component: UserRecipeListRecipesList, props: true },
        { path: '/profile/:username/recipesLists/:idUrl/recipes/:idRecipe', name: "recipe", component: RecipeDetails, props: true },
        { path: '/profile/:username/recipesLists/:idUrl/recipes/:idRecipe/editRecipe', component: EditRecipeForm, props: true },
        { path: '/recipe/addRecipe', component: AddRecipe },
        { path: '/user/:username/recipesLists', name: "userRecipeLists", component: UserRecipeLists, props: true },
        { path: '/user/:username/recipesLists/recipes', name: "userRecipes", component: UserRecipesList, props: true },
        { path: '/user/:username/recipesLists/recipes/recipe', name: "userRecipe", component: RecipeDetails, props: true },
        { path: '/:notFound(.*)', component: NotFound }
    ]
});

export default router;