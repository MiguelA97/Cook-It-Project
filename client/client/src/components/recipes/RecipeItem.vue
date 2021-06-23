<template>
    <li>
      <h3>Name: {{title}}</h3>
      <h3>Image: {{image}}</h3>
      <div class="actions">
        <base-button @click="getRecipeDetails">View Details</base-button>
      </div>
    </li>
</template>

<script>
export default {
    props: ['id', 'title', 'image'],
    methods: {
      getRecipeDetails() {
        this.$store.dispatch('recipes/getRecipeDetails', this.id); 
        this.$router.push('/recipes/' + this.id);
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