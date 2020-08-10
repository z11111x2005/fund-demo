<template>
  <el-row :gutter="32">
    <el-col :span="12">
      <el-card>
        <div slot="header" class="clearfix">
          <span>基金涨幅TOP10</span>
        </div>
        <el-table :data="gszzlTop10Data" border>
          <el-table-column type="index" width="50"></el-table-column>
          <el-table-column prop="fundCode" label="基金代码" width="100px"></el-table-column>
          <el-table-column prop="fundName" label="基金名称" min-width="150px"></el-table-column>
          <el-table-column prop="gszzl" label="估算涨跌百分比" width="150px">
            <template slot-scope="scope">
              <div v-if="scope.row.gszzl>0" class="add">
                <i class="el-icon-caret-top"></i>
                <span>{{scope.row.gszzl}}</span>
              </div>
              <div v-else class="reduce">
                <i class="el-icon-caret-bottom"></i>
                <span>{{scope.row.gszzl}}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="gztime" label="估值时间" width="180px"></el-table-column>
        </el-table>
      </el-card>
    </el-col>

    <el-col :span="12">
      <el-card>
        <div slot="header" class="clearfix">
          <span>当日净值TOP10</span>
        </div>
        <el-table :data="dwjzTop10Data" border>
          <el-table-column type="index" width="50"></el-table-column>
          <el-table-column prop="fundCode" label="基金代码" width="100px"></el-table-column>
          <el-table-column prop="fundName" label="基金名称" min-width="150px"></el-table-column>
          <el-table-column prop="dwjz" label="当日净值" width="100px"></el-table-column>
          <el-table-column prop="gsz" label="估算净值" width="100px"></el-table-column>
          <el-table-column prop="gztime" label="估值时间" width="180px"></el-table-column>
        </el-table>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: 'index',
  data () {
    return {
      gszzlTop10Data: [],
      dwjzTop10Data: []
    }
  },
  created () {
    this.queryGszzlTop10()
    this.queryDwjzTop10()
  },
  methods: {
    async queryGszzlTop10 () {
      const { data: res } = await this.$http.get('/fund/real/time/gszzl/top10')
      this.gszzlTop10Data = res
    },
    async queryDwjzTop10 () {
      const { data: res } = await this.$http.get('/fund/real/time/dwjz/top10')
      this.dwjzTop10Data = res
    }
  }
}
</script>

<style lang="less" scoped>
.add {
  color: red;
}
.reduce {
  color: green;
}
.el-table {
  margin-top: 10px;
}
.card-btn{
  float: right;
  padding: 3px 0;
}
</style>
