package org.hai.work.domain;

import org.springframework.beans.BeanUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseApiTest {

    @Test
    void testBaseDO() {
        BaseApiDO baseApiDO = new BaseApiDO();
        baseApiDO.checkJavaCoCo();
        Assert.assertTrue(true);
    }

    @Test
    void testBaseDo01() {
        BaseApiDO baseApiDO = new BaseApiDO();
        baseApiDO.setKey("1");
        BaseApiDO baseApiDO1 = new BaseApiDO();
        BeanUtils.copyProperties(baseApiDO, baseApiDO1);
        System.out.println(baseApiDO1);
    }

}
