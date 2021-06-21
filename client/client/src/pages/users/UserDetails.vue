<template>
    <div>
        <h3>Name: {{ user.name }}</h3>
        <h3>Username: {{ user.username }}</h3>
        <h3>Email: {{ user.email }}</h3>
    </div>
</template>

<script>
export default {
    props: ['username'],    //vai ser necessario verificar se este user corresponde ao user logged in!
    computed: {
        user() {
            return this.$store.getters['user/user'];
        }
    }
}
</script>