<template>
    <li>
      <h3>Name: {{name}}</h3>
      <h3>Description: {{description}}</h3>
      <div class="actions">
        <base-button @click="getListRecipes">View Recipes</base-button>
      </div>
    </li>
</template>

<script>
export default {
    props: ['id', 'name', 'description', 'recipes'],
    methods: {
        getListRecipes() {
            this.$store.commit('user/setUserRecipeListIndex', this.id);
            this.$router.push({name: 'userRecipes'});  
        }
    }
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
</style>