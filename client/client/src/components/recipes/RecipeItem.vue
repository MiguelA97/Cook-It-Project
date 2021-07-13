<template>
    <li>
      <div v-if="isLoading">
        <base-spinner></base-spinner>
      </div>
      <div v-else>
        <h3>{{title}}</h3>
        <p v-if="image"><img class="center" v-bind:src=image></p>
        <div class="actions">
          <base-button @click="getRecipeDetails">View Details</base-button>
        </div>
      </div>
    </li>
</template>

<script>
export default {
    props: ['id', 'title', 'image'],
    data() {
      return {
        isLoading: false
      }
    },
    methods: {
      async getRecipeDetails() {
        this.isLoading = true;
        await this.$store.dispatch('recipes/getRecipeDetails', this.id);
        this.isLoading = false; 
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