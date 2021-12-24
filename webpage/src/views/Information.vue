<template>
  <div>
    <!-- 查询时间的弹窗 -->
    <el-dialog v-model="timeDialogVisible" title="查询时间" width="30%">
      <el-input v-model="time" disabled />
    </el-dialog>

    <div class="container">
      <div class="demo-collapse">
        <el-autocomplete
          v-model="searchName"
          placeholder="输入您要搜索的人物名字"
          :fetch-suggestions="querySearch"
          @select="handleSelect"
          value-key="name"
          class="handle-input mr10"
          clearable
        ></el-autocomplete>
        <el-button
          type="primary"
          icon="el-icon-search"
          @click="searchByName"
          >人物搜索</el-button
        >
        <el-button
          type="primary"
          icon="el-icon-search"
          @click="search"
          >组合查询</el-button
        >
        <el-button
          type="primary"
          icon="el-icon-search"
          @click="timeDialogVisible = true"
          >搜索时间</el-button
        >
        <el-button type="primary" icon="el-icon-search" @click="getAll"
          >所有数据</el-button
        >
        <el-collapse v-model="activeName" accordion>
          <el-collapse-item title="请选择类型" name="1">
            <div>
              <el-tag
                v-for="tag in search.genres"
                :key="tag"
                closable
                :disable-transitions="false"
                @close="handleClose(tag)"
              >
                {{ tag }}
              </el-tag>
              <el-select
                v-if="selectVisible"
                ref="saveTagInput"
                v-model="inputValue"
                class="input-new-tag"
                @change="handleInputConfirm"
                @blur="handleInputConfirm"
                ><el-option v-for="item in allGenre" :key="item" :value="item">
                </el-option
              ></el-select>
              <el-button v-else class="button-new-tag" @click="showSelect"
                >+ 新的电影类型</el-button
              >
            </div>
          </el-collapse-item>
        </el-collapse>
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
          prop="rating"
          label="电影等级"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="releaseDate"
          label="发行日期"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="genre"
          label="电影类型"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="formatNum"
          label="版本数量"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="dateFrom"
          label="数据源"
          align="center"
        ></el-table-column>
        <el-table-column label="电影口碑" prop="rate" :formatter="stateFormat">
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
      allData: [], //所有数据
      tableData: [], //表格数据
      showData: [], //展示的数据
      searchName: "", //搜索的人物姓名

      timeDialogVisible: false, //显示查询时间
      time: "", //查询时间

      search: {
        genres: [],
        rate: -1,
      },

      rates: [
        {
          //电影评价
          value: "1",
          label: "较好",
        },
        {
          value: "0",
          label: "较差",
        },
      ],

      selectVisible: false,

      currentPage: 1,
      pagesize: 10,
      totalPage: "",
      inputValue: "",
    };
  },

  methods: {
    //获取所有电影
    getAll() {
      fetch(this.$URL + "/Information/All", {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.allData = result;
          this.tableData = result;
        });
      });
    },

    //任意组合查询
    search() {
      fetch(this.$URL + "/Information/SearchCombination" + this.search, {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData = result.data;
          this.time = result.time + "毫秒";
          this.timeDialogVisible = "true";
        });
      });
    },

    //根据电影名字搜索
    searchByName() {
      fetch(this.$URL + "/Information/SearchFormat?name=" + this.searchName, {
        method: "GET",
      }).then((response) => {
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

    //标签搜索
    handleClose(tag) {
      this.search.genres.splice(this.search.genres.indexOf(tag), 1);
    },

    showSelect() {
      this.selectVisible = true;
      this.$nextTick((_) => {
        this.$refs.saveTag.$refs.input.focus();
      });
    },

    handleInputConfirm() {
      const inputValue = this.inputValue;
      if (inputValue) {
        this.search.genres.push(inputValue);
      }
      this.inputValue = "";
      for (let tag of this.search.genres) {
        console.log(tag);
      }
    },

    //好坏过滤器
    stateFormat(row) {
      if (row.rate == "1") return "较好";
      else if (row.rate == "0") return "较差";
    },
  },
  mounted() {
    this.getAll();
  },

  setup() {
    return {
      allGenre: ref([
        "Film-Noir",
        "Action",
        "War",
        "History",
        "Western",
        "Documentary",
        "Sport",
        "Thriller",
        "News",
        "Biography",
        "Comedy",
        "Mystery",
        "Musical",
        "Short",
        "Talk-Show",
        "Adventure",
        "Horror",
        "Romance",
        "Sci-Fi",
        "Drama",
        "Music",
        "Game-Show",
        "Crime",
        "Fantasy",
        "Family",
        "Animation",
        "Reality-TV",
      ]),
    };
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

.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 200px;
  margin-left: 10px;
  margin-right: 10px;
  vertical-align: bottom;
}

.tag-group {
  margin-bottom: 8px;
}
.tag-group-title {
  margin-right: 8px;
}
</style>
