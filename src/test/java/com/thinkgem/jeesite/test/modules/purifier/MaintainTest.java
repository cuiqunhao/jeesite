package com.thinkgem.jeesite.test.modules.purifier;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.purifier.web.ContractController;
import com.thinkgem.jeesite.modules.purifier.web.MaintainController;
import com.thinkgem.jeesite.test.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * 请填写类注释
 *
 * @author addison
 * @since 2017年03月15日
 */
public class MaintainTest extends TestConfig{
    private MockMvc mockMvc;
    @Autowired
    private MaintainController maintainController;
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(maintainController).build();
    }

    @Test
    public void save() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/maintain/save")
        .param("contractId","1")
        .param("mainTime",DateUtils.getDate("yyyy-MM-dd"))
        .param("nextMainTime", DateUtils.getDate("yyyy-MM-dd"))
        .param("userId.id", "1")
        .param("createBy.id", "1")
        .param("createDate", DateUtils.getDate("yyyy-MM-dd"))
        .param("mainContent", "120")
        )
        .andReturn();
    }

    @Test
    public void pageList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/maintain/list")
        .param("contractNo","11")
        )
        .andDo(MockMvcResultHandlers.print())
        .andReturn();
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/maintain/update")
        .param("id","1")
        .param("mainTime",DateUtils.getDate("yyyy-MM-dd"))
        .param("nextMainTime", DateUtils.getDate("yyyy-MM-dd"))
        .param("userId.id", "1")
        .param("updateBy.id", "1")
        .param("updateDate", DateUtils.getDate("yyyy-MM-dd"))
        .param("mainContent", "111")
        )
        .andReturn();
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/maintain/delete")
        .param("id","1")
        )
        .andReturn();
    }


}
