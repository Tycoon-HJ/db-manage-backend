package org.hai.work.application.resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 上传进度条
 *
 * @author mr.ahai
 */
@RestController
public class ProgressBar {

    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @PostMapping("/upload")
    public void progressBar(@RequestParam(value = "file") MultipartFile[] multipartFiles) {
        int index = 1;
        try {
            for (MultipartFile multipartFile : multipartFiles) {
                byte[] bytes = multipartFile.getBytes();
                String fileStr = new String(bytes);
                File inputFile = new File(index + "input.txt");
                if (!inputFile.exists()) {
                    inputFile.createNewFile();
                }
                FileOutputStream fis = new FileOutputStream(inputFile);
                // 写取到文件
                fis.write(fileStr.getBytes());
                Thread.sleep(5 * 1000);
                redisTemplate.opsForValue().set("progress", index);
                index++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
