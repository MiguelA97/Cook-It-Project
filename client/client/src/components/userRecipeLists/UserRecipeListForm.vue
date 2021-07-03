<template>
    <base-button class="border" v-if="hideForm" @click="hideForm = false">Create List</base-button>
    <base-card v-else>
        <h2>Add a Recipe List:</h2>
        <form @submit.prevent="submitForm">
            <div class="form-control" :class="{invalid: !name.isValid}">
                <label for="name">Name *</label>
                <input type="text" id="name" v-model.trim="name.val" @blur="clearValidations('name')"/>
                <p v-if="!name.isValid">Name must not be empty!</p>
            </div>
            <div class="form-control">
                <label for="description">Description</label>
                <textarea id="description" rows="5" v-model.trim="description" placeholder="A small description about the list..."></textarea>
            </div> 
            <div>
                <label for="visibility">Visibility</label>
                <select id="listToAdd" v-model.trim="visibility">
                    <option value="private">private</option>
                    <option value="public">public</option>
                </select>
            </div>
            <p v-if="!formIsValid">Please fix the above errors and submit again.</p>
            <base-button class="border">Create List</base-button>
            <base-button class="border" @click="cancel">Cancel</base-button>
        </form>
    </base-card>        
</template>

<script>
export default {
    props: ['userId', 'username'],
    data() {
        return {
            name: {
                val: '',
                isValid: true
            },
            description: '',
            visibility: 'private',
            formIsValid: true,
            hideForm: true
        }
    },
    methods: {
        cancel() {
            this.name.val = '';
            this.description = '';
            this.visibility = 'private';
            this.hideForm = true;
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
        },
        submitForm() {
            this.validateForm();
            if (!this.formIsValid) return;

            const formData = {
                idUser: this.userId,
                listName: this.name.val,
                description: this.description,
                visibility : this.visibility
            };

            this.name.val = '';
            this.description = '';
            this.visibility = 'private';
            this.$store.dispatch('recipes/addUserRecipeList', {formData, username: this.username}); 
            this.hideForm = true;
        }
    }
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