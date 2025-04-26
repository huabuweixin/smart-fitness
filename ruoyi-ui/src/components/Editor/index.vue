<template>
  <div>
    <!-- 图片上传组件 -->
    <el-upload
      :action="uploadUrl"
      :before-upload="handleBeforeUpload"
      :on-success="handleUploadSuccess"
      :on-error="handleUploadError"
      name="file"
      :show-file-list="false"
      :headers="headers"
      style="display: none"
      ref="imageUpload"
      v-if="type === 'url'"
    >
    </el-upload>

    <!-- 视频上传组件 -->
    <el-upload
      :action="uploadUrl"
      :before-upload="handleBeforeVideoUpload"
      :on-success="handleVideoUploadSuccess"
      :on-error="handleUploadError"
      name="file"
      :show-file-list="false"
      :headers="headers"
      style="display: none"
      ref="videoUpload"
      v-if="type === 'url'"
    >
    </el-upload>

    <div class="editor" ref="editor" :style="styles"></div>
  </div>
</template>

<script>
import Quill from "quill";
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import { getToken } from "@/utils/auth";
// 自定义视频Blot（新增部分）
const BlockEmbed = Quill.import('blots/block/embed');

class VideoBlot extends BlockEmbed {
  static create(url) {
    const node = super.create();
    node.setAttribute('src', url);
    node.setAttribute('controls', true);
    node.setAttribute('style', 'max-width: 100%; height: auto;');
    node.setAttribute('class', 'ql-video-custom');
    return node;
  }

  static value(node) {
    return node.getAttribute('src');
  }
}

VideoBlot.blotName = 'video';
VideoBlot.tagName = 'VIDEO';
Quill.register(VideoBlot, true);

export default {
  name: "Editor",
  props: {
    value: {
      type: String,
      default: "",
    },
    height: {
      type: Number,
      default: null,
    },
    minHeight: {
      type: Number,
      default: null,
    },
    readOnly: {
      type: Boolean,
      default: false,
    },
    fileSize: {
      type: Number,
      default: 50,
    },
    type: {
      type: String,
      default: "url",
    }
  },
  data() {
    return {
      uploadUrl: process.env.VUE_APP_BASE_API + "/common/upload",
      headers: {
        Authorization: "Bearer " + getToken()
      },
      Quill: null,
      currentValue: "",
      options: {
        theme: "snow",
        bounds: document.body,
        debug: "warn",
        modules: {
          toolbar: [
            ["bold", "italic", "underline", "strike"],
            ["blockquote", "code-block"],
            [{ list: "ordered" }, { list: "bullet" }],
            [{ indent: "-1" }, { indent: "+1" }],
            [{ size: ["small", false, "large", "huge"] }],
            [{ header: [1, 2, 3, 4, 5, 6, false] }],
            [{ color: [] }, { background: [] }],
            [{ align: [] }],
            ["clean"],
            ["link", "image", "video"]
          ],
        },
        placeholder: "请输入内容",
        readOnly: this.readOnly,
      },
    };
  },
  computed: {
    styles() {
      let style = {};
      if (this.minHeight) style.minHeight = `${this.minHeight}px`;
      if (this.height) style.height = `${this.height}px`;
      return style;
    },
  },
  watch: {
    value: {
      handler(val) {
        if (val !== this.currentValue) {
          this.currentValue = val === null ? "" : val;
          if (this.Quill) {
            this.Quill.clipboard.dangerouslyPasteHTML(this.currentValue);
          }
        }
      },
      immediate: true,
    },
  },
  mounted() {
    this.init();

  },
  beforeDestroy() {
    this.Quill = null;
  },
  methods: {
    init() {
      const editor = this.$refs.editor;
      this.Quill = new Quill(editor, this.options);

      if (this.type === 'url') {
        let toolbar = this.Quill.getModule("toolbar");

        // 图片上传处理
        toolbar.addHandler("image", () => {
          this.$refs.imageUpload.$children[0].$refs.input.click();
        });

        // 视频上传处理
        toolbar.addHandler("video", () => {
          this.$refs.videoUpload.$children[0].$refs.input.click();
        });
      }

      this.Quill.clipboard.dangerouslyPasteHTML(this.currentValue);
      this.Quill.on("text-change", (delta, oldDelta, source) => {
        const html = this.$refs.editor.children[0].innerHTML;
        this.currentValue = html;
        this.$emit("input", html);
        this.$emit("on-change", {
          html,
          text: this.Quill.getText(),
          quill: this.Quill
        });
      });
    },

    // 图片上传前校验
    handleBeforeUpload(file) {
      const types = ["image/jpeg", "image/jpg", "image/png", "image/svg"];
      if (!types.includes(file.type)) {
        this.$message.error("图片格式错误（只支持JPG/PNG/SVG）");
        return false;
      }
      if (file.size / 1024 / 1024 > this.fileSize) {
        this.$message.error(`图片大小不能超过 ${this.fileSize}MB`);
        return false;
      }
      return true;
    },

    // 视频上传前校验
    handleBeforeVideoUpload(file) {
      const videoTypes = ['video/mp4', 'video/webm', 'video/ogg'];
      if (!videoTypes.includes(file.type)) {
        this.$message.error("请上传MP4/WEBM/OGG格式视频");
        return false;
      }
      if (file.size / 1024 / 1024 > this.fileSize) {
        this.$message.error(`视频大小不能超过 ${this.fileSize}MB`);
        return false;
      }
      return true;
    },

    // 图片上传成功
    handleUploadSuccess(res, file) {
      if (res.code === 200) {
        const quill = this.Quill;
        const range = quill.getSelection().index;
        quill.insertEmbed(range, "image", process.env.VUE_APP_BASE_API + res.fileName);
        quill.setSelection(range + 1);
      } else {
        this.$message.error("图片上传失败");
      }
    },

// 修改视频上传成功处理（关键修改）
    handleVideoUploadSuccess(res, file) {
      if (res.code === 200) {
        const quill = this.Quill;
        const range = quill.getSelection().index;
        const videoUrl = process.env.VUE_APP_BASE_API + res.fileName;

        // 使用自定义Blot插入视频
        quill.insertEmbed(range, 'video', videoUrl);
        quill.setSelection(range + 1);
      } else {
        this.$message.error("视频上传失败");
      }
    }
  }
};
</script>

<style>
/* 新增视频样式 */
.ql-editor video.ql-video-custom {
  display: block !important;
  max-width: 100% !important;
  height: auto !important;
  margin: 1rem 0;
  border-radius: 8px;
  background-color: #000;
}
.editor, .ql-toolbar {
  white-space: pre-wrap !important;
  line-height: normal !important;
}
/* 在组件内添加样式 */
.preview-content img {
  max-width: 100%;
  height: auto !important;
  display: block;
  margin: 10px auto;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1); /* 可选：添加阴影保持与编辑器一致 */
}
.ql-editor {
  min-height: 200px;
}

.ql-editor video {
  max-width: 500px;
  height: auto;
  margin: 10px 0;
  display: block;
}

.ql-snow .ql-tooltip[data-mode="video"]::before {
  content: "请上传视频文件";
}

.ql-snow .ql-tooltip.ql-editing a.ql-action::after {
  content: "保存";
}

.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="huge"]::before {
  content: "32px";
}

.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="1"]::before {
  content: "标题1";
}
</style>
