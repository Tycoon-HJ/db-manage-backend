package org.jeecg.modules.fieldmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.fieldmanage.entity.TbFieldManager;

/**
 * @Description: 字段管理
 * @Author: jeecg-boot
 * @Date:   2023-03-23
 * @Version: V1.0
 */
public interface ITbFieldManagerService extends IService<TbFieldManager> {

    /**
     * 检查字典引向
     * @param code
     * @return
     */
    boolean checkHaveDictUse(String code);

}
