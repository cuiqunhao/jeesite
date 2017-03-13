package com.thinkgem.jeesite.test.modules.purifier;

import com.thinkgem.jeesite.modules.purifier.web.GoodsController;
import com.thinkgem.jeesite.modules.purifier.web.WareController;
import com.thinkgem.jeesite.test.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * 商品管理测试
 *
 * @author addison
 * @since 2017年03月13日
 */
public class GoodsTest extends TestConfig{
    private MockMvc mockMvc;
    @Autowired
    private GoodsController goodsController;
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(goodsController).build();
    }

    @Test
    public void insert() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/goods/save")
        .param("goodName","1234")
        .param("type","1234")
        .param("createBy.id","1")
        ).andReturn();
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/goods/save")
        .param("id","3")
        .param("goodName","222")
        .param("type","222")
        .param("updateBy.id","1")
        ).andReturn();

    }

    @Test
    public void pageList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/goods/list")
        .param("goodName","222")
        ).andReturn();
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/goods/delete")
        .param("id","3")
        ).andReturn();
    }





}
