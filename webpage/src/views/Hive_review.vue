<template>
  <div>
    <!-- 查询时间的弹窗 -->
    <el-dialog v-model="timeDialogVisible" title="查询时间" width="30%">
      <el-input v-model="time" disabled />
    </el-dialog>

    <div class="container">
      <el-button plain type="primary" @click="timeDialogVisible = true"
        >显示上一次搜索时间</el-button
      >
      <el-collapse v-model="activeName" accordion>
        <el-collapse-item title="分数区间查询" name="1">
          <div class="demo-collapse">
            <el-input
              v-model="left"
              placeholder="输入最低分"
              @input="change"
              class="handle-input"
              clearable
            ></el-input>

            <el-input
              v-model="right"
              placeholder="输入最高分"
              @input="change"
              class="handle-input"
              clearable
            ></el-input>

            <el-button
              type="primary"
              icon="el-icon-search"
              @click="searchByScale"
              >搜索</el-button
            >
          </div>
        </el-collapse-item>
        <el-collapse-item title="具体分数查询" name="2">
          <el-input
            v-model="searchScore"
            placeholder="输入分数"
            @input="change"
            class="handle-input"
            clearable
          ></el-input>

          <el-button type="primary" icon="el-icon-search" @click="searchByScore"
            >搜索</el-button
          >
        </el-collapse-item>
        <el-collapse-item title="电影口碑搜索" name="3">
          <el-select
            v-model="searchRate"
            placeholder="选取电影口碑"
            @change="searchByRate"
          >
            <el-option
              v-for="(item, index) in rates"
              :key="index"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-collapse-item>
      </el-collapse>
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
        <el-table-column
          prop="language"
          label="版本数量"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="avgOverall"
          label="平均分"
          align="center"
        ></el-table-column>
        、
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

      rates: [
        {
          //电影评价
          value: 1,
          label: "较好",
        },
        {
          value: 0,
          label: "较差",
        },
      ],

      searchScore: "", //搜索的具体分数
      searchRate: "", //搜索的电影口碑

      left: "",
      right: "",

      currentPage: 1,
      pagesize: 10,

      inputVisible: false,
      inputValue: "",
    };
  },

  methods: {
    //区间搜索
    searchByScale() {
      fetch(
        "http://localhost:8089/hive/by-review/scores-between?left=" +
          this.left +
          "&right=" +
          this.right,
        {
          method: "GET",
        }
      ).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData = result.data.results;
          this.time = result.data.time + "毫秒";
          this.timeDialogVisible = "true";
        });
      });
    },

    //根据分数搜索
    searchByScore() {
      fetch("http://localhost:8089/hive/by-review/score?score=" + this.searchScore, {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData = result.data.results;
          this.time = result.data.time + "毫秒";
          this.timeDialogVisible = "true";
        });
      });
    },

    //根据分数搜索
    searchByRate() {
      fetch(
        "http://localhost:8089/hive/by-review/rating?rating=" + this.searchRate,
        {
          method: "GET",
        }
      ).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData = result.data.results;
          this.time = result.data.time + "毫秒";
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
