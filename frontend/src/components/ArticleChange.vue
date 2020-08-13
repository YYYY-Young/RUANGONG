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
      <el-card style="background:#D0D0D0;">        
        <input
        v-model="article.doc_title"
        style="margin: 10px 0px;font-size: 18px;"
        placeholder="请输入标题" />
        按下Ctrl+S或者点击工具栏保存按钮提交文章修改内容
      </el-card>       
    </el-row>
    <el-row>
        <!--编辑文本内容,待修改-->
        <mavon-editor
        v-model="article.doc_content_md" 
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
      <el-dialog
        :visible.sync="dialogVisible"
        width="30%">
        <el-divider content-position="left">摘要</el-divider>
        <el-input
          type="textarea"
          v-model="article.doc_abstract"
          rows="6"
          maxlength="255"
          show-word-limit></el-input>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog>     
    </el-row>
</div>
</template>

<script>
  export default {
    name: 'Editor',
    data () {
      return {
        article: {} ,
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
          var _this=this
        // articleTitle没写则无法提交
        //响应还未加

        
        this.$confirm('是否保存并发布文章?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            if(_this.article.doc_title==""){
              _this.article.doc_title="default header"
        }     
        //如果没有输入标题将以默认标题提交
           var date=new Date()

            this.$axios.post('/doc/save', {
            id:_this.article.id,
            doc_title:_this.article.doc_title,
            doc_content_html: render,
            doc_content_md: value,
            doc_abstract: _this.article.doc_abstract,
            doc_cover:"",
            doc_found_date: _this.article.doc_found_date,
            doc_founder:_this.article.doc_founder,
            doc_team: _this.article.doc_team,
            doc_only_team: _this.article.doc_only_team,
            doc_read: _this.article.doc_read,
            doc_edit:_this.article.doc_edit,
            doc_comment: _this.article.doc_comment,
            doc_delete:_this.article.doc_delete,
            doc_share:_this.article.doc_share, 
            doc_recycle:_this.article.doc_recycle,
            doc_last_edit_uid: this.$store.state.user.id,           
            doc_last_edit_time: date

            }
              ).then(resp => {
              if (resp && resp.data.code === 200) {
                this.$message({
                  type: 'info',
                  message: '已修改成功'
                })
              }
            })
            //console.log(this.articleTitle)            
            //console.log(value)
            //console.log(render)
            //console.log(this.articleAbstract)
            console.log(date)
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