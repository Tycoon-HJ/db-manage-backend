package org.hai.work.domain;

import org.springframework.beans.BeanUtils;

/**
 * @author mr.ahai
 */
public class BaseApiDO {

    private String key;

    public void checkJavaCoCo() {
        BaseApiDO baseApiDO = new BaseApiDO();
        baseApiDO.setKey("1");
        BaseApiDO baseApiDO1 = new BaseApiDO();
        BeanUtils.copyProperties(baseApiDO, baseApiDO1);
        System.out.println(baseApiDO1);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
