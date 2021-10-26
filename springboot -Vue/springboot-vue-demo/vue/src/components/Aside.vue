<template>
<div>
        <el-menu
            style="width:200px;min-height:calc(100vh - 50px)"
            default-active="user"
            class="el-menu-vertical-demo"
            >

          <el-sub-menu index="1">
            <template #title>
              <i class="el-icon-location"></i>
              <span>系统管理</span>
              <el-menu-item index="user">用户管理</el-menu-item>
              <el-menu-item index="goods">商品管理</el-menu-item>
              <el-menu-item index="order">订单管理</el-menu-item>
            </template>
          </el-sub-menu>
        </el-menu>
</div>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>

</style>