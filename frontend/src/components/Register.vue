<template>
  <div class="register_container">
    <div class="register_box">
    <div class='we'>欢迎注册钻石文档</div>
   
      <!-- 注册表单区域 -->
      <el-form ref="registerFormRef" :model="registerForm" :rules="registerFormRules" label-width="0px" class="register_form">
        <!-- 用户名 -->
        <el-form-item prop="username">
          <el-input v-model="registerForm.username" prefix-icon="iconfont icon-user" placeholder="请输入用户名"
    ></el-input>
        </el-form-item>

        <el-form-item prop="email">
      <el-input type="text" v-model="registerForm.email"
                auto-complete="off" placeholder="请输入E-Mail"></el-input>
                <el-button class="check" type="primary" @click="checkemail">验证邮箱</el-button>
    </el-form-item>

     <el-form-item prop="code">
          <el-input type="text" v-model="registerForm.code"
                auto-complete="off" placeholder="请输入邮箱验证码"></el-input>
    </el-form-item>
    <el-form-item prop="phone">
          <el-input v-model="registerForm.phone"   auto-complete="off"  type="text" placeholder="请输入您的电话"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input v-model="registerForm.password" prefix-icon="iconfont icon-3702mima" type="password" placeholder="请设置您的登陆密码"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="registerForm.repetpassword" prefix-icon="iconfont icon-3702mima" type="password"  placeholder="请确认您的登陆密码"></el-input>
        </el-form-item>
        <!-- 按钮区域 -->
        <el-form-item class="btns">
          <el-button type="primary" @click="register">注册</el-button>
          <el-button type="info" @click="resetregisterForm">重置</el-button>
          <el-button type="primary" @click="login">登陆</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
  
      registerForm: {
        username: '',
        password: '',
        repetpassword: '',
        email: '',
        code: '',
        phone: '',
      },

      registerFormRules: {
        username: [
          { required: true, message: '请输入登录名称', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        // 验证密码是否合法
        password: [
          { required: true, message: '请输入登录密码', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        email: [ 
          { required: true, type: 'email',message: '请输入正确的邮箱地址', trigger: 'blur' }
        ],
        code: [  
          { required: true, message: '请输入邮箱验证码', trigger: 'blur' },
           { min: 6, max: 6, message: '验证码长度为6 ', trigger: 'blur' } 
           ],
        phone: [{ required: true, message: '请输入电话', trigger: 'blur' },
           { min: 11, max: 11, message: '请输入正确号码 ', trigger: 'blur' } 
           ]
      }
    }
  },
  methods: {
    // 点击重置按钮，重置注册表单
    checkemail(){
 alert('check')
    },
    resetregisterForm() {
      // console.log(this);
      this.$refs.registerFormRef.resetFields()
    },
    login(){
        this.$router.push('/login')
    },
    register() {
       this.$refs.registerFormRef.validate(async valid => {
        if (!valid) return
       
        var _this = this
        this.$axios.post('/register', {
            username: this.registerForm.username,
            password: this.registerForm.password,
            email: this.registerForm.email,
            code: this.registerForm.code,
            phone: this.registerForm.phone
          })
          .then(resp => {
            if (resp.data.code === 200) {
              var data = resp.data.result
              _this.$store.commit('login', data)
              this.$message.success('注册成功，请登录')
              this.$router.push('/login')
            } else {
              this.$alert(resp.data.message, '邮箱验证码错误', {
                confirmButtonText: '确定'
              })
            }
          }
          )
          .catch(failResponse => {})
      })
    }
  }
}
</script>

<style lang="less" scoped>
.register_container {
  background-color: #2b4b6b;
  height: 100%;
  background-image: url('');
}
.we {
  text-align: center;
  font-size: 40;
}
.check {
  float: right;
}
.register_box {
  width: 450px;
  height: 500px;
  background-color: rgb(207, 207, 200);
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);

  .avatar_box {
    height: 130px;
    width: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
  }
}

.register_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}

.btns {
  display: flex;
  justify-content: flex-end;
}
</style>