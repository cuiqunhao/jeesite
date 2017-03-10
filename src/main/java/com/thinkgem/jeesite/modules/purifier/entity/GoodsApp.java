package com.thinkgem.jeesite.modules.purifier.entity;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;
import java.util.List;

/**
 * 货物申请单实体类
 *
 * @author addison
 * @since 2017年03月02日
 */
public class GoodsApp extends DataEntity<GoodsApp>{
    /**
     * 申请时间开始
     */
    private Date appTimeStart;
    /**
     * 申请时间结束
     */
    private Date appTimeEnd;
    /**
     * 申请时间
     */
    private Date appTime;

    /**
     * 申请人
     */
    private User applicantUser;
    /**
     * 收货人电话
     */
    private String consigneePhone;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 收货人地址
     */
    private String consigneeAddress;
    /**
     * 确认收货时间
     */
    private Date consigneeTime;
    /**
     * 一级审核状态
     */
    private String firstExaStatus;
    /**
     * 一级审核内容
     */
    private String firstExaContent;
    /**
     * 二级审核状态
     */
    private String secExaStatus;
    /**
     * 二级审核内容
     */
    private String secExaContent;
    /**
     * 发货地址
     */
    private String shipAddress;
    /**
     * 物流信息
     */
    private String logistics;
    /**
     * 申请编号
     */
    private String appNo;
    /**
     * 商品列表
     */
    private List<GoodsAppRel> goodList = Lists.newArrayList();


    public List<GoodsAppRel> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<GoodsAppRel> goodList) {
        this.goodList = goodList;
    }

    public Date getAppTimeStart() {
        return appTimeStart;
    }

    public void setAppTimeStart(Date appTimeStart) {
        this.appTimeStart = appTimeStart;
    }

    public Date getAppTimeEnd() {
        return appTimeEnd;
    }

    public void setAppTimeEnd(Date appTimeEnd) {
        this.appTimeEnd = appTimeEnd;
    }

    public Date getAppTime() {
        return appTime;
    }

    public void setAppTime(Date appTime) {
        this.appTime = appTime;
    }

    public User getApplicantUser() {
        return applicantUser;
    }

    public void setApplicantUser(User applicantUser) {
        this.applicantUser = applicantUser;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public Date getConsigneeTime() {
        return consigneeTime;
    }

    public void setConsigneeTime(Date consigneeTime) {
        this.consigneeTime = consigneeTime;
    }

    public String getFirstExaStatus() {
        return firstExaStatus;
    }

    public void setFirstExaStatus(String firstExaStatus) {
        this.firstExaStatus = firstExaStatus;
    }

    public String getFirstExaContent() {
        return firstExaContent;
    }

    public void setFirstExaContent(String firstExaContent) {
        this.firstExaContent = firstExaContent;
    }

    public String getSecExaStatus() {
        return secExaStatus;
    }

    public void setSecExaStatus(String secExaStatus) {
        this.secExaStatus = secExaStatus;
    }

    public String getSecExaContent() {
        return secExaContent;
    }

    public void setSecExaContent(String secExaContent) {
        this.secExaContent = secExaContent;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }
}
