<template>
  <div id="app">
    <router-view />
    <theme-picker />
  </div>
</template>

<script>
import ThemePicker from "@/components/ThemePicker";

export default {
  name: "App",
  components: { ThemePicker },
  metaInfo() {
    return {
      title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
      titleTemplate: title => {
        return title ? `${title} - ${process.env.VUE_APP_TITLE}` : process.env.VUE_APP_TITLE
      }
    }
  }
};
</script>
<style scoped>
#app .theme-picker {
  display: none;
}
</style>
<script>
import ThemePicker from"@/components/ThemePicker";
export default {
  name:"App",
  components:{ThemePicker},
  metaInfo() {
    return {
      title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
      titleTemplate: title => {
        return title ? `${title} - ${process.env.VUE_APP_TITLE}` : process.env.VUE_APP_TITLE
      }
    }
  },
  mounted() {
    window.difyChatbotConfig={
      token: 'pok6aPCYM0NSLNCL',
      baseUrl: 'http://127.0.0.1',
    };
    const script = document.createElement('script');
    script.src = 'http://127.0.0.1/embed.min.js';
    script.id='pok6aPCYM0NSLNCL';
    script.defer=true;
    document.body.appendChild(script);
  }
};
</script>