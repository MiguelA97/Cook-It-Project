<template>
    <section>
        <base-card>
            <h2>Name: {{name}}</h2>
            <h2>Ready in: {{readyInMinutes}} minutes</h2>
            <h2>Instructions: {{instructions}}</h2>
            <h2>Servings: {{servings}}</h2>
            <h2>Dairy free: {{dairyFree}}</h2>
            <h2>Gluten free: {{glutenFree}}</h2>
            <h2>Vegan: {{vegan}}</h2>
            <h2>Vegetarian: {{vegetarian}}</h2>
            <h2>Image: {{image}}</h2>
        </base-card>
        <section>
            <base-card>
                <h2>Ingredients:</h2>
                <ul>
                    <li v-for="ingredient in ingredients" :key="ingredient.id">{{ingredient.name}}</li>
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