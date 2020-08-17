<template>

 <el-container>
<!-- 顶栏容器开始 -->
   <el-header height="50px"></el-header>
<!-- 顶栏容器结束 --> 
<el-container>  
<!-- 侧边栏容器开始 -->   
    <el-aside width="170px">
    <Menu />
    </el-aside>
<!-- 侧边栏容器结束 -->
<!-- 主要渲染栏容器开始 -->
      <el-main>
        <el-row>
            <div style="height:30px;background-color:#FAFAFA">
            </div>
        </el-row>
<!-- 面包屑栏目开始 -->
        <el-row>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <!--现在在第三个标签所示页面，那么就可以通过点击前面的标签返回其页面-->
                <el-breadcrumb-item >
                    <router-link :to="{path:'/myteam'}">团队空间</router-link>
                </el-breadcrumb-item>
                <el-breadcrumb-item >我的团队</el-breadcrumb-item>
            </el-breadcrumb>
        </el-row>
<!-- 面包屑栏目结束 -->
<!-- 下面开始写要渲染的东西 -->
    <div style="height:20px;background-color:#FAFAFA">
    </div>
  <el-table
      :data="userteams"
      style="width: 100%;"
      max-height="600">
<el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
         
          <el-form-item label="团队简介">
            <span>{{ props.row.team.des}}</span>
          </el-form-item>
           <el-form-item label="团队创建人ID">
            <span>{{ props.row.team.leaderid}}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column
        prop="team.teamname"
        label="团队名称"
        width="220">
    </el-table-column> 
           <el-table-column
        prop="team.id"
        label="团队ID"
        width="220">
    </el-table-column>  
       <el-table-column
        prop="team.time"
        label="创建时间"
        width="220">
    </el-table-column>  
   <el-table-column
      fixed="right"
      label="操作"
      width="220">
      <template slot-scope="scope">
        
        <el-button @click="viewteam(scope.row)" type="text" size="small">查看团队空间</el-button>
      </template>
    </el-table-column>
  </el-table>
      </el-main>
<!-- 主要渲染栏容器结束 -->
</el-container> 
</el-container>

</template> 


<script>
import Menu from './menu.vue'
export default {
 components: {
   Menu
  },
  data () {
      return{
          userteams:[
                   
          ]
      }
  },
  mounted () {
      this.loadTeams()  
        },
    methods: {
        loadTeams(){
        //使用的阅读列表进行的测试
       var _this = this
        this.$axios.get('/team/findteams/'+this.$store.state.user.id).then(resp => {
          if (resp && resp.data.code === 200) {
                _this.userteams=resp.data.result
               console.log(_this.userteams)
          }
        })
        },
        viewteam(userteam){
            this.$router.push({
                path:'/teamdetail',
                query:{
                    tid:userteam.team.id,
                    teamname: userteam.team.teamname,
                    issys: userteam.issys
                    
                }
            })

        }
    }
};
</script>

<style >
  * {
        margin:0px; 
    padding:0px; 
  }
  .el-aside {
    background-color: #F5F5F5;
    color: #333;
    text-align: center;
    min-height:800px;
    margin:0px; 
    padding:0px;
    
  }
   .el-header {
    background-color: #ffffff;
    margin:0px; 
    padding:0px;
  }
  
  .el-main {
    background-color:#FAFAFA;
    color: #333;
    text-align: center;
    min-height:800px;
    margin:0px; 
    padding:0px;
  }
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 100%;
  }
</style> 