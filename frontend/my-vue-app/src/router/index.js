import { createRouter, createWebHistory } from 'vue-router';
import BirthdayReminder from '../components/BirthdayReminder.vue'; 
import AllBirthdays from '@/components/AllBirthdays.vue';

const routes = [
  { path: '/', component: BirthdayReminder },
  { path: '/all-birthdays', component: AllBirthdays},
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
