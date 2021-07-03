import { createApp } from 'vue';

import store from './store/index.js';
import router from './router.js';
import App from './App.vue';
import BaseCard from './components/ui/BaseCard.vue'
import BaseButton from './components/ui/BaseButton.vue'
import BaseSpinner from './components/ui/BaseSpinner.vue'

const app = createApp(App);

app.use(store);
app.use(router);

app.component('base-card', BaseCard);
app.component('base-button', BaseButton);
app.component('base-spinner', BaseSpinner);

app.mount('#app');