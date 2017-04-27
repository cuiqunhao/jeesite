package com.thinkgem.jeesite.modules.purifier.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
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
    @ExcelField(title="合同编号", align=2, sort=1)
    private Contract contract;
    /**
     * 发票信息
     */
    @ExcelField(title="发票信息", align=2, sort=1)
    private String invoice;
    /**
     * 收款金额
     */
    @ExcelField(title="收款金额", align=2, sort=1)
    private Double amount;
    /**
     * 下次收款时间
     */
    @ExcelField(title="下次收款时间", align=2, sort=1)
    private Date nextRecTime;
    /**
     * 收款时间
     */
    @ExcelField(title="收款时间", align=2, sort=1)
    private Date recTime;
    /**
     * 收款人ID
     */
    @ExcelField(title="收款人ID", align=2, sort=1)
    private User userId;
    /**
     * 是否开发票
     */
    @ExcelField(title="是否开发票", align=2, sort=1,dictType = "isInvoiceType")
    private String isInvoice;

    public String getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }

    public Date getRecTime() {
        return recTime;
    }

    public void setRecTime(Date recTime) {
        this.recTime = recTime;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
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
