<template>
    <section>
        <base-card>
            <h3>Recipe Filters</h3>
            <label for="diet">Diets</label>
            <multi-select class="border-select" v-model="diet" :options="dietOptions" placeholder="Choose diet"></multi-select>
            <label for="intolerances">Intolerances</label>
            <multi-select class="border-select" mode=multiple v-model="intolerances" :options="intolerancesOptions" placeholder="Select intolerances">{{intolerances}}</multi-select>
            <label for="types">Recipe type</label>
            <multi-select class="border-select" v-model="type" :options="typeOptions" placeholder="Choose type"></multi-select>
            <label for="cuisines">Cuisines</label>
            <multi-select class="border-select" v-model="cuisine" :options="cuisineOptions" placeholder="Choose cuisine"></multi-select>
        </base-card>
        <base-card>
            <div v-if="isLoading">
              <base-spinner></base-spinner>
            </div>
            <div v-else>
              <div v-if="user !== null" class="controls">
                <h3>Search for:</h3>
                <label class="radio">
                  <input type="radio" v-model="searchType" value="recipe">Recipes     
                </label>   
                <label class="radio">
                  <input type="radio" v-model="searchType" value="user">User           
                </label>   
              </div>
              <label for="search">Find your favorite recipe & Cook It!</label>
              <input class="border" type="text" id="searchBox" v-model.trim="toSearch" placeholder="Search recipes by name or ingredients">
              <base-button @click="search">Search Recipes</base-button>
            </div>
        </base-card>
    </section>
</template>

<script>
export default {
    data() {
        return {
            toSearch: '',
            isLoading: false,
            searchType: 'recipe',
            diet: null,
            dietOptions: ['lacto vegetarian', 'ovo vegetarian', 'pescetarian', 'vegan', 'vegetarian'],
            intolerances: null,
            intolerancesOptions: ['dairy', 'egg', 'gluten', 'peanut', 'sesame', 'seafood', 'shellfish', 'soy', 'sulfite', 'tree nut', 'wheat'],
            type: null,
            typeOptions: ['main course', 'side dish', 'dessert', 'appetizer', 'salad', 'bread', 'breakfast', 'soup', 'beverage', 'sauce', 'drink'],
            cuisine: null,
            cuisineOptions: ['african', 'chinese', 'japanese', 'korean', 'vietnamese', 'thai', 'indian', 'british', 'irish', 'french', 'italian', 'mexican', 'spanish', 'middle eastern', 'jewish', 'american', 'cajun', 'southern', 'greek', 'german', 'nordic', 'eastern european', 'caribbean', 'latin american']
        }
    },
    computed: {
        user() {
            return this.$store.getters['user/user'];
        }
    },
    methods: {
        async search() {
          if (this.searchType === 'recipe') {
            await this.searchRecipes();
          }
          else if (this.searchType === 'user') {
            this.searchUser();
          }
        },
        searchUser() {
          this.$router.push({name: 'userRecipeLists', params: {username: this.toSearch}});  
        },
        async searchRecipes() {
            if (this.toSearch === '') return;
            this.isLoading = true;                                                                                                     
            await this.$store.dispatch('recipes/searchRecipes', {toSearch: this.toSearch, filter: {diet: this.diet, intolerances: this.intolerances, type: this.type, cuisine: this.cuisine}}); 
            this.isLoading = false;
            this.$router.push('/recipes');
        }
    }
}
</script>

<style src="@vueform/multiselect/themes/default.css"></style>

<style scoped>
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

.border {
  border: 1px solid black;
  margin-bottom: 5px;
}

.border-select {
  margin-bottom: 5px;
}

.controls {
  display: flex;
}

.radio {
  flex: 1 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>