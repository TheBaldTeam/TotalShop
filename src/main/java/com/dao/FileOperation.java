package com.dao;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class FileOperation {
    public boolean SellerBcImgDelete(String imgName) {
        String path = "C:\\Users\\70681\\Desktop\\Img\\";
        File file = new File(path + imgName);
        if (file == null || !file.exists()) {
            return false;
        }
        // 如果是文件
        if (file.isFile()) {
            return file.delete();
        } else
            // 如果是文件夹
            if (file.isDirectory()) {
                File[] childFiles = file.listFiles();
                // 文件夹没有内容,删除文件夹
                if (childFiles == null || childFiles.length == 0) {
                    return file.delete();
                }
                // 删除文件夹内容
                boolean reslut = true;
                for (File item : file.listFiles()) {
                    reslut = reslut && item.delete();
                }
                // 删除文件夹
                return reslut && file.delete();
            }
        return false;
    }

    public void callTomcat() {
        String path = System.getProperty("user.dir");
//        String command = path+"\\shutdown.bat";//关闭tomcat命令
        System.out.println(path);
        Runtime runtime = Runtime.getRuntime();//返回与当前的Java应用相关的运行时对象
        //指示Java虚拟机创建一个子进程执行指定的可执行程序，并返回与该子进程对应的Process对象实例
        Process process = null;
        try {
            process = runtime.exec(path);
            runtime.gc();//运行垃圾回收器
            String line = null;
            String content = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = br.readLine()) != null) {
                content += line + "\r\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String SellerBcImgAdd(MultipartFile image) {
        if (!image.isEmpty()) {
            String fileName = image.getOriginalFilename();
            String path = null;
            String type = null;
            String trueFileName = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase()) || "JPEG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = "C:\\Users\\70681\\Desktop\\SellerBcImg\\";
                    File file = new File(realPath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    // 自定义的文件名称
                    String uuid = UUID.randomUUID().toString();
                    trueFileName = uuid + "." + type;
                    // 设置存放图片文件的路径
                    path = realPath + trueFileName;
                    try {
                        image.transferTo(new File(path));
                    } catch (IOException e) {
                        System.out.println("文件路径不存在");
                        e.printStackTrace();
                    }
                    return trueFileName;
                }
                return trueFileName;
            }
            return trueFileName;
        }
        return "error";
    }
}
