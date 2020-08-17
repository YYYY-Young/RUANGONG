<!--该组件为编辑器组件-->
<template>
 <el-container>
   <el-header height="50px"></el-header>
    
<el-container>   
    <el-aside width="170px">
    <Menu />
    </el-aside>
      <el-main>
        <el-row>
            <div style="height:30px;background-color:#FAFAFA">
            </div>
        </el-row>
        <el-row>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item >
					<router-link :to="{path:'/myteam'}">团队空间</router-link>
				</el-breadcrumb-item>
                <el-breadcrumb-item >创建团队</el-breadcrumb-item>
            </el-breadcrumb>
        </el-row>
<!--------------------------------------------------------------------------------------------->
     <el-row style="text-align:left">      
        <input
        v-model="teamName"
        style="margin: 10px 0px;font-size: 18px;"
        placeholder="请输入团队名称" />
        按下Ctrl+S或者点击工具栏保存按钮创建团队    
    </el-row> 
	<el-row style="text-align:left">
		<el-button style="background-color: #F5F5F5;margin-left:10px"  @click="dialogVisible = true">输入简介</el-button>
    <el-button style="background-color: #F5F5F5;margin-right:10px" @click="saveTeams()">创建</el-button>
	</el-row>
  
    
        
<!-- 团队简介弹框 -->
      <el-dialog
        :visible.sync="dialogVisible"
        width="30%" id="dia1">
        <el-divider content-position="left">团队简介</el-divider>
        <el-input
          type="textarea"
          v-model="teamDes"
          rows="6"
          maxlength="255"
          show-word-limit></el-input>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>

        </span>
      </el-dialog> 

<!--------------------------------------------------------------------------------------------->
      </el-main>
</el-container> 
</el-container>
</template>

<script>
import Menu from './menu.vue'
  export default {
	name: 'Editor',
	components: {
		Menu
	},
    data () {
      return {
        teamDes: "default des",
        teams: {} ,
        teamName: "",//默认标题
		dialogVisible: false, //控制是否显示摘要弹框
		permissionVisible: false, //控制是否显示权限弹框
	
      }
    },
    mounted () {
      
      if (this.$route.params.team) { 
        this.team = this.$route.params.team
        this.teamName=this.team.teamname
        this.teamDes=this.team.des
      }
    },
    methods: {
        saveTeams (value, render) {
          var _this=this
        // articleTitle没写则无法提交
        //响应还未加

        
        this.$confirm('是否保存并创建团队?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            if(this.teamName==""){
				alert("团队名不能为空！")
				return
        }     
        //如果没有输入标题将以默认标题提交
           var date=new Date()

            this.$axios.post('/team/initteam', {
             teamname: _this.teamName,
             des: _this.teamDes,
             leaderid: _this.$store.state.user.id
            
            }
              ).then(resp => {
              if (resp && resp.data.code === 200) {
                this.$message({
                  type: 'info',
                  message: '已保存成功'
                })
                this.$router.push('/myteam')

              }
            })
            //console.log(this.articleTitle)            
            //console.log(value)
            //console.log(render)
            //console.log(this.articleAbstract)
            console.log(date)
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消发布'
          })
        })
      },
    }
    }
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
</style> 