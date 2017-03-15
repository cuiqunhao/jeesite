package com.thinkgem.jeesite.test.modules.purifier;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.purifier.web.MaintainController;
import com.thinkgem.jeesite.modules.purifier.web.ReceivablesController;
import com.thinkgem.jeesite.test.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * 请填写类注释
 *
 * @author addison
 * @since 2017年03月15日
 */
public class ReceivablesTest extends TestConfig{
    private MockMvc mockMvc;
    @Autowired
    private ReceivablesController receivablesController;
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(receivablesController).build();
    }

    @Test
    public void save() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reveivables/save")
        .param("contractId","1")
        .param("recTime",DateUtils.getDate("yyyy-MM-dd"))
        .param("nextRecTime", DateUtils.getDate("yyyy-MM-dd"))
        .param("invoice", "1")
        .param("amount", "1")
        .param("userId.id", "1")
        .param("createBy.id", "1")
        .param("createDate", DateUtils.getDate("yyyy-MM-dd"))
        )
        .andReturn();
    }

    @Test
    public void pageList() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/reveivables/list")
        .param("contractNo","11")
        )
        .andDo(MockMvcResultHandlers.print())
        .andReturn();
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reveivables/update")
        .param("id","1")
        .param("recTime",DateUtils.getDate("yyyy-MM-dd"))
        .param("nextRecTime", DateUtils.getDate("yyyy-MM-dd"))
        .param("invoice", "1")
        .param("amount", "1")
        .param("userId.id", "1")
        .param("updateBy.id", "1")
        .param("updateDate", DateUtils.getDate("yyyy-MM-dd"))
        )
        .andReturn();
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reveivables/delete")
        .param("id","1")
        )
        .andReturn();
    }


}
