<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="教练" prop="coachId">
        <el-select
          v-model="queryParams.coachId"
          placeholder="请选择教练"
          clearable
          @keyup.enter.native="handleQuery"
        style="width: 200px"
        >
        <el-option
          v-for="item in coachOptions"
          :key="item.userId"
          :label="item.userName"
          :value="item.userId"
        />
        </el-select>
      </el-form-item>


<!--      <el-form-item label="价格" prop="price">-->
<!--        <el-input-->
<!--          v-model="queryParams.price"-->
<!--          placeholder="请输入价格"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->



      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:Tmanagement:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:Tmanagement:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:Tmanagement:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:Tmanagement:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-upload2"
          size="mini"
          :disabled="multiple"
          @click="handleShelf"
          v-hasPermi="['system:Tmanagement:shelf']"
        >上架</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-bottom"
          size="mini"
          :disabled="multiple"
          @click="handleUnshelf"
          v-hasPermi="['system:Tmanagement:unshelf']"
        >下架</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="TmanagementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="排序" align="center" prop="tutorialId" />
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="简介" align="center" prop="description" show-overflow-tooltip/>
      <el-table-column label="封面" align="center" prop="cover">
        <template slot-scope="scope">
          <img :src="scope.row.cover" class="table-avatar" v-if="scope.row.cover" />
          <span v-else>无封面</span>
        </template>
      </el-table-column>
      <el-table-column label="教练" align="center" prop="coachName">
        <template slot-scope="scope">
          {{ findCoachName(scope.row.coachId) }}
        </template>
      </el-table-column>
      <el-table-column label="内容" align="center" prop="content" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="handleDetail(scope.row)"
          >查看详情</el-button>
        </template>
      </el-table-column>>
      <el-table-column
        label="状态"
        align="center"
        prop="status"
        :formatter="statusFormatter"
      />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:Tmanagement:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:Tmanagement:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改教程管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="简介" prop="description">
          <el-input v-model="form.description" placeholder="请输入简介" />
        </el-form-item>
        <el-form-item label="封面" prop="cover">
          <el-upload
            class="avatar-uploader"
            :action="uploadUrl"
          :show-file-list="false"
          :on-success="handleCoverSuccess"
          :before-upload="beforeCoverUpload"
          :headers="uploadHeaders"
          >
          <img v-if="form.cover" :src="form.cover" class="avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <!-- 修改教练选择下拉框部分 -->
        <template>
          <el-form-item label="选择教练" prop="coachId">
            <el-select
              v-model="form.coachId"
              placeholder="请选择教练"
              clearable
              @change="handleCoachChange"
            >
              <el-option
                v-for="item in coachOptions"
                :key="item.userId"
                :label="item.userName"
                :value="item.userId"
              />
            </el-select>
          </el-form-item>
        </template>
        <el-form-item label="内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTmanagement, getTmanagement, delTmanagement, addTmanagement, updateTmanagement,shelfTmanagement, unshelfTmanagement} from "@/api/system/Tmanagement";
import { getToken } from '@/utils/auth'
import { listCoaches } from "@/api/system/user";
export default {
  name: "Tmanagement",
  data() {
    return {
      // 新增教练选项数据
      coachOptions: [],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 教程管理表格数据
      TmanagementList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        description: null,
        content: null,
        coachId: null,
        cover: null,
        status: null ,
        updateId: null,
        viewNumber: null,
        price: null,
        costId: null,
        collectionNumber: null,
        categoryId: null
      },
      // 表单参数
      form: {
        coachId: null
      },
      // 修改表单校验规则
      rules: {
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        coachId: [
          { required: true, message: "请选择教练", trigger: "change" }
        ]
      },
      uploadUrl: process.env.VUE_APP_BASE_API + "/common/upload", // 动态拼接接口地址
      uploadHeaders: {  // 上传请求头（携带token）
        Authorization: "Bearer " + getToken()
      }
    };
  },
  created() {
    this.getCoachList().then(() => {
      this.getList();
    });
  },
  methods: {
// 新增方法：根据教练ID查找姓名
    findCoachName(coachId) {
      const coach = this.coachOptions.find(item => item.userId === coachId);
      return coach ? coach.userName : '未知教练';
    },
    /** 获取教练列表 */
    // 修改后的获取教练方法
    getCoachList() {
      return new Promise((resolve, reject) => {
        listCoaches().then(response => {
          this.coachOptions = response.data;
          resolve();
        }).catch(error => reject(error));
      });
    },
    handleCoachChange(val) {
      this.form.coachId = val;
    }
,
    statusFormatter(row, column, cellValue) {
      // 处理数据库null值
      const status = cellValue === null ? 0 : cellValue;
      // 状态映射表（可根据实际业务扩展）
      const statusMap = {
        0: '未上架',
        1: '已上架',
        2: '已下架'
      };

      return statusMap[status] || '未知状态';
    },
    /** 上架操作 */
    handleShelf() {
      if (this.ids.length === 0) {
        this.$message.warning("请先选择要上架的教程");
        return;
      }
      this.$modal.confirm("确定要将选中的教程上架吗？").then(() => {
        // 调用批量上架接口
        shelfTmanagement(this.ids, 1).then(() => {
          this.getList();
          this.$modal.msgSuccess("上架成功");
        });
      });
    },
    /** 下架操作 */
    handleUnshelf() {
      if (this.ids.length === 0) {
        this.$message.warning("请先选择要下架的教程");
        return;
      }
      this.$modal.confirm("确定要将选中的教程下架吗？").then(() => {
        // 调用批量下架接口
        unshelfTmanagement(this.ids, 2).then(() => {
          this.getList();
          this.$modal.msgSuccess("下架成功");
        });
      });
    },
    handleDetail(row) {
      this.$router.push({
        path: '/system/Tmanagement/detail',
        query: {
          id: row.tutorialId,
          title: encodeURIComponent(row.title) // 可选：传递标题用于页面显示
        }
      })
    },
    // 处理上传成功
    handleCoverSuccess(res) {
      this.form.cover = process.env.VUE_APP_BASE_API + res.fileName;
      this.$forceUpdate(); // 强制更新视图
    },
    // 上传前校验
    beforeCoverUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isPNG = file.type === 'image/png';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG && !isPNG) {
        this.$message.error('上传封面只能是 JPG/PNG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传封面大小不能超过 2MB!');
      }
      return (isJPG || isPNG) && isLt2M;
    },
    /** 查询教程管理列表 */
    getList() {
      this.loading = true;
      listTmanagement(this.queryParams).then(response => {
        this.TmanagementList = response.rows.map(item => ({
          ...item,
          // 添加映射字段
          coachName: this.findCoachName(item.coachId)
        }));
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        tutorialId: null,
        title: null,
        description: null,
        content: null,
        coachId: null,
        createTime: null,
        cover: null,
        status: null,
        updateTime: null,
        updateId: null,
        viewNumber: null,
        price: null,
        costId: null,
        collectionNumber: null,
        categoryId: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.queryParams.coachId = null;  // 增加重置教练选择
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.tutorialId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加教程管理";
      console.log("弹窗状态:", this.open); // 调试输出
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const tutorialId = row.tutorialId || this.ids
      getTmanagement(tutorialId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改教程管理";
      });
    },
    // 修改提交表单方法
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.tutorialId != null) {
            updateTmanagement(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTmanagement(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const tutorialIds = row.tutorialId || this.ids;
      this.$modal.confirm('是否确认删除教程管理编号为"' + tutorialIds + '"的数据项？').then(function() {
        return delTmanagement(tutorialIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/Tmanagement/export', {
        ...this.queryParams
      }, `Tmanagement_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.table-avatar {
  width: 40px;
  height: 40px;
  object-fit: cover;
}
</style>
