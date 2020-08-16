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
                <el-breadcrumb-item >工作空间</el-breadcrumb-item>
                <el-breadcrumb-item >工作台</el-breadcrumb-item>
            </el-breadcrumb>
        </el-row>
       <el-row :gutter="20">
           <el-col :span="5">
               <img src="../assets/images/design.png" class="image-1"  @click="createarticle()">
               <span>创建文档</span>
           </el-col>
           <el-col :span="5">              
               <img src="../assets/images/design.png" class="image-1" @click="recentreview()"> 
               <span>最近评论</span>              
           </el-col> 
           <el-col :span="5">               
               <img src="../assets/images/design.png" class="image-1" @click="recenteditor()">      
               <span>最近编辑</span>         
           </el-col>
           <el-col :span="5">               
               <img src="../assets/images/more-design.png" class="image-1" @click="morearticle()">    
               <span>最近浏览</span>           
           </el-col>                 
       </el-row>
      </el-main>
</el-container> 
</el-container>

</template> 
<script>
import Menu from './menu.vue'
export default {
 components: {
   Menu
  },
  props: {
    msg: String
  },
  methods: {
		createarticle(){
            this.$router.push('/editor')
		},
		morearticle(){
            this.$router.push('/articles')
    },
    recentreview(){
            this.$router.push('/recentreview')
    },
    recenteditor(){
            this.$router.push('/recenteditor')
    }
  },
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
  .image-1 {
      width: 100%;
      height: 100%;
  }
</style> 
