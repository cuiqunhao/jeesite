package com.thinkgem.jeesite.modules.purifier.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 货物申请单与商品关系
 *
 * @author addison
 * @since 2017年03月10日
 */
public class GoodsAppRel extends DataEntity<GoodsAppRel>{
    /**
     * 申请单ID
     */
    private Long goodsAppId;
    /**
     * 商品ID
     */
    private Goods good;
    /**
     * 数量
     */
    private Long appNum;
    /**
     * 用途
     */
    private String usefor;
    /**
     * 备注
     */
    private String remark;

    public Long getGoodsAppId() {
        return goodsAppId;
    }

    public void setGoodsAppId(Long goodsAppId) {
        this.goodsAppId = goodsAppId;
    }

    public Goods getGood() {
        return good;
    }

    public void setGood(Goods good) {
        this.good = good;
    }

    public Long getAppNum() {
        return appNum;
    }

    public void setAppNum(Long appNum) {
        this.appNum = appNum;
    }

    public String getUsefor() {
        return usefor;
    }

    public void setUsefor(String usefor) {
        this.usefor = usefor;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
