<template>
    <section>
        <base-card>
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