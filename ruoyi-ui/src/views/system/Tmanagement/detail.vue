<template>
  <div class="app-container">
    <el-page-header @back="goBack" content="教程详情">
    </el-page-header>

    <el-card shadow="never" class="mt-3">
      <div v-loading="loading" element-loading-text="加载中...">
        <!-- 标题 -->
        <h2 class="text-center mb-4" v-if="form.title">{{ form.title }}</h2>
        <div v-else class="text-center mb-4 text-gray-500">无标题</div>

        <!-- 封面 -->
        <div class="text-center mb-4">
          <el-image
            v-if="form.cover"
            :src="form.cover"
            :preview-src-list="[form.cover]"
            style="max-width: 300px; max-height: 300px; border-radius: 8px;"
            fit="cover"
          >
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
              <div class="text-gray-500">封面加载失败</div>
            </div>
          </el-image>
          <div v-else class="text-gray-500">无封面图片</div>
        </div>

        <!-- 富文本内容 -->
        <div v-if="form.content" class="content-box" v-html="form.content"></div>
        <el-empty v-else description="暂无内容" class="mt-4"></el-empty>
      </div>
    </el-card>
  </div>
</template>

<script>
import { getTfitness } from "@/api/fitness/Tfitness"; // 确认接口路径正确

export default {
  name: "TmanagementDetail",
  data() {
    return {
      loading: true,
      form: {
        title: "",
        content: "",
        cover: ""
      }
    };
  },
  created() {
    this.loadDetail();

  },


methods: {
    async loadDetail() {
      try {
        // 获取路由参数
        const tutorialId = this.$route.query.id;

        // 参数校验
        if (!tutorialId) {
          throw new Error('缺少必要参数');
        }

        // 调用API
        const response = await getTfitness(tutorialId);

        // 数据校验
        if (!response?.data?.tutorialId) {
          throw new Error('数据格式异常');
        }

        this.form = {
          ...response.data,
          content: this.sanitizeContent(response.data.content) // 安全过滤
        };
      } catch (error) {
        console.error('加载详情失败:', error);
        this.$message({
          message: error.message || '加载详情失败',
          type: 'error',
          duration: 3000
        });
        // 错误时自动返回列表页
        setTimeout(() => this.goBack(), 3000);
      } finally {
        this.loading = false;
      }
    },
    // 内容安全过滤（根据项目需求添加）
    sanitizeContent(content) {
      if (!content) return '';
      // 这里可以添加XSS过滤逻辑
      return content;
    },
    goBack() {
      this.$router.push({
        path: '/system/Tmanagement',
        query: { ...this.$route.query, refresh: Date.now() } // 返回时带刷新参数
      });
    }
  }
};
</script>

<style scoped>
.content-box {
  padding: 20px;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  background: #fff;
  line-height: 1.8;
}

.content-box /deep/ img {
  max-width: 100% !important;
  height: auto !important;
  margin: 10px 0;
  border-radius: 4px;
}

.text-gray-500 {
  color: #909399;
}

.image-slot {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
  font-size: 14px;
}
</style>
