package com.thinkgem.jeesite.modules.purifier.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 仓库实体类
 *
 * @author addison
 * @since 2017年03月02日
 */
public class WareGoodsRel extends DataEntity<WareGoodsRel>{
    /**
     * 商品
     */
    private Goods good;
    /**
     * 仓库
     */
    private Ware ware;
    /**
     * 数量
     */
    private Long num;


    public Goods getGood() {
        return good;
    }

    public void setGood(Goods good) {
        this.good = good;
    }

    public Ware getWare() {
        return ware;
    }

    public void setWare(Ware ware) {
        this.ware = ware;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}
