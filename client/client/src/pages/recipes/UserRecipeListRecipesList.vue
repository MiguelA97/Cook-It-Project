<template>
    <section>
        <user-recipe-list-edit-form
            :username="username"
            :idUrl="idUrl"
        ></user-recipe-list-edit-form>
        <base-card v-if="recipes.length > 0">
            <ul>
                <recipe-item-db
                    v-for="(recipe, index) in recipes" 
                    :key="recipe.id"
                    :index="index"
                    :recipe="recipe"
                    :username="username"
                    :idUrl="idUrl"
                ></recipe-item-db>
            </ul>
        </base-card>
    </section>
</template>

<script>
import RecipeItemDb from '../../components/recipes/RecipeItemDB.vue'
import UserRecipeListEditForm from '../../components/userRecipeLists/UserRecipeListEditForm.vue'

export default {
    props: ['username', 'idUrl', 'name', 'description', 'visibility'],
    components: {
        RecipeItemDb,
        UserRecipeListEditForm
    },
    computed: {
        recipes() {
            return this.$store.getters['recipes/recipes'];
        }
    },
    created() {
        this.$store.dispatch('recipes/getRecipesByUserRecipeListId', {username: this.username, listId: this.idUrl});
    }
}
</script>

<style scoped>
ul {
  list-style: none;
  margin: 0;
  padding: 0;
}
</style>