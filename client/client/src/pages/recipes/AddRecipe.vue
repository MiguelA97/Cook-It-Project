<template>
    <section>
        <base-card>
            <h2>Add a Recipe:</h2>
            <recipe-form @save-data="saveData"></recipe-form>
        </base-card>
    </section>
</template>

<script>
import RecipeForm from '../../components/recipes/RecipeForm.vue'

export default {
    components: {
        RecipeForm
    },
    methods: {
        saveData(data) {
            this.$store.dispatch('recipes/addRecipe', {username: this.user.username, listId: data.idUrl, recipe: data, vm: this});
            this.$router.replace('/');
        }
    },
    computed: {
        user() {
            return this.$store.getters['user/user'];
        }
    },
}
</script>