import { createApp } from 'vue';
import PrimeVue from 'primevue/config';

import App from './App.vue';

import 'primevue/resources/themes/md-light-indigo/theme.css';
import 'primeflex/primeflex.css';

const app = createApp(App);

app.use(PrimeVue);
app.mount('#app');
