import {createRouter, createWebHashHistory} from "vue-router";
import Home from "../views/Home.vue";

const routes = [
    {
        path: "/",
        name: "Home",
        component: Home,
        children: [
     {
                path: "/charts",
                name: "basecharts",
                meta: {
                    title: '图表'
                },
                component: () => import ( /* webpackChunkName: "charts" */ "../views/BaseCharts.vue")
            }, {
                path: "/tabs",
                name: "tabs",
                meta: {
                    title: 'tab标签'
                },
                component: () => import ( /* webpackChunkName: "tabs" */ "../views/Tabs.vue")
            },
            {
                path: "/person",
                name: "person",
                meta: {
                    title: '人物查询'
                },
                component: () => import ( /* webpackChunkName: "table" */ "../views/Person.vue")
            }, 
            {
                path: "/genre",
                name: "genre",
                meta: {
                    title: '类型查询'
                },
                component: () => import ( /* webpackChunkName: "table" */ "../views/Genre.vue")
            }, 
            {
                path: "/time",
                name: "time",
                meta: {
                    title: '时间查询'
                },
                component: () => import ( /* webpackChunkName: "table" */ "../views/Time.vue")
            }, 
            {
                path: "/review",
                name: "review",
                meta: {
                    title: '评论查询'
                },
                component: () => import ( /* webpackChunkName: "table" */ "../views/Review.vue")
            },
            {
                path: "/information",
                name: "information",
                meta: {
                    title: '信息查询'
                },
                component: () => import ( /* webpackChunkName: "table" */ "../views/Information.vue")
            },
        ]
    }, 

];

const router = createRouter({
    history: createWebHashHistory(),
    // history: createWebHistory(process.env.BASE_URL),
    routes
});


export default router;