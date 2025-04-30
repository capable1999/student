import { createRouter, createWebHistory } from 'vue-router'
import StudentList from '../components/StudentList.vue'
import StudentEdit from '../components/StudentEdit.vue'
import StudentForm from '../components/StudentForm.vue'
import StudentView from '../components/StudentView.vue'

const routes = [
  { path: '/', name: 'StudentList', component: StudentList },
  { path: '/form', name: 'StudentForm', component: StudentForm },         
  { path: '/edit/:id', component: StudentEdit, props: true },
  { path: '/view/:id', name: 'StudentView', component: StudentView, props: true}, 
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
