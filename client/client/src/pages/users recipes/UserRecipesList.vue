<template>
    <section>
        <base-card>
            <ul>
                <search-user-recipe-item
                    v-for="(recipe, index) in recipeList" 
                    :key="index"
                    :recipe="recipe"
                ></search-user-recipe-item>
            </ul>
        </base-card>
    </section>
</template>

<script>
import SearchUserRecipeItem from '../../components/recipes/SearchUserRecipeItem.vue'

export default {
    components: {
        SearchUserRecipeItem
    },
    computed: {
        recipeLists() {
            return this.$store.getters['user/recipeLists'];
        },
        id() {
            return this.$store.getters['user/recipeListIndex'];
        },
        recipeList() {
            return this.recipeLists.filter(list => list.id === this.id)[0].recipes;
        }
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