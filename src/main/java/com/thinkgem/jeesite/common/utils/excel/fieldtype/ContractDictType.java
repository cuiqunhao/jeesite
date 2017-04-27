package com.thinkgem.jeesite.common.utils.excel.fieldtype;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.purifier.entity.ContractGoodsRel;
import com.thinkgem.jeesite.modules.purifier.entity.Goods;

import java.util.List;

/**
 * Created by Admin on 2017/4/24.
 */
public class ContractDictType {
    /**
     * 获取对象值（导入）
     */
    public static Object getValue(String val) {
        if(StringUtils.isEmpty(val)){
            return "";
        }
        return "家用".equals(val)?"1":"2";
    }

    /**
     * 设置对象值（导出）
     */
    public static String setValue(Object val) {
        if (val != null){
            return "1".equals(val)?"家用":"商用";
        }
        return "";
    }

}
