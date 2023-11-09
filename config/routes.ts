export default [
  {
    path: '/user',
    name: '用户',
    layout: false,
    routes: [{ path: '/user/login', name: '登录', component: './User/Login' }],
  },
  { path: '/welcome', name: '主页', icon: 'smile', component: './Index' },

  {
    // 动态路由
    path: '/interface_info/:id',
    name: 'interface info',
    component: './InterfaceInfo',
    // 不在菜单页显示
    hideInMenu: true,
  },

  {
    path: '/admin',
    icon: 'crown',
    name: '管理页',
    access: 'canAdmin',
    routes: [
      {
        name: '接口管理',
        icon: 'table',
        path: '/admin/interface_info',
        component: './Admin/InterfaceInfo',
      },
      {
        name: '接口排行',
        icon: 'table',
        path: '/admin/chart_rank',
        component: './Admin/charts',
      },
    ],
  },
  { path: '/', redirect: '/welcome' },
  { path: '*', layout: false, component: './404' },
];
