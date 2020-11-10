package com.mms.mapper;

import com.mms.entity.Goods;

public interface GoodsMapper {

    /**
     * 更新库存
     * goods表示本次用户购买的商品信息
     * @param goods
     */
    void updateGoods(Goods goods);

    //查询商品信息
    Goods queryGoods(Integer id);
}
