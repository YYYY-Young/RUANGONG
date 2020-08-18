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
                <!-- <el-breadcrumb-item >
                    <router-link :to="{path:''}">模板标签1</router-link>
                </el-breadcrumb-item>
                <el-breadcrumb-item >
                    <router-link :to="{path:''}">模板标签2</router-link>
                </el-breadcrumb-item> -->
                <el-breadcrumb-item >消息中心</el-breadcrumb-item>
            </el-breadcrumb>
        </el-row>
<!-- 面包屑栏目结束 -->
<!-- 下面开始写要渲染的东西 -->
<div style="background-color:#FAFAFA;margin-top:10px">
<el-collapse v-model="activeNames" @change="handleChange">
 <!-- ---------------------------------------分享消息区 -------------------------------------------->
  <el-collapse-item name="1">
    
    <template slot="title">
      <el-badge :value="sharemessages.length" :max="99" >
      分享消息
      </el-badge>
    </template>
    <div>
 <el-table
      :data="sharemessages"
      style="width: 100%;"
      :row-style='rowStyle'
      max-height="500px">   
    <el-table-column
        prop="doc_share_user.username"
        label="分享人"
        width="250">
    </el-table-column>
    <el-table-column
        prop="docid"
        label="分享的文章id"
        width="250">
    </el-table-column>
    <el-table-column
        prop="doc_open_time"
        label="分享时间"
        width="250">
    </el-table-column>
   <el-table-column
      fixed="right"
      label="操作"
      width="150">
      <template slot-scope="scope">
        <el-button @click="viewsharearticle(scope.row.docid)" type="text" size="small">查看文章</el-button>
        <el-button @click="deletesharemessages(scope.row.id)" type="text" size="small">删除消息</el-button>
      </template>
    </el-table-column>
  </el-table>
    </div>
  </el-collapse-item>
  <!-- ------------------------------------------评论消息区 ----------------------------------------------->
  <el-collapse-item name="2" >
    <template slot="title">
      <el-badge :value="reviewmessages.length" :max="99" >
      评论消息
      </el-badge>
    </template>
   <div>
 <el-table
      :data="reviewmessages"
      style="width: 100%;"
      :row-style='rowStyle'
      max-height="500px"> 
<el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="评论内容">
            <span>{{ props.row.des }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column> 
    <el-table-column
        prop="uid"
        label="评论人id"
        width="250">
    </el-table-column>
    <el-table-column
        prop="doc.doc_title"
        label="评论的文章"
        width="250">
    </el-table-column>
    <el-table-column
        prop="time"
        label="评论时间"
        width="250">
    </el-table-column>
   <el-table-column
      fixed="right"
      label="操作"
      width="150">
      <template slot-scope="scope">
        <el-button @click="viewsharearticle(scope.row.docid)" type="text" size="small">查看文章</el-button>
        <el-button @click="deletereviewmessages(scope.row.id)" type="text" size="small">删除该评论</el-button>
      </template>
    </el-table-column>
  </el-table>
    </div>
  </el-collapse-item>
  <!-- -------------------------------------------团队消息区-------------------------------------------- -->
  <el-collapse-item  name="3">
    <template slot="title">
      <el-badge :value="teammessages.length" :max="99" >
      团队邀请
      </el-badge>
    </template>
    <div>
<el-table
      :data="teammessages"
      style="width: 100%;"
      :row-style='rowStyle'
      max-height="500px">   
    <el-table-column
        prop="tid"
        label="团队ID"
        width="250">
    </el-table-column>
    <el-table-column
        prop="team.teamname"
        label="团队名字"
        width="250">
    </el-table-column>
    <el-table-column
        prop="time"
        label="邀请时间"
        width="250">
    </el-table-column>
   <el-table-column
      fixed="right"
      label="操作"
      width="150">
      <template slot-scope="scope">
        <el-button @click="accept(scope.row.id)" type="text" size="small">接受</el-button>
        <el-button @click="refuse(scope.row.id)" type="text" size="small">拒绝</el-button>
      </template>
    </el-table-column>
  </el-table>  
    </div>
  </el-collapse-item>
  <!--------------------dddddddddddddddddddd------------------------------>
    <el-collapse-item  name="4">
    <template slot="title">
      <el-badge :value="teamremove.length" :max="99" >
      团队消息
      </el-badge>
    </template>
    <div>
<el-table
      :data="teamremove"
      style="width: 100%;"
      :row-style='rowStyle'
      max-height="500px">   
    <el-table-column
        prop="tid"
        label="团队ID"
        width="250">
    </el-table-column>
    <el-table-column
        prop="team.teamname"
        label="团队名字"
        width="250">
    </el-table-column>
    <el-table-column
        prop="time"
        label="踢出时间"
        width="250">
    </el-table-column>
   <el-table-column
      fixed="right"
      label="操作"
      width="150">
      <template slot-scope="scope">
        <el-button @click="deletethis(scope.row.id)" type="text" size="small">删除消息</el-button>
      </template>
    </el-table-column>
  </el-table>  
    </div>
  </el-collapse-item>
</el-collapse>
</div>
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
  mounted (){
    this.loadmessages()
  },
  data(){
    return{
      sharemessages:[],
      s_unread:"",
      reviewmessages:[],
      r_unread:"",
      teammessages:[],
      teamremove:[],
      t_unread:"",
      activeNames: "1"
    }
  },
  methods:{
    loadmessages(){
      var _this=this
this.$axios.get('/doc/list/share/'+this.$store.state.user.id).then(resp=>{
  if(resp&&resp.data.code==200){
    _this.sharemessages=resp.data.result
  }
})
this.$axios.get('/commment/getcommentbyothers/'+this.$store.state.user.id).then(resp=>{
  if(resp&&resp.data.code==200){
    _this.reviewmessages=resp.data.result
  }
})
this.$axios.get('/team/findteamsnotaccepted/'+this.$store.state.user.id).then(resp=>{
  if(resp&&resp.data.code==200){
    _this.teammessages=resp.data.result
  }
})   
this.$axios.get('/team/findthrown/'+this.$store.state.user.id).then(resp=>{
  if(resp&&resp.data.code==200){
    _this.teamremove=resp.data.result
    console.log(resp.data.result)
  }else{
    alert("error!")
  }
})   

    },
    viewsharearticle(id){
      //查看被分享或者被评论的文章，如果加了标记记得load
      //如果要改颜色，记得把这行的对象传进来修改值
        this.$router.push({
                path:'/articledetail',
                query:{
                    id: id
                }
            })

    },
    deletesharemessages(id){
      //删除分享消息，记得load
      this.$axios.delete('/doc/deleterecord/'+id).then(resp=>{
        if(resp&&resp.data.code==200){
         this.$message({
            type: 'info',
            message: '删除消息成功'
          }) 
          this.loadmessages()            
        }else{
          alert("失败")
        }
      })
    },
    deletereviewmessages(id){
      //删除评论消息，记得load
      this.$axios.delete('/comment/delete/'+this.$store.state.user.id+'/'+id).then(resp=>{
        if(resp&&resp.data.code==200){
         this.$message({
            type: 'info',
            message: '删除评论成功'
          }) 
          this.loadmessages()            
        }else{
          alert("失败")
        }
      })
    },
    accept(id){
//接受邀请
      this.$axios.get('/team/accept/'+id).then(resp=>{
        if(resp&&resp.data.code==200){
         this.$message({
            type: 'info',
            message: '你成功加入该团队'
          }) 
          this.loadmessages()            
        }else{
          alert("失败")
        }
      })
    },
    refuse(id){
      //拒绝邀请
           this.$axios.delete('/team/deny/'+id).then(resp=>{
        if(resp&&resp.data.code==200){
         this.$message({
            type: 'info',
            message: '你成功拒绝'
          }) 
          this.loadmessages()            
        }else{
          alert("失败")
        }
      })
    },
    deletethis(id){
           this.$axios.delete('/team/deny/'+id).then(resp=>{
        if(resp&&resp.data.code==200){
         this.$message({
            type: 'info',
            message: '删除消息成功'
          }) 
          this.loadmessages()            
        }else{
          alert("失败")
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
 
</style> 

