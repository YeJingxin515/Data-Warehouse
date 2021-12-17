<template>
  <div>
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
        <el-button type="primary" icon="el-icon-search" @click="searchAll">搜索</el-button>
      </div>

      <el-tabs type="border-card" @tab-click="changeState">
        <el-tab-pane label="导演的电影">
          <el-table :data="showData1" stripe style="width: 100%" border>
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
        <el-tab-pane label="主演的电影">
          <el-table :data="showData2" stripe style="width: 100%" border>
            <el-table-column
              prop="actorName"
              label="人物姓名"
              align="center"
            ></el-table-column>
            <el-table-column
              prop="filmName"
              label="主演的电影"
              align="center"
            ></el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="参演的电影">
          <el-table :data="showData3" stripe style="width: 100%" border>
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
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalPage"
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
      tableData1: [], //表格数据
      tableData2: [], //表格数据
      tableData3: [], //表格数据

      showData1: [], //展示的数据
      showData2: [], //展示的数据
      showData3: [], //展示的数据

      search:ref(''), //搜索的名字
      allPerson: [], //所有人物
      onePerson:"",//某个人的信息
      persons: ref([]),
      timeout: null,

      currentPage: 1,
      pagesize: 10,
      totalPage: "",
    };
  },

  methods: {
    //获取所有的人的名字
    getAllPerson() {
      fetch(this.$URL+"/person/all", {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.allPerson = result;
          console.log(result);
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
          search.name.toLowerCase().indexOf(queryString.toLowerCase()) !==-1
        );
      };
    },
    handleSelect(item) {
      console.log(item);
    },

    //搜索演员主演的电影
    searchDirector() {
      fetch(this.$URL+"person/director/" + this.search, {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData1 = result;
          this.totalPage = this.tableData1.length;
          console.log(result);
          this.showData1 = this.tableData1.filter((item, index) => {
            return (
              index < this.currentPage * this.pagesize &&
              index >= this.pagesize * (this.currentPage - 1)
            );
          });
        });
      });
    },

    //搜索演员主演的电影
    searchMainActor() {
      fetch(this.$URL+"/person/mainactor/" + this.search, {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData2 = result;
          this.totalPage = this.tableData2.length;
          console.log(result);
          this.showData2 = this.tableData2.filter((item, index) => {
            return (
              index < this.currentPage * this.pagesize &&
              index >= this.pagesize * (this.currentPage - 1)
            );
          });
        });
      });
    },

    //搜索演员参演的电影
    searchActor() {
      fetch(this.$URL+"/person/actor/" + this.search, {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData3 = result;
          this.totalPage = this.tableData3.length;
          console.log(result);
          this.showData3 = this.tableData3.filter((item, index) => {
            return (
              index < this.currentPage * this.pagesize &&
              index >= this.pagesize * (this.currentPage - 1)
            );
          });
        });
      });
    },

    //全部搜索
    searchAll() {
      this.searchDirector();
      this.searchMainActor();
      this.searchActor();
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
