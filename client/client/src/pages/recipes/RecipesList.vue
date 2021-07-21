<template>
    <section>
        <base-card v-if="recipes.length > 0">
            <ul>
                <recipe-item 
                    v-for="recipe in recipes" 
                    :key="recipe.id"
                    :id="recipe.id"
                    :title="recipe.title"
                    :image="recipe.image"
                ></recipe-item>
            </ul>
        </base-card>
        <h2 v-else>There are no recipes available with those parameters!</h2>
    </section>
</template>

<script>
import RecipeItem from '../../components/recipes/RecipeItem.vue'

export default {
    components: {
        RecipeItem
    },
    computed: {
        recipes() {
            return this.$store.getters['recipes/recipes'];
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