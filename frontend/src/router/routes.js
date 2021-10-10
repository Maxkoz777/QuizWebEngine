const routes = [
  {
    path: '/main',
    component: () => import('pages/Index.vue'),
    children: [
      {path: 'register', component: () => import("components/SignUp/SignUpDialog")},
      {path: 'login', component: () => import("components/Login/LoginDialog")},
    ],
  },
  {
    path: '/',
    component: () => import('pages/HomePage'),
    children: [
      {path: '/', component: () => import("components/User/UserInfoSection")},
      {path: '/quiz/:id', component: () => import("components/Quiz/QuizEditorSection")}
    ]
  },
  {
    path: '/game/:gameId',
    component: () => import('pages/GamePage'),
    children: []
  },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
