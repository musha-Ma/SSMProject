package com.mms.service;

public interface IBuyGoodsService {

    //购买商品的方法，goodsId是购买商品的编号，nums是购买商品的数量
    void buy(Integer goodsId,Integer nums);
}
