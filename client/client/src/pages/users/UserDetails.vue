<template>
    <div v-if="user !== null">
        <base-card>
            <h2>User Details</h2>
            <form @submit.prevent="submitForm">
                <div class="form-control" :class="{invalid: !name.isValid}">
                    <label for="name">Name</label>
                    <input type="text" id="name" v-model.trim="name.val" @blur="clearValidations('name')"/>
                    <p v-if="!name.isValid">Name must not be empty!</p>
                </div>
                <div class="form-control" :class="{invalid: !username.isValid}">
                    <label for="username">Username</label>
                    <input type="text" id="username" v-model.trim="username.val" @blur="clearValidations('username')"/>
                    <p v-if="!username.isValid">Username must not be empty!</p>
                </div>        
                <div class="form-control" :class="{invalid: !email.isValid}">
                    <label for="email">Email</label>
                    <input type="email" id="email" v-model.trim="email.val" @blur="clearValidations('email')"/>
                    <p v-if="!email.isValid">Email must not be empty!</p>
                </div>
                <div class="form-control" :class="{invalid: !password.isValid}">
                    <label for="password">Password</label>
                    <input type="password" id="password" v-model.trim="password.val" @blur="clearValidations('password')"/>
                    <p v-if="!password.isValid">Password must not be empty!</p>
                </div>
                <p v-if="!formIsValid">Please fix the above errors and submit again.</p>
                <base-button class="border" type="submit">Update</base-button>
                <base-button class="border" type="button" @click="deleteUser">Delete Account</base-button>
            </form>
        </base-card>    
    </div>
</template>

<script>
import BaseCard from '../../components/ui/BaseCard.vue';
export default {
  components: { BaseCard },
    data() {
        return {
            name: {
                val: '',
                isValid: true
            },
            username: {
                val: '',
                isValid: true
            },
            email: {
                val: '',
                isValid: true
            },
            password: {
                val: '',
                isValid: true
            },
            formIsValid: true
        }
    },
    computed: {
        user() {
            return this.$store.getters['user/user'];
        }
    },
    methods: {
        deleteUser() {
            if (confirm("Are you sure? This will delete all your data.")) {
                this.$store.dispatch('user/deleteUser', this.user.username);   
                this.$store.dispatch('user/logout');   
                this.$router.replace('/');
            }
        },
        clearValidations(input) {
            this[input].isValid = true;
        },
        validateForm() {
            this.formIsValid = true;
            if (this.name.val === '') {
                this.name.isValid = false;
                this.formIsValid = false;
            }
            if (this.username.val === '') {
                this.username.isValid = false;
                this.formIsValid = false;
            }
            if (this.email.val === '') {
                this.email.isValid = false;
                this.formIsValid = false;
            }
            if (this.password.val === '') {
                this.password.isValid = false;
                this.formIsValid = false;
            }
        },
        submitForm() {
            this.validateForm();

            if (!this.formIsValid) return;

            const formData = {
                name: this.name.val,
                username: this.username.val,
                email: this.email.val,
                password: this.password.val
            }
            this.password.val = '';

            this.$store.dispatch('user/updateUser', {user: formData, username: this.user.username});
        }
    },
    created() {
        this.name.val = this.user.name;
        this.username.val = this.user.username;
        this.email.val = this.user.email;
    },
}
</script>

<style scoped>
.form-control {
  margin: 0.5rem 0;
}

label {
  font-weight: bold;
  display: block;
  margin-bottom: 0.5rem;
}

input[type='checkbox'] + label {
  font-weight: normal;
  display: inline;
  margin: 0 0 0 0.5rem;
}

input,
textarea {
  display: block;
  width: 100%;
  border: 1px solid #ccc;
  font: inherit;
}

input:focus,
textarea:focus {
  background-color: #f0e6fd;
  outline: none;
  border-color: #1e7433;
}

input[type='checkbox'] {
  display: inline;
  width: auto;
  border: none;
}

input[type='checkbox']:focus {
  outline: #1e7433 solid 1px;
}

h3 {
  margin: 0.5rem 0;
  font-size: 1rem;
}

.invalid label {
  color: red;
}

.invalid input,
.invalid textarea {
  border: 1px solid red;
}

.border {
  border: 1px solid;
  margin-top: 10px;
  margin-left: 5px;
}
</style>