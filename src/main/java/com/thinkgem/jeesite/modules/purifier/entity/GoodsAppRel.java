package com.thinkgem.jeesite.modules.purifier.entity;

/**
 * 请填写类注释
 *
 * @author addison
 * @since 2017年03月10日
 */
public class GoodsAppRel {
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

    public Goods getGoodId() {
        return good;
    }

    public void setGoodId(Goods goodId) {
        this.good = goodId;
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
