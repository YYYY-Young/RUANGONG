<template>
  <div class="header">
    <div class="container" >
      <!--
      <div id="logo" >
        <router-link to="/">
           <img url="../assets/logo.png"/>
        </router-link>
      </div>
      -->
      <div id="avatar" >
        <b-list-group style="max-width: 300px; min-width: 170px;" >
          <b-list-group-item class="d-flex align-items-center" >
            <!--
            <router-link to="/User">
              <b-avatar class="mr-3"></b-avatar>
            </router-link>
            -->
            <div @click="popup=true">
            <b-avatar class="mr-3" ></b-avatar>
            </div>
            <span class="mr-auto">
              <router-link to="/login" style="margin-right: 15px">{{user.username}}</router-link>
            </span>
           <!-- <b-badge>{{rank}}</b-badge>-->
          </b-list-group-item>
        </b-list-group>
        <div id="js-popup-settings" :class="{'tt-popup-settings':true, 'column-open':popup}">
      <div class="tt-btn-col-close">
        <b-button @click="popup=false">  返回 </b-button>
        <a  style="cursor: pointer">
          <span class="tt-icon-title">
            个人信息
            <svg>
              <use xlink:href="#icon-settings_fill" />
            </svg>
          </span>
          <span class="tt-icon-text"></span>
          
          <span class="tt-icon-close">
            <svg>
              <use xlink:href="#icon-cancel" />
            </svg>
          </span>
        </a>
      </div>
      <form class="form-default">
         <div class="form-group">
          <label for="settingsUserName">用户名USERNAME：</label>
          <label for="settingsUserName">{{user.username}}</label>
        </div>
        <div class="form-group">

          <label for="settingsUserName">账号ID：</label>
          <label for="settingsUserName">{{user.id}}</label>
        </div>
        <div class="form-group">
          <label for="settingsUserName">邮箱EMAIL：</label>
          <label for="settingsUserName">{{user.email}}</label>
        </div>
        <div class="form-group">
          <label for="settingsUserName">电话phone：</label>
          <label for="settingsUserName">{{user.phone}}</label>
        </div>
    
      </form>
    </div>
      </div>
      
      <div id="clear"></div>
    </div>
  </div>
</template>

<script>


export default {
  data() {
    return {
      user:{},
      curTab: 0,
      popup: false,
      pwd: { oldp: "", newp: "", newprep: "" },
    

    };
  },
  computed: {
    //读取全局状态（支持双向绑定的全局变量）
    username: function() {
      if (this.$store.state.isLogin) {
        return this.$store.state.user.username;
      } else return "未登录";
    },
    rank: function() {
      if (this.$store.state.isLogin) {
        return this.$store.state.user.rank;
      } else return "0";
    }
  },
  methods: {
    updatePwd() {
      var that = this;
      let sid = that.$store.state.user.sid;


      if (that.pwd.newp != that.pwd.newprep) {
        alert("两次输入的密码不一致，请检查！");
        return;
      }

      that.axios
        .get(`/${sid}/change?ps1=${that.pwd.oldp}&ps2=${that.pwd.newp}`)
        .then(function(response) {
          console.log(response);
          if (response.data.status == 0) {
            alert("成功修改密码。");
            location.reload();
          } else {
            alert("修改密码失败，原因：" + response.data.msg);
          }
        })
        .catch(function(error) {
          console.log(error);
          alert("请求修改密码时遇到了错误");
        });
    }
  },
  mounted() {
    var that = this;
    this.user=this.$store.state.user
    let sid = that.$store.state.user.sid;
    let un = that.$store.state.user.username;

    that.axios
      .get(`/${sid}/myrank`)
      .then(function(response) {
        console.log(response);
        if (response.data.status == 0) {
          //设置全局状态（支持双向绑定的全局变量）
          that.$store.commit("setUser", {
            //用户信息
            username: un,
            sid: sid,
            rank: response.data.data.rank
          });
        }
      })
      .catch(function(error) {
        console.log(error);
      });
  }
};
</script>

<style scoped>

.header{

        height:70px;
        }
       

#logo {
  float: left;
  /* margin-left: 3%; */
}
#logo img {
  width: 192px;
  height: 96px;
  vertical-align: middle;
}


#avatar {
  
  float: right;
 
}
#clear {
  clear: both;
}
.header {
  margin-bottom: 25px;
}


</style>
