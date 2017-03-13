package com.thinkgem.jeesite.test.modules.purifier;

import com.thinkgem.jeesite.modules.purifier.web.WareController;
import com.thinkgem.jeesite.test.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * 仓库管理单元测试
 *
 * @author addison
 * @since 2017年03月13日
 */
public class WareTest extends TestConfig{
    private MockMvc mockMvc;
    @Autowired
    private WareController wareController;
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(wareController).build();
    }

    @Test
    public void save() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ware/save")
        .param("wareName","南京仓库")
        .param("wareAddress","仓库地址")
        .param("createBy.id","1")
        .param("updateBy.id","1")
        )
        .andReturn();
    }

    @Test
    public void pageList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ware/list")
        .param("wareName","北京仓库")
        )
        .andReturn();
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ware/save")
        .param("id","1")
        .param("wareName","123")
        .param("wareAddress","123")
        )
        .andReturn();
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ware/delete")
        .param("id","1")
        )
        .andReturn();
    }


}
