<!--该组件为编辑器组件-->
<template>
<div>
    <!--该行为标题-->
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item >文章管理</el-breadcrumb-item>
        <el-breadcrumb-item>编辑器</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-row>
      <input
        v-model="article.articleTitle"
        style="margin: 10px 0px;font-size: 18px;"
        placeholder="请输入标题" />
       
    </el-row>
    <el-row>
        <!--编辑文本内容,待修改-->
        <mavon-editor
        v-model="article.articleContentMd" 
        style="height: 100%;"
        ref=md
        @save="saveArticles"
        fontSize="16px">
        <!--使用的是编辑器给的四个插件槽中名为left-left-toolbar-afterd那一个-->
        <!--点击出发弹框显示-->
        <button type="button" 
        class="op-icon el-icon-document" 
        :title="'摘要/封面'" 
        slot="left-toolbar-after"
        @click="dialogVisible = true"></button>
      </mavon-editor>
      <el-button class="submit" type="primary" @click="saveArticles" style="margin: 10px">确认提交</el-button>
    </el-row>
</div>
</template>

<script>
  export default {
    name: 'Editor',
    data () {
      return {
        article: {},
        dialogVisible: false, //控制是否显示弹框
      }
    },
    mounted () {
      if (this.$route.params.article) {
        this.article = this.$route.params.article
      }
    },
    methods: {
        saveArticles (value, render) {
        // article还不知道怎么定义的
        //响应还未加
        
        this.$confirm('是否保存并发布文章?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
           /* this.$axios
              .post('/article', {
                  
                
              }).then(resp => {
              if (resp && resp.data.code === 200) {
                this.$message({
                  type: 'info',
                  message: '已保存成功'
                })
              }
            })*/
            console.log("保存")
            console.log(this.article.articleTitle)
            console.log(value)
            console.log(render)
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消发布'
          })
        })
      },
    }
    }
</script>