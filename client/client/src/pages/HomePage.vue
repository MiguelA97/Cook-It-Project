<template>
    <section>
        <base-card>
            <div v-if="isLoading">
              <base-spinner></base-spinner>
            </div>
            <div v-else>
              <div class="controls">
                <h3>Search for:</h3>
                <label class="radio">
                  <input type="radio" v-model="searchType" value="recipe">Recipes     
                </label>   
                <label class="radio">
                  <input type="radio" v-model="searchType" value="user">User           
                </label>   
              </div>
              <label for="search">Find your favorite recipe & Cook It!</label>
              <input class="border" type="text" id="searchBox" v-model.trim="toSearch" placeholder="Search recipes by name, ingredients or user">
              <base-button @click="search">Search Recipes</base-button>
            </div>
        </base-card>
    </section>
</template>

<script>
import BaseCard from '../components/ui/BaseCard.vue'

export default {
    components: {
        BaseCard
    },
    data() {
        return {
            toSearch: '',
            isLoading: false,
            searchType: 'recipe'
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
            await this.$store.dispatch('recipes/searchRecipes', {toSearch: this.toSearch, filter: {diet: '', intolerances: '', type: '', cuisine: ''}}); //depois adicionar as variaveis (diet, intolerances, type, cuisine) dos filtros
            this.isLoading = false;
            this.$router.replace('/recipes');
        }
    },
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