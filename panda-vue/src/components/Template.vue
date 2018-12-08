<template lang="pug">
  div(style="padding-top:10px")
    el-form(ref="form" :model="form" label-width="120px" style="width:500px" @submit.native.prevent)
      el-form-item(label="表名")
        el-select(v-model="form.table" )
          el-option(v-for="item in tables" :key="item" :label="item" :value="item")
      el-form-item(label="表前缀")
        el-input(v-model="form.tablePrefix" placeholder="生成类名会忽略该前缀，不匹配时失效")
      el-form-item(label="生成选项")
        el-checkbox(v-model="form.generateVue") 前台
        el-checkbox(v-model="form.generateBackEnd") 后台
        el-checkbox(v-model="form.generateMenu") 菜单项
      el-button(type="primary" @click="onSubmit") 立即创建
</template>

<script>
  export default {
    name: "Template",
    data() {
      return {
        tables: [],
        alertSuccess: false,
        form: {
          table: "",
          tablePrefix: "",
          generateBackEnd: false,
          generateMenu: false,
          generateVue: false
        }
      }
    },
    created()  {
      this.getTables()
    },
    methods: {
      getTables: function () {
        this.axios.get("/api/database/tables")
        .then(res => {
          this.tables = res.data
        })
      },
      onSubmit: function () {
        this.axios.post("/api/template",this.form)
        .then(res => {
          this.alertSuccess = true
        })
      }
    }
  }
</script>

<style lang="stylus" scoped>

</style>