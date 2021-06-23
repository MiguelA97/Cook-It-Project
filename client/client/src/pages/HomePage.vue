<template>
    <section>
        <base-card>
            <label for="search">Find your favorite recipe & Cook It!</label>
            <input class="border" type="text" id="searchBox" v-model.trim="toSearch" placeholder="Search recipes by name or ingredients">
            <base-button @click="searchRecipes">Search Recipes</base-button>
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
            toSearch: ''
        }
    },
    methods: {
        searchRecipes() {
            if (this.toSearch === '') return;
            this.$store.dispatch('recipes/searchRecipes', this.toSearch, '', '', '', ''); //depois adicionar as variaveis (diet, intolerances, type, cuisine) dos filtros
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
</style>