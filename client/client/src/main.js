import { createApp } from 'vue';

import store from './store/index.js';
import router from './router.js';
import App from './App.vue';
import BaseCard from './components/ui/BaseCard.vue'
import BaseButton from './components/ui/BaseButton.vue'

const app = createApp(App);

app.use(store);
app.use(router);

app.component('base-card', BaseCard);
app.component('base-button', BaseButton);

app.mount('#app');