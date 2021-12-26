<template>
  <div>
    <!-- 查询时间的弹窗 -->
    <el-dialog v-model="timeDialogVisible" title="搜索时间" width="30%">
      <div>
        <span>查询导演电影时间：</span>
        <el-input v-model="time1" disabled />
      </div>
      <div>
        <span>查询参演电影时间：</span>
        <el-input v-model="time2" disabled />
      </div>
    </el-dialog>

    <div class="container">
      <div class="handle-box">
        <el-autocomplete
          v-model="search"
          placeholder="输入您要搜索的人物名字"
          :fetch-suggestions="querySearch"
          @select="handleSelect"
          value-key="name"
          class="handle-input mr10"
          clearable
        ></el-autocomplete>
        <el-button type="primary" icon="el-icon-search" @click="searchAll"
          >搜索</el-button
        >
        <el-button
          plain
          type="primary"
          @click="timeDialogVisible = true"
          >显示上一次搜索时间</el-button
        >
      </div>

      <el-tabs type="border-card" @tab-click="changeState">
        <el-tab-pane label="导演的电影">
          <el-table
            :data="
              tableData1.slice(
                (currentPage - 1) * pagesize,
                currentPage * pagesize
              )
            "
            style="width: 100%"
            :cell-style="{ textAlign: 'center' }"
            :header-cell-style="{ textAlign: 'center' }"
            stripe
          >
            <el-table-column
              prop="directorName"
              label="人物姓名"
              align="center"
            ></el-table-column>
            <el-table-column
              prop="filmName"
              label="导演的电影"
              align="center"
            ></el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="参演的电影">
          <el-table
            :data="
              tableData2.slice(
                (currentPage - 1) * pagesize,
                currentPage * pagesize
              )
            "
            style="width: 100%"
            :cell-style="{ textAlign: 'center' }"
            :header-cell-style="{ textAlign: 'center' }"
            stripe
          >
            <el-table-column
              prop="actorName"
              label="人物姓名"
              align="center"
            ></el-table-column>
            <el-table-column
              prop="filmName"
              label="参演的电影"
              align="center"
            ></el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>

      <div class="pagination">
        <el-pagination
          v-model:currentPage="currentPage"
          :page-sizes="[10, 50, 100, 200]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="allPerson.length"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        >
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from "@vue/reactivity";
export default {
  name: "format",
  data() {
    return {
      tableData1: [], //导演的电影表格数据
      tableData2: [], //参演的电影表格数据

      allPerson: [], //所有人物
      search: ref(""), //搜索的名字
      onePerson: "", //某个人的信息
      persons: ref([]),
      timeout: null,

      timeDialogVisible: false, //显示查询时间
      time1: "", //查询导演时间
      time2: "", //查询演员时间

      currentPage: 1,
      pagesize: 10,
      totalPage: "",
    };
  },

  methods: {
    //获取所有的人的名字
    getAllPerson() {
      fetch(this.$URL + "/Person/All", {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.allPerson = result;
        });
      });
    },

    //获取所有人的信息
    loadAllPerson() {
      return this.allPerson;
    },
    //远程搜索
    querySearch(queryString, cb) {
      var persons = this.persons;
      var results = queryString
        ? persons.name.filter(this.createStateFilter(queryString))
        : persons.name;

      clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        cb(results);
      }, 3000 * Math.random());
    },
    createStateFilter(queryString) {
      return (search) => {
        return (
          search.name.toLowerCase().indexOf(queryString.toLowerCase()) !== -1
        );
      };
    },

    //搜索演员导演的电影
    searchDirector() {
      fetch(this.$URL + "/Person/Director?name=" + this.search, {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData1 = result.data;
          this.totalPage = this.tableData1.length;
          this.time1 = result.time + "毫秒";
          this.timeDialogVisible = "true";
        });
      });
    },

    //搜索演员参演的电影
    searchActor() {
      fetch(this.$URL + "/Person/Actor?name=" + this.search, {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData2 = result.data;
          this.totalPage = this.tableData2.length;
          this.time2 = result.time + "毫秒";
          this.timeDialogVisible = "true";
        });
      });
    },

    //全部搜索
    searchAll() {
      this.searchDirector();
      this.searchActor();
      this.getAllPerson();
    },

    //分页
    handleSizeChange(pagesize) {
      this.pagesize = pagesize;
    },
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
    },
  },

  mounted() {
    this.getAllPerson();
    setTimeout(() => {
      this.persons.name = this.loadAllPerson();
    }, 2000);
  },
};
</script>

<style scoped>
.handle-box {
  margin-bottom: 20px;
}

.handle-select {
  width: 150px;
}

.handle-input {
  width: 300px;
  display: inline-block;
}
.table {
  width: 100%;
  font-size: 14px;
}
.red {
  color: #ff0000;
}
.mr10 {
  margin-right: 10px;
}
.table-td-thumb {
  display: block;
  margin: auto;
  width: 40px;
  height: 40px;
}
</style>
