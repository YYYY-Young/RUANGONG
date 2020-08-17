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
                    <router-link :to="{path:'/articledetail',query:{id:this.$route.query.article_id}}">{{this.$route.query.article_title}}</router-link>
                </el-breadcrumb-item>
                <el-breadcrumb-item >评论</el-breadcrumb-item>
            </el-breadcrumb>
        </el-row>
<!-- 面包屑栏目结束 -->
<!-- 下面开始写要渲染的东西 -->
<div style="height:15px;background-color:#FAFAFA">
            </div>
<el-input
  type="textarea"
  :autosize="{ minRows: 4,maxRows: 4}"
  placeholder="请输入评论内容"
  style="width:95%"
  maxlength="500"
  show-word-limit
  v-model="text">
</el-input>
<el-row style="text-align:right;margin-top:10px">
  <el-button style="background-color: #F5F5F5;margin-right:25px;height:autopx;width:100px" @click="review()">确认发表</el-button>
</el-row>
<!-- 接下来是评论页面 -->
<div style="margin-top:10px"> 
  <el-card style="width:95%;margin-left:25px;margin-right:10px;"> 
    <div slot="header" >
    <span>评论区({{comments.length}}条)</span>
  </div>
  <div v-for="item in comments" :key="item.id">
    <div style="height:auto">
      <el-row>
<!-- 之后这个名字是通过方法里的一个函数由ID获取名字 -->
        <el-col :span="12" style="text-align:left">{{item.uid}}:</el-col>
      </el-row>
      <div style="width:95%;margin-left:25px;height:auto">
          <p style="text-align:left">{{item.des}}</p>
      </div>
      <el-divider content-position="right">{{item.time}}</el-divider>
    </div>
  </div>
  </el-card>
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
  data(){
    return{
      comments: [
        // {name:"1",time:"2020-01-23",des:"djfwkjekrjkwl"},
        // {name:"2",time:"2020-01-23",des:"d23"},
        // {name:"3",time:"2020-01-23",des:"d4"},
      ],
      text:""
    }
  },
  mounted() {
this.loadcomments()
  },
  methods:{
    //加载评论
    loadcomments(){
         this.$axios.get('/comment/getcommentbydoc/'+this.$route.query.article_id).then(resp => {
          if (resp && resp.data.code === 200) {
                this.comments=resp.data.result
               
          }
        })
    },
    //评论发布
    review(){
        this.$confirm('是否发表评论?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            if(this.text==""){
				alert("评论内容不能为空！")
				return
        }     
              console.log(this.text)
            this.$axios.post('/comment/save', {
                docid: this.$route.query.article_id,
                uid: this.$store.state.user.id,
                des: this.text               
            }
              ).then(resp => {
              if (resp && resp.data.code === 200) {
                this.$message({
                  type: 'info',
                  message: '已发表评论'
                })
                this.loadcomments()
              }
            })
            //console.log(this.articleTitle)            
            //console.log(value)
            //console.log(render)
            //console.log(this.articleAbstract)
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消发布'
          })
        })
    },
    //通过用户uid返回用户名字
    getNameById(id){

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
    .clearfix:after {
    clear: both;
    text-align: left;
  }
</style> 

