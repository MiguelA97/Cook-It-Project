import mutations from './mutations.js';
import actions from './actions.js';
import getters from './getters.js';

export default {
    namespaced: true,
    state() {
        return {
            recipes: [
                /*{
                    id: '',
                    idApi: null,
                    idUser: null,
                    idUrl: null,
                    name: '',
                    readyInMinutes: null,
                    instructions: '',
                    image: null,
                    servings: null,
                    dairyFree: false,
                    glutenFree: false,
                    vegan: false,
                    vegetarian: false,
                    ingredients: [
                        {
                            id: null,
                            idApi: null,
                            idRecipe: null,
                            aisle: '',
                            name: '',
                            amount: null,
                            unit: '',
                            image: null
                        }
                    ]
                }*/
            ],
            recipe: {}
        }   
    },
    mutations,
    actions,
    getters
}