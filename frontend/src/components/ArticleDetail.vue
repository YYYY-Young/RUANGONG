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
                    <router-link :to="{path:'/workbench'}">工作空间</router-link>
                </el-breadcrumb-item>
                <el-breadcrumb-item >
                    <router-link :to="{path:'/workbench'}">工作台</router-link>
                </el-breadcrumb-item>
                <el-breadcrumb-item >
                    <router-link :to="{path:'/articles'}">最近阅读</router-link>
                </el-breadcrumb-item>
                <el-breadcrumb-item >{{article.doc_title}}</el-breadcrumb-item>
            </el-breadcrumb>
        </el-row>
<!-- 面包屑栏目结束 -->
<!-- 下面开始写要渲染的东西 -->
 <div class="articles-area">
  <el-row style="text-align:left;margin-top:10px">
		<el-button style="background-color: #F5F5F5;margin-left:10px;height:30px;width:100px"   @click="editorarticle()">编辑文章</el-button>
		<el-button style="background-color: #F5F5F5;margin-right:10px;height:30px;width:100px" @click="deleteArticle (article.id)">删除文章</el-button>
    <el-button style="background-color: #F5F5F5;margin-right:10px;height:30px;width:100px" @click="viewcomments()" >查看/发表评论</el-button>
    <el-button style="background-color: #F5F5F5;margin-right:10px;height:30px;width:100px" icon="el-icon-star-off" @click="likeAnddislike()" >收藏</el-button>
	</el-row>
    <el-card style="text-align: left;width: 990px;margin: 10px auto 0 auto;min-height:500px; ">
      <div>   
        <span style="font-size: 20px"><strong>{{article.doc_title}}</strong></span>
        <div style="height:20px;background-color:#FAFAFA"></div>
        <el-divider content-position="left" >{{article.doc_abstract}}</el-divider>
        <div style="height:20px;background-color:#FAFAFA"></div>
        <div class="markdown-body">
          <div v-html="article.doc_content_html"></div>
         </div>
         <!-- <el-button type="primary" @click="editorarticle()">编辑文章</el-button> 
         <el-button type="danger" @click="deleteArticle (article.id)">删除文章</el-button>     -->
      </div> -
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
    name: 'ArticleDetails',
    components: {
   Menu,
   Head
  },
    data () {
      return {
        article: {},
        //likes:""
      }
    },
    mounted () {
      this.loadArticle()
      //this.loadLikes()
    },
    methods: {
      loadArticle () {
        var _this = this
        this.$axios.get('/doc/getone/'+this.$route.query.id+'/'+this.$store.state.user.id).then(resp => {
          if (resp && resp.data.code === 200) {
            _this.article = resp.data.result
            //console.log(_this.article)
          }
          if(resp.data.result==null){
                this.$message({
                  type: 'info',
                  message: '该文章已被删除或者你没有查看权限'
                })               
          }
          console.log(resp.data.result)
        })
        // if(this.article.id==""){
        //         this.$message({
        //           type: 'info',
        //           message: '该文章已被删除或者你没有查看权限'
        //         })         
        // }
       
      },
      // loadLikes(){
      //   this.$axios.get('/doc/likes/'+this.article.id).then(resp => {
      //     if (resp && resp.data.code === 200){
      //       this.likes=resp.data.result
      //     }
      //   })

      // },
      editorarticle(){
        // if(this.article.isedit==true){
        //     <el-alert
        //       title="该文章正在被修改"
        //        type="error">
        //   </el-alert>
        //   return
        // }
        if(this.$store.state.user.id!=this.article.doc_founder&&this.article.doc_edit){
          alert("你没有编辑权限！")
        }else{
          // this.article.isedit==true  通过接口改变
        this.$router.push(
          {
            name: 'ArticleChange',
            params: {
              article: this.article
            }
          }
        )
        }

     },
      deleteArticle (id) {
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
                this.$router.push('/articles')
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
      likeAnddislike(){
        this.$axios.get('/doc/islike/'+this.$store.state.user.id+'/'+this.article.id).then(resp =>{
          if (resp&&resp.data.code=== 200){
            console.log(resp.data.result)
            if(resp.data.result=="没有收藏记录"){
                  this.$axios.post('/doc/editrecord', {
                      uid:this.$store.state.user.id,
                      docid:this.article.id,
                      doc_like:true
            }
              ).then(resp => {
              if (resp && resp.data.code === 200) {
                this.$message({
                  type: 'info',
                  message: '收藏成功'
                })
                  this.loadArticle()
                  this.loadLikes()

              }
            })
            }else{
              alert("已被收藏!")
            }
          }
        })
      },
      viewcomments(){
        this.$router.push(
          {
            path: '/comments',
            query: {
              article_id:this.article.id,
              article_title:this.article.doc_title
            }
          }
        )
      }

    }
  }
</script>
<style scoped>
  @import "../assets/css/markdown.css";
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
