package com.thinkgem.jeesite.common.utils.excel.fieldtype;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * Created by Admin on 2017/4/24.
 */
public class SalesmanType {
    /**
     * 导出
     * @param val
     * @return
     */
    public static String setValue(Object val){
        if (val != null && ((User)val).getName() != null){
            return ((User)val).getName();
        }
        return "";
    }

    /**
     * 导入
     * @param val
     * @return
     */
    public static Object getValue(String val){
        for (User e : UserUtils.getUserList()){
            if (StringUtils.trimToEmpty(val).equals(e.getName())){
                return e;
            }
        }
        return null;
    }
}
