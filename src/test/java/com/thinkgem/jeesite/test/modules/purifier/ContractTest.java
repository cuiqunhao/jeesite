package com.thinkgem.jeesite.test.modules.purifier;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.purifier.web.ContractController;
import com.thinkgem.jeesite.modules.purifier.web.GoodsAppController;
import com.thinkgem.jeesite.test.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * 合同单测试类
 *
 * @author addison
 * @since 2017年03月14日
 */
public class ContractTest extends TestConfig{
    private MockMvc mockMvc;
    @Autowired
    private ContractController contractController;
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(contractController).build();
    }


    @Test
    public void save() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contract/save")
        .param("contractNo","X12345")
        .param("contractName","合同名称")
        .param("contractBenginTime", DateUtils.getDate("yyyy-MM-dd"))
        .param("contractEndTime", DateUtils.getDate("yyyy-MM-dd"))
        .param("contacts", "张三")
        .param("contactsPhone", "15620312320")
        .param("contactsAddress", "1")
        .param("contractTime", DateUtils.getDate("yyyy-MM-dd"))
        .param("salesman.id", "1")
        .param("item","项目1")
        .param("installer.id", "1")
        .param("collCycle", "120")
        .param("createBy.id", "1")
        .param("createDate", "120")
        .param("remarks", "120")
        .param("contractAmount", "120")
        )
        .andReturn();
    }

    @Test
    public void pageList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contract/list")
        .param("contractNo","11")
        )
        .andDo(MockMvcResultHandlers.print())
        .andReturn();
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contract/update")
        .param("id","1")
        .param("contractNo","11")
        .param("contractName","合同名称22")
        .param("contractBenginTime", DateUtils.getDate("yyyy-MM-dd"))
        .param("contractEndTime", DateUtils.getDate("yyyy-MM-dd"))
        .param("contacts", "张三123")
        .param("contactsPhone", "15620312320")
        .param("contactsAddress", "1")
        .param("contractTime", DateUtils.getDate("yyyy-MM-dd"))
        .param("salesman.id", "1")
        .param("item","项目1")
        .param("installer.id", "1")
        .param("collCycle", "1")
        .param("createBy.id", "1")
        .param("createDate", "1")
        .param("remarks", "1")
        .param("contractAmount", "120")
        )
        .andReturn();
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contract/delete")
        .param("id","1")
        )
        .andReturn();
    }


}
