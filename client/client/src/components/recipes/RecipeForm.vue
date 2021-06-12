<template>
    <form @submit.prevent="submitForm">
        <div class="form-control" :class="{invalid: !name.isValid}">
            <label for="name">Name</label>
            <input type="text" id="name" v-model.trim="name.val" @blur="clearValidations('name')"/>
            <p v-if="!name.isValid">Name must not be empty!</p>
        </div>
        <div>
            <ingredient-form></ingredient-form>
        </div>
        <div class="form-control" :class="{invalid: !instructions.isValid}">
            <label for="instructions">Instructions</label>
            <textarea id="instructions" rows="5" v-model.trim="instructions.val" @blur="clearValidations('instructions')"></textarea>
            <p v-if="!instructions.isValid">Instructions must not be empty!</p>
        </div>        
        <div class="form-control" :class="{invalid: !readyInMinutes.isValid}">
            <label for="readyInMinutes">Ready in minutes</label>
            <input type="number" id="readyInMinutes" v-model.trim="readyInMinutes.val" @blur="clearValidations('readyInMinutes')"/>
            <p v-if="!readyInMinutes.isValid">Ready in minutes must be greater than 0.</p>
        </div>
        <div class="form-control" :class="{invalid: !servings.isValid}">
            <label for="servings">Servings</label>
            <input type="number" id="servings" v-model.trim="servings.val" @blur="clearValidations('servings')"/>
            <p v-if="!servings.isValid">Servings must be greater than 0.</p>
        </div>
        <div>
            <label for="listToAdd">List to add</label>
            <select id="listToAdd" v-model.trim="listToAdd.val" @blur="clearValidations('listToAdd')">
                <option value="volvo">Volvo</option>
                <option value="saab">Saab</option>
            </select>
        </div>
        <div class="form-control">
            <h2>Diet details:</h2>
            <div>
                <label for="dairyFree">Dairy free</label>
                <input type="checkbox" id="dairyFree" v-model="dairyFree"/>
            </div>
            <div>
                <label for="glutenFree">Gluten free</label>
                <input type="checkbox" id="glutenFree" v-model="glutenFree"/>
            </div>
            <div>
                <label for="vegan">Vegan</label>
                <input type="checkbox" id="vegan" v-model="vegan"/>
            </div>
            <div>
                <label for="vegetarian">Vegetarian</label>
                <input type="checkbox" id="vegetarian" v-model="vegetarian"/>
            </div>
        </div>
        <p v-if="!formIsValid">Please fix the above errors and submit again.</p>
        <base-button>Save the Recipe</base-button>
    </form>
</template>

<script>
import IngredientForm from '../ingredients/IngredientForm.vue'

export default {
    emits: ['save-data'],
    components: {
        IngredientForm
    },
    data() {
        return {
            name: {
                val: '',
                isValid: true
            },
            instructions: {
                val: '',
                isValid: true
            },
            readyInMinutes: {
                val: null,
                isValid: true
            },
            servings: {
                val: '',
                isValid: true
            },
            listToAdd: {
                val: '',
                isValid: true
            },
            dairyFree: false,
            glutenFree: false,
            vegan: false,
            vegetarian: false,
            formIsValid: true
        }
    },
    methods: {
        clearValidations(input) {
            this[input].isValid = true;
        },
        validateForm() {
            this.formIsValid = true;
            if (this.name.val === '') {
                this.name.isValid = false;
                this.formIsValid = false;
            }
            if (this.instructions.val === '') {
                this.instructions.isValid = false;
                this.formIsValid = false;
            }
            if (!this.readyInMinutes.val || this.readyInMinutes.val < 0) {
                this.readyInMinutes.isValid = false;
                this.formIsValid = false;
            }
            if (!this.servings.val || this.servings.val < 0) {
                this.servings.isValid = false;
                this.formIsValid = false;
            }
            if (!this.listToAdd.val) {
                this.listToAdd.isValid = false;
                this.formIsValid = false;
            }
        },
        submitForm() {
            this.validateForm();

            if (!this.formIsValid) return;

            const formData = {
                name: this.name.val,
                instructions: this.instructions.val,
                readyInMinutes: this.readyInMinutes.val,
                servings: this.servings.val,
                idUrl: this.listToAdd.val,
                dairyFree: this.dairyFree.val,
                glutenFree: this.glutenFree.val,
                vegan: this.vegan.val,
                vegetarian: this.vegetarian.val
            }

            console.log(FormData);
            this.$emit('save-data', formData);
        }
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
</style>