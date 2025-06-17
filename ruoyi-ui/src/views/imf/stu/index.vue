<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" label-width="68px">
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户账号" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户账号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户昵称" prop="nickName">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入用户昵称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户邮箱" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入用户邮箱"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号码" prop="phonenumber">
        <el-input
          v-model="queryParams.phonenumber"
          placeholder="请输入手机号码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="filteredUserList">
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="用户账号" align="center" prop="userName" />
      <el-table-column label="用户昵称" align="center" prop="nickName" />
      <el-table-column label="用户邮箱" align="center" prop="email" />
      <el-table-column label="手机号码" align="center" prop="phonenumber" />
      <el-table-column label="用户性别" align="center" prop="sex" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listUser } from "@/api/system/user";
import { listRole } from "@/api/system/role"; // 导入获取角色关联的API

export default {
  name: "User",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
      // 用户信息表格数据
      userList: [],
      // 角色ID为100的用户ID集合
      userIdsWithRole100: new Set(),
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        userName: null,
        nickName: null,
        userType: null,
        email: null,
        phonenumber: null,
        sex: null,
        status: null
      }
    };
  },
  computed: {
    // 过滤出角色ID为100的用户
    filteredUserList() {
      // 如果还没有角色数据，显示加载中的用户列表
      if (this.userIdsWithRole100.size === 0) {
        return this.userList;
      }
      
      // 过滤用户列表
      return this.userList.filter(user => 
        this.userIdsWithRole100.has(user.userId)
      );
    }
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用户信息列表 */
    async getList() {
      this.loading = true;
      try {
        // 1. 获取角色ID为100的用户ID集合
        await this.getRole100UserIds();
        
        // 2. 获取用户列表
        const userResponse = await listUser(this.queryParams);
        this.userList = userResponse.rows;
        this.total = userResponse.total;
        
      } catch (error) {
        console.error("获取数据失败:", error);
      } finally {
        this.loading = false;
      }
    },
    
    /** 获取角色ID为100的用户ID集合 */
    async getRole100UserIds() {
      try {
        // 重置用户ID集合
        this.userIdsWithRole100 = new Set();
        
        // 调用角色关联API，获取所有角色ID为100的记录
        const roleResponse = await listRole({
          roleId: 100, // 只查询角色ID为100的记录
          pageNum: 1,
          pageSize: 10000 // 获取所有符合条件的角色关联数据
        });
        
        // 将用户ID添加到集合中
        roleResponse.rows.forEach(role => {
          this.userIdsWithRole100.add(role.userId);
        });
        
      } catch (error) {
        console.error("获取角色关联失败:", error);
      }
    },
    
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    }
  }
};
</script>