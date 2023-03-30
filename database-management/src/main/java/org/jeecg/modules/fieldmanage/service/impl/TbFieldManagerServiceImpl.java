package org.jeecg.modules.fieldmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.fieldmanage.entity.TbFieldManager;
import org.jeecg.modules.fieldmanage.mapper.TbFieldManagerMapper;
import org.jeecg.modules.fieldmanage.service.ITbFieldManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 字段管理
 * @Author: jeecg-boot
 * @Date: 2023-03-23
 * @Version: V1.0
 */
@Service
public class TbFieldManagerServiceImpl extends ServiceImpl<TbFieldManagerMapper, TbFieldManager> implements ITbFieldManagerService {
    @Resource
    private TbFieldManagerMapper tbFieldManagerMapper;
    @Override
    public boolean checkHaveDictUse(String code) {
        LambdaQueryWrapper<TbFieldManager> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TbFieldManager::getFieldId, code);
        return tbFieldManagerMapper.selectCount(lambdaQueryWrapper) > 0;
    }
}
