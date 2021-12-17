<template>
  <div>
    <div class="container">
      <div class="demo-collapse">
        <el-collapse v-model="activeName" accordion>
          <el-collapse-item title="请选择类型" name="2">
            <div>
              <el-tag
                v-for="tag in dynamicTags"
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
              <el-button type="primary" icon="el-icon-search" @click="search"
                >搜索</el-button
              >
            </div>
          </el-collapse-item>
        </el-collapse>
      </div>
      <el-table :data="showData" stripe style="width: 100%" border>
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
          prop="release_data"
          label="发行日期"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="language"
          label="语言"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="genre"
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
import { ref } from "@vue/reactivity";
export default {
  data() {
    return {
      allData: [], //所有数据
      tableData: [], //表格数据
      showData: [], //展示的数据

      dynamicTags: [],
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
      fetch(this.$URL + "/information/all", {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.allData = result;
          this.tableData = result;
          this.setPage();
          console.log(result);
        });
      });
    },

    //搜索电影类型
    search() {
      fetch(
        this.$URL + "/information/genre/" + this.dynamicTags,
        {
          method: "GET",
        }
      ).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData = result;
          this.setPage();
          console.log(result);
        });
      });
    },

    //页码
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
    },

    setPage() {
      this.showData = this.tableData.filter((item, index) => {
        return (
          index < this.currentPage * this.pagesize &&
          index >= this.pagesize * (this.currentPage - 1)
        );
      });
    },

    //标签搜索
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
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
        this.dynamicTags.push(inputValue);
      }
      this.inputValue = "";
      for (let tag of this.dynamicTags) {
        console.log(tag);
      }
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
