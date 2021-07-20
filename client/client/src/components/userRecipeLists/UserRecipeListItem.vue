<template>
    <li>
      <h3>Name: {{name}}</h3>
      <h3>Description: {{description}}</h3>
      <h4>visibility: {{visibility}}</h4>
      <div class="actions">
        <base-button @click="getListRecipes">View Recipes</base-button>
        <base-button @click="deleteList">Delete</base-button>
      </div>
    </li>
</template>

<script>
export default {
    props: ['index', 'username', 'id', 'userId', 'name', 'description', 'visibility', 'recipes'],
    methods: {
        getListRecipes() {
          this.$router.push({name: 'recipes', params: {username: this.username, idUrl: this.id, name: this.name, description: this.description, visibility: this.visibility}});  
        },
        deleteList() {
          if (confirm("Do you want to delete this list?")) {
            this.$store.dispatch('recipes/deleteUserRecipeList', {username: this.username, listId: this.id, index: this.index, vm: this});
          }
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