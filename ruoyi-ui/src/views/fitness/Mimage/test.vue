<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="视频名称" prop="fileName">
        <el-input
          v-model="queryParams.fileName"
          placeholder="请输入视频名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
          v-hasPermi="['fitness:Mvideo:add']"
        >新增</el-button>
      </el-col>

      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="danger"-->
      <!--          plain-->
      <!--          icon="el-icon-delete"-->
      <!--          size="mini"-->
      <!--          :disabled="multiple"-->
      <!--          @click="handleDelete"-->
      <!--          v-hasPermi="['fitness:Mimage:remove']"-->
      <!--        >删除</el-button>-->
      <!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 卡片式布局 -->
    <div v-loading="loading" class="card-container">
      <el-row :gutter="30">
        <el-col
          v-for="(item, index) in MvideoList"
          :key="index"
          :xs="24" :sm="12" :md="8" :lg="4" :xl="4"
          class="card-col"
        >
          <div class="image-card" @mouseenter="hoverIndex = index" @mouseleave="hoverIndex = -1" @click="handlePreview(item)">
            <!-- 封面图片 -->
            <div class="card-thumb">
              <video
                :src="item.storagePath"
                class="card-image"
                :alt="item.fileName"
                v-if="item.storagePath"
              />
              <div v-else class="empty-cover">无视频</div>

              <!-- 操作按钮（hover显示） -->
              <div class="card-actions" v-show="hoverIndex === index">
                <el-button
                  type="primary"
                  icon="el-icon-edit"
                  circle
                  size="mini"
                  @click.stop="handleUpdate(item)"
                  v-hasPermi="['fitness:Mvideo:edit']"
                ></el-button>
                <el-button
                  type="danger"
                  icon="el-icon-delete"
                  circle
                  size="mini"
                  @click.stop="handleDelete(item)"
                  v-hasPermi="['fitness:Mvideo:remove']"
                ></el-button>
              </div>
            </div>

            <!-- 文件名 -->
            <div class="card-footer">
              <el-tooltip effect="dark" :content="item.fileName" placement="top">
                <span class="filename">{{ item.fileName }}</span>
              </el-tooltip>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
    <!-- 新增视频预览对话框 -->
    <el-dialog
      :visible.sync="previewVisible"
      title="视频预览"
      width="70%"
      @close="handleClosePreview"
    >
      <video
        ref="previewVideo"
        :src="previewVideoUrl"
        controls
        style="width: 100%;"
        autoplay
      >您的浏览器不支持视频播放</video>
    </el-dialog>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!-- 添加或修改图片素材对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="原始文件名" prop="fileName">
          <el-input v-model="form.fileName" placeholder="请输入原始文件名" />
        </el-form-item>
        <el-form-item label="存储路径" prop="storagePath">
          <el-upload id="storagePath" :visible.sync="open"
                     class="avatar-uploader"
                     :action="uploadUrl"
                     :show-file-list="false"
                     :on-success="handleCoverSuccess"
                     :before-upload="beforeCoverUpload"
                     :headers="uploadHeaders"
          >
            <video v-if="form.storagePath" :src="form.storagePath" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
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
import { listMvideo, getMvideo, delMvideo, addMvideo, updateMvideo } from "@/api/fitness/Mvideo";
import { getToken } from '@/utils/auth'

export default {
  name: "Mvideo",
  data() {
    return {
      previewVisible: false,
      previewVideoUrl: '',
      hoverIndex: -1, // 当前hover的卡片索引
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
      // 图片素材表格数据
      MvideoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        fileName: null,
        storagePath: null,
        fileSize: null,
        fileType: null,
        uploadTime: null,
        uploaderId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      uploadUrl: process.env.VUE_APP_BASE_API + "/common/VUpload", // 动态拼接接口地址
      uploadHeaders: {  // 上传请求头（携带token）
        Authorization: "Bearer " + getToken()
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handlePreview(item) {
      this.previewVideoUrl = item.storagePath;
      this.previewVisible = true;
      // 自动播放处理
      this.$nextTick(() => {
        const video = this.$refs.previewVideo;
        if (video) {
          video.play().catch(error => {
            console.log('视频自动播放受阻:', error);
          });
        }
      });
    },

    // 新增关闭预览处理
    handleClosePreview() {
      const video = this.$refs.previewVideo;
      if (video) {
        video.pause();
        video.currentTime = 0;
      }
    },
    /** 查询图片素材列表 */
    getList() {
      this.loading = true;
      listMvideo(this.queryParams).then(response => {
        this.MvideoList = response.rows;
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
        videoId: null,
        fileName: null,
        storagePath: null,
        fileSize: null,
        fileType: null,
        uploadTime: null,
        uploaderId: null
      };
      this.resetForm("form");
    },
    // 处理上传成功
    handleCoverSuccess(res) {
      this.form.storagePath = process.env.VUE_APP_BASE_API + res.fileName;
      this.form.fileName = res.fileName;
      this.$forceUpdate(); // 强制更新视图
    },
    // 增强上传前校验
    beforeCoverUpload(file) {
      // 视频类型白名单
      const videoTypes = ['video/mp4', 'video/webm', 'video/ogg']
      const isVideo = videoTypes.includes(file.type)
      const isLt500M = file.size / 1024 / 1024 < 500

      // 类型校验
      if (!isVideo) {
        this.$message.error('仅支持MP4/WebM/OGG格式视频!')
        return false
      }
      // 大小校验
      if (!isLt500M) {
        this.$message.error('视频大小不能超过50MB!')
        return false
      }

      return true
    }
  ,
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.videoId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加视频素材";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const videoId = row.videoId || this.ids
      getMvideo(videoId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改视频素材";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.videoId != null) {
            updateMvideo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMvideo(this.form).then(response => {
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
      const videoIds = row.videoId || this.ids;
      this.$modal.confirm('是否确认删除视频素材编号为"' + videoIds + '"的数据项？').then(function() {
        return delMvideo(videoIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('fitness/Mvideo/export', {
        ...this.queryParams
      }, `Mvideo_${new Date().getTime()}.xlsx`)
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
.card-container {
  padding: 20px 0;
}

.card-col {
  margin-bottom: 20px;
}

.image-card {
  border: 1px solid #ebeef5;
  border-radius: 4px;
  background: #fff;
  transition: all 0.3s;
  overflow: hidden;
  cursor: pointer;
  &:hover {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    transform: translateY(-3px);
  }
}
.card-thumb {
  position: relative;
  height: 0;
  padding-bottom: 100%;
  background: #f5f7fa;

  .card-image {
    position: absolute;
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

  .empty-cover {
    position: absolute;
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #909399;
  }
  .card-actions {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    padding: 10px;
    background: linear-gradient(transparent, rgba(0, 0, 0, 0.5));
    display: flex;
    justify-content: flex-end;
    gap: 8px;
  }
}
/* 新增视频预览样式 */
.preview-video {
  width: 100%;
  max-height: 70vh;
  outline: none;
}
</style>
