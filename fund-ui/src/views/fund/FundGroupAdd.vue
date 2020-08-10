<template>
  <el-dialog title="新增分组" :visible.sync="visible">
    <el-form :model="form" label-width="120px" ref="form">
      <el-form-item prop="groupName" label="分组名称" :rules="{ required: true, message: '分组名称不能为空'}">
        <el-input v-model="form.groupName" autocomplete="off" clearable></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取 消</el-button>
      <el-button type="primary" @click="submit('form')">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
export default {
  name: 'FundGroupAdd',
  data () {
    return {
      visible: false,
      form: {
        groupName: null
      }
    }
  },
  methods: {
    open () {
      this.visible = true
    },
    submit (form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          this.add()
        }
      })
    },
    async add () {
      const res = await this.$http.post('/fund/group/add', {
        groupName: this.form.groupName,
        userId: JSON.parse(window.localStorage.getItem('user')).id
      })
      if (res.status === 200) {
        this.$message.success('提交成功!')
        this.$refs.form.resetFields()
        this.$emit('close-dialog')
        this.visible = false
      } else {
        this.$message.error('提交失败!')
      }
    }
  }
}
</script>
