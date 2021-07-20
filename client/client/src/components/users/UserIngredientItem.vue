<template>
    <li>
        <h3>{{name}}</h3>
        <base-button class="center" type="button" @click="deleteIngredient">Delete Ingredient</base-button>
    </li>
</template>

<script>
export default {
    props: ['name', 'username', 'index'],
    methods: {
        deleteIngredient() {
            this.$store.dispatch('user/deleteUserIngredient', {username: this.username, ingredient: this.name, index: this.index, vm: this});      
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
  text-align: center;
}

h3,
h4 {
  margin: 0.5rem 0;
}

div {
  margin: 0.5rem 0;
}

.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
}
</style>