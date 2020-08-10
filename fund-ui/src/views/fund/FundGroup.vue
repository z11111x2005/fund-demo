<template>
  <el-card>
    <el-row :gutter="20">
      <el-col :span="3.5">
        <el-button
          type="primary"
          icon="el-icon-circle-plus-outline"
          @click="addGroup"
        >添加分组</el-button>
      </el-col>
    </el-row>

    <el-divider></el-divider>

    <el-row :gutter="20">
      <el-col :span="3.5">
        <el-select v-model="params.groupId" placeholder="请选择" @change="changeGroup">
          <el-option
            v-for="item in groupList"
            :key="item.groupId"
            :label="item.groupName"
            :value="item.groupId"
          ></el-option>
        </el-select>
      </el-col>
      <el-col :span="3.5" v-if="this.params.groupId">
        <el-button
          type="primary"
          icon="el-icon-circle-plus-outline"
          @click="relationFond"
        >关联基金</el-button>
        <el-button
          type="danger"
          icon="el-icon-delete-solid"
          @click="deleteGroup"
        >删除分组</el-button>
      </el-col>
    </el-row>

    <el-table :data="fundTableData" border>
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

    <fund-group-add ref="fundGroupAddRef" @close-dialog="init"></fund-group-add>
    <fund-group-relation ref="fundGroupRelationRef" @close-dialog="init"></fund-group-relation>
  </el-card>
</template>

<script>
import FundGroupAdd from '@/views/fund/FundGroupAdd'
import FundGroupRelation from '@/views/fund/FundGroupRelation'

export default {
  name: 'FundGroup',
  components: {
    FundGroupAdd,
    FundGroupRelation
  },
  data () {
    return {
      groupList: [],
      fundTableData: [],
      params: {
        groupId: null
      }
    }
  },
  created () {
    this.init()
  },
  methods: {
    init () {
      this.query()
    },
    addGroup () {
      this.$refs.fundGroupAddRef.open()
    },
    async getFundInfo (groupId) {
      const res = await this.$http.get(`/fund/real/time/list/${groupId}`)
      this.fundTableData = res.data
    },
    async query () {
      const res = await this.$http.get('/fund/group/list/me', {
        params: {
          userId: JSON.parse(window.localStorage.getItem('user')).id
        }
      })
      this.groupList = res.data
    },
    relationFond () {
      this.$refs.fundGroupRelationRef.open(this.params.groupId)
    },
    changeGroup (val) {
      this.params.groupId = val
      this.getFundInfo(this.params.groupId)
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
    async deleteGroup () {
      const res = await this.$http.post(`/fund/group/delete/${this.params.groupId}`)
      if (res.status !== 200) {
        this.$message.error('删除分组失败!')
      } else {
        this.$message.success('删除分组成功!')
        this.params.groupId = null
        this.init()
      }
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
