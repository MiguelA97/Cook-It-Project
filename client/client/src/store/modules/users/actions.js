export default {
    addUser(context, data) {
        const userData = {
            name: data.name,
            username: data.username,
            email: data.email,
            password: data.password
        };

        context.commit('addUser', userData);
    }
};