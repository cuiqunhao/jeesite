package com.thinkgem.jeesite.test.modules.purifier;

import com.thinkgem.jeesite.modules.purifier.web.WareGoodsRelController;
import com.thinkgem.jeesite.test.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * 库存管理测试类
 *
 * @author addison
 * @since 2017年03月13日
 */
public class WareGoodsRelTest extends TestConfig {
    private MockMvc mockMvc;
    @Autowired
    private WareGoodsRelController wareGoodsRelController;
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(wareGoodsRelController).build();
    }


    @Test
    public void save() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/wareGoodsRel/save")
        .param("ware.id","2")
        .param("good.id","1")
        .param("num","111")
        .param("createBy.id","1")
        .param("updateBy.id","1")
        )
        .andReturn();
    }


    @Test
    public void pageList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/wareGoodsRel/list")
        .param("ware.wareName","123")
        )
        .andReturn();
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/wareGoodsRel/update")
        .param("ware.id","1")
        .param("good.id","1")
        .param("num","123")
        .param("createBy.id","1")
        .param("updateBy.id","1")
        )
        .andReturn();
    }


    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/wareGoodsRel/delete")
        .param("ware.id","1")
        .param("good.id","1")
        )
        .andReturn();
    }


}
