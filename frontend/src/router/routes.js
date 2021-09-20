const routes = [
  {
    path: '/main',
    component: () => import('pages/Index.vue'),
    children: [
      {path: 'register', component: () => import("components/SignUp/SignUpDialog")}
    ],
  },
  {
    path: '/',

  },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
