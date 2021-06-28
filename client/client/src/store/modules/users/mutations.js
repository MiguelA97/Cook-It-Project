export default {
    login(state, user) {
        state.loggedIn = true;
        state.user = user;
    },
    logout(state) {
        state.loggedIn = false;
        state.user = null;
    }
};