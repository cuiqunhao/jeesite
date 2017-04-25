package com.thinkgem.jeesite.modules.purifier.entity;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.common.utils.excel.fieldtype.ContractType;
import com.thinkgem.jeesite.common.utils.excel.fieldtype.GoodListType;
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
    @ExcelField(title="合同编号", align=2, sort=1)
    private String contractNo;
    /**
     * 合同类型
     */
    @ExcelField(title="合同类型", align=2, sort=2,dictType = "contractType")
    private String contractType;
    /**
     * 合同名称
     */
    @ExcelField(title="合同名称", align=2, sort=3)
    private String contractName;
    /**
     * 合同金额
     */
    @ExcelField(title="合同金额", align=2, sort=4)
    private Double contractAmount;
    /**
     * 合同期限开始
     */
    @ExcelField(title="合同期限开始", align=2, sort=5)
    private Date contractBenginTime;
    /**
     * 合同期限结束
     */
    @ExcelField(title="合同期限结束", align=2, sort=6)
    private Date contractEndTime;
    /**
     * 联系人
     */
    @ExcelField(title="联系人", align=2, sort=7)
    private String contacts;
    /**
     * 联系电话
     */
    @ExcelField(title="联系电话", align=2, sort=7)
    private String contactsPhone;
    /**
     * 业务员
     */
    @ExcelField(title="业务员", align=2, sort=7)
    private User salesman;
    /**
     * 所属项目
     */
    @ExcelField(title="所属项目", align=2, sort=7)
    private String item;
    /**
     * 联系地址
     */
    @ExcelField(title="联系地址", align=2, sort=7)
    private String contactsAddress;
    /**
     * 签订合同时间
     */
    @ExcelField(title="签订合同时间", align=2, sort=7)
    private Date contractTime;
    /**
     * 项目施工完成时间
     */
    @ExcelField(title="项目施工完成时间", align=2, sort=7)
    private Date completeTime;
    /**
     * 安装人员
     */
    @ExcelField(title="安装人员", align=2, sort=7)
    private User installer;
    /**
     * 维护周期
     */
    @ExcelField(title="维护周期", align=2, sort=7)
    private Integer mianCycle;
    /**
     * 合同收款周期
     */
    @ExcelField(title="合同收款周期", align=2, sort=7)
    private Integer collCycle;
    /**
     * 备注
     */
    @ExcelField(title="备注", align=2, sort=7)
    private String remarks;

    /**
     * 签订合同时间开始
     */
//    @ExcelField(title="签订合同时间开始", align=2, sort=7)
    private Date contractTimeBegin;
    /**
     * 签订合同时间结束
     */
//    @ExcelField(title="签订合同时间结束", align=2, sort=7)
    private Date contractTimeEnd;

    /**
     * 商品列表
     */
    @ExcelField(title="商品列表", align=2, sort=7,fieldType = GoodListType.class)
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

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
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
