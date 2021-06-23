<template>
    <section>
        <base-card>
            <h2>Name: {{recipe.title}}</h2>
            <h2>Ready in: {{recipe.readyInMinutes}} minutes</h2>
            <h2>Instructions: {{recipe.instructions}}</h2>
            <h2>Servings: {{recipe.servings}}</h2>
            <h2>Dairy free: {{recipe.dairyFree}}</h2>
            <h2>Gluten free: {{recipe.glutenFree}}</h2>
            <h2>Vegan: {{recipe.vegan}}</h2>
            <h2>Vegetarian: {{recipe.vegetarian}}</h2>
            <h2>Image: {{recipe.image}}</h2>
            <h2>Summary: {{recipe.summary}}</h2>
        </base-card>
        <section>
            <base-card>
                <h2>Ingredients:</h2>
                <ul>
                    <li v-for="ingredient in recipe.ingredients" :key="ingredient.apiId">{{ingredient.name}}</li>
                </ul>
            </base-card>
        </section>
    </section>
</template>

<script>
export default {
    props: ['id'],
    computed: {
        recipe() {
            return this.$store.getters['recipes/recipe'];
        }
    }
}
</script>