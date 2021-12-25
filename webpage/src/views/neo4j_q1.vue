<template>
    <div>
        <div style="fill">
            <el-autocomplete
            v-model="state"
            :fetch-suggestions="querySearchAsync"
            placeholder="输入人物姓名"
            style="width:55%"
            @select="handleSelect"
            ></el-autocomplete>
            <el-select v-model="relation" placeholder="选择关系类型" style="width:25%">
            <el-option
            label="参演"
            value="ACTED_IN">
            </el-option>
            <el-option
            label="执导"
            value="DIRECT">
            </el-option>
            </el-select>
            <el-button type="primary" style="float:right;margin-right:60px;" @click="q()">查询</el-button>
        </div>
         <el-table
      :data="resList"
      height="400"
      v-loading="loading"
       element-loading-spinner="el-icon-loading"
      style="width: 100%">
      <el-table-column
        prop="person_name"
        label="人物姓名"
        min-width="30%">
      </el-table-column>
      <el-table-column
        prop="movie_title"
        label="电影名称"
        min-width="30%">
      </el-table-column>
      <el-table-column
        prop="relation"
        label="关系"
        min-width="30%">
      </el-table-column>
    </el-table>
    查询时间：{{time/1000000}}ms
    </div>
</template>
<script>
export default {
    data(){
        return{
            relation:'',
            state:'',
            peopleList:[],
            timeout: null,
            resList:[],
            time:'',
            loading:false,
        }
    },
    mounted(){
        fetch( 'http://101.34.157.75:9527/neo4j/allPeople', {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.peopleList = result.data;
          this.$forceUpdate();
        });
      });
    },
    methods:{
        querySearchAsync(queryString, cb) {
        var peopleList = this.peopleList;
        var results = queryString ? peopleList.filter(this.createStateFilter(queryString)) : peopleList;

        clearTimeout(this.timeout);
        this.timeout = setTimeout(() => {
          cb(results);
        }, 3000 * Math.random());
      },
      createStateFilter(queryString) {
        return (state) => {
          return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
      handleSelect(item) {
        console.log(item);
      },
      q(){
          this.loading=true;
          fetch( 'http://101.34.157.75:9527/neo4j/People_Movie?personName='+this.state+'&relation='+this.relation, {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.resList = result.data;
          this.time=result.query_time;
          this.loading=false;
          this.$forceUpdate();
        });
      });
      }
    }
}
</script>
<style scoped>
.fill{
    width: 100%;
}
</style>