<template>
    <div>
        <base-card>
            <h2>Possible recipes:</h2>
            <base-button class="border" type="button" @click="searchRecipes">Search Recipes</base-button>
            <h2>Add Ingredient to pantry</h2>
            <form @submit.prevent="submitForm">
                <div class="form-control">
                    <label for="name">Name</label>
                    <input type="text" id="name" placeholder="Ingredient name" v-model.trim="name"/>
                </div>
                <base-button class="border" type="button" @click="addIngredient">Add Ingredient</base-button>
            </form>
            <div v-if="isLoading">  
                <base-spinner></base-spinner>
            </div>
            <div v-else>
                <h1>{{user.username}} ingredients</h1>
                <ul v-if="ingredients !== null && ingredients.length > 0">
                    <user-ingredient-item
                        v-for="(ingredient, index) in ingredients"
                        :key="index"
                        :name="ingredient"
                        :username="user.username"
                        :index="index"
                    ></user-ingredient-item>
                </ul>
                <h3 v-if="ingredients === null || ingredients.length <= 0">You have no ingredients in your pantry!</h3>        
            </div>
        </base-card>
    </div>
</template>

<script>
import UserIngredientItem from '../../components/users/UserIngredientItem.vue'

export default {
    components: {
        UserIngredientItem
    },
    data() {
        return {
            name: '',
            isLoading: false
        }
    },
    computed: {
        user() {
            return this.$store.getters['user/user'];
        },
        ingredients() {
            return this.$store.getters['user/ingredients'];
        }
    },
    methods: {
        addIngredient() {
            if (this.name !== '') {
                this.$store.dispatch('user/addUserIngredient', {username: this.user.username, ingredient: this.name, vm: this})
                this.name = '';       
            }
            else {
                this.$notify("The ingredient name must not be empty!");
            }
        },
        async searchRecipes() {
            this.isLoading = true;  
            let toSearch = '';
            this.ingredients.forEach(ingredient => {
                toSearch += ingredient + ',';
            });                       
            await this.$store.dispatch('recipes/searchRecipes', {toSearch, filter: {diet: null, intolerances: null, type: null, cuisine: null}});
            this.isLoading = false;
            this.$router.replace('/recipes');
        }
    },
    created() {
        this.$store.dispatch('user/getUserIngredients', this.user.username);         
    }
}
</script>

<style scoped>
.form-control {
  margin: 0.5rem 0;
}

label {
  font-weight: bold;
  display: block;
  margin-bottom: 0.5rem;
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

.border {
  border: 1px solid;
  margin-top: 10px;
  margin-left: 5px;
}

ul {
  list-style: none;
  margin: 0;
  padding: 0;
}
</style>