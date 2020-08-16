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
                    <router-link :to="{path:'/workbench'}">工作空间</router-link>
                </el-breadcrumb-item>
                <el-breadcrumb-item >
                    <router-link :to="{path:'/workbench'}">工作台</router-link>
                </el-breadcrumb-item>
                <el-breadcrumb-item >最近阅读</el-breadcrumb-item>
            </el-breadcrumb>
        </el-row>
<!-- 面包屑栏目结束 -->
<!-- 下面开始写要渲染的东西 -->
 <div style="margin-top: 10px;background-color:#FAFAFA" >       
       <div class="articles-area" style="background-color:#FAFAFA">
           <el-card style="text-align: left;background-color:#FAFAFA;">
                <div v-for="item in articles" :key="item.id" style="background-color:#FAFAFA">                  
                    <div style="float:left;width:85%;height: 150px;background-color:#FAFAFA">
                      <row>
                        <el-divider></el-divider> 
                        <router-link class="article-link" :to="{path:'/articledetail',query:{id: item.id}}"><span style="font-size: 20px"><strong>{{item.doc_title}}</strong></span></router-link>
                      </row>
                      <row>
                      <p style="text-align:left"><i>{{item.doc_abstract}}</i></p> 
                      </row>   
                          <!-- <el-divider content-position="left">{{item.doc_abstract}}</el-divider>     -->                                     
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
      </el-main>
<!-- 主要渲染栏容器结束 -->
</el-container> 
</el-container>

</template>

<script>
import Menu from './menu.vue'
export default {
    name: 'Articles',
 components: {
   Menu
  },
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
