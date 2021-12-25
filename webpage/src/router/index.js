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
            {
                path: "/neo4j/co",
                name: "neo4j_q2",
                meta: {
                    title: '合作关系查询'
                },
                component: () => import ( /* webpackChunkName: "table" */ "../views/neo4j_q2.vue"),
            },
            {
                path: "/neo4j/pm",
                name: "neo4j_q1",
                meta: {
                    title: '人物-电影查询'
                },
                component: () => import ( /* webpackChunkName: "table" */ "../views/neo4j_q1.vue"),
            },
            {
                path: "/hive/genre",
                name: "hive_genre",
                meta: {
                    title: '类型查询'
                },
                component: () => import ( /* webpackChunkName: "table" */ "../views/Hive_genre.vue"),
            },
            {
                path: "/hive/time",
                name: "hive_time",
                meta: {
                    title: '时间查询'
                },
                component: () => import ( /* webpackChunkName: "table" */ "../views/Hive_time.vue"),
            },
            {
                path: "/hive/person",
                name: "hive_person",
                meta: {
                    title: '人物查询'
                },
                component: () => import ( /* webpackChunkName: "table" */ "../views/Hive_person.vue"),
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