<template>
   <div>
     <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item >最近浏览</el-breadcrumb-item>
        <el-breadcrumb-item>文章列表</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
   <div style="margin-top: 40px" >       
       <div class="articles-area">
           <el-card style="text-align: left">
                <div v-for="item in articles" :key="item.id">
                  
                    <div style="float:left;width:85%;height: 150px;">
                        <router-link class="article-link" :to="{path:'/articledetail',query:{id: item.id}}"><span style="font-size: 20px"><strong>{{item.doc_title}}</strong></span></router-link>
                          <el-divider content-position="left">{{item.doc_abstract}}</el-divider>    
                         <el-divider></el-divider>                 
                    </div>                                                     
                </div> 
           </el-card>
       </div>      
       <!-- <el-pagination
           background
           @current-change="handleCurrentChange"
           layout="total, prev, pager, next, jumper"
           :page-size="13"
           :total="this.total"></el-pagination> -->
       
   </div>
   </div>
</template>

<script>
export default {
    name: 'Articles',
  data () {
      return {
        docid: [],
        articles: [
           
           // {id:1,title:"one",abs:"jdklfjklqjwklrew"},
           // {id:2,title:"two",abs:"sdfwerwetretwerwet"},
           // {id:3,title:"emm",abs:"dfjkwljekjlkwjkel"},
           // {id:4,title:"qaq",abs:"djfkljwkerklwjeklr"},
        ],
        pageSize: 4,
        total: 5
      }
    },
    mounted () {
      this.loadArticles()  
        },
    methods : {
        loadArticles (){
       var _this = this
       //console.log(this.$store.)
        this.$axios.get('/doc/docread/'+this.$store.state.user.id).then(resp => {
          if (resp && resp.data.code === 200) {
                _this.articles=resp.data.result
               
          }
        })
      }
       },
         
         handleCurrentChange (){

         }
    }

</script>


<style scoped>
  .articles-area {
    width: auto;
    height: auto;
    margin-left: auto;
    margin-right: auto;
  }

  .article-link {
    text-decoration: none;
    color: #606266;
  }

  .article-link:hover {
    color: #409EFF;
  }
</style>
