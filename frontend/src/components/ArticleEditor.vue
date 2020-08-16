<!--该组件为编辑器组件-->
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
                <el-breadcrumb-item >
					<router-link :to="{path:'/workbench'}">工作空间</router-link>
				</el-breadcrumb-item>
                <el-breadcrumb-item >
					<router-link :to="{path:'/workbench'}">工作台</router-link>
				</el-breadcrumb-item>
                <el-breadcrumb-item >创建文章</el-breadcrumb-item>
            </el-breadcrumb>
        </el-row>
<!--------------------------------------------------------------------------------------------->
     <el-row style="text-align:left">      
        <input
        v-model="articleTitle"
        style="margin: 10px 0px;font-size: 18px;"
        placeholder="请输入标题" />
        按下Ctrl+S或者点击工具栏保存按钮保存文章    
    </el-row> 
	<el-row style="text-align:left">
		<el-button style="background-color: #F5F5F5;margin-left:10px"  @click="dialogVisible = true">输入摘要</el-button>
		<el-button style="background-color: #F5F5F5;margin-right:10px" @click="permissionVisible= true">设置文章权限</el-button>
	</el-row>
    <el-row>
        <!--编辑文本内容,待修改-->
        <mavon-editor
        v-model="article.doc_content_md" 
        style="min-height: 500px;"
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
<!-- 摘要弹框 -->
      <el-dialog
        :visible.sync="dialogVisible"
        width="30%" id="dia1">
        <el-divider content-position="left">摘要</el-divider>
        <el-input
          type="textarea"
          v-model="articleAbstract"
          rows="6"
          maxlength="255"
          show-word-limit></el-input>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog> 
<!-- 权限弹框     -->
	<el-dialog
        :visible.sync="permissionVisible"
        width="30%" id="dia2">
		<el-divider content-position="left">文章权限设置</el-divider>
			<el-row>
			<el-switch
				v-model="permissions[0]"
				active-text="团队公开"
				inactive-text="完全公开">
			</el-switch>
			</el-row>
			<el-row>
			<el-switch
				v-model="permissions[1]"
				active-text="可读"
				inactive-text="不可读">
			</el-switch>
			</el-row>
			<el-row>
			<el-switch
				v-model="permissions[2]"
				active-text="可修改"
				inactive-text="不可修改">
			</el-switch>
			</el-row>
			<el-row>
			<el-switch
				v-model="permissions[3]"
				active-text="可评论"
				inactive-text="不可评论">
			</el-switch>
			</el-row>
			<el-row>
			<el-switch
				v-model="permissions[4]"
				active-text="可删除"
				inactive-text="不可删除">
			</el-switch>
			</el-row>
			<el-row>
			<el-switch
				v-model="permissions[5]"
				active-text="可分享"
				inactive-text="不可分享">
			</el-switch>
			</el-row>
			<el-row>
			<el-switch
				v-model="permissions[6]"
				active-text="回收"
				inactive-text="不回收">
			</el-switch>
			</el-row>
          <el-button type="primary" @click="permissionVisible = false">确 定</el-button>
	</el-dialog>   

    </el-row>
<!--------------------------------------------------------------------------------------------->
      </el-main>
</el-container> 
</el-container>
</template>

<script>
import Menu from './menu.vue'
  export default {
	name: 'Editor',
	components: {
		Menu
	},
    data () {
      return {
        articleAbstract: "default abstract",
        article: {} ,
        articleTitle: "",//默认标题
		dialogVisible: false, //控制是否显示摘要弹框
		permissionVisible: false, //控制是否显示权限弹框
		permissions: [true,true,true,true,true,true,false]
      }
    },
    mounted () {
      
      if (this.$route.params.article) { 
        this.article = this.$route.params.article
        this.articleTitle=this.article.doc_title
        this.articleAbstract=this.article.doc_abstract
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
            if(this.articleTitle==""){
				alert("标题不能为空！")
				return
        }     
        //如果没有输入标题将以默认标题提交
           var date=new Date()

            this.$axios.post('/doc/save', {
             doc_title: _this.articleTitle,
             doc_content_html: render,
             doc_content_md: value,
             doc_abstract: _this.articleAbstract,
             doc_cover: "",
             doc_found_date: date,
             doc_founder: _this.$store.state.user.id,
             doc_team: "0",
             doc_only_team: _this.permissions[0], 
             doc_read: _this.permissions[1],
             doc_edit: _this.permissions[2],
             doc_comment: _this.permissions[3],
             doc_delete: _this.permissions[4],
             doc_share: _this.permissions[5],
             doc_recycle: _this.permissions[6],
             doc_last_edit_uid: _this.$store.state.user.id,           
             doc_last_edit_time: date

            }
              ).then(resp => {
              if (resp && resp.data.code === 200) {
                this.$message({
                  type: 'info',
                  message: '已保存成功'
                })
                this.$router.push('/workbench')

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
</style> 
