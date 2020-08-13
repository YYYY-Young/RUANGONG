<template>
  <div>
      <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item >最近浏览</el-breadcrumb-item>
        <el-breadcrumb-item>文章列表</el-breadcrumb-item>
        <el-breadcrumb-item>{{article.doc_title}}</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
  <div class="articles-area">
    <el-card style="text-align: left;width: 990px;margin: 35px auto 0 auto;min-height:500px ">
      <div>
   
        <span style="font-size: 20px"><strong>{{article.doc_title}}</strong></span>
        <el-divider content-position="left">{{article.doc_abstract}}</el-divider>
        <div class="markdown-body">
          <div v-html="article.doc_content_html"></div>
        </div>
         <el-button type="primary" @click="editorarticle()">编辑文章</el-button> 
         <el-button type="danger" @click="deleteArticle (article.id)">删除文章</el-button>    
      </div>
    </el-card>
  </div>
  </div>
</template>

<script>
  export default {
    name: 'ArticleDetails',
    data () {
      return {
        article: {}
      }
    },
    mounted () {
      this.loadArticle()
    },
    methods: {
      loadArticle () {
        var _this = this
        this.$axios.get('/doc/getone/'+this.$route.query.id+'/'+this.$store.state.user.id).then(resp => {
          if (resp && resp.data.code === 200) {
            _this.article = resp.data.result
            console.log(_this.article)
          }
        })
       
      },
      editorarticle(){
        this.$router.push(
          {
            name: 'ArticleChange',
            params: {
              article: this.article
            }
          }
        )
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
              }
            })
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      }

    }
  }
</script>
<style scoped>
  @import "../assets/css/markdown.css";
</style>