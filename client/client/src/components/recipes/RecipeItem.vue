<template>
    <li>
      <h3>Name: {{name}}</h3>
      <h3>Ready in: {{readyInMinutes}} minutes</h3>
      <h3>Servings: {{servings}}</h3>
      <h3>Dairy free: {{dairyFree}}</h3>
      <h3>Gluten free: {{glutenFree}}</h3>
      <h3>Vegan: {{vegan}}</h3>
      <h3>Vegetarian: {{vegetarian}}</h3>
      <h3>Image: {{image}}</h3>
      <div class="actions">
        <base-button link :to="recipeDetailsLink">View Details</base-button>
      </div>
    </li>
</template>

<script>
export default {
    props: ['id', 'name', 'readyInMinutes', 'servings', 'dairyFree', 'glutenFree', 'vegan', 'vegetarian', 'image'],
    computed: {
      recipeDetailsLink() {
        return this.$route.path + '/' + this.id;
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