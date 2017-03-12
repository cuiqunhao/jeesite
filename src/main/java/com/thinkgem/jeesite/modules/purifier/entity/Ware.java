package com.thinkgem.jeesite.modules.purifier.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 仓库实体类
 *
 * @author addison
 * @since 2017年03月02日
 */
public class Ware extends DataEntity<Ware>{
    private String wareName;
    private String wareAddress;

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
    }

    public String getWareAddress() {
        return wareAddress;
    }

    public void setWareAddress(String wareAddress) {
        this.wareAddress = wareAddress;
    }
}
