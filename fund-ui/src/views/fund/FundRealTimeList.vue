<template>
  <el-card>
    <el-row :gutter="20">
      <el-col :span="3.5">
        <el-input placeholder="基金代码" v-model="params.fundCode" clearable></el-input>
      </el-col>
      <el-col :span="3.5">
        <el-input placeholder="基金名称" v-model="params.fundName" clearable></el-input>
      </el-col>
      <el-col :span="3.5">
        <el-select v-model="params.type" clearable placeholder="请选择基金类型">
          <el-option
            v-for="item in typeList"
            :key="item.type"
            :label="item.type"
            :value="item.type"
          ></el-option>
        </el-select>
      </el-col>
      <el-col :span="3.5">
        <el-button type="primary" icon="el-icon-search" @click="query"></el-button>
        <el-button type="primary" icon="el-icon-error" @click="clean"></el-button>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top:20px">
      <el-col>
        <el-button
          type="warning"
          icon="el-icon-refresh-right"
          @click="handlePushAll"
          size="small"
        >刷新全部基金数据</el-button>
      </el-col>
    </el-row>

    <el-table :data="tableData" border>
      <el-table-column type="index" width="50"></el-table-column>
      <el-table-column prop="fundCode" label="基金代码" width="100px"></el-table-column>
      <el-table-column prop="fundName" label="基金名称" min-width="120px"></el-table-column>
      <el-table-column prop="jzrq" label="净值日期"></el-table-column>
      <el-table-column prop="dwjz" label="当日净值"></el-table-column>
      <el-table-column prop="gsz" label="估算净值"></el-table-column>
      <el-table-column prop="gszzl" label="估算涨跌百分比">
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
      <el-table-column prop="gztime" label="估值时间"></el-table-column>
      <el-table-column prop="type" label="基金类型"></el-table-column>
      <el-table-column prop="createTime" label="创建时间"></el-table-column>
      <el-table-column fixed="right" label="操作" width="50">
        <template slot-scope="scope">
          <el-button @click="push(scope.row)" type="text" size="mini">刷新</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="params.current"
      :page-sizes="[15, 30, 50]"
      :page-size="params.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="params.total"
    ></el-pagination>
  </el-card>
</template>

<script>
export default {
  name: 'FundRealTimeList',
  data () {
    return {
      params: {
        pages: 0,
        total: 0,
        current: 1,
        size: 10,
        type: null,
        fundName: null,
        fundCode: null
      },
      tableData: [],
      typeList: []
    }
  },
  created () {
    this.query()
    this.getAllType()
  },
  methods: {
    async query () {
      const { data: res } = await this.$http.get('/fund/real/time/list', {
        params: {
          current: this.params.current,
          size: this.params.size,
          fundName: this.params.fundName,
          fundCode: this.params.fundCode,
          type: this.params.type
        }
      })
      this.tableData = res.records
      this.params.pages = res.pages
      this.params.total = res.total
    },
    handleSizeChange (val) {
      this.params.size = val
      this.query()
    },
    handleCurrentChange (val) {
      this.params.current = val
      this.query()
    },
    async push (row) {
      const res = await this.$http.get(`/fund/real/time/push/${row.fundCode}`)
      if (res.status !== 200) {
        this.$message.error('刷新数据失败!')
      } else {
        this.$message.success('刷新数据成功!')
        this.query()
      }
    },
    handlePushAll () {
      this.$confirm('此操作将会异步刷新基金数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.pushAll()
        })
        .catch(() => {})
    },
    async pushAll () {
      const res = await this.$http.get('/fund/real/time/push/all')
      if (res.status !== 200) {
        this.$message.error('刷新数据失败!')
      } else {
        this.$message.success('刷新数据成功!')
      }
    },
    async getAllType () {
      const res = await this.$http.get('/fund/real/time/list/all/type')
      this.typeList = res.data
    },
    clean () {
      this.params.type = null
      this.params.fundName = null
      this.params.fundCode = null
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
</style>
