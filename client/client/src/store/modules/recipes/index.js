import mutations from './mutations.js';
import actions from './actions.js';
import getters from './getters.js';

export default {
    namespaced: true,
    state() {
        return {
            recipes: [
                {
                    id: '1',
                    idApi: null,
                    idUser: 1,
                    idUrl: 1,
                    name: 'Massa Tik Tok',
                    readyInMinutes: 40,
                    instructions: 'Por no forno.',
                    image: null,
                    servings: 6,
                    dairyFree: false,
                    glutenFree: false,
                    vegan: false,
                    vegetarian: false,
                    ingredients: [
                        {
                            id: 1,
                            idApi: null,
                            idRecipe: 1,
                            aisle: 'Meats',
                            name: 'Bacon',
                            amount: 300,
                            unit: 'g',
                            image: null
                        },
                        {
                            id: 2,
                            idApi: null,
                            idRecipe: 1,
                            aisle: 'Cheeses',
                            name: 'Feta Cheese',
                            amount: 150,
                            unit: 'g',
                            image: null
                        },
                        {
                            id: 3,
                            idApi: null,
                            idRecipe: 1,
                            aisle: 'Vegetables',
                            name: 'Cherry Tomato',
                            amount: 300,
                            unit: 'g',
                            image: null
                        },
                        {
                            id: 4,
                            idApi: null,
                            idRecipe: 1,
                            aisle: 'Pasta',
                            name: 'Pasta',
                            amount: 1.2,
                            unit: 'kg',
                            image: null
                        }
                    ]
                },
                {
                    id: '2',
                    idApi: null,
                    idUser: 1,
                    idUrl: 1,
                    name: 'Steak with Fries',
                    readyInMinutes: 20,
                    instructions: 'Cook steak and fries',
                    image: null,
                    servings: 2,
                    dairyFree: true,
                    glutenFree: false,
                    vegan: false,
                    vegetarian: false,
                    ingredients: [
                        {
                            id: 5,
                            idApi: null,
                            idRecipe: 2,
                            aisle: 'Meats',
                            name: 'Steak',
                            amount: 300,
                            unit: 'g',
                            image: null
                        },
                        {
                            id: 6,
                            idApi: null,
                            idRecipe: 2,
                            aisle: 'Vegetagles',
                            name: 'Potato',
                            amount: 300,
                            unit: 'g',
                            image: null
                        }
                    ]
                }
            ]
        }   
    },
    mutations,
    actions,
    getters
}