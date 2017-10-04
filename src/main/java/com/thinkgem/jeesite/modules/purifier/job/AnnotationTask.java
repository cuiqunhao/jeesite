package com.thinkgem.jeesite.modules.purifier.job;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyRecord;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyService;
import com.thinkgem.jeesite.modules.purifier.dao.ContractDao;
import com.thinkgem.jeesite.modules.purifier.entity.Contract;
import com.thinkgem.jeesite.modules.purifier.entity.Receivables;
import com.thinkgem.jeesite.modules.purifier.service.ContractService;
import com.thinkgem.jeesite.modules.purifier.service.ReceivablesService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Lazy(false)
public class AnnotationTask {

    @Autowired
    private ContractDao contract;
    @Autowired
    private OaNotifyService oaNotifyService;
    @Autowired
    private SystemService systemService;

//    @Scheduled(cron="0/5 * * * * ?") //每5秒执行一次
    @Scheduled(cron = "0 0 0 * * ?")
    public void job1() {
        System.out.println("spring task 注解使用。。。任务进行中");
        List<Contract> listNotRec = contract.findNotReceivablesList(new Contract());
        List<Contract> listNotMain = contract.findNotReceivablesList(new Contract());
        if (listNotRec.size()>0){
            for(Contract contract:listNotRec){
                OaNotify oaNotify = new OaNotify();
                oaNotify.setContent("订单号"+contract.getContractNo()+"需要收款，请查看收款提醒");
                oaNotify.setTitle("到期收款提醒");
                oaNotify.setStatus("1");
                oaNotify.setType("4");
                List<User> userList = systemService.findUser(new User(systemService.getRoleByEnname("jsp")));
                List<OaNotifyRecord> oaNotifyRecordList = Lists.newArrayList();
                for(User u:userList){
                    OaNotifyRecord entity = new OaNotifyRecord();
                    entity.setId(IdGen.uuid());
                    entity.setOaNotify(oaNotify);
                    entity.setUser(u);
                    entity.setReadFlag("0");
                    oaNotifyRecordList.add(entity);
                }
                oaNotify.setOaNotifyRecordList(oaNotifyRecordList);
                oaNotifyService.save(oaNotify);
            }
        }
        if (listNotMain.size()>0){
            for(Contract contract:listNotRec){
                OaNotify oaNotify = new OaNotify();
                oaNotify.setContent("订单号"+contract.getContractNo()+"需要维护，请查看维护提醒");
                oaNotify.setTitle("到期维护提醒");
                oaNotify.setStatus("1");
                oaNotify.setType("4");
                List<User> userList = systemService.findUser(new User(systemService.getRoleByEnname("jsp")));
                List<OaNotifyRecord> oaNotifyRecordList = Lists.newArrayList();
                for(User u:userList){
                    OaNotifyRecord entity = new OaNotifyRecord();
                    entity.setId(IdGen.uuid());
                    entity.setOaNotify(oaNotify);
                    entity.setUser(u);
                    entity.setReadFlag("0");
                    oaNotifyRecordList.add(entity);
                }
                oaNotify.setOaNotifyRecordList(oaNotifyRecordList);
                oaNotifyService.save(oaNotify);
            }

        }
    }

}