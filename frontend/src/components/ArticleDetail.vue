<template>
  <div>
      <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item >最近浏览</el-breadcrumb-item>
        <el-breadcrumb-item>文章列表</el-breadcrumb-item>
        <el-breadcrumb-item>{{article.articleTitle}}</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
  <div class="articles-area">
    <el-card style="text-align: left;width: 990px;margin: 35px auto 0 auto;min-height:500px ">
      <div>
        <span style="font-size: 20px"><strong>{{article.articleTitle}}</strong></span>
        <el-divider content-position="left">{{article.abs}}</el-divider>
        <!--渲染
        <div class="markdown-body">
          <div v-html="article.articleContentHtml"></div>
        </div>
        -->
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
       /* var _this = this
        this.$axios.get('/article/' + this.$route.query.id).then(resp => {
          if (resp && resp.data.code === 200) {
            _this.article = resp.data.result
          }
        })*/
        this.article= {id:1,articleTitle:"one",abs:"jdklfjklqjwklrew"}
      }
    }
  }
</script>
<style scoped>
  @import "../assets/css/markdown.css";
</style>