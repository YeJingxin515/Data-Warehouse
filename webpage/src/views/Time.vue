<template>
  <div>
    <!-- 查询时间的弹窗 -->
    <el-dialog v-model="timeDialogVisible" title="搜索时间" width="30%">
      <el-input v-model="time" disabled />
    </el-dialog>

    <div class="container">
      <div class="handle-box">
        <el-input
          v-model="search.year"
          placeholder="输入年份"
          @input="change"
          class="handle-input"
          clearable
        ></el-input>
        <el-select
          v-model="search.quarter"
          :disabled="search.month != '0'"
          placeholder="选取季度"
          @change="change"
          class="handle-select"
        >
          <el-option
            v-for="(item, index) in quarter"
            :key="index"
            :label="item.name"
            :value="item.num"
          ></el-option>
        </el-select>
        <el-select
          v-model="search.month"
          :disabled="search.quarter != '0'"
          placeholder="选取月份"
          @change="change"
          class="handle-select"
        >
          <el-option
            v-for="(item, index) in month"
            :key="index"
            :label="item.name"
            :value="item.num"
          ></el-option>
        </el-select>
        <el-input
          v-model="search.date"
          placeholder="输入某天"
          @input="change"
          class="handle-select mr10"
          clearable
        ></el-input>
        <el-button type="primary" @click="searchFilm">搜索</el-button>

        <el-button type="primary" @click="searchAdd">新增查询</el-button>
        <el-button type="primary" @click="getAll">所有数据</el-button>
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
          prop="filmTitle"
          label="电影名字"
          align="center"
        ></el-table-column>
        <el-table-column label="上映日期" align="center">
          <el-table-column
            prop="year"
            label="年份"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="month"
            label="月份"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="date"
            label="日期"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="quarter"
            label="季度"
            align="center"
          ></el-table-column>
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
export default {
  name: "format",
  data() {
    return {
      allData: [], //所有数据
      tableData: [], //表格数据

      month: [
        { num: 0, name: "不选择月份" },
        { num: 1, name: "January " },
        { num: 2, name: "February" },
        { num: 3, name: "March" },
        { num: 4, name: "April" },
        { num: 5, name: "May" },
        { num: 6, name: "June" },
        { num: 7, name: "July" },
        { num: 8, name: "August" },
        { num: 9, name: "September" },
        { num: 10, name: "October" },
        { num: 11, name: "November" },
        { num: 12, name: "December" },
      ],

      quarter: [
        { num: 0, name: "不选择季度" },
        { num: 1, name: "第一季度" },
        { num: 2, name: "第二季度" },
        { num: 3, name: "第三季度" },
        { num: 4, name: "第四季度" },
      ],

      search: {
        year: "",
        month: 0,
        quarter: 0,
        date: "",
      },

      timeDialogVisible: false, //显示查询时间
      time: "", //查询时间

      currentPage: 1,
      pagesize: 10,
    };
  },
  methods: {
    //获取所有数据
    getAll() {
      fetch(this.$URL + "/Time/All", {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData = result.data;
          this.allData = result.data;
          this.time = result.time + "毫秒";
          this.timeDialogVisible = "true";
        });
      });
    },

    //搜索某一年的电影
    searchFilm() {
      //搜索年份
      if (this.search.month == 0 && this.search.quarter == 0) {
        fetch(this.$URL + "/Time/Year", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(this.search),
        }).then((response) => {
          let result = response.json();
          result.then((result) => {
            this.tableData = result.data;
            this.time = result.time + "毫秒";
            this.timeDialogVisible = "true";
          });
        });
      } else if (this.search.month != 0) {
        fetch(this.$URL + "/Time/YearAndMonth", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(this.search),
        }).then((response) => {
          let result = response.json();
          result.then((result) => {
            console.log("yearmonth");
            this.tableData = result.data;
            this.time = result.time + "毫秒";
            this.timeDialogVisible = "true";
          });
        });
      } else if (this.search.quarter != 0) {
        fetch(this.$URL + "/Time/YearAndQuarter", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(this.search),
        }).then((response) => {
          let result = response.json();
          result.then((result) => {
            console.log("yearqurter");
            this.tableData = result.data;
            this.time = result.time + "毫秒";
            this.timeDialogVisible = "true";
          });
        });
      }
    },

    searchAdd() {
      fetch(this.$URL + "/Time/SomeDay", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.search),
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          console.log(result.data);
          this.tableData = result.data;
          this.time = result.time + "毫秒";
          this.timeDialogVisible = "true";
        });
      });
    },

    //分页
    handleSizeChange(pagesize) {
      this.pagesize = pagesize;
    },
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
    },

    //强制循环
    change() {
      this.$forceUpdate();
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
  width: 150px;
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
