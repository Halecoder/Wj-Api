export default [
  {
    path: '/user',
    name: '用户',
    layout: false,
    routes: [{ path: '/user/login', name: '登录', component: './User/Login' }],
  },
  { path: '/', name: '主页', icon: 'smile', component: './Welcome' },
  {
    path: '/admin',
    icon: 'crown',
    name: '管理页',
    access: 'canAdmin',
    routes: [
      { path: '/admin', name: '子页', redirect: '/admin/sub-page' },
      { path: '/admin/sub-page', name: '二级子页', component: './Admin' },
    ],
  },
  { icon: 'table', name: '接口管理', path: '/list', component: './TableList' },
  { path: '/', redirect: '/welcome' },
  { path: '*', layout: false, component: './404' },
];
