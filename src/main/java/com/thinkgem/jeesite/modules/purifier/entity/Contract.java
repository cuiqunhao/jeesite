package com.thinkgem.jeesite.modules.purifier.entity;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;
import java.util.List;

/**
 * 合同单
 *
 * @author addison
 * @since 2017年03月02日
 */
public class Contract extends DataEntity<Contract>{
    /**
     * 合同编号
     */
    private String contractNo;
    /**
     * 合同名称
     */
    private String contractName;
    /**
     * 合同金额
     */
    private Double contractAmount;
    /**
     * 合同期限开始
     */
    private Date contractBenginTime;
    /**
     * 合同期限结束
     */
    private Date contractEndTime;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 联系电话
     */
    private String contactsPhone;
    /**
     * 业务员
     */
    private User salesman;
    /**
     * 所属项目
     */
    private String item;
    /**
     * 联系地址
     */
    private String contactsAddress;
    /**
     * 签订合同时间
     */
    private Date contractTime;
    /**
     * 项目施工完成时间
     */
    private Date completeTime;
    /**
     * 安装人员
     */
    private User installer;
    /**
     * 维护周期
     */
    private Integer mianCycle;
    /**
     * 合同收款周期
     */
    private Integer collCycle;
    /**
     * 备注
     */
    private String remarks;

    /**
     * 签订合同时间开始
     */
    private Date contractTimeBegin;
    /**
     * 签订合同时间结束
     */
    private Date contractTimeEnd;

    /**
     * 商品列表
     */
    private List<ContractGoodsRel> goodList = Lists.newArrayList();

    public List<ContractGoodsRel> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<ContractGoodsRel> goodList) {
        this.goodList = goodList;
    }

    public Date getContractTimeBegin() {
        return contractTimeBegin;
    }

    public void setContractTimeBegin(Date contractTimeBegin) {
        this.contractTimeBegin = contractTimeBegin;
    }

    public Date getContractTimeEnd() {
        return contractTimeEnd;
    }

    public void setContractTimeEnd(Date contractTimeEnd) {
        this.contractTimeEnd = contractTimeEnd;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Double getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(Double contractAmount) {
        this.contractAmount = contractAmount;
    }

    public Date getContractBenginTime() {
        return contractBenginTime;
    }

    public void setContractBenginTime(Date contractBenginTime) {
        this.contractBenginTime = contractBenginTime;
    }

    public Date getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(Date contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public User getSalesman() {
        return salesman;
    }

    public void setSalesman(User salesman) {
        this.salesman = salesman;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getContactsAddress() {
        return contactsAddress;
    }

    public void setContactsAddress(String contactsAddress) {
        this.contactsAddress = contactsAddress;
    }

    public Date getContractTime() {
        return contractTime;
    }

    public void setContractTime(Date contractTime) {
        this.contractTime = contractTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public User getInstaller() {
        return installer;
    }

    public void setInstaller(User installer) {
        this.installer = installer;
    }

    public Integer getMianCycle() {
        return mianCycle;
    }

    public void setMianCycle(Integer mianCycle) {
        this.mianCycle = mianCycle;
    }

    public Integer getCollCycle() {
        return collCycle;
    }

    public void setCollCycle(Integer collCycle) {
        this.collCycle = collCycle;
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
