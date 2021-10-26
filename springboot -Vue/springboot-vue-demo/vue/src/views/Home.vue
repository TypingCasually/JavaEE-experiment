<template>
  <div style="padding: 10px">
<!--    功能区域  -->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
<!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>

    <el-table :data="tableData" border  stripe style="width: 100%">
      <el-table-column prop="uid" label="ID" sortable />
      <el-table-column prop="uname" label="姓名"  />
      <el-table-column prop="usex" label="性别" />
      <el-table-column prop="uphone" label="电话号码" />
      <el-table-column  label="Operations">
        <template #default="scope">
          <el-button size="mini"  type="primary" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row.uid)">
            <template #reference>
              <el-button size="mini" type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px 0">
    <el-pagination
        :page-sizes="[5, 10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        :currentPage="currentPage"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    >
    </el-pagination>

      <el-dialog
          v-model="dialogVisible"
          title="提示"
          width="30%"
      >
       <el-form :model="form" label-width="120px">
<!--         <el-form-item label="ID">-->
<!--           <el-input v-model="form.uid" style="width:80%"></el-input>-->
<!--         </el-form-item>-->
         <el-form-item label="姓名">
           <el-input v-model="form.uname" style="width:80%"></el-input>
         </el-form-item>
         <el-form-item label="性别">
             <el-radio v-model="form.usex" label="男">男</el-radio>
             <el-radio v-model="form.usex" label="女">女</el-radio>
             <el-radio v-model="form.usex" label="未知">未知</el-radio>
         </el-form-item>
         <el-form-item label="电话号码">
           <el-input type="textarea" v-model="form.uphone" style="width:80%"></el-input>
         </el-form-item>
       </el-form>

        <template #footer>
           <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="save">确定</el-button>
      </span>
        </template>

      </el-dialog>

  </div>
  </div>
</template>

<script>

import request from "../utils/request";
import {ElMessage} from 'element-plus';

export default {
  name: 'Home',
  components: {

  },
  data(){
    return {
      form: {},
      dialogVisible: false,
      total: 0 ,
      search:'' ,
      currentPage: 1 ,
      pageSize:10,
      tableData:[]
    }
  },

  created() {
    this.load()
  },

  methods:{

    load(){
      request.get("/api/user",{
        params:{
          pageNum: this.currentPage,
          pageSize:this.pageSize,
          search:  this.search
        }
      }).then(res=>{
        console.log(res);
        this.tableData=res.data.records
        this.total=res.data.total
      })
    },
    save(){
      if(this.form.uid){
          request.put("/api/user",this.form).then(res=>{
            console.log(res)
            if(res.code === '0') {
              ElMessage({
                type: "success",
                message: "更新成功"
              })
              this.load();
              this.dialogVisible=false
            } else {
              ElMessage({
                type: "error",
                message: res.msg
              })
            }
          })
      }else {
        request.post('/api/user',this.form).then(res =>{
          console.log(res);
          if(res.code === '0') {
            ElMessage({
              type: "success",
              message: "新增成功"
            })
            this.load();
            this.dialogVisible=false
          } else {
            ElMessage({
              type: "error",
              message: res.msg
            })
          }
        })
      }
    },

    add(){
        this.dialogVisible=true
        this.form={}
    },
    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible=true
    },

    handleDelete(uid){
      console.log(uid)
       request.delete("/api/user/" + uid).then(res=>{
         if(res.code === '0') {
           ElMessage({
             type: "success",
             message: "删除成功"
           })
           this.load();
           this.dialogVisible=false
         } else {
           ElMessage({
             type: "error",
             message: res.msg
           })
           this.load();
         }
       })
    },

    handleSizeChange(pageSize){
      this.pageSize=pageSize;
      this.load();
    },
    handleCurrentChange(pageNum){
      this.currentPage=pageNum;
      this.load();
    }
  }

}
</script>
