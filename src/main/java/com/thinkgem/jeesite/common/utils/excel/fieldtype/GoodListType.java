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
public class GoodListType {
    /**
     * 获取对象值（导入）
     */
    public static Object getValue(String val) {
        List<ContractGoodsRel> contractGoodsRelList = Lists.newArrayList();
        for (String s : StringUtils.split(val, ",")){
            String[] contractGoodsRel = StringUtils.split(val, "#");
            ContractGoodsRel contractGoodsRel1 = new ContractGoodsRel();
            Goods good = new Goods();
            good.setGoodName(contractGoodsRel[0]);
            good.setType(contractGoodsRel[1]);
            contractGoodsRel1.setGood(good);
            contractGoodsRel1.setAppNum(Long.valueOf(contractGoodsRel[2]));
            contractGoodsRel1.setUsefor(contractGoodsRel[3]);
            contractGoodsRelList.add(contractGoodsRel1);
        }
        return contractGoodsRelList.size()>0?contractGoodsRelList:null;
    }

    /**
     * 设置对象值（导出）
     */
    public static String setValue(Object val) {
        if (val != null){
            @SuppressWarnings("unchecked")
            List<ContractGoodsRel> roleList = (List<ContractGoodsRel>)val;
            return Collections3.convertToString(roleList, ",");
        }
        return "";
    }

}
