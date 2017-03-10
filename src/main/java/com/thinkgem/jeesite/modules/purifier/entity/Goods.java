package com.thinkgem.jeesite.modules.purifier.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商品实体类
 *
 * @author addison
 * @since 2017年03月02日
 */
public class Goods extends DataEntity<Goods>{
    private String goodName;
    private String type;

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
