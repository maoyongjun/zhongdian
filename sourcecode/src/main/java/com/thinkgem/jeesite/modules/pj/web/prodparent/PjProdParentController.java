/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.web.prodparent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.pj.entity.prodchild.PjProdChild;
import com.thinkgem.jeesite.modules.pj.service.prodchild.PjProdChildService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pj.entity.prodparent.PjProdParent;
import com.thinkgem.jeesite.modules.pj.service.prodparent.PjProdParentService;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成评价父表Controller
 *
 * @author victor.chang
 * @version 2020-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/pj/prodparent/pjProdParent")
public class PjProdParentController extends BaseController {

    @Autowired
    private PjProdParentService pjProdParentService;

    @Autowired
    private PjProdChildService pjProdChildService;

    @ModelAttribute
    public PjProdParent get(@RequestParam(required = false) String id) {
        PjProdParent entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = pjProdParentService.get(id);
        }
        if (entity == null) {
            entity = new PjProdParent();
        }
        return entity;
    }

    @RequiresPermissions("pj:prodparent:pjProdParent:view")
    @RequestMapping(value = {"list", ""})
    public String list(PjProdParent pjProdParent, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PjProdParent> page = pjProdParentService.findPage(new Page<PjProdParent>(request, response), pjProdParent);
        model.addAttribute("page", page);
        return "modules/pj/prodparent/pjProdParentList";
    }

    @RequiresPermissions("pj:prodparent:pjProdParent:view")
    @RequestMapping(value = "form")
    public String form(PjProdParent pjProdParent, Model model) {
        PjProdChild pjProdChild = new PjProdChild();
        List<PjProdChild> pjProdChildList = new ArrayList<>();
        if (null != pjProdParent.getId() && !"".equals(pjProdParent.getId())) {
            pjProdChild.setParentId(pjProdParent.getId());
            pjProdChildList = pjProdChildService.findList(pjProdChild);
        }
        model.addAttribute("pjProdParent", pjProdParent);
        model.addAttribute("pjProdChildList", pjProdChildList);
        return "modules/pj/prodparent/pjProdParentForm";
    }

    @RequiresPermissions("pj:prodparent:pjProdParent:edit")
    @RequestMapping(value = "save")
    public String save(PjProdParent pjProdParent, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, pjProdParent)) {
            return form(pjProdParent, model);
        }
        pjProdParentService.save(pjProdParent);
        addMessage(redirectAttributes, "保存评价信息成功");
        return "redirect:" + Global.getAdminPath() + "/pj/prodbase/pjProdBase/?repage";
    }

    @RequiresPermissions("pj:prodparent:pjProdParent:edit")
    @RequestMapping(value = "delete")
    public String delete(PjProdParent pjProdParent, RedirectAttributes redirectAttributes) {
        pjProdParentService.delete(pjProdParent);
        addMessage(redirectAttributes, "删除评价信息成功");
        return "redirect:" + Global.getAdminPath() + "/pj/prodparent/pjProdParent/?repage";
    }

}