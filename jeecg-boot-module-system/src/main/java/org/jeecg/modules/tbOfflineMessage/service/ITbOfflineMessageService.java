package org.jeecg.modules.tbOfflineMessage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.tbOfflineMessage.entity.TbOfflineMessage;

import java.util.List;

/**
 * @Description: 离线信息表
 * @Author: jeecg-boot
 * @Date: 2024-01-08
 * @Version: V1.0
 */
public interface ITbOfflineMessageService extends IService<TbOfflineMessage> {

    List<TbOfflineMessage> listOfflineMessageList(String receiver);

    void deleteOfflineByReceiver(String receiver);
}
