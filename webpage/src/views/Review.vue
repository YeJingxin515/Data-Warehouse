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
              v-model="scale.low"
              placeholder="输入最低分"
              @input="change"
              class="handle-input"
              clearable
            ></el-input>

            <el-input
              v-model="scale.high"
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
          prop="rate"
          label="电影口碑"
          align="center"
          :formatter="stateFormat"
        ></el-table-column>
        <el-table-column
          prop="overall"
          label="平均分"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="dateFrom"
          label="产品"
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
      scale:{
        low:"",
        high:""
      },
      searchScore: "", //搜索的具体分数
      searchRate: "", //搜索的电影口碑

      currentPage: 1,
      pagesize: 10,

      inputVisible: false,
      inputValue: "",
    };
  },

  methods: {
    //区间搜索
    searchByScale() {
      fetch(this.$URL + "/Score/Scale", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.scale),
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          console.log(result.data),
          this.tableData = result.data;
          this.time = result.time + "毫秒";
          this.timeDialogVisible = "true";
        });
      });
    },

    //根据分数搜索
    searchByScore() {
      fetch(this.$URL +"/Score/Overall?overall=" + this.searchScore, {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          console.log(result.data),
          this.tableData = result.data;
          this.time = result.time + "毫秒";
          this.timeDialogVisible = "true";
        });
      });
    },

    //根据分数搜索
    searchByRate() {
      fetch(
        this.$URL+"/Score/Rate?rate=" + this.searchRate,
        {
          method: "GET",
        }
      ).then((response) => {
        let result = response.json();
        result.then((result) => {
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

       //电影口碑过滤器
    stateFormat(row) {
      if (row.rate == "1") return "较好";
      else if (row.rate == "0") return "较差";
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
