<template>
    <section>
        <div>
            <user-recipe-list-form
            :userId="userId"
            :username="username"
            ></user-recipe-list-form>
        </div>
        <base-card v-if="recipeLists.length > 0">
            <ul>
                <user-recipe-list-item
                    v-for="recipeList in recipeLists" 
                    :key="recipeList.id"
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
    props: ['userId', 'username'],          //isto deve ter de ser mudado para passar a usar vuex!
    components: {
        UserRecipeListForm,
        UserRecipeListItem,
        BaseCard
    },
    computed: {
        recipeLists() {
            return this.$store.getters['recipes/recipeLists'];
        }
    },
    created() {
        this.$store.dispatch('recipes/getUserRecipeListsByUsername', this.username);          //buscar as listas a bd! e preencher em recipesLists do vuex!
    },
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