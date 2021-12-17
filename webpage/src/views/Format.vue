<template>
  <div>
    <div class="container">
      <div class="handle-box">
        <el-select
          v-model="formatName"
          placeholder="电影版本"
          @change="searchByFormat"
        >
          <el-option
            v-for="(item, index) in allFormat"
            :key="index"
            :label="item.name"
            :value="item.name"
          ></el-option>
        </el-select>
        <el-input
          v-model="filmTitle"
          placeholder="电影名"
          class="handle-input mr10"
          clearable
        ></el-input>
        <el-button type="primary" icon="el-icon-search" @click="searchByFilm"
          >搜索</el-button
        >
        <el-button type="primary" @click="getData">所有数据</el-button>
      </div>
      <el-table :data="showData" stripe style="width: 100%">
         <el-table-column
          prop="filmTitle"
          label="电影名字"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="formatName"
          label="电影版本"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="source"
          label="电影来源"
          align="center"
        ></el-table-column>
      </el-table>

      <div class="pagination">
        <el-pagination
          v-model:currentPage="currentPage"
          :page-sizes="[10, 50, 100, 200]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="tableData.length"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        >
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "format",
  data() {
    return {
      allData: [], //所有数据
      allFormat: [], //所有版本类型
      tableData: [], //表格数据
      showData: [], //展示的数据

      formatName: "", //版本名字
      filmTitle: "", //电影名字

      currentPage: 1,
      pagesize: 10,
    };
  },
  methods: {
    // 获取表格数据
    getData() {
      fetch( this.$URL+"/format/alldata", {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData = result;
          this.allData = result;
          this.setPage();
        });
      });
    },

    // 获取所有版本形式
    getFormat() {
      fetch( this.$URL+"/format/allformat", {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.allFormat = result;
        });
      });
    },

    //搜索某一部电影
    searchByFilm() {
      fetch( this.$URL+"/searchFilm/" + this.filmTitle, {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData = result;
          this.setPage();
        });
      });
    },

    //搜索某一版本的所有电影
    searchByFormat() {
      fetch( this.$URL+"/format/searchFormat/" + this.formatName, {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData = result;
          this.setPage();
        });
      });
    },

    //分页
    handleSizeChange(pagesize) {
      this.pagesize = pagesize;
      this.setPage();
    },
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      this.setPage();
    },

    setPage() {
      this.showData = this.tableData.filter((item, index) => {
        return (
          index < this.currentPage * this.pagesize &&
          index >= this.pagesize * (this.currentPage - 1)
        );
      });
    },
  },
  mounted() {
    this.getData();
    this.getFormat();
  },

  //   setup() {
  //     const page = reactive({
  //       currentPage: 1,
  //       pageSize: 10,
  //     });

  //     //分页
  //     const handleSizeChange = (size) => {
  //       page.pageSize = size;
  //     };
  //     const handleCurrentChange = (currentPage) => {
  //       page.currentPage = currentPage;
  //     };

  //     const query = reactive({
  //       pageIndex: 1,
  //       pageSize: 10,
  //     });

  //     //const tableData = ref([]);

  //     // 查询版本
  //     const searchByFormat = () => {
  //       query.pageIndex = 1;
  //     };
  //     //查询电影
  //     const searchByFilm = () => {
  //       query.pageIndex = 1;
  //     };
  //     return {
  //       //   tableData: [], //表格展示的数据
  //       allData: [], //所有的数据
  //       searchFilm: "", //搜索的电影名字
  //       searchFormat: "", //搜索的版本
  //       page,
  //       handleSizeChange,
  //       handleCurrentChange,
  //       searchByFormat, //版本搜索
  //       searchByFilm, //电影搜索
  //     };
  //   },
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
  width: 400px;
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
