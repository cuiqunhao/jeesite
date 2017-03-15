package com.thinkgem.jeesite.modules.purifier.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

/**
 * 合同收款单
 *
 * @author addison
 * @since 2017年03月02日
 */
public class Receivables extends DataEntity<Receivables>{
    /**
     * 合同ID
     */
    private Long contractId;
    /**
     * 发票信息
     */
    private String invoice;
    /**
     * 收款金额
     */
    private Double amount;
    /**
     * 下次收款时间
     */
    private Date nextRecTime;
    /**
     * 下次收款时间
     */
    private Date recTime;
    /**
     * 收款人ID
     */
    private User userId;

    public Date getRecTime() {
        return recTime;
    }

    public void setRecTime(Date recTime) {
        this.recTime = recTime;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getNextRecTime() {
        return nextRecTime;
    }

    public void setNextRecTime(Date nextRecTime) {
        this.nextRecTime = nextRecTime;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
