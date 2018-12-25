<template lang="pug">
  el-dialog(title="创建用户" :visible.sync="showAdd" :close-on-click-modal="false" :show-close="false" width="400px")
    el-form(:model="form" label-width="80px")
      el-form-item(label="帐号" size="small")
        el-input(v-model="form.account")
      el-form-item(label="姓名" size="small")
        el-input(v-model="form.name")
      el-form-item(label="密码" size="small")
        el-input(v-model="form.password")
      el-form-item(label="确认密码" size="small")
        el-input(v-model="form.confirmPwd")
      el-form-item(label="手机号" size="small")
        el-input(v-model="form.mobilePhone")
      el-form-item(label="邮箱" size="small")
        el-input(v-model="form.email")
      el-button(type="primary" @click="doSubmit" size="small") 提交
      el-button(@click="cancel" size="small") 取消
</template>