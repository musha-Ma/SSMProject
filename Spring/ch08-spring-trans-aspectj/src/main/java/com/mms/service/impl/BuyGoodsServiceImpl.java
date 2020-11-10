package com.mms.service.impl;

import com.mms.entity.Goods;
import com.mms.entity.Sale;
import com.mms.exception.NotEnoughException;
import com.mms.mapper.GoodsMapper;
import com.mms.mapper.SaleMapper;
import com.mms.service.IBuyGoodsService;

public class BuyGoodsServiceImpl implements IBuyGoodsService {

    private SaleMapper saleMapper;
    private GoodsMapper goodsMapper;
    //set注入赋值
    public void setSaleMapper(SaleMapper saleMapper) {
        this.saleMapper = saleMapper;
    }

    public void setGoodsMapper(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }


    /**
     * 购买商品方法
     * goodsId表示要购买的商品编号，nums表示购买的数量
     * @param goodsId
     * @param nums
     */
    @Override
    public void buy(Integer goodsId, Integer nums) {
        System.out.println("buy开始...");

        /*
            记录销售的信息，向sale表添加记录，由于sale表id是自增长的，所以不需要设置id属性
            向sale表添加一条购买记录
         */
        Sale sale = new Sale();
        sale.setGid(goodsId);
        sale.setNums(nums);
        saleMapper.addSale(sale);

        //更新库存
        //先查询该编号的商品是否存在
        Goods goods = goodsMapper.queryGoods(goodsId);
        //进行判断
        if (goods == null) {
            //说明商品不存在
            throw new NullPointerException("编号为:"+goodsId+"商品不存在...");
        } else if (goods.getAmount() < nums) {
            //说明库存不足
            throw new NotEnoughException("编号为:"+goodsId+"商品库存不足...");
        }

        //能够进行到这里说明商品信息是合法的，可以更新库存
        Goods good = new Goods();
        good.setId(goodsId);
        good.setAmount(nums);
        goodsMapper.updateGoods(good);

        System.out.println("buy结束...");
    }
}
