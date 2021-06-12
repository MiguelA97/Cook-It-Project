<template>
    <section>
        <base-card>
            <h2>Add a Recipe:</h2>
            <recipe-form></recipe-form>
        </base-card>
    </section>
</template>

<script>
import BaseCard from '../../components/ui/BaseCard.vue'
import RecipeForm from '../../components/recipes/RecipeForm.vue'

export default {
    components: {
        RecipeForm,
        BaseCard
    }
}
</script>