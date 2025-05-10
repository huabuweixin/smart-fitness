package com.ruoyi.common.utils.file;

import com.ruoyi.common.config.RuoYiConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class MaterialDirInitializer implements CommandLineRunner {
    @Override
    public void run(String... args) {
        // 初始化图片目录
        createDir(RuoYiConfig.getImagePath());
        // 初始化视频目录
        createDir(RuoYiConfig.getVideoPath());
    }

    private void createDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new RuntimeException("创建目录失败：" + path);
            }
        }
    }
}
