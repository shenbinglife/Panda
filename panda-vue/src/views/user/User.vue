<template lang="pug">
  el-container(style="background-color: beige !important")
    el-header(class="search-panel")
      el-container
        el-main
          div(class="base-panel")
            div
              el-button(size="small" type="primary" @click="add") 新增
              el-button(size="small" type="primary") 修改
              el-button(size="small" type="primary") 删除
            div
              el-input(style="width:200px" size="small" v-model="keyword" placeholder="帐号或姓名")
              el-button(size="small" @click="loadTable") 查询
    el-main(class="center-panel")
      el-table(ref="multipleTable" :data="tableData" border class="table-panel" @row-click="clickRow" size="small")
        el-table-column(type="selection" width="40px")
        el-table-column( prop="id" label="ID"  header-align="center" fixed v-if="false" )
        el-table-column( prop="account" label="账号"  header-align="center" fixed)
        el-table-column( prop="name" label="姓名"  header-align="center" fixed)
        el-table-column( prop="age" label="年龄"  header-align="center" fixed)
        el-table-column( prop="mobilePhone" label="手机号"  header-align="center" fixed)
        el-table-column( prop="email" label="邮箱"  header-align="center" fixed)
        el-table-column( prop="description" label="描述"  header-align="center" fixed)
        el-table-column( prop="state" label="状态"  header-align="center" fixed)
        el-table-column( prop="updateTime" label="修改时间"  header-align="center" fixed)
      el-pagination(class="pagination-panel" :page-sizes="[10, 20, 50, 100]" :page-size="pageSize"
      :current-page="page" layout="total, sizes, prev, pager, next, jumper" :total="total"
      @current-change="changePage" @size-change="changeSize")
    add-user-model(:show.sync="showAdd" )
    modify-user-model(:show.sync="showModify")


</template>

<script>
  import addUserModel from './AddUser'
  import modifyUserModel from './ModifyUser'

  export default {
    components: {addUserModel, modifyUserModel},
    name: 'User',
    data() {
      return {
        tableData: [],
        page: 1,
        pageSize: 20,
        total: 0,
        keyword: "",
        showAdd: false,
        showModify: false,
      }
    },
    mounted() {
      this.loadTable()
    },
    methods: {
      clickRow(row) {
        this.$refs.multipleTable.toggleRowSelection(row)
      },
      changeSize(size) {
        this.pageSize = size
        this.loadTable()
      },
      changePage(page) {
        this.page = page
        this.loadTable()
      },
      loadTable() {
        this.$axios.get("/api/users",
            {params: {page: this.page, pageSize: this.pageSize, name: this.keyword}})
        .then(res => {
          this.tableData = res.data.content
          this.total = res.data.totalElements
        })
      },
      add() {
        this.showAdd = true
      },
      modify() {
        this.showModify = true
      },
      delete() {

      }
    }
  }
</script>

<style lang="stylus" scoped>
  .base-panel
    padding 0px 20px 0px 20px
    height 100%
    display: flex;
    align-items: center;
    justify-content space-between

  .search-panel
    height: 50px !important

  .center-panel
    text-align center
    padding 0 20px 0 20px !important

  .table-panel
  .el-table
    line-height 24px

</style>