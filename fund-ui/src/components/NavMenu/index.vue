<template>
  <el-row>
    <el-col :span="24">
      <el-menu router unique-opened :default-active="activePath">
        <el-menu-item :index="menu.path" v-for="(menu, index) in menuList[0].children" :key="index">
          <i :class="'iconfont '+menu.icon"></i>
          <span slot="title">{{menu.name}}</span>
        </el-menu-item>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: 'NavMenu',
  data () {
    return {
      menuList: [],
      activePath: ''
    }
  },
  methods: {},
  created () {
    this.menuList = this.$router.options.routes
    this.activePath = this.$router.currentRoute.path
  }
}
</script>

<style lang="less" scoped>
.iconfont {
  margin-right: 10px;
}
</style>
