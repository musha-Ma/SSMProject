package com.mms;

import com.mms.service.IBuyGoodsService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    //不使用事务测试
    @Test
    public void test01() {
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //获取service对象
        IBuyGoodsService service = (IBuyGoodsService) ac.getBean("buyGoodsService");

        //商品编号不存在情况
        //java.lang.NullPointerException: 编号为:1003商品不存在...
        //service.buy(1003,50);
        //商品库存不足情况
        //com.mms.exception.NotEnoughException: 编号为:1002商品库存不足...
        //service.buy(1002,100);
        //正常情况
        service.buy(1002,10);
    }
}
