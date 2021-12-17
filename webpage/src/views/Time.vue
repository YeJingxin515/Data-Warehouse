<template>
  <div>
    <div class="container">
      <div class="handle-box">
        <el-input
          v-model="searchYear"
          placeholder="输入年份"
          class="handle-input"
          clearable
        ></el-input>
        <el-select
          v-model="searchQuarter"
          placeholder="选取季度"
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
          v-model="searchMonth"
          placeholder="选取月份"
          class="handle-select mr10"
        >
          <el-option
            v-for="(item, index) in month"
            :key="index"
            :label="item.name"
            :value="item.num"
          ></el-option>
        </el-select>
        <el-button type="primary" @click="searchByYear">年份搜索</el-button>
        <el-button type="primary" @click="searchByYearAndQuarter"
          >季度搜索</el-button
        >
        <el-button type="primary" @click="searchByYearAndMonth"
          >月份搜索</el-button
        >
        <el-button type="primary" @click="getData">所有数据</el-button>
      </div>
      <el-table :data="showData" stripe style="width: 100%">
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
      showData: [], //展示的数据

      month: [
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
        { num: 1, name: "第一季度" },
        { num: 2, name: "第二季度" },
        { num: 3, name: "第三季度" },
        { num: 4, name: "第四季度" },
      ],

      searchYear: "", //年份
      searchMonth: "", //月份
      searchQuarter: "", //季度

      currentPage: 1,
      pagesize: 10,
    };
  },
  methods: {
    // 获取表格数据
    getData() {
      fetch(this.$URL+"/time/all", {
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

    //搜索某一年的电影
    searchByYear() {
      fetch(this.$URL+"/time/year/" + this.searchYear, {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData = result;
          console.log(result);
          console.log(this.searchYear);
          this.setPage();
        });
      });
    },

    //搜索某年某季度的电影
    searchByYearAndQuarter() {
      fetch(
        this.$URL+"/time/yearandquarter/" +
          this.searchYear +
          "/" +
          this.searchQuarter,
        {
          method: "GET",
        }
      ).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData = result;
          this.setPage();
          console.log(this.searchQuarter);
        });
      });
    },

    //搜索某年某月的电影
    searchByYearAndMonth() {
      fetch(
        this.$URL+"/time/yearandmonth/" +
          this.searchYear +
          "/" +
          this.searchMonth,
        {
          method: "GET",
        }
      ).then((response) => {
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
