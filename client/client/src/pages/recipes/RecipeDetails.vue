<template>
    <section>
        <base-card>
            <h2>Name: {{recipe.name}}</h2>
            <h2>Ready in: {{recipe.readyInMinutes}} minutes</h2>
            <h2>Instructions: {{recipe.instructions}}</h2>
            <h2>Servings: {{recipe.servings}}</h2>
            <h2>Dairy free: {{recipe.dairyFree}}</h2>
            <h2>Gluten free: {{recipe.glutenFree}}</h2>
            <h2>Vegan: {{recipe.vegan}}</h2>
            <h2>Vegetarian: {{recipe.vegetarian}}</h2>
            <h2>Image: {{recipe.image}}</h2>
            <h2 v-if="recipe.summary">Summary: {{recipe.summary}}</h2>
        </base-card>
        <section>
            <base-card>
                <h2>Ingredients:</h2>
                <ul>
                    <li v-for="ingredient in recipe.ingredientDetailsList" :key="ingredient.apiId">{{ingredient.ingredientName}}, {{ingredient.amount}} {{ingredient.unit}}</li>
                </ul>
            </base-card>
        </section>
    </section>
</template>

<script>
export default {
    computed: {
        recipe() {
            return this.$store.getters['recipes/recipe'];
        }
    },
    created() {
        console.log(this.recipe)
    },
}
</script>