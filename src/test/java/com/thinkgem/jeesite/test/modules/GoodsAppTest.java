package com.thinkgem.jeesite.test.modules;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.purifier.web.GoodsAppController;
import com.thinkgem.jeesite.test.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

/**
 * 请填写类注释
 *
 * @author addison
 * @since 2017年03月08日
 */
public class GoodsAppTest extends TestConfig{
    private MockMvc mockMvc;
    @Autowired
    private GoodsAppController goodsAppServiceController;
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(goodsAppServiceController).build();
    }


    @Test
    public void goodsAppList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/goodsApp/list")
                .param("consigneePhone","1562032321")
                .param("applicantId", "1")
                .param("firstExaStatus", "1")
                .param("secExaStatus", "2")
                .param("appTimeStart", DateUtils.getDate("2017-03-07 12:01:01"))
                .param("appTimeEnd", DateUtils.getDate("2017-03-09 12:01:01")))
                .andReturn();
    }

    @Test
    public void getGoodsAppById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/goodsApp/form")
                .param("id","13")
        )
                .andReturn();
    }

    @Test
    public void saveGoodsApp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/goodsApp/save")
                .param("consigneePhone","1562032321")
                .param("applicantUser.id", "1")
                .param("appNo", UUID.randomUUID().toString())
                .param("consignee", "me")
                .param("consigneeAddress", "北京北京")
                .param("createBy.id", "1")
                .param("updateBy.id", "1")

                .param("goodList[0].goodsAppId","1")
                .param("goodList[0].good.id","1")
                .param("goodList[0].appNum","12")
                .param("goodList[0].usefor","121")
                .param("goodList[0].remark","121")

                .param("goodList[1].goodsAppId","1")
                .param("goodList[1].good.id","2")
                .param("goodList[1].appNum","12")
                .param("goodList[1].usefor","1111")
                .param("goodList[1].remark","121")

                .param("appTime", DateUtils.getDate("2017-03-09 12:01:01")))
                .andReturn();
    }

    @Test
    public void deleteGoodsApp() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/goodsApp/delete")
                .param("id","13")
                .param("updateBy.id", "1")
        ).andReturn();
    }



    @Test
    public void updateGoodsApp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/goodsApp/update")
                .param("id","13")
                .param("consigneePhone","1562032321")
                .param("consignee", "me111")
                .param("consigneeAddress", "北京北京112")
                .param("createBy.id", "1")
                .param("updateBy.id", "1")

                .param("goodList[0].goodsAppId","1")
                .param("goodList[0].good.id","2")
                .param("goodList[0].appNum","10")
                .param("goodList[0].usefor","11")
                .param("goodList[0].remark","11")

                .param("goodList[1].goodsAppId","1")
                .param("goodList[1].good.id","1")
                .param("goodList[1].appNum","101")
                .param("goodList[1].usefor","11")
                .param("goodList[1].remark","11")
        )
                .andReturn();
    }


}
