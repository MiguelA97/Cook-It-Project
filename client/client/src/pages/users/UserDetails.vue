<template>
    <div v-if="user !== null">
        <h3>Name: {{ user.name }}</h3>
        <h3>Username: {{ user.username }}</h3>
        <h3>Email: {{ user.email }}</h3>
    </div>
</template>

<script>
export default {
    computed: {
        user() {
            return this.$store.getters['user/user'];
        }
    }
}
</script>