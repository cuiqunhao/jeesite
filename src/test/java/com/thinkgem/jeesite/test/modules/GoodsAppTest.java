package com.thinkgem.jeesite.test.modules;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.purifier.web.GoodsAppServiceController;
import com.thinkgem.jeesite.test.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
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
    private GoodsAppServiceController goodsAppServiceController;
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
        mockMvc.perform(MockMvcRequestBuilders.get("/goodsApp/list")
        .param("id","1")
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
        .param("appTime", DateUtils.getDate("2017-03-09 12:01:01")))
        .andReturn();
    }


}
