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

      <el-form-item label="指定教练" prop="coachId">
        <el-input
          v-model="queryParams.coachId"
          placeholder="请输入指定教练"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="loading" :data="TfitnessList">
      <!-- 封面列（左侧1/3宽度） -->
      <el-table-column label="健身项目" width="300">
        <template slot-scope="scope">
          <div class="cover-container">
            <img
              :src="scope.row.cover"
              class="cover-image"
              v-if="scope.row.cover"
            />
            <div v-else class="no-cover">无封面</div>
          </div>
        </template>
      </el-table-column>
      <!-- 右侧内容列 -->
      <el-table-column>
        <template slot-scope="scope">
          <div class="content-wrapper">
            <!-- 修改router-link的参数传递方式 -->
            <router-link
              :to="{
    path: '/system/Tmanagement/detail',
    query: { id: scope.row.tutorialId }
  }"
              class="tutorial-title-link"
            >
              <h3 class="tutorial-title">{{ scope.row.title }}</h3>
            </router-link>

            <!-- 简介区域（中间） -->
            <div class="description">{{ scope.row.description }}</div>

            <!-- 创建日期（底部右侧） -->
            <div class="meta-info">
              <!-- 其他元信息（如果有） -->
              <div class="date-wrapper">
                <i class="el-icon-time date-icon"></i>
                <span class="create-date">{{ scope.row.createTime }}</span>
              </div>
            </div>
          </div>
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
  </div>
</template>

<script>
import { listTfitness, getTfitness, delTfitness, addTfitness, updateTfitness } from "@/api/fitness/Tfitness";

export default {
  name: "Tfitness",
  data() {
    return {
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
      // 健身教程表格数据
      TfitnessList: [],
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
        status: 1,
        updateId: null,
        viewNumber: null,
        price: null,
        costId: null,
        collectionNumber: null,
        categoryId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询健身教程列表 */
    getList() {
      this.loading = true;
      listTfitness(this.queryParams).then(response => {
        this.TfitnessList = response.rows;
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
      this.handleQuery();
    },

  }
};
</script>
<style scoped>
/* 封面容器样式 */
.cover-container {
  position: relative;
  width: 100%;
  height: 200px;
  border-radius: 8px;
  overflow: hidden;
  background: #f5f7fa;
}

/* 封面图片样式 */
.cover-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.3s ease;
}

/* 无封面提示样式 */
.no-cover {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #909399;
  font-size: 14px;
}

/* 新增标题样式 */
.tutorial-title {
  margin: 0 0 12px 0;
  font-size: 18px;
  font-weight: 600;
  color: #303133;
  line-height: 1.4;
  /* 保持文本换行 */
  word-break: break-word;
}
/* 新增链接样式 */
.tutorial-title-link {
  text-decoration: none; /* 去除下划线 */
  color: inherit; /* 继承父元素颜色 */
  display: block; /* 使整个区域可点击 */
}

/* 修改标题hover效果 */
.tutorial-title-link:hover .tutorial-title {
  color: #409eff; /* Element主色 */
  text-decoration: underline; /* 悬停下划线 */
}
/* 标题文字样式 */
.tutorial-title {
  font-size: 32px;
  font-weight: 600;
  color: black;
  text-shadow: 0 1px 2px rgba(1, 1, 1, 0.5);
}

/* 修改后的内容容器样式 */
.content-wrapper {
  display: flex;
  flex-direction: column;
  height: 200px;
  padding: 16px 20px;
  position: relative;
}

/* 调整简介区域 */
.description {
  flex: 1;
  margin-bottom: 8px;
  font-size: 14px;
  line-height: 1.6;
  color: #606266;
  overflow: hidden;
  /* 保持3行显示 */
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}

/* 调整元信息容器为flex布局 */
.meta-info {
  margin-top: auto;
  display: flex;
  justify-content: space-between; /* 左右分布 */
  align-items: center;
  width: 100%;
  padding-top: 12px;
  border-top: 1px solid #ebeef5; /* 添加分隔线 */
}
/* 新增右侧日期样式 */
.date-wrapper {
  margin-left: auto; /* 推到右侧 */
  display: flex;
  align-items: center;
  gap: 8px;
}
/* 调整创建日期样式 */
.create-date {
  background: rgba(245, 247, 250, 0.8);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  color: #909399;
}
/* 可选：添加时间图标 */
.date-icon {
  color: #c0c4cc;
  font-size: 14px;
}
</style>
