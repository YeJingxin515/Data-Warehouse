<template>
  <div>
    <!-- 查询时间的弹窗 -->
    <el-dialog v-model="timeDialogVisible" title="搜索时间" width="30%">
      <el-input v-model="time" disabled />
    </el-dialog>
    <div>
      <span>请输入分数范围</span>
    </div>

    <div>
      <el-input
        v-model="timeScale.low"
        placeholder="输入最低分"
        @input="change"
        class="handle-input"
        clearable
      ></el-input>

      <el-input
        v-model="timeScale.high"
        placeholder="输入最高分"
        @input="change"
        class="handle-input"
        clearable
      ></el-input>

      <el-button type="primary" icon="el-icon-search" @click="searchAll"
        >搜索</el-button
      >
      <el-button plain type="primary" @click="timeDialogVisible = true"
        >显示上一次搜索时间</el-button
      >
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
        prop="title"
        label="电影名字"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="overall"
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
</template>

<script>
export default {
  name: "genre",
  data() {
    return {

      tableData: [], //表格数据

      timeDialogVisible: false, //显示查询时间
      time: "", //查询时间
      timeScale: {
        low: "",
        high: "",
      },

      currentPage: 1,
      pagesize: 10,
    };
  },
  methods: {
    show() {
      console.log(this.timeScale);
    },

    //分页
    handleSizeChange(size) {
      this.pagesize = size;
    },
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
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
