<template>
  <el-container class="home-container">
    <el-header class="header">
      <div>
        <span>XDD基金管理平台</span>
      </div>
      <div>
        <i class="header-text">尊敬的{{userName}}, 欢迎登陆</i>
        <el-button @click="logout">退出</el-button>
      </div>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <nav-menu></nav-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import NavMenu from '@/components/NavMenu'

export default {
  name: 'layout',
  components: {
    NavMenu
  },
  data () {
    return {
      userName: JSON.parse(window.localStorage.getItem('user')).userName
    }
  },
  methods: {
    logout () {
      window.localStorage.removeItem('user')
      this.$router.push('/login')
    }
  }
}
</script>

<style lang="less" scoped>
.home-container {
  height: 100%;
}
.el-header {
  background-color: #409eff;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #fff;
  font-size: 20px;
  > div {
    display: flex;
    align-items: center;
    span {
      margin-left: 15px;
    }
  }
  .header-text{
    font-size: 15px;
    margin-right: 10px;
  }
}
.el-aside {
  .el-menu {
    border-right: none;
  }
}
.el-main {
  background-color: #eaedf1;
}
</style>
