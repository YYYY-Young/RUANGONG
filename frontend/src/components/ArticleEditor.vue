<!--该组件为编辑器组件-->
<template>
 <el-container>
   <el-header height="50px">
     <Head />
   </el-header>
    
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
		<el-button icon="el-icon-edit" style="background-color: #F5F5F5;margin-left:10px;margin-right:5px;margin-bottom:5px"  @click="dialogVisible = true">输入摘要</el-button>
		<el-button icon="el-icon-setting" style="background-color: #F5F5F5;margin-right:5px" @click="permissionVisible= true">设置文章权限</el-button>
    <el-button icon="el-icon-s-order" style="background-color: #F5F5F5;margin-right:5px" @click="modelVisible=true">使用模板创建</el-button>    
	</el-row>
    <el-row>
        <!--编辑文本内容,待修改-->
        <mavon-editor
        v-model="articlecontent" 
        style="min-height: 500px;"
        ref=md
        @imgAdd="$imgAdd"
        @imgDel="handleEditorImgDel"
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
        width="30%" 
        id="dia1"
        :close-on-click-modal="false"
        :show-close="false">
        <el-divider content-position="left">摘要</el-divider>
        <el-input
          type="textarea"
          v-model="articleAbstract"
          rows="6"
          maxlength="255"
          show-word-limit></el-input>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog> 
<!-- 权限弹框     -->
	<el-dialog
        :visible.sync="permissionVisible"
        width="30%" 
        id="dia2"
        :close-on-click-modal="false"
        :show-close="false">
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

          <el-button type="primary" @click="dialogclose()">确 定</el-button>
	</el-dialog> 
  <!-- 团队设置弹框 -->
 <el-dialog
        title="请选择文章所属团队id"
        :visible.sync="teamVisible"
        width="30%" 
        id="dia3"
        :close-on-click-modal="false"
        :show-close="false">
      <div v-for="item in teams" :key="item.id" >
         <el-radio v-model="articleTeam" :label="item.id">{{item.id}}</el-radio>        
      </div>
        <div slot="footer" class="dialog-footer">
    <el-button type="primary" @click="closeteamdialog()">确 定</el-button>
  </div>
 </el-dialog>
 <!-- 模板设置弹框 -->
<el-dialog
        title="请选择模板"
        :visible.sync="modelVisible"
        width="30%" 
        id="dia4"
        :close-on-click-modal="false"
        :show-close="false">
  <el-radio v-model="modelchoose" label="0">取消</el-radio>
  <el-radio v-model="modelchoose" label="1">个人信息模板</el-radio>
  <el-radio v-model="modelchoose" label="2">团队模板</el-radio>
  <div slot="footer" class="dialog-footer">
    <el-button type="primary" @click=" closemodeldialog()">确 定</el-button>
  </div>
</el-dialog>
    </el-row>
<!--------------------------------------------------------------------------------------------->
      </el-main>
</el-container> 
</el-container>
</template>

<script>
import Menu from './menu.vue'
import Head from './Head.vue'
import {Modle1} from '../mdmodel/md.js'
import {Modle2} from '../mdmodel/md.js'
import qs from 'qs'
//import axios2 from 'axios'

  export default {
	name: 'Editor',
	components: {
    Menu,
    Head
	},
    data () {
      return {
        articlecontent:"",
        articleAbstract: "default abstract",
        article: {} ,
        articleTitle: "",//默认标题
		dialogVisible: false, //控制是否显示摘要弹框
		permissionVisible: false, //控制是否显示权限弹框
    permissions: [false,true,true,true,true,true,false],
    teamVisible:false,
    articleTeam:"0",
    teams:[],
    modelVisible:false,
    modelchoose:"0"
      }
    },
    mounted () {
      this.loadteams()
      if (this.$route.params.article) { 
        this.article = this.$route.params.article
        this.articleTitle=this.article.doc_title
        this.articleAbstract=this.article.doc_abstract
        this.articlecontent=this.article.doc_content_md
      }
    },
    methods: {
      loadteams(){
        this.$axios.get('/team/findteams/'+this.$store.state.user.id).then(resp =>{
          if(resp&&resp.data.code==200){
            this.teams=resp.data.result
            console.log(this.teams)
          }
        })
      },
        saveArticles (value, render) {
          var _this=this
        // articleTitle没写则无法提交
        //响应还未加
        console.log(render)
        
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
            console.log(value)
            this.$axios.post('/doc/save', {
             doc_title: _this.articleTitle,
             doc_content_html: render,
             doc_content_md: value,
             doc_abstract: _this.articleAbstract,
             doc_cover: "",
             doc_found_date: date,
             doc_founder: _this.$store.state.user.id,
             doc_team: _this.articleTeam,
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
      dialogclose(){
        if(this.permissions[0]==true){
          if(this.teams.length!=0){
            this.teamVisible=true
          }else{
            this.articleTeam="0"
            this.permissions[0]=false
            this.permissionVisible=false
          }
          
        }else{
          this.articleTeam="0"
          this.permissionVisible = false
          console.log(this.articleTeam)
        }
      },
      closeteamdialog(){
        if(this.articleTeam=="0"){
          alert("请选择文章所属团队！")
          return
        }
        console.log(this.articleTeam)
        this.teamVisible=false
        this.permissionVisible=false
      },
      closemodeldialog(){
        if(this.modelchoose=="1"){
          this.articlecontent=Modle1
          this.modelVisible=false
          return
        }
        if(this.modelchoose=="2"){
          this.articlecontent=Modle2
          this.modelVisible=false
          return          
        }
        if(this.modelchoose=="0"){
          this.modelVisible=false
          return
        }
      },
      //下面是图片上传的函数
            $imgAdd(pos, $file){
            // 第一步.将图片上传到服务器.
        //     console.log($file)
        //    var formdate = new FormData();
        //    formdate.append('smfile', $file);
        //    this.$axios({
        //      url:'https://sm.ms/api/upload',
        //      method:'post',
        //      data:formdate,
        //      cache: false,
        // contentType: false,
        // processData: false
        //    // headers: { 'Content-Type': 'multipart/form-data' },
        //    }).then(resp=>{
        //      //console.log("jll")
        //      console.log(resp.data)
        //       if(resp){
        //         this.$refs.md.$img2Url(pos, resp.data.imgurl);
        //       }
        //    })
          // this.$axios.post("https://sm.ms/api/upload",qs.stringify({
          //   url:formdate
          // })).then(resp=>{
          //   // console.log("jll")
          //    console.log(resp.data)
          //      if(resp){
          //        this.$refs.md.$img2Url(pos,resp.data.imgurl);
          //      }
          //  })
          //  axios({
          //      url: 'server url',
          //      method: 'post',
          //      data: formdata,
          //      headers: { 'Content-Type': 'multipart/form-data' },
          //  }).then((url) => {
          //      // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
          //      // $vm.$img2Url 详情见本页末尾
          //      $vm.$img2Url(pos, url);
          //  })
        }      
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
