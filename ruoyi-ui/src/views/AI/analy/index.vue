<template>
  <div class="AI分析">
    <!-- 模式选择 -->
    <el-radio-group v-model="mode" class="mode-selector">
      <el-radio label="beginner">新手模式</el-radio>
      <el-radio label="pro">进阶模式</el-radio>
    </el-radio-group>

    <!-- 上传区域 -->
    <el-upload
      class="upload-container"
      :drag="true"
      action="http://localhost:81/AI/AI-Anly"
      :show-file-list="false"
      :data="{ 
        SavePath: Path.url,
        mode: mode,      
        userId: userId || null   
      }"
      :on-success="handleVideoSuccess"
    >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
    </el-upload>

    <!-- 独立视频播放容器 -->
    <div class="video-wrapper">
      <div class="video-container">
        <video
          v-if="processedVideoUrl"
          ref="videoPlayer"
          controls
          @click="handleVideoClick"
        >
          <source :src="processedVideoUrl" type="video/mp4">
        </video>

        <div v-if="videoError" class="video-error">
          <i class="el-icon-warning"></i>
          {{ errorMessage }}
          <el-button type="text" @click="retryLoadVideo">重试</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import store from "@/store";
export default {
  name: 'AI分析',
  userId: store.state.user.id,
  data() {
    return {
      mode: 'beginner',
      processedVideoUrl: '',
      videoError: false,
      errorMessage: '',
      Path: {
        url: 'E:/smart-fitness/ai-fitness-trainer-using-mediapipe/shared_storage/upload'
      }
    }
  },
  methods: {
    async handleVideoSuccess(res) {
      try {
        if (res.resCode === '200' && res.processedName) {
            const filename = res.processedName;
             this.processedVideoUrl = `http://localhost:82/${filename}`;

          // 手动加载视频
          await this.$nextTick();
          const video = this.$refs.videoPlayer;
          video.load();
          
          // 用户交互后自动播放
          video.oncanplay = () => video.play().catch(() => {});
        }
      } catch (error) {
        this.showError('视频加载失败，请检查文件格式');
      }
    },

    handleVideoClick() {
      // 点击视频区域时尝试播放
      const video = this.$refs.videoPlayer;
      if (video.paused) video.play().catch(() => {});
    },

    showError(msg) {
      this.videoError = true;
      this.errorMessage = msg;
    },

    retryLoadVideo() {
      this.videoError = false;
      this.processedVideoUrl += `&retry=${Date.now()}`;
    }
  }
}
</script>

<style scoped>
/* 整体布局 */
.AI分析 {
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;
  padding: 30px 20px;
}

/* 模式选择样式 */
.mode-selector {
  margin-bottom: 30px;
}

/* 上传容器样式 */
.upload-container {
  width: 100%;
  max-width: 600px;
  padding: 20px;
  border: 2px dashed #dcdfe6;
  border-radius: 8px;
  transition: border-color 0.3s;
}

.upload-container:hover {
  border-color: #409EFF;
}

/* 视频外层容器 */
.video-wrapper {
  width: 100%;
  max-width: 800px;
  margin-top: 40px;
}

/* 视频容器 */
.video-container {
  position: relative;
  background: #000;
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
  aspect-ratio: 16/9; /* 保持16:9比例 */
}

video {
  width: 100%;
  height: 100%;
  outline: none;
}

/* 错误提示 */
.video-error {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 20px 30px;
  background: rgba(254, 240, 240, 0.95);
  border-radius: 8px;
  color: #f56c6c;
  display: flex;
  align-items: center;
  gap: 15px;
  font-size: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

/* 上传文字样式 */
.el-upload__text {
  margin: 15px 0;
  color: #606266;
  font-size: 14px;
}

.el-upload__text em {
  color: #409EFF;
  font-style: normal;
  text-decoration: underline;
}
</style>