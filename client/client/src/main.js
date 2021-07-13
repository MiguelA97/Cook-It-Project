import { createApp } from 'vue';

import store from './store/index.js';
import router from './router.js';
import App from './App.vue';
import BaseCard from './components/ui/BaseCard.vue'
import BaseButton from './components/ui/BaseButton.vue'
import BaseSpinner from './components/ui/BaseSpinner.vue'
import Multiselect from '@vueform/multiselect'
import Notifications from '@kyvg/vue3-notification'

const app = createApp(App);

app.use(store);
app.use(router);
app.use(Notifications);

app.component('base-card', BaseCard);
app.component('base-button', BaseButton);
app.component('base-spinner', BaseSpinner);
app.component('multi-select', Multiselect);

app.mount('#app');