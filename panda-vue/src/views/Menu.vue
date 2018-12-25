<template>
  <div class="navMenu">
    <label v-for="navMenu in navMenus">
      <div v-if="navMenu.children">
        <el-menu-item v-if="navMenu.children.length == 0" :index="navMenu.code" :route="navMenu.url">
          <i class="el-icon-tickets"></i>
          <span slot="title">{{navMenu.name}}</span>
        </el-menu-item>
        <el-submenu v-if="navMenu.children.length != 0" :index="navMenu.code">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span> {{navMenu.name}}</span>
          </template>
          <NavMenu :navMenus="navMenu.children"></NavMenu>
        </el-submenu>
      </div>
    </label>
  </div>
</template>

<script>
  export default {
    name: 'NavMenu',
    props: ['navMenus'],
    data() {
      return {}
    },
    methods: {}
  }
</script>

<style scoped>

</style>