package org.hai.work.application.resource;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * 上传进度条
 *
 * @author mr.ahai
 */
@RestController
public class ProgressBar {

    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @Resource
    private RestTemplate restTemplate;

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

    @GetMapping("/getxxx")
    public void ddd() throws Exception {

        NamingService namingService = NacosFactory.createNamingService("localhost:8848");
        List<Instance> allInstances = namingService.getAllInstances("netty-server");
        Instance instance = allInstances.get(0);
        String ip = instance.getIp();
        int port = instance.getPort();
        String forObject = restTemplate.getForObject("http://" + ip + ":" + port + "/getHeartBeat", String.class);
        System.out.println(forObject);
    }


}
