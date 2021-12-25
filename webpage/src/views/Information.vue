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
          placeholder="输入您要搜索的电影名字"
          :fetch-suggestions="querySearch"
          @select="handleSelect"
          value-key="name"
          class="handle-input mr10"
          clearable
        ></el-autocomplete>
        <el-button type="primary" icon="el-icon-search" @click="searchByName"
          >搜索</el-button
        >
        <el-button type="primary" icon="el-icon-search" @click="searchCombination"
          >组合查询</el-button
        >
        <el-button plain type="primary" @click="timeDialogVisible = true"
          >显示上一次搜索时间</el-button
        >
        <el-button plain type="primary" @click="getAll">所有数据</el-button>
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
              <el-input
                v-if="inputVisible"
                ref="saveTagInput"
                v-model="inputValue"
                class="input-new-tag"
                size="mini"
                @keyup.enter="handleInputConfirm"
                @blur="handleInputConfirm"
              >
              </el-input>
              <el-button
                v-else
                class="button-new-tag"
                style="margin-bottom: 8px"
                size="small"
                @click="showInput"
                >+ 添加电影类型</el-button
              >
            </div>
            <div class="tag-group">
              <div>
                <span style="margin-bottom: 8px">所有电影类型</span>
              </div>
            </div>
            <div class="tag-group">
              <el-tag
                v-for="item in allGenre1"
                :key="item"
                :type="item"
                effect="plain"
              >
                {{ item }}
              </el-tag>
            </div>
            <div class="tag-group">
              <el-tag
                v-for="item in allGenre2"
                :key="item"
                :type="item"
                effect="plain"
              >
                {{ item }}
              </el-tag>
            </div>
          </el-collapse-item>
          <el-collapse-item title="请选择电影口碑" name="2">
            <el-select
              v-model="search.rate"
              placeholder="选取电影口碑"
              @change="change"
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

      //组合查询搜索时间
      search: {
        genres: [],
        rate: -1,
      },

      rates: [
        {
          value: -1,
          label: "不选择",
        },
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

      allGenre1: [
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
      ],
      allGenre2: [
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
      ],

      currentPage: 1,
      pagesize: 10,

      inputVisible: false,
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
    searchCombination() {
      fetch(this.$URL + "/Information/SearchCombination", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.search),
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          console.log(result);
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

    showInput() {
      this.inputVisible = true;
      this.$nextTick((_) => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },

    handleInputConfirm() {
      const inputValue = this.inputValue;
      if (inputValue) {
        this.search.genres.push(inputValue);
      }
      this.inputVisible = false;
      this.inputValue = "";
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
          search.name.toLowerCase().indexOf(queryString.toLowerCase()) !== -1
        );
      };
    },

    loadAllPerson() {
      return this.allData;
    },
  },
  mounted() {
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
