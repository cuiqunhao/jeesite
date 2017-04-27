package com.thinkgem.jeesite.modules.purifier.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
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
    @ExcelField(title="合同编号", align=2, sort=1)
    private Contract contract;
    /**
     * 维护人员
     */
    @ExcelField(title="维护人员", align=2, sort=1)
    private User userId;
    /**
     * 本次维护时间
     */
    @ExcelField(title="本次维护时间", align=2, sort=1)
    private Date mainTime;
    /**
     * 下次维护时间
     */
    @ExcelField(title="下次维护时间", align=2, sort=1)
    private Date nextMainTime;

    /**
     * 维护内容
     */
    @ExcelField(title="维护内容", align=2, sort=1)
    private String mainContent;

    private String remarks;

    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
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
