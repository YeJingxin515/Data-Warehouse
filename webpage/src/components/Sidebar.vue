<template>
  <div class="sidebar">
    <el-menu
      class="sidebar-el-menu"
      :default-active="onRoutes"
      :collapse="collapse"
      background-color="#324157"
      text-color="#bfcbd9"
      active-text-color="#20a0ff"
      unique-opened
      router
    >
      <template v-for="item in items">
        <template v-if="item.subs">
          <el-submenu :index="item.index" :key="item.index">
            <template #title>
              <i :class="item.icon"></i>
              <span>{{ item.title }}</span>
            </template>
            <template v-for="subItem in item.subs">
              <el-submenu
                v-if="subItem.subs"
                :index="subItem.index"
                :key="subItem.index"
              >
                <template #title>{{ subItem.title }}</template>
                <el-menu-item
                  v-for="(threeItem, i) in subItem.subs"
                  :key="i"
                  :index="threeItem.index"
                >
                  {{ threeItem.title }}</el-menu-item
                >
              </el-submenu>
              <el-menu-item v-else :index="subItem.index" :key="subItem.index"
                >{{ subItem.title }}
              </el-menu-item>
            </template>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :index="item.index" :key="item.index">
            <i :class="item.icon"></i>
            <template #title>{{ item.title }}</template>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
import { computed, watch } from "vue";
import { useStore } from "vuex";
import { useRoute } from "vue-router";
export default {
  setup() {
    const items = [
      {
        icon: "el-icon-search",
        index: "0",
        title: "MySQL关系型数据库",
        subs: [
          {
            index: "/information",
            title: "信息查询",
          },
          {
            index: "/genre",
            title: "类型查询",
          },

          {
            index: "/time",
            title: "时间查询",
          },
          {
            index: "/review",
            title: "评价查询",
          },
          {
            index: "/person",
            title: "人物查询",
          },
        ],
      },
      {
        icon: "el-icon-pie-chart",
        index: "/neo4j",
        title: "Node4j图数据库",
        subs: [
          {
            index: "/neo4j/co",
            title: "合作关系查询",
          },
          {
            index: "/neo4j/pm",
            title: "人物-电影查询",
          },
        ],
      },
      {
        icon: "el-icon-menu",
        index: "/charts",
        title: "Hbase分布式数据库 ",
        subs: [
          {
            index: "/hive/genre",
            title: "类型查询",
          },
          {
            index: "/hive/time",
            title: "时间查询",
          },
          {
            index: "/hive/person",
            title: "人物查询",
          },
          {
            index: "/hive/review",
            title: "评价查询",
          },
          {
            index: "/hive/format",
            title: "版本查询",
          },
        ],
      },
    ];

    const route = useRoute();

    const onRoutes = computed(() => {
      return route.path;
    });

    const store = useStore();
    const collapse = computed(() => store.state.collapse);

    return {
      items,
      onRoutes,
      collapse,
    };
  },
};
</script>

<style scoped>
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 70px;
  bottom: 0;
  overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
  width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
  width: 250px;
}
.sidebar > ul {
  height: 100%;
}
</style>
