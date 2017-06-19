package com.thinkgem.jeesite.modules.purifier.web;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyRecord;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyService;
import com.thinkgem.jeesite.modules.purifier.entity.GoodsApp;
import com.thinkgem.jeesite.modules.purifier.service.GoodsAppService;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 货物申请controller
 *
 * @author addison
 * @since 2017年03月08日
 */
@Controller
@RequestMapping(value = "${adminPath}/goodsApp")
public class GoodsAppController extends BaseController {
    @Autowired
    private OaNotifyService oaNotifyService;
    @Autowired
    private GoodsAppService goodsAppService;

    @Autowired
    private SystemService systemService;

    @ModelAttribute
    public GoodsApp get(@RequestParam(value = "id",required = false) Long id) {
        GoodsApp goodsApp = new GoodsApp();
        if (id != null){
            goodsApp =  goodsAppService.getByGoodsAppId(id);
        }
        User user = UserUtils.getUser();
        goodsApp.getSqlMap().put("dsf",goodsAppService.dataScopeFilter(user, "o", "b"));

        return goodsApp;

    }
    @RequestMapping(value = "list")
    public String list(GoodsApp goodsApp, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<GoodsApp> page = goodsAppService.findPage(new Page<GoodsApp>(request, response), goodsApp);
        model.addAttribute("page", page);
        return "modules/purifier/goodsAppList";
    }

    @RequestMapping(value = "needFirstExaList")
    public String needFirstExaList(GoodsApp goodsApp, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<GoodsApp> page = goodsAppService.needFirstExaList(new Page<GoodsApp>(request, response), goodsApp);
        model.addAttribute("page", page);
        return "modules/purifier/needFirstExaList";
    }

    @RequestMapping(value = "needSecExaList")
    public String needSecExaList(GoodsApp goodsApp, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<GoodsApp> page = goodsAppService.needSecExaList(new Page<GoodsApp>(request, response), goodsApp);
        model.addAttribute("page", page);
        return "modules/purifier/needSecExaList";
    }

    @RequestMapping(value = "needShipList")
    public String needShipList(GoodsApp goodsApp, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<GoodsApp> page = goodsAppService.needShipList(new Page<GoodsApp>(request, response), goodsApp);
        model.addAttribute("page", page);
        return "modules/purifier/needShipList";
    }

    @RequestMapping(value = "needConsigneeList")
    public String needConsigneeList(GoodsApp goodsApp, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<GoodsApp> page = goodsAppService.needConsigneeList(new Page<GoodsApp>(request, response), goodsApp);
        model.addAttribute("page", page);
        return "modules/purifier/needConsigneeList";
    }


    @RequestMapping(value = "form")
    public String form(GoodsApp goodsApp, Model model) {
        model.addAttribute("goodsApp", goodsApp);
        return "modules/purifier/goodsAppForm";
    }

    @RequestMapping(value = "needFirstExaForm")
    public String needFirstExaForm(GoodsApp goodsApp, Model model) {
        model.addAttribute("goodsApp", goodsApp);
        return "modules/purifier/needFirstExaForm";
    }

    @RequestMapping(value = "needSecExaForm")
    public String needSecExaForm(GoodsApp goodsApp, Model model) {
        model.addAttribute("goodsApp", goodsApp);
        return "modules/purifier/needSecExaForm";
    }

    @RequestMapping(value = "needShipForm")
    public String needShipForm(GoodsApp goodsApp, Model model) {
        model.addAttribute("goodsApp", goodsApp);
        return "modules/purifier/needShipForm";
    }

    @RequestMapping(value = "needConsigneeForm")
    public String needConsigneeForm(GoodsApp goodsApp, Model model) {
        model.addAttribute("goodsApp", goodsApp);
        return "modules/purifier/needConsigneeForm";
    }

    @RequestMapping(value = "goodsAppRelForm")
    public String relForm() {
        return "modules/purifier/goodsAppRelForm";
    }

    @RequestMapping(value = "save")
    public String save(GoodsApp goodsApp, Model model, RedirectAttributes redirectAttributes,@RequestParam(value = "opt",required = false) String opt) {
        if (!beanValidator(model, goodsApp)){
            return form(goodsApp, model);
        }
        goodsAppService.insterGoodsApp(goodsApp);

        if("1".equals(goodsApp.getSecExaStatus()) && "ecsh".equals(opt)){//二次审核通过
            //发货通知
            OaNotify oaNotify = new OaNotify();
            oaNotify.setContent("申请单号"+goodsApp.getAppNo()+"二级审核通过，请查看发货提醒");
            oaNotify.setTitle("安排发货提醒");
            oaNotify.setStatus("1");
            oaNotify.setType("4");
            List<User> userList = systemService.findUser(new User(systemService.getRoleByEnname("ckgly")));
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
        }else if("0".equals(goodsApp.getSecExaStatus()) && "ecsh".equals(opt)){
            //发货通知
            OaNotify oaNotify = new OaNotify();
            oaNotify.setContent("申请单号"+goodsApp.getAppNo()+"二级审核不通过，请查看具体原因");
            oaNotify.setTitle("二级审核不通过");
            oaNotify.setStatus("1");
            oaNotify.setType("4");
            List<User> userList = systemService.findUser(new User(systemService.getRoleByEnname("ywy")));
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
        }else if("0".equals(goodsApp.getFirstExaStatus()) && "ycsh".equals(opt)){
            //发货通知
            OaNotify oaNotify = new OaNotify();
            oaNotify.setContent("申请单号"+goodsApp.getAppNo()+"一级审核不通过，请查看具体原因");
            oaNotify.setTitle("一级审核不通过");
            oaNotify.setStatus("1");
            oaNotify.setType("4");
            List<User> userList = systemService.findUser(new User(systemService.getRoleByEnname("ywy")));
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
        }else if(StringUtils.isNotEmpty(goodsApp.getShipAddress()) && "fh".equals(opt)){
            //发货通知
            OaNotify oaNotify = new OaNotify();
            oaNotify.setContent("申请单号"+goodsApp.getAppNo()+"已经发货，请查看发货提醒");
            oaNotify.setTitle("已发货提醒");
            oaNotify.setStatus("1");
            oaNotify.setType("4");
            List<User> userList = systemService.findUser(new User(systemService.getRoleByEnname("ywy")));
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


        addMessage(redirectAttributes, "保存成功");
        return "redirect:" + adminPath + "/goodsApp/list";
    }
    @RequestMapping(value = "update")
    public String update(GoodsApp goodsApp, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, goodsApp)){
            return form(goodsApp, model);
        }
        goodsAppService.updateGoodsApp(goodsApp);
        addMessage(redirectAttributes, "修改成功");
        return "redirect:" + adminPath + "/goodsApp/list";
    }

    @RequestMapping(value = "delete")
    public String delete(GoodsApp goodsApp, RedirectAttributes redirectAttributes) {
        goodsAppService.deleteGoodsApp(goodsApp);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/goodsApp/list";
    }

}
