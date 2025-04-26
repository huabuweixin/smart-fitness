<template>
  <div class="app-container">
    <!-- 搜索区域 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文件名" prop="fileName">
        <el-input
          v-model="queryParams.fileName"
          placeholder="请输入文件名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作按钮区域 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-upload
          class="upload-demo"
          action="image/image/upload"
          :data="{ file: file }"
          :headers="headers"
          :on-success="handleUploadSuccess"
          :before-upload="beforeUpload"
          :show-file-list="false"
        >
          <el-button
            type="primary"
            plain
            icon="el-icon-upload"
            size="mini"
            v-hasPermi="['image:image:add']"
          >上传</el-button>
        </el-upload>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['image:image:edit']"
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
          v-hasPermi="['image:image:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['image:image:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 数据表格 -->
    <el-table v-loading="loading" :data="imageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="图片ID" align="center" prop="imageId" />
      <el-table-column label="文件名" align="center" prop="fileName" />
      <el-table-column label="文件类型" align="center" prop="fileType" />
      <el-table-column label="上传时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="200">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-download"
            @click="handleDownload(scope.row)"
            v-hasPermi="['image:image:query']"
          >下载</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['image:image:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['image:image:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 修改元数据对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文件名" prop="fileName">
          <el-input v-model="form.fileName" placeholder="请输入文件名" />
        </el-form-item>
        <el-form-item label="文件类型" prop="fileType">
          <el-input v-model="form.fileType" :disabled="true" />
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
import { listImage, getImage, delImage, updateImage } from "@/api/image/image";
import { getToken } from "@/utils/auth";

export default {
  name: "Image",
  data() {
    return {
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      showSearch: true,
      total: 0,
      imageList: [],
      title: "",
      open: false,
      headers: { Authorization: "Bearer " + getToken() },
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        fileName: null
      },
      form: {},
      rules: {
        fileName: [
          { required: true, message: "文件名不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      listImage(this.queryParams).then(response => {
        this.imageList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    beforeUpload(file) {
      const isImage = file.type.startsWith("image/");
      const isLt10M = file.size / 1024 / 1024 < 10;

      if (!isImage) {
        this.$message.error("只能上传图片文件!");
      }
      if (!isLt10M) {
        this.$message.error("图片大小不能超过10MB!");
      }
      return isImage && isLt10M;
    },

    handleUploadSuccess(response) {
      if (response.code === 200) {
        this.$message.success("上传成功");
        this.getList();
      } else {
        this.$message.error(response.msg);
      }
    },

    handleDownload(row) {
      window.open(`image/image/download/${row.imageId}?Authorization=Bearer ${getToken()}`);
    },

    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },

    resetQuery() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        fileName: null
      };
      this.resetForm("queryForm");
      this.handleQuery();
    },

    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.imageId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },

    handleUpdate(row) {
      this.reset();
      const imageId = row.imageId || this.ids[0];
      getImage(imageId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改图片信息";
      });
    },

    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          updateImage(this.form).then(response => {
            this.$message.success("修改成功");
            this.open = false;
            this.getList();
          });
        }
      });
    },

    cancel() {
      this.open = false;
      this.reset();
    },

    reset() {
      this.form = {
        imageId: null,
        fileName: null,
        fileType: null
      };
      this.resetForm("form");
    },

    handleDelete(row) {
      const ids = row.imageId || this.ids;
      this.$confirm('是否确认删除所选图片?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        delImage(ids).then(response => {
          if (response.code === 200) {
            this.$message.success("删除成功");
            this.getList();
          }
        });
      });
    },

    handleExport() {
      // 导出逻辑需要根据实际接口实现
      this.$message.info("导出功能需要根据实际接口实现");
    }
  }
};
</script>

<style scoped>
.upload-demo {
  display: inline-block;
}
</style>
