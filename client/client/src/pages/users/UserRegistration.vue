<template>
    <section>
        <base-card>
            <h2>Register as a new user:</h2>
            <user-form @save-data="saveData"></user-form>
        </base-card>
    </section>
</template>

<script>
import UserForm from '../../components/users/UserForm.vue'

export default {
    components: {
        UserForm
    },
    methods: {
        saveData(data) {
            this.$store.dispatch('user/addUser', data);
            this.$router.replace('/login');
        }
    },
}
</script>