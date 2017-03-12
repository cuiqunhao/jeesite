package com.thinkgem.jeesite.modules.purifier.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.purifier.entity.GoodsApp;
import com.thinkgem.jeesite.modules.purifier.entity.Ware;
import com.thinkgem.jeesite.modules.purifier.service.WareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Admin on 2017/3/12.
 */
@Controller
@RequestMapping(value = "/ware")
public class WareController extends BaseController {
    @Autowired
    private WareService wareService;

    @ModelAttribute
    public Ware get(@RequestParam(value = "id",required = false) Long id){
        if (id != null){
            Ware ware = new Ware();
            ware.setId(id.toString());
            return wareService.get(ware);
        }else{
            return new Ware();
        }
    }

    @RequestMapping(value = "list")
    public String list(Ware ware, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Ware> page = wareService.findPage(new Page<Ware>(request, response), ware);
        model.addAttribute("page", page);
        return "modules/ware/wareList";
    }

    @RequestMapping(value = "form")
    public String form(Ware ware,Model model){
        model.addAttribute("ware",ware);
        return "modules/ware/wareForm";
    }





}
