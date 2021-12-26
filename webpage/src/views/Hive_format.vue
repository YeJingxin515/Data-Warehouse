<template>
  <div>
    <!-- 查询时间的弹窗 -->
    <el-dialog v-model="timeDialogVisible" title="查询时间" width="30%">
      <el-input v-model="time" disabled />
    </el-dialog>

    <div class="container">
      <el-input
        v-model="search"
        placeholder="输入电影名字"
        @input="change"
        class="handle-input"
        clearable
      ></el-input
      ><el-button type="primary" @click="searchByName">搜索</el-button
      ><el-button plain type="primary" @click="timeDialogVisible = true"
        >显示上一次搜索时间</el-button
      >
      <el-table
        :data="
          tableData.slice((currentPage - 1) * pagesize, currentPage * pagesize)
        "
        style="width: 100% margin-top:10px"
        :cell-style="{ textAlign: 'center' }"
        :header-cell-style="{ textAlign: 'center' }"
        stripe
      >
        <el-table-column
          prop="title"
          label="电影名字"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="rating"
          label="电影等级"
          align="center"
        ></el-table-column>
        <el-table-colum
          prop="releaseDate"
          label="发行日期"
          align="center"
        ></el-table-colum>
        <el-table-column
          prop="genre"
          label="电影类型"
          align="center"
        ></el-table-column>
        <el-table-column label="版本数量" prop="formatNum" align="center">
        </el-table-column>
        <el-table-column label="产品">
          <template v-slot="scope">
            <el-button
              style="padding: 0px 2px"
              v-for="item in scope.row.asins"
              :key="item"
              size="mini"
              type="warning"
              @click="jump(item.slice(1, item.length - 1))"
              >{{ item.slice(1, item.length - 1) }}</el-button
            >
          </template>
        </el-table-column>
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
import { ref } from "@vue/reactivity";
export default {
  data() {
    return {
      tableData: [], //表格数据

      timeDialogVisible: false, //显示查询时间
      time: "", //查询时间

      search: "", //搜索的电影名字

      currentPage: 1,
      pagesize: 10,

      inputVisible: false,
      inputValue: "",
    };
  },

  methods: {
    //电影名字搜索
    searchByName() {
      fetch("http://localhost:8089/hive/by-title/?title=" + this.search, {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          console.log(result.data.results);
          this.tableData = result.data.results;
          this.time = result.data.time + "毫秒";
          this.timeDialogVisible = "true";
        });
      });
    },
    //强制循环
    change() {
      this.$forceUpdate();
    },

    //分页
    handleSizeChange(pagesize) {
      this.pagesize = pagesize;
    },
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
    },
    jump(asin) {
      window.open("https://www.amazon.com/dp/" + asin, "_blank");
    },
  },
  mounted() {},
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

.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  margin-left: 10px;
  width: 90px;
  vertical-align: bottom;
}
.tag-group {
  margin-bottom: 8px;
}
.tag-group-title {
  margin-right: 8px;
}
</style>
