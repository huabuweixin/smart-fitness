package com.BruceandLee.image.service;

import com.ruoyi.common.utils.uuid.UUID;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    // 使用跨平台路径分隔符
    private static final String IMAGE_DIR = "image";
    @Value("${ruoyi.profile}")
    private String uploadBaseDir;

    private String imagePath = uploadBaseDir + File.separator + "image" + File.separator;

    public String storeFile(MultipartFile file) throws IOException {
        Path rootPath = Paths.get(imagePath);

        // 使用同步块保证线程安全（虽然Files.createDirectories本身是线程安全的）
        synchronized (this) {
            if (!Files.isDirectory(rootPath)) {
                Files.createDirectories(rootPath);
                System.out.println("自动创建目录: " + rootPath);
            }
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path targetPath = rootPath.resolve(fileName);

        // 保存文件（自动覆盖已存在文件）
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    // 获取完整文件路径（新增方法）
    public String getFullFilePath(String fileName) {
        return imagePath + fileName;
    }

    // 获取基础路径（保留原有方法）
    public  String getImagePath() {
        return imagePath;
    }
}
