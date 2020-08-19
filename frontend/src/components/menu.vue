<template>
   
    
    <el-menu
       
      class="el-menu-vertical-demo"
      style="background-color: #F5F5F5;min-height:800px;width:200px;text-align:left;"
      @open="handleOpen"
      @close="handleClose">
      <!-- <el-menu-item index="1" >
        <i class="el-icon-s-home"></i>
        <span slot="title" @click="returnbench()">工作空间  </span>
      </el-menu-item>      -->
<el-submenu index="1" style="background-color: #F5F5F5">
        <template slot="title">
          <i class="el-icon-s-home"></i>
          <span >工作空间</span>
        </template>
        <el-menu-item-group >
          <el-menu-item index="1-1" @click="returnbench()" ><i class="el-icon-s-platform"></i>工作台</el-menu-item>
          <el-menu-item index="1-2" @click="gocollections()"><i class="el-icon-star-on"></i>我的收藏</el-menu-item>
          <el-menu-item index="1-3" @click="gomycreate()"><i class="el-icon-document"></i>我的文章</el-menu-item>
      
        </el-menu-item-group>
</el-submenu>
      <el-submenu index="2" style="background-color: #F5F5F5">
        <template slot="title">
          <i class="el-icon-user-solid"></i>
          <span >团队空间</span>
        </template>
        <el-menu-item-group >
         <el-menu-item index="2-2" @click="createteam()">
           <i class="el-icon-circle-plus-outline"></i>
          <span>创建团队</span>
         </el-menu-item>
          <el-menu-item index="2-1" @click="myteam()"> 
            <i class="el-icon-s-cooperation"></i>
          <span> 我的团队</span>   
          </el-menu-item>
        
        </el-menu-item-group>
</el-submenu>
      <el-menu-item index="3">
        <i class="el-icon-delete-solid"></i>
        <span slot="title" @click="gorecyclebin()">回收站点  </span>
      </el-menu-item>
      <!-- <el-menu-item index="4">
        <i class="el-icon-setting"></i>
        <span slot="title">帮助中心</span>
      </el-menu-item> -->
      <el-menu-item index="5">
        <i class="el-icon-phone"></i>
        <span slot="title" @click="gomessagecenter()">消息中心</span>
      </el-menu-item>
 <!-- <el-submenu index="1" style="background-color: #F5F5F5">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span @click="returnbench()">工作空间</span>
        </template>
        <el-menu-item-group >
          <el-menu-item index="1-1">我的收藏</el-menu-item>
          <el-menu-item index="1-2">我的创建</el-menu-item>
        </el-menu-item-group>
</el-submenu>  -->
    </el-menu>
</template>

<script>
export default {
  methods:{
    returnbench(){
      this.$router.push('/workbench')
    },
    gorecyclebin(){
      this.$router.push('/recyclebin')
    },
    gomessagecenter(){
      this.$router.push('/messagecenter')
    },
    gocollections(){
      this.$router.push('/collections')
    },
    gomycreate(){
      this.$router.push('/mycreate')
    },
    gorecenteditor(){
      this.$router.push('/recenteditor')
    },
    gorecentreview(){
      this.$router.push('/recentreview')
    },
    teamdetail(){
      this.$router.push('/teamdetail')
    },
    createteam(){
      this.$router.push('/teameditor')
    },
    myteam(){
       this.$router.push('/myteam')
    }
  }
}
</script>

<style>

</style>