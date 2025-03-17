<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>智能健身教练平台</h2>


      </el-col>
      <!-- 新增轮播图 -->
      <el-carousel :interval="2500" height="400px" indicator-position="outside">
        <el-carousel-item v-for="(item, index) in carouselItems" :key="index">
          <img :src="item.img" :alt="item.title" class="carousel-image" />
          <h3 class="carousel-title">{{ item.title }}</h3>
        </el-carousel-item>
      </el-carousel>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2></h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4></h4>

          </el-col>
          <el-col :span="6">
            <h4></h4>

          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="12">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>联系信息</span>
          </div>
          <div class="body">




          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="12">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>更新日志</span>
          </div>

        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">

      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.9",
      carouselItems: [
        {
          img: require('@/assets/images/banner1.jpg'),
          title: '个性化训练计划'
        },
        {
          img: require('@/assets/images/banner2.jpg'),
          title: '专业动作指导'
        },
        {
          img: require('@/assets/images/banner3.jpg'),
          title: '智能体态分析'
        }
      ]
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  /* 新增轮播图样式 */
  .el-carousel {
    margin-top: 100px;
    border-radius: 8px;
    overflow: hidden;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);

    .carousel-image {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
    .carousel-title {
      position: absolute;
      bottom: 20%;
      left: 50%;
      transform: translateX(-50%);
      color: #fff;
      font-size: 32px;
      text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
      white-space: nowrap;
    }
  }

    blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    position: absolute;
    left:50%;
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

