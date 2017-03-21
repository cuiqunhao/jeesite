package com.thinkgem.jeesite.modules.purifier.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.purifier.entity.GoodsApp;
import com.thinkgem.jeesite.modules.purifier.service.GoodsAppService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private GoodsAppService goodsAppService;

    @ModelAttribute
    public GoodsApp get(@RequestParam(value = "id",required = false) Long id) {
        if (id != null){
            return goodsAppService.getByGoodsAppId(id);
        }else{
            return new GoodsApp();
        }
    }
    @RequestMapping(value = "list")
    public String list(GoodsApp goodsApp, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<GoodsApp> page = goodsAppService.findPage(new Page<GoodsApp>(request, response), goodsApp);
        model.addAttribute("page", page);
        return "modules/purifier/goodsAppList";
    }

    @RequestMapping(value = "form")
    public String form(GoodsApp goodsApp, Model model) {
        model.addAttribute("goodsApp", goodsApp);
        return "modules/purifier/goodsAppForm";
    }

    @RequestMapping(value = "goodsAppRelForm")
    public String relForm() {
        return "modules/purifier/goodsAppRelForm";
    }

    @RequestMapping(value = "save")
    public String save(GoodsApp goodsApp, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, goodsApp)){
            return form(goodsApp, model);
        }
        goodsAppService.insterGoodsApp(goodsApp);
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
        return "redirect:" + adminPath + "/goodsApp/goodsAppList";
    }

}
