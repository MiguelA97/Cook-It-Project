<template>
    <li>
        <h3>{{recipe.name}}</h3>
        <p v-if="recipe.image"><img class="center" v-bind:src=recipe.image></p>
        <div class="actions">
            <base-button @click="getRecipeDetails">View Details</base-button>
        </div>
    </li>
</template>

<script>
export default {
    props: ['recipe'],
    methods: {
        getRecipeDetails() {
            this.$store.commit('recipes/setRecipe', this.recipe);
            this.$router.push({name: 'userRecipe'});  
        }
    },
}
</script>

<style scoped>
li {
  margin: 1rem 0;
  border: 1px solid #424242;
  border-radius: 12px;
  padding: 1rem;
}

h3 {
  font-size: 1.5rem;
  text-align: center;
}

h3,
h4 {
  margin: 0.5rem 0;
}

div {
  margin: 0.5rem 0;
}

.actions {
  display: flex;
  justify-content: flex-end;
}

.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
}
</style>