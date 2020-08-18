<template>

 <el-container>
<!-- 顶栏容器开始 -->
   <el-header height="50px">
     <Head />
   </el-header>
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
                <el-breadcrumb-item ><router-link :to="{path:''}">
                <el-dropdown>
               <!-- 设置 -->
  <span class="el-dropdown-link">
    {{teamname}}<i class="el-icon-arrow-down el-icon--right"></i>
  </span>
  <el-dropdown-menu slot="dropdown">
    <el-dropdown-item ><el-button type="text" @click="opendialog()">协作</el-button></el-dropdown-item>
    <el-dropdown-item>设置</el-dropdown-item>
    <el-dropdown-item ><el-button type="text" @click="deleteteam()">删除</el-button></el-dropdown-item>
  </el-dropdown-menu>
                </el-dropdown>

                    </router-link></el-breadcrumb-item>
            </el-breadcrumb>
        </el-row>
<!-- 面包屑栏目结束 -->
<!-- 下面开始写要渲染的东西 -->
    <div style="height:20px;background-color:#FAFAFA">
    </div>
  <el-table
      :data="articles"
      style="width: 100%;"
      max-height="600">
<el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="文章ID">
            <span>{{ props.row.id }}</span>
          </el-form-item>
           
          <el-form-item label="文章摘要">
            <span>{{ props.row.doc_abstract }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column
        prop="doc_title"
        label="文章标题"
        width="220">
    </el-table-column>     
    <el-table-column
        prop="doc_found_date"
        label="创建日期"
        width="220">
    </el-table-column>
    <el-table-column
        prop="doc_founder"
        label="创建人"
        width="220">
    </el-table-column>
   <el-table-column
      fixed="right"
      label="操作"
      width="220">
      <template slot-scope="scope">
        <el-button @click="viecollection(scope.row.id)" type="text" size="small">查看文章</el-button>
        <el-button @click="deletearticle(scope.row.id)" type="text" size="small">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <div>
  <!-- <el-button
          size="mini"
          type="danger"
          @click="handleDelete(this.$store.state.user.id)">退出队伍</el-button></div> -->
  </div>
<el-dialog  top="5vh" center="false" title="成员管理" :visible.sync="dialogFormVisible" width="40%">
  
    <div>
    <div >
        <el-input v-model="input" placeholder="请输入邀请用户id"></el-input>
        <span>是否设置为管理员</span>
        <el-switch
          v-model="issys"
          
        active-color="#13ce66"
        inactive-color="#ff4949">
        </el-switch>
        <el-button style="float:right;" type="success" :disabled="!adderissys" @click="addteammate()">添加协作者</el-button>
    </div>
    </div>
  
    
    <el-divider content-position="center">已加入成员如下</el-divider>
    <el-table
    :data="tableData"
    max-height="360"
    style="width: 100%;margin-bottom:0px">
    <el-table-column
      label="Name"
      prop="user.username">
    </el-table-column>
    <el-table-column
      label="Phone"
      prop="user.phone">
    </el-table-column>
    <el-table-column
      align="right">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="danger"
          :disabled="!adderissys"
          @click="handleDelete(scope.row.id,scope.row.user.id)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-divider content-position="center">待加入成员如下</el-divider>
<el-table
    :data="notaccept"
    max-height="360"
    style="width: 100%;margin-bottom:10px">
    <el-table-column
      label="Name"
      prop="user.username">
    </el-table-column>
    <el-table-column
      label="Phone"
      prop="user.phone">
    </el-table-column>
    <el-table-column
      align="right">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="danger"
          :disabled="!adderissys"
          @click="regret(scope.row.id)">撤回</el-button>
      </template>
    </el-table-column>
  </el-table>

    <!-- </div> -->
  <!-- </el-form> -->
  <div slot="footer" class="dialog-footer">
    <!-- <el-button @click="dialogFormVisible = false">取 消</el-button> -->
    <el-button type="primary" @click="dialogFormVisible = false">关 闭</el-button>
  </div>
</el-dialog>


</el-main>
<!-- 主要渲染栏容器结束 -->
</el-container> 
</el-container>

</template> 


<script>
import Menu from './menu.vue'
import Head from './Head.vue'
export default {
 components: {
   Menu,
   Head
  },
  data () {
      return{
          articles:[],
          tableData: [],
        teamname:'',
        search: '',
        dialogFormVisible: false,
        formLabelWidth: '120px',
        input:'',
        issys:'false',
        adderissys:'',
        tid:'',
        notaccept:[]
      }
      
  },
  mounted () {
      this.teamname=this.$route.query.teamname,
      this.tid=this.$route.query.tid,
      this.adderissys=this.$route.query.issys,
      this.loadArticles(),
      this.loadtableData()
      
      
        },
        
    methods: {
      loadtableData(){
         var _this = this
         this.$axios.get('/team/findusers/'+this.tid).then(resp => {
                console.log("进来了")
              if(resp && resp.data.code === 200) {
                _this.tableData=resp.data.result
                console.log(_this.tableData)             
        }})
        this.$axios.get('/team/findusersnotaccepted/'+this.tid).then(resp=>{
            if(resp&&resp.data.code==200){
              _this.notaccept=resp.data.result
            }
        })
      },
      opendialog(){
        this.loadtableData(),
        this.dialogFormVisible = true
      },
      
        addteammate(){
          var _this=this  
         this.$axios.put('/team/edit', {
             uid: _this.input,
             tid: _this.tid,
             issys: _this.issys
            }
              ).then(resp => {
              if (resp && resp.data.code === 200) {
                this.$message({
                  type: 'info',
                  message: '发送邀请成功',
                })
                this.loadtableData()           
        }})},
        handleDelete(id,uid){
            var _this = this
       
       var msg=""
       if(this.$store.state.user.id==uid){
         msg="退出成功"
       }else{
         msg="删除成功"
       }
        this.$axios.delete('/team/deleteuser/'+this.$store.state.user.id+'/'+id).then(resp => {
          console.log(resp.data.code)
          if (resp && resp.data.code === 200) {
                this.$message({
                  type: 'info',
                  message: msg,
                  
                })
                if(msg=="退出成功"){
                  this.$router.push('/workbench')
                }else{
                  this.loadtableData()
                }
                 //this.loadtableData()
          }else{
            this.$message({
                  type: 'info',
                  message: '权限不足',
                  
                })
          }
                         
        })     
        
        },
        loadArticles(){
       var _this = this
       //console.log(this.$store.)
        this.$axios.get('/doc/teamdocs/'+this.$store.state.user.id+'/'+this.$route.query.tid).then(resp => {
          if (resp && resp.data.code === 200) {
                _this.articles=resp.data.result
          }
        })
        },
        
        viecollection(id){
            this.$router.push({
                path:'/articledetail',
                query:{
                    id: id
                }
            })

        },
        //删除函数
        deletearticle(id){
        this.$confirm('此操作将文章放入回收站, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            this.$axios
              .delete('/doc/deleteone/'+id+'/'+this.$store.state.user.id).then(resp => {
              if (resp && resp.data.code=== 200) {
                // this.loadArticles()
                this.$message({
                  type: 'info',
                  message: '已移到回收站'
                })
                this.loadArticles()  
              }
            })
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
        },
        regret(id){
          //撤回
        this.$axios.delete('/team/deny/'+id).then(resp=>{
        if(resp&&resp.data.code==200){
         this.$message({
            type: 'info',
            message: '撤回成功'
          }) 
          this.loadtableData()        
        }else{
          alert("失败")
        }
        
      })           
        },
        deleteteam(){
          var _this=this
  this.$axios.delete('/team/delete/'+this.$store.state.user.id+'/'+_this.tid).then(resp=>{
        if(resp&&resp.data.code==200){
         this.$message({
            type: 'info',
            message: '解散成功'
          }) 
          this.$router.push('/workbench')      
        }else{
          this.$message({
            type: 'info',
            message: '没有足够权限'
          }) 
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