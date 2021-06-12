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
    data() {
        return {
            recipe: null
        };
    },
    computed: {
        name() {
            return this.recipe.name;
        },
        readyInMinutes() {
            return this.recipe.readyInMinutes;
        },
        instructions() {
            return this.recipe.instructions;
        },
        servings() {
            return this.recipe.servings;
        },
        dairyFree() {
            return this.recipe.dairyFree;
        },
        glutenFree() {
            return this.recipe.glutenFree;
        },
        vegan() {
            return this.recipe.vegan;
        },
        vegetarian() {
            return this.recipe.vegetarian;
        },
        image() {
            return this.recipe.image;
        },
        ingredients() {
            return this.recipe.ingredients;
        }
    },
    created() {
        this.recipe = this.$store.getters['recipes/recipes'].find(recipe => recipe.id === this.id);
    }
}
</script>