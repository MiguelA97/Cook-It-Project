<template>
    <section>
        <div v-if="user !== null">
            <user-recipe-list-form
            :userId="user.id"
            :username="user.username"
            ></user-recipe-list-form>
        </div>
        <base-card v-if="recipeLists.length > 0">
            <ul>
                <user-recipe-list-item
                    v-for="recipeList in recipeLists" 
                    :key="recipeList.id"
                    :username="user.username"
                    :id="recipeList.id"
                    :userId="recipeList.userId"
                    :name="recipeList.name"
                    :description="recipeList.description"
                    :visibility="recipeList.visibility"
                    :recipes="recipeList.recipes"
                ></user-recipe-list-item>
            </ul>
        </base-card>
    </section>
</template>

<script>
import BaseCard from '../../components/ui/BaseCard.vue';
import UserRecipeListForm from '../../components/userRecipeLists/UserRecipeListForm.vue'
import UserRecipeListItem from '../../components/userRecipeLists/UserRecipeListItem.vue'

export default {
    components: {
        UserRecipeListForm,
        UserRecipeListItem,
        BaseCard
    },
    computed: {
        recipeLists() {
            return this.$store.getters['recipes/recipeLists'];
        },
        user() {
            return this.$store.getters['user/user'];
        }
    },
    created() {
        this.$store.dispatch('recipes/getUserRecipeListsByUsername', this.user.username);          //buscar as listas a bd! e preencher em recipesLists do vuex!
    },
    watch: {
        recipeLists() {
            this.$store.dispatch('recipes/getUserRecipeListsByUsername', this.user.username);
        }
    }
}

</script>

<style scoped>
ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

h2 {
  margin: 0.5rem;
}
</style>