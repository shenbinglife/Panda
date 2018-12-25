<template lang="pug">
  el-container
    el-header(class="app-header")
      // TODO 保证title在html中间
      div(class="app-title") 自管理平台
      div(class="app-user") xx
    el-container
      el-aside(class="menu_container")
        el-menu(default-active=0 class="el-menu-vertical-demo" background-color="#F0F6F6" text-color="#3C3F41" active-text-color="black" router=true)
          Menu(:navMenus="leftMenus")
      el-container
        el-main(class="main-panel")
          router-view
        el-footer(class="app-footer") powered by shenbinglife@163.com
</template>

<script>
  import Menu from "./views/Menu"
  export default {
    components: {Menu},
    data() {
      return {
        menuSelected: 0,
        leftMenus: []
      }
    },
    mounted() {
      this.loadMenus()
    },
    methods: {
      loadMenus() {
        this.$axios.get('/api/menus/tree')
        .then(res => {
          this.leftMenus = res.data
        });
      }
    }
  }

</script>
<style lang="stylus" scoped>
  .menu_container
    width 20% !important
    min-width 100px !important
    max-width 300px

  .app-footer
    height 30px !important
    font-size 12px !important
    line-height 30px !important
    background-color: white !important

  .app-header
    display:flex
    height 60px !important
    font-size 22px !important
    line-height 60px !important
    background-color: white !important
    text-align center !important

  .app-title
    flex 1

  .app-user
    color aquamarine
    width 200px

</style>