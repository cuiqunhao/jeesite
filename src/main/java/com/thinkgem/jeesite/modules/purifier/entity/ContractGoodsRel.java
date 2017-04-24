package com.thinkgem.jeesite.modules.purifier.entity;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.List;

/**
 * 订单与商品关系
 *
 * @author addison
 * @since 2017年03月10日
 */
public class ContractGoodsRel extends DataEntity<ContractGoodsRel>{
    /**
     * 申请单ID
     */
    private Long contractId;
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

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
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

    @Override
    public String toString() {
        return good.getGoodName()+"#"+good.getType()+"#"+appNum+"#"+usefor;
    }
}
