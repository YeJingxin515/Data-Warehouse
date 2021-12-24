<template>
  <div>
    <!-- 查询时间的弹窗 -->
    <el-dialog v-model="timeDialogVisible" title="搜索时间" width="30%">
      <el-input v-model="time" disabled />
    </el-dialog>

    <div class="container">
      <div class="handle-box">
        <el-select
          v-model="search"
          placeholder="电影类型"
          @change="searchByGenre"
        >
          <el-option
            v-for="(item, index) in allGenre"
            :key="index"
            :label="item.genreName"
            :value="item.genreName"
          ></el-option>
        </el-select>
        <el-button plain type="primary" @click="timeDialogVisible=true">显示上一次搜索时间 </el-button>
      </div>
      <el-table
        :data="
          tableData.slice((currentPage - 1) * pagesize, currentPage * pagesize)
        "
        style="width: 100%"
        :cell-style="{ textAlign: 'center' }"
        :header-cell-style="{ textAlign: 'center' }"
        stripe
      >
        <el-table-column
          prop="filmName"
          label="电影名字"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="genreName"
          label="电影类型"
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
  name: "genre",
  data() {
    return {
      allGenre: [], //所有版本类型
      tableData: [], //表格数据
      search: "", //类型名字
      timeDialogVisible: false, //显示查询时间
      time:"",//查询时间

      currentPage: 1,
      pagesize: 10,
    };
  },
  methods: {
    // 获取表格数据,搜索电影类型
    searchByGenre() {
      fetch(this.$URL + "/Genre?name=" + this.search, {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData = result.data;
          this.time=result.time+"毫秒"
          this.timeDialogVisible="true"
        });
      });
    },

    // 获取所有电影类型数据
    getAllGenre() {
      fetch(this.$URL + "/Genre/All", {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.allGenre = result;
        });
      });
    },

    //分页
    handleSizeChange(size) {
      this.pagesize = size;

    },
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
    },
  },
  mounted() {
    this.getAllGenre();
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
