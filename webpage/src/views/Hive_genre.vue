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
            :label="item"
            :value="item"
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
          prop="title"
          label="电影名字"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="genre"
          label="电影类型"
          align="center"
        ></el-table-column>
         <el-table-column
          prop="releaseDate"
          label="发行日期"
          align="center"
        ></el-table-column>
         <el-table-column
          prop="rating"
          label="等级"
          align="center"
        ></el-table-column>
         <el-table-column
          prop="genre"
          label="电影类型"
          align="center"
        ></el-table-column>
         <el-table-column label="产品">
             <template v-slot="scope">
        <el-button
        style="padding: 0px 2px;"
        v-for="item in scope.row.asins"
        :key="item"
          size="mini"
          type="warning"
          @click="jump(item.slice(1,item.length-1))">{{item.slice(1,item.length-1)}}</el-button>
        
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
export default {
  name: "genre",
  data() {
    return {
      allGenre: ["Action","Adventure","Animation","Biography","Comedy","Crime","Documentary","Drama","Family","Fantasy","Film-Noir","Game-Show","History",'Horror',"Music","Musical","Mystery","News","Reality-TV","Romance","Sci-Fi","Short","Sport","Talk-Show","Thriller","War","Western"], //所有版本类型
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
      fetch("http://localhost:8080/hive/by-genre/genre?genre="+this.search, {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
            console.log(result)
          this.tableData = result.data.results;
          console.log(this.tableData)
          this.time=result.data.time+"毫秒"
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
    jump(asin){
        window.open('https://www.amazon.com/dp/'+asin, '_blank')
    }
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
