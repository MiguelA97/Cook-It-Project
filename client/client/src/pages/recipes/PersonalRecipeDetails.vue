<template>
    <section>
        <base-card>
            <form @submit.prevent="submitForm">
                <div class="form-control" :class="{invalid: !name.isValid}">
                    <label for="name">Name</label>
                    <input type="text" id="name" v-model.trim="name.val" @blur="clearValidations('name')"/>
                    <p v-if="!name.isValid">Name must not be empty!</p>
                </div>
                <p v-if="recipe.image"><img class="center" v-bind:src=recipe.image></p>
                <div class="form-control" :class="{invalid: !readyInMinutes.isValid}">
                    <label for="readyInMinutes">Ready in minutes</label>
                    <input type="number" id="readyInMinutes" v-model.trim="readyInMinutes.val" @blur="clearValidations('readyInMinutes')"/>
                    <p v-if="!readyInMinutes.isValid">Ready in minutes must be greater than 0.</p>
                </div>         
                <div class="form-control" :class="{invalid: !instructions.isValid}">
                    <label for="instructions">Instructions</label>
                    <textarea id="instructions" rows="5" v-model.trim="instructions.val" @blur="clearValidations('instructions')"></textarea>
                    <p v-if="!instructions.isValid">Instructions must not be empty!</p>
                </div> 
                <div class="form-control" :class="{invalid: !servings.isValid}">
                    <label for="servings">Servings</label>
                    <input type="number" id="servings" v-model.trim="servings.val" @blur="clearValidations('servings')"/>
                    <p v-if="!servings.isValid">Servings must be greater than 0.</p>
                </div>
                <div class="form-control">
                    <h2>Diet details:</h2>
                    <div>
                        <label for="dairyFree">Dairy free</label>
                        <input type="checkbox" id="dairyFree" v-model="dairyFree"/>
                    </div>
                    <div>
                        <label for="glutenFree">Gluten free</label>
                        <input type="checkbox" id="glutenFree" v-model="glutenFree"/>
                    </div>
                    <div>
                        <label for="vegan">Vegan</label>
                        <input type="checkbox" id="vegan" v-model="vegan"/>
                    </div>
                    <div>
                        <label for="vegetarian">Vegetarian</label>
                        <input type="checkbox" id="vegetarian" v-model="vegetarian"/>
                    </div>
                </div>
                <p v-if="!formIsValid">Please fix the above errors and submit again.</p>
                <base-button>Update Recipe</base-button>
            </form>
        </base-card>
        <section>
            <base-card v-if="!showUpdateIngredients">
                <h2>Ingredients:</h2>
                <ul>
                    <li v-for="ingredient in ingredients" :key="ingredient.apiId">
                        <h3>{{ingredient.ingredientName}}, {{ingredient.amount}} {{ingredient.unit}}</h3>
                    </li>
                </ul>
                <base-button @click="showUpdateIngredients = true">Update Ingredients</base-button>
            </base-card>
            <base-card v-else>
                <h2>Ingredients:</h2>
                <ul>
                    <div class="form-control">
                        <li class="border" v-for="(ingredient, index) in ingredients" :key="index">
                            <input type="text" v-model.trim="ingredient.ingredientName" placeholder="Ingredient Name">
                            <input type="text" v-model.trim="ingredient.aisle" placeholder="Ingredient Aisle">
                            <input type="number" step="0.01" v-model.trim="ingredient.amount" placeholder="Ingredient Amount">
                            <input type="text" v-model.trim="ingredient.unit" placeholder="Ingredient Unit">
                            <button @click="updateIngredient(index, ingredient.id)">Update Ingredient</button>
                            <button @click="deleteIngredient(index, ingredient.id)">Remove Ingredient</button>                            
                        </li>
                    </div>
                </ul>
                <base-button @click="addIngredient">Add Ingredient</base-button>
                <base-button @click="showUpdateIngredients = false">Close</base-button>
            </base-card>
        </section>
    </section>
</template>

<script>
export default {
    data() {
        return {
            name: {
                val: '',
                isValid: true
            },
            instructions: {
                val: '',
                isValid: true
            },
            readyInMinutes: {
                val: null,
                isValid: true
            },
            servings: {
                val: '',
                isValid: true
            },
            ingredients: [],
            dairyFree: false,
            glutenFree: false,
            vegan: false,
            vegetarian: false,
            formIsValid: true,
            showUpdateIngredients: false
        }
    },
    computed: {
        recipe() {
            return this.$store.getters['recipes/recipe'];
        },
        user() {
            return this.$store.getters['user/user'];
        }
    },
    methods: {
        updateIngredient(index, ingredientDetailsId) {
            const formData = {
                ingredientName: this.ingredients[index].ingredientName,
                aisle: this.ingredients[index].aisle,
                amount: this.ingredients[index].amount,
                unit: this.ingredients[index].unit   
            }

            if (ingredientDetailsId) {  //fazer update
                this.$store.dispatch('recipes/updateIngredientDetails', {username: this.user.username, idUrl: this.$route.params.idUrl, recipeId: this.recipe.id, ingredientDetailsId: ingredientDetailsId, formData: formData, ingredients: this.ingredients, vm: this});
            }
            else {                      //adicionar ingrediente
                if (formData.ingredientName === '' || formData.amount === null || formData.unit === '') {
                    this.$notify("Name, amount and unit must not be empty!");
                    return;
                }
                this.ingredients.splice(index, 1);
                this.$store.dispatch('recipes/addIngredientDetails', {username: this.user.username, idUrl: this.$route.params.idUrl, recipeId: this.recipe.id, formData: formData, vm: this});
            }
        },
        addIngredient() {
            this.ingredients.push({
                        ingredientName: '',
                        aisle: '',
                        amount: null,
                        unit: ''
            });
        },
        deleteIngredient(index, ingredientDetailsId) {
            this.ingredients.splice(index, 1);
            if (ingredientDetailsId) {
                this.$store.dispatch('recipes/deleteIngredientDetails', {username: this.user.username, idUrl: this.$route.params.idUrl, recipeId: this.recipe.id, ingredientDetailsId: ingredientDetailsId, ingredients: this.ingredients, vm: this});
            }
        },
        clearValidations(input) {
            this[input].isValid = true;
        },
        validateForm() {
            this.formIsValid = true;
            if (this.name.val === '') {
                this.name.isValid = false;
                this.formIsValid = false;
            }
            if (this.instructions.val === '') {
                this.instructions.isValid = false;
                this.formIsValid = false;
            }
            if (!this.readyInMinutes.val || this.readyInMinutes.val < 0) {
                this.readyInMinutes.isValid = false;
                this.formIsValid = false;
            }
            if (!this.servings.val || this.servings.val < 0) {
                this.servings.isValid = false;
                this.formIsValid = false;
            }
        },        
        submitForm() {
            this.validateForm();

            if (!this.formIsValid) return;

            const formData = {
                name: this.name.val,
                instructions: this.instructions.val,
                readyInMinutes: this.readyInMinutes.val,
                servings: this.servings.val,
                idUrl: this.$route.params.idUrl,
                idUser: this.user.id,
                dairyFree: this.dairyFree,
                glutenFree: this.glutenFree,
                vegan: this.vegan,
                vegetarian: this.vegetarian,
                image: this.recipe.image
            }
            this.$store.dispatch('recipes/updateRecipe', {username: this.user.username, listId: this.$route.params.idUrl, recipeId: this.recipe.id, recipe: formData, vm: this});
        }
    },
    created() {
        this.name.val = this.recipe.name;
        this.instructions.val = this.recipe.instructions;
        this.readyInMinutes.val = this.recipe.readyInMinutes;
        this.servings.val = this.recipe.servings;
        this.dairyFree = this.recipe.dairyFree;
        this.glutenFree = this.recipe.glutenFree;
        this.vegan = this.recipe.vegan;
        this.vegetarian = this.recipe.vegetarian;
        this.ingredients = this.recipe.ingredientDetailsList;

    }
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

.form-control {
  margin: 0.5rem 0;
}

label {
  font-weight: bold;
  display: block;
  margin-bottom: 0.5rem;
}

input[type='checkbox'] + label {
  font-weight: normal;
  display: inline;
  margin: 0 0 0 0.5rem;
}

input,
textarea {
  display: block;
  width: 100%;
  border: 1px solid #ccc;
  font: inherit;
}

input:focus,
textarea:focus {
  background-color: #f0e6fd;
  outline: none;
  border-color: #1e7433;
}

input[type='checkbox'] {
  display: inline;
  width: auto;
  border: none;
}

input[type='checkbox']:focus {
  outline: #1e7433 solid 1px;
}

h3 {
  margin: 0.5rem 0;
  font-size: 1rem;
}

.invalid label {
  color: red;
}

.invalid input,
.invalid textarea {
  border: 1px solid red;
}

.border {
  border: 1px solid black;
  margin-bottom: 5px;
}
</style>