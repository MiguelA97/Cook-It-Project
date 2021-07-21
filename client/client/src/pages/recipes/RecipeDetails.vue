<template>
    <section>
        <base-card>
            <h2>{{recipe.name}}</h2>
            <p v-if="recipe.image"><img class="center" v-bind:src=recipe.image></p>
            <h2>Ready in: {{recipe.readyInMinutes}} minutes</h2>
            <h2>Instructions: {{recipe.instructions}}</h2>
            <h2>Servings: {{recipe.servings}}</h2>
            <h2>Dairy free: {{recipe.dairyFree}}</h2>
            <h2>Gluten free: {{recipe.glutenFree}}</h2>
            <h2>Vegan: {{recipe.vegan}}</h2>
            <h2>Vegetarian: {{recipe.vegetarian}}</h2>
            <h2 v-if="recipe.summary">Summary: {{recipe.summary}}</h2>
            <div v-if="user !== null">
                <label for="listToAdd">Add recipe to list</label>
                <select class="border-select" id="listToAdd" v-model.trim="listToAdd">
                    <option disabled selected value="">Choose list</option>
                    <option v-for="list in recipeLists" :key="list.id" :value="list.id">{{list.name}}</option>
                </select>
            </div>
            <base-button v-if="user !== null" @click="addRecipeToList">Save the Recipe</base-button>
        </base-card>
        <section>
            <base-card>
                <h2>Ingredients:</h2>
                <ul>
                    <li v-for="ingredient in recipe.ingredientDetailsList" :key="ingredient.apiId"><h3>{{ingredient.ingredientName}}, {{ingredient.amount}} {{ingredient.unit}}</h3></li>
                </ul>
            </base-card>
        </section>
    </section>
</template>

<script>
export default {
    data() {
        return {
            listToAdd: ''
        }
    },
    computed: {
        recipe() {
            return this.$store.getters['recipes/recipe'];
        },
        recipeLists() {
            return this.$store.getters['recipes/recipeLists'];
        },
        user() {
            return this.$store.getters['user/user'];
        }
    },
    methods: {
        addRecipeToList() {
            if (this.listToAdd !== '') {
                this.$store.dispatch('recipes/addRecipe', {username: this.user.username, listId: this.listToAdd, recipe: {
                    dairyFree: this.recipe.dairyFree,
                    glutenFree: this.recipe.glutenFree,
                    idApi: this.recipe.idApi,
                    idUrl: this.recipe.idUrl,
                    idUser: this.recipe.idUser,
                    image: this.recipe.image,
                    ingredientDetailsList: this.recipe.ingredientDetailsList,
                    instructions: this.recipe.instructions,
                    name: this.recipe.name,
                    readyInMinutes: this.recipe.readyInMinutes,
                    servings: this.recipe.servings,
                    vegan: this.recipe.vegan,
                    vegetarian: this.recipe.vegetarian
                }, 
                vm: this});
            }
        }
    },
    created() {
        if (this.user !== null) {
            this.$store.dispatch('recipes/getUserRecipeListsByUsername', this.user.username);          //buscar as listas a bd! e preencher em recipesLists do vuex!
        }
    },
}
</script>

<style scoped>
label {
  font-weight: bold;
  display: block;
  margin-bottom: 0.5rem;
}

.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
}

.border-select {
  margin: 5px;
  margin-bottom: 10px;
  margin-top: 0px;
}
</style>