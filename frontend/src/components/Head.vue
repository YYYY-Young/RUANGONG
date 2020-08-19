<template>
  <div style="height:50px;"><el-row>
    <el-col :span="5">
       <img src="../assets/images/logoend.jpg" style="height:50px;width:160px;"> 
    </el-col>
    <el-col :span="18" style="text-align:right">
      <el-select v-model="search_method" placeholder="搜索方式" style="width:105px;margin-right:10px;margin-top:5px">
        <el-option
         v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value">
        </el-option>
      </el-select>
      <el-input style="width:300px" v-model="search_content" placeholder="请输入搜索内容">
      </el-input>
      <!-- 注释的是带搜索历史的搜索框 -->
      <!-- <el-autocomplete
        class="inline-input"
        style="width:300px"
        v-model="search_content"
        :fetch-suggestions="querySearch"
        placeholder="请输入搜索内容"
      ></el-autocomplete> -->
       <img src="../assets/images/button.jpg" style="height:38px;margin-left:5px" @click="search(search_method,search_content)" > 
       <!-- <el-button type="primary" icon="el-icon-search" style="margin:5px" @click="search(search_method,search_content)">搜索</el-button>       -->
    </el-col>
    <el-col :span="1">
      <el-avatar  @click.native="drawer = true" type="primary" style="margin: 5px;" icon="el-icon-user-solid"></el-avatar>
      </el-col>
      <el-drawer
          title="information"
          :visible.sync="drawer"
          :with-header="false"
          :direction="direction"
          :before-close="handleClose">
          <div id="un" style="margin:0px;text-align:center;background-color:#EFEFEF">
        <el-avatar   type="primary" :size="50" icon="el-icon-user-solid"></el-avatar>
          </div> 
          <div id="in" style="background-color:#F5F5F5;height:100%;margin:0px">
            <div>
            <div class="drawer_row">
              <div >
              <h5>账号ID:</h5>
              </div>
              <div class="drawer_info">
                <p>{{user.id}}</p>
               </div>
            </div>
            <div class="drawer_row">
              <div>
              <h5>用户名USERNAME:</h5>
              </div>
              <div class="drawer_info">
                <p>{{user.username}}</p>
               </div>
            </div>
            <div class="drawer_row">
              <div>
              <h5>邮箱EMAIL:</h5>
              </div>
              <div class="drawer_info">
                <p>{{user.email}}</p>
               </div>
            </div>
            <div class="drawer_row">
              <div>
              <h5>电话PHONE:</h5>
              </div>
              <div class="drawer_info">
                <p>{{user.phone}}</p>
               </div>
            </div>
            </div>
            <div style="height:100px"></div>
            <div style="text-align:right">
                <el-button style="margin-right:10px;height:40px;width:100px" type="danger" @click="userlogout()">注销</el-button>
                <el-button style="margin-right:10px;height:40px;width:100px" type="primary" @click="editInfoopen()">修改信息</el-button>
                <el-button style="margin-right:10px;height:40px;width:100px" type="primary" @click="resetPwopen()">修改密码</el-button>
            </div>
          </div>    
      </el-drawer>
  </el-row>
  <!-- 修改个人信息 -->
          <el-dialog
        title="修改个人信息"
        :visible.sync="editInfoVisible"
        width="30%"
        :close-on-click-modal="false"
        :show-close="false">
<div>
<el-form :model="infoForm">
    <el-form-item label="用户名称" :label-width="formLabelWidth">
      <el-input v-model="infoForm.username" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="用户电话" :label-width="formLabelWidth">
      <el-input v-model="infoForm.phone" autocomplete="off"></el-input>
    </el-form-item>
    <!-- <el-form-item label="用户邮箱" :label-width="formLabelWidth">
      <el-input v-model="infoForm.email" autocomplete="off"></el-input>
    </el-form-item> -->
  </el-form>
  <el-button type="primary" @click="editInfosubmit()" style="margin-bottom:5px">确 定 </el-button>
</div>
<div>
 <el-input style="width:300px;margin-top:10px" v-model="newemail" placeholder="请输入新邮箱" >
      </el-input>
   <el-input style="width:300px;margin-top:10px" v-model="emailcode" placeholder="请输入验证码">
      </el-input>
<el-button type="primary" @click=" getpwcode()">获取验证码</el-button>
<el-button type="primary" @click="resetemailsubmit()">确认</el-button>
</div>
  <div slot="footer" class="dialog-footer">
    <el-button @click="editInfoVisible = false">取 消</el-button>
    <!-- <el-button type="primary" @click="editInfosubmit()">确 定 </el-button> -->
  </div>
        </el-dialog>
        <!-- 个人信息弹框结束 -->
        <!-- 修改密码 -->
          <el-dialog
        title="密码修改"
        :visible.sync="resetPwVisible"
        width="30%"
        :close-on-click-modal="false"
        :show-close="false">
        <el-form :model="pwForm">
            <!-- <el-form-item label="原密码" :label-width="formLabelWidth">
              <el-input v-model="pwForm.oldpw" autocomplete="off"></el-input>
            </el-form-item> -->
            <el-form-item label="新密码" :label-width="formLabelWidth">
              <el-input v-model="pwForm.newpw" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="验证码" :label-width="formLabelWidth">
              <el-input v-model="pwForm.code" autocomplete="off"></el-input>
            </el-form-item>
       </el-form>
      <div slot="footer" class="dialog-footer">
            <el-button @click=" resetPwVisible = false">取 消</el-button>
            <el-button type="primary" @click=" getpwcode()">获取验证码</el-button>
            <el-button type="primary" @click="resetPwsubmit()">确认</el-button>
      </div>
        </el-dialog>
        <!-- 修改密码弹框结束 -->
        <!-- 搜索的用户显示 -->
        <el-dialog
        title="搜索结果"
        :visible.sync="search_users_visible"
        width="50%"
        :close-on-click-modal="false"
        :show-close="false"
        >
       <!-- <div style="margin:0px;text-align:center;background-color:#EFEFEF">
        <el-avatar   type="primary" :size="50">{{search_users[0].username}}</el-avatar>
        </div>
        
        <div  class="info1">
        <h1>  用户ID：{{search_users[0].id}}</h1>
        </div>
        <div  class="info2">
        <h1>  名称：{{search_users[0].username}}</h1>
        </div>
        <div  class="info1">
         <h1> 邮箱：{{search_users[0].email}}</h1>
        </div>
        <div  class="info2">
          <h1>电话：{{search_users[0].phone}}</h1>
        </div>  -->
         <el-table :data="search_users" max-height="400px">
          <el-table-column prop="id" label="用户ID" width="100"></el-table-column>
           <el-table-column prop="username" label="名称" width="150"></el-table-column>
           <el-table-column prop="email" label="邮箱" width="150"></el-table-column>
           <el-table-column prop="phone" label="电话" width="150"></el-table-column>
          </el-table>  
          <div slot="footer" class="dialog-footer">
            <el-button @click=" search_users_visible = false">确 定</el-button>
          </div>
        </el-dialog>
        <!-- 搜索用户显示结束 -->
        <!-- 搜索文章显示 -->
        <el-dialog
        title="搜索结果"
        :visible.sync="search_articles_visible"
        width="70%"
        :close-on-click-modal="false"
        :show-close="false">
<el-table
      :data="search_articles"
      style="width: 100%"
      max-height="400px">
<el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="摘要">
            <span>{{ props.row.doc_abstract}}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column
        prop="id"
        label="文章ID"
        width="150">
    </el-table-column>
    <el-table-column
        prop="doc_title"
        label="文章标题"
        width="150">
    </el-table-column>     
    <el-table-column
        prop="doc_founder"
        label="创建人id"
        width="150">
    </el-table-column>
    <el-table-column
        prop="doc_found_date"
        label="创建日期"
        width="150">
    </el-table-column>
   <el-table-column
      fixed="right"
      label="操作"
      width="100">
      <template slot-scope="scope">
        <el-button @click="viewarticle(scope.row.id)" type="text" size="small">查看文章</el-button>
      </template>
    </el-table-column>
  </el-table>       
          <div slot="footer" class="dialog-footer">
            <el-button @click=" search_articles_visible = false">确 定</el-button>
           </div>
        </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Head',
  data() {

        return {
          drawer: false,
          user:{},
          editInfoVisible:false,
          resetPwVisible:false,
          infoForm:{
            id:"",
            username:"",
            phone:"",
            email:""
          },
          newemail:"",
          emailcode:"",
          pwForm:{
            newpw:"",
            code:""
          },
          options: [{
            label:"用户名",
            value:"1"
          },{
            label:"创建者名称",
            value:"2"
          },{
            label:"文章标题",
            value:"4"
          },{
            label:"文章内容",
            value:"5"
          }],
          search_method:"1",
          search_content:"",
          search_articles:[],
          search_users:[],
          search_users_visible:false,
          search_articles_visible:false
    };
  },
   mounted () {
  this.loaduser()
},
  methods:{
    loaduser(){
      this.user=this.$store.state.user
    },
    editInfoopen(){
      //设置infoForm值
      this.infoForm.id=this.user.id
      this.infoForm.username=this.user.username
      this.infoForm.phone=this.user.phone
      this.infoForm.email=this.user.email
      this.editInfoVisible=true
    },
    editInfosubmit(){
      //提交修改信息，提交后己得重新load
      var _this=this
        this.$confirm('是否确认修改信息?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            if(this.infoForm.username==""||this.infoForm.phone==""||this.infoForm.email==""){
				alert("个人信息不能为空！")
				return
        }     
        //如果没有输入标题将以默认标题提交
            this.$axios.put('/user', {
              id:this.infoForm.id,
              username:this.infoForm.username,
              phone:this.infoForm.phone,
              email:this.infoForm.email,            
            }
              ).then(resp => {
              if (resp && resp.data.code === 200) {
                _this.$store.commit('login',resp.data.result)
                this.$message({
                  type: 'info',
                  message: '修改成功'
                })
                
                this.loaduser()
              }
            })
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消修改'
          })
        })      
      this.editInfoVisible=false
    },
    resetPwopen(){
      //设置pwForm值
      this.resetPwVisible=true
    },
    getpwcode(){
//获取修改的验证码
this.$axios.get('/sendemail/'+this.$store.state.user.email).then(resp =>{
  if(resp&&resp.data.code==200){
          this.$message({
            type: 'info',
            message: '请在邮箱中查看验证码'
          })    
  }
})
    },
    resetPwsubmit(){
      var user={}
      this.$axios.put('/user/password',{
        username:this.$store.state.user.username,
        email:this.$store.state.user.email,
        code:this.pwForm.code,
        password:this.pwForm.newpw
      }).then(resp=>{
if(resp&&resp.data.code==200){
  user=resp.data.result
  if(user.password==this.$store.state.user.password){
          this.$message({
            type: 'info',
            message: '验证码不正确或者新密码与旧密码一致，请重新获取验证码'
          })    
  }else{
    this.$store.commit('login', user)
              this.$message({
            type: 'info',
            message: '修改成功请重新登录'
          })  
    this.$store.commit('logout')
    this.$router.push('/login')
  }
}
      })
      this.resetPwVisible=false
    },
    resetemailsubmit(){
      var user={}
        this.$confirm('是否确认新邮箱为可用邮箱', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            if(this.newemail==""){
				alert("邮箱不能为空！")
				return
        }     
        //如果没有输入标题将以默认标题提交
              // console.log(this.emailcode)
              // console.log(this.newemail)
            this.$axios.put('/user/emailchange', {
                username:this.$store.state.user.username,
                email:this.newemail,
                code:this.emailcode
            }           
              ).then(resp => {
              if (resp && resp.data.code === 200) {
                user=resp.data.result
                if(user.email==this.$store.state.user.email){
                this.$message({
                  type: 'info',
                  message: '验证码错误或者新旧邮箱一致，请重新获取验证码'
                })                  
                }else{
                this.$message({
                  type: 'info',
                  message: '已修改成功'
                })   
                  this.$store.commit('login', user) 
                  this. loaduser()
                    this.editInfoVisible=false  
                }


              }
            })
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消修改'
          })
        })
    },
    querySearch(queryString, cb){
      //搜索历史
      var search_histry=[{"value":"111"},{"value":"222"}]
      cb(search_histry)
    },
    search(index,content){
      //console.log("触发了弹框")
      if(index=="1"){
        //按照用户名搜索
        this.$axios.get('/getuser/'+content).then(resp => {
          if(resp&&resp.data.code==200){
            this.search_users=[resp.data.result]
          }
        })
        this.search_users_visible=true
        return
      }
      if(index=="2"){
        //按照创建者名字搜索
        this.$axios.get('/doc/search/author/'+this.$store.state.user.id+'/'+content).then(resp => {
          if(resp&&resp.data.code==200){
              this.search_articles=resp.data.result                     
          }
        })       
        this.search_articles_visible=true
        return
      }
      if(index=="4"){
        //按照文章标题搜索
        this.$axios.get('/doc/search/title/'+this.$store.state.user.id+'/'+content).then(resp => {
          if(resp&&resp.data.code==200){
              this.search_articles=resp.data.result                     
          }
        })  
        this.search_articles_visible=true
        return
      }
      if(index=="5"){
        this.$axios.get('/doc/search/content/'+this.$store.state.user.id+'/'+content).then(resp => {
          if(resp&&resp.data.code==200){
              this.search_articles=resp.data.result                     
          }
        })  
        this.search_articles_visible=true
        //按照文章内容搜索
        return
      }
    },
    viewarticle(id){
      this.search_articles_visible=false
      this.$router.push({
        path:'/articledetail',
        query:{
          id:id
        }
      })
      
    },
    userlogout(){
        this.$confirm('是否确认注销登录', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {  
                        this.$message({
            type: 'info',
            message: '成功注销'
          })  
    this.$store.commit('logout')
    this.$router.push('/login')  
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          })
        })

    }
  },
}



</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
/* &:last-child {
   margin-bottom: 0;
 } */
}
.el-col
{
  border-radius: 4px;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.drawer_info {
  margin-left:20px;
  color:#a6cfeb;
  font-size:20px
}
.drawer_row{
  height: 100px;
  margin-left:10px
}
.info1{
  height: 100px;
  width: 100%;
  text-align: center;
  background-color: #e0e0e0;
}
.info2{
  height: 100px;
  width: 100%;
  text-align: center;
  background-color: #cecaca;
}
</style>
