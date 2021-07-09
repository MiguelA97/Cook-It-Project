<template>
    <section>
        <base-card v-if="recipeLists.length > 0">
            <h1>{{username}} recipe lists:</h1>
            <ul>
                <search-user-recipe-list-item
                    v-for="recipeList in recipeLists" 
                    :key="recipeList.id"
                    :id="recipeList.id"
                    :name="recipeList.name"
                    :description="recipeList.description"
                    :recipes="recipeList.recipes"
                ></search-user-recipe-list-item>
            </ul>
        </base-card>
        <h1 v-else>The user {{username}} has no public lists.</h1>
    </section>
</template>

<script>
import SearchUserRecipeListItem from '../../components/userRecipeLists/SearchUserRecipeListItem.vue'

export default {
    props: ['username'],
    components: {
        SearchUserRecipeListItem
    },
    computed: {
        recipeLists() {
            return this.$store.getters['user/recipeLists'];
        }
    },
    created() {
        this.$store.dispatch('user/getUser', this.username);         
    }
}

</script>

<style scoped>
ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

h2 {
  margin: 0.5rem;
}
</style>