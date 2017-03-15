package com.thinkgem.jeesite.modules.purifier.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

/**
 * 合同维护单
 *
 * @author addison
 * @since 2017年03月02日
 */
public class Maintain extends DataEntity<Maintain>{
    /**
     * 合同ID
     */
    private Long contractId;
    /**
     * 维护人员
     */
    private User userId;
    /**
     * 本次维护时间
     */
    private Date mainTime;
    /**
     * 下次维护时间
     */
    private Date nextMainTime;

    private String mainContent;


    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }
    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Date getMainTime() {
        return mainTime;
    }

    public void setMainTime(Date mainTime) {
        this.mainTime = mainTime;
    }

    public Date getNextMainTime() {
        return nextMainTime;
    }

    public void setNextMainTime(Date nextMainTime) {
        this.nextMainTime = nextMainTime;
    }
}
