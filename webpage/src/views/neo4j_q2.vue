<template>
    <div class="co">
        <div class="left-box">
            查询经常一起合作的演员
            <el-button type="primary" style="float:right;margin-right:60px;" @click="q1()">查询</el-button>
            
             <el-table
      :data="tableData1"
      height="400"
      v-loading="loading1"
       element-loading-spinner="el-icon-loading"
      style="width: 100%">
      <el-table-column
        prop="actor_name1"
        label="演员姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="actor_name2"
        label="演员姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="co_times"
        label="合作次数">
      </el-table-column>
    </el-table>
    查询时间：{{time1/1000000}}ms
        </div>
        <div class="right-box">
            查询经常一起合作的导演和演员
            <el-button type="primary" style="float:right;margin-right:60px;" @click="q2()">查询</el-button>
            <el-table
            height="400"
      :data="tableData2"
      v-loading="loading2"
      element-loading-spinner="el-icon-loading"
      style="width: 100%">
      <el-table-column
        prop="actor_name"
        label="演员姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="director_name"
        label="导演姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="co_times"
        label="合作次数">
      </el-table-column>
    </el-table>
    查询时间：{{time2/1000000}}ms
        </div>
     
    </div>
</template>
<script>
export default {
    data(){
        return{
            tableData1:'',
            tableData2:'',
            time1:'',
            time2:'',
            loading1:false,
            loading2:false,
        }
    },
    mounted(){
      
       
    },
    methods:{
      q1(){
        this.loading1=true;
       fetch( 'http://101.34.157.75:9527/neo4j/Actor_Actor?limit=25', {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData1 = result.data;
          this.time1=result.query_time;
          this.loading1=false;
          this.$forceUpdate();
        });
      });
    },
    q2(){
      this.loading2=true;
      fetch( 'http://101.34.157.75:9527/neo4j/Director_Actor?limit=25', {
        method: "GET",
      }).then((response) => {
        let result = response.json();
        result.then((result) => {
          this.tableData2 = result.data;
          this.time2=result.query_time;
          this.loading2=false;
          this.$forceUpdate();
        });
      });

    }
    }
}
</script>
<style scoped>
.co{
    display: flex;
    justify-content: center;
}
.left-box{
    width: 45%;
    color: rgb(60, 60, 60);
}
.right-box{
    width: 45%;
    color: rgb(60, 60, 60);
}
</style>