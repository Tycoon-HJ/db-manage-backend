package org.jeecg.modules.tbOfflineMessage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.tbOfflineMessage.entity.TbOfflineMessage;
import org.jeecg.modules.tbOfflineMessage.mapper.TbOfflineMessageMapper;
import org.jeecg.modules.tbOfflineMessage.service.ITbOfflineMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 离线信息表
 * @Author: jeecg-boot
 * @Date: 2024-01-08
 * @Version: V1.0
 */
@Service
public class TbOfflineMessageServiceImpl extends ServiceImpl<TbOfflineMessageMapper, TbOfflineMessage> implements ITbOfflineMessageService {
    @Resource
    private TbOfflineMessageMapper tbOfflineMessageMapper;

    /**
     * 查询接收者的离线消息
     *
     * @param receiver
     * @return
     */
    @Override
    public List<TbOfflineMessage> listOfflineMessageList(String receiver) {
        LambdaQueryWrapper<TbOfflineMessage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TbOfflineMessage::getReceiver, receiver);
        lambdaQueryWrapper.orderByAsc(TbOfflineMessage::getCreateTime);
        return tbOfflineMessageMapper.selectList(lambdaQueryWrapper);
    }

    /**
     * 删除离线消息
     *
     * @param receiver
     */
    public void deleteOfflineByReceiver(String receiver) {
        LambdaQueryWrapper<TbOfflineMessage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TbOfflineMessage::getReceiver, receiver);
        tbOfflineMessageMapper.delete(lambdaQueryWrapper);
    }

}
