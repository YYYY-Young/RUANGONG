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
                <el-breadcrumb-item >回收站点</el-breadcrumb-item>
            </el-breadcrumb>
        </el-row>
<!-- 面包屑栏目结束 -->
<!-- 下面开始写要渲染的东西 -->
    <div style="height:20px;background-color:#FAFAFA">
    </div>
  <el-table
      :data="articles"
      style="width: 100%"
      max-height="600">
<el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="文章ID">
            <span>{{ props.row.id }}</span>
          </el-form-item>
           <el-form-item label="所属团队">
            <span>{{ props.row.doc_team }}</span>
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
      width="160">
      <template slot-scope="scope">
        <el-button @click="restorearticle(scope.row.id)" type="text" size="small">恢复</el-button>
        <el-button @click="deletearticle(scope.row.id)" type="text" size="small">永久删除</el-button>
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
          articles:[
            //  {id:1,title:"one",abs:"jdklfjklqjwklrew"},
            // {id:2,title:"two",abs:"sdfwerwetretwerwet"},
            // {id:3,title:"emm",abs:"dfjkwljekjlkwjkel"},
            // {id:4,title:"qaq",abs:"djfkljwkerklwjeklr"},             
          ]
      }
  },
  mounted () {
      this.loadArticles()  
        },
    methods: {
        loadArticles(){
        //使用的阅读列表进行的测试
       var _this = this
       //console.log(this.$store.)
        this.$axios.get('/doc/trashdocs/'+this.$store.state.user.id).then(resp => {
          if (resp && resp.data.code === 200) {
                _this.articles=resp.data.result
                 console.log(_this.articles)              
          }
        })
        },
        //恢复函数
        restorearticle(id){
        console.log(id)
        this.$axios.delete('/notrash/'+this.$store.state.user.id+'/'+id).then(resp => {
          if (resp && resp.data.code === 200) {
          this.$message({
            type: 'info',
            message: resp.data.result
          }) 
          this.loadArticles()                      
          }
        })              
        },
        //删除函数
      deletearticle (id) {
        this.$confirm('此操作将永久删除该文章, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            this.$axios.delete('/doc/deleteone/'+id+'/'+this.$store.state.user.id).then(resp => {
                // this.loadArticles()
                 this.$message({
                   type: 'info',
                   message: '永久删除'
                 })
                this.loadArticles()
              
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

