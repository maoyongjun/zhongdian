/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.web.prodbase;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.pj.entity.prodbase.PjProdBase;
import com.thinkgem.jeesite.modules.pj.entity.prodparent.PjProdParent;
import com.thinkgem.jeesite.modules.pj.service.prodbase.PjProdBaseService;
import com.thinkgem.jeesite.modules.pj.service.prodparent.PjProdParentService;
import com.thinkgem.jeesite.modules.pj.vo.eval.EvalDetails;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成评价基本信息Controller
 *
 * @author chang
 * @version 2020-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/pj/prodbase/pjProdBase")
public class PjProdBaseController extends BaseController {

    @Autowired
    private PjProdBaseService pjProdBaseService;
    @Autowired
    private PjProdParentService pjProdParentService;

    @ModelAttribute
    public PjProdBase get(@RequestParam(required = false) String id) {
        PjProdBase entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = pjProdBaseService.get(id);
        }
        if (entity == null) {
            entity = new PjProdBase();
        }
        return entity;
    }

    @RequiresPermissions("pj:prodbase:pjProdBase:view")
    @RequestMapping(value = {"list", ""})
    public String list(PjProdBase pjProdBase, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PjProdBase> page = pjProdBaseService.findPage(new Page<PjProdBase>(request, response), pjProdBase);
        model.addAttribute("page", page);
        return "modules/pj/prodbase/pjProdBaseList";
    }


    @RequiresPermissions("pj:prodbase:pjProdBase:view")
    @RequestMapping(value = "form")
    public String form(PjProdBase pjProdBase, Model model) {
        model.addAttribute("pjProdBase", pjProdBase);
        List<PjProdParent> pjProdParentList = new ArrayList<>();
        if (null != pjProdBase.getCode() && !"".equals(pjProdBase.getCode())) {
            PjProdParent query = new PjProdParent();
            query.setCode(pjProdBase.getCode());
            pjProdParentList = pjProdParentService.findList(query);
        }
        model.addAttribute("pjParentList", pjProdParentList);

        return "modules/pj/prodbase/pjProdBaseForm";
    }

    @RequiresPermissions("pj:prodbase:pjProdBase:edit")
    @RequestMapping(value = "save")
    public String save(PjProdBase pjProdBase, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, pjProdBase)) {
            return form(pjProdBase, model);
        }
        pjProdBaseService.save(pjProdBase);
        addMessage(redirectAttributes, "保存评价基本信息成功");
        return "redirect:" + Global.getAdminPath() + "/pj/prodbase/pjProdBase/?repage";
    }

    @RequiresPermissions("pj:prodbase:pjProdBase:edit")
    @RequestMapping(value = "delete")
    public String delete(PjProdBase pjProdBase, RedirectAttributes redirectAttributes) {
//        pjProdBaseService.delete(pjProdBase);
        pjProdBaseService.deleteAllEvalData(pjProdBase);
        addMessage(redirectAttributes, "删除评价基本信息成功");
        return "redirect:" + Global.getAdminPath() + "/pj/prodbase/pjProdBase/?repage";
    }

}