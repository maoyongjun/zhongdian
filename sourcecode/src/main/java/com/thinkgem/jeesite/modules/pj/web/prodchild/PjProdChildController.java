/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.web.prodchild;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.pj.entity.prodchild.PjProdChild;
import com.thinkgem.jeesite.modules.pj.service.prodchild.PjProdChildService;

/**
 * 生成评价子表信息Controller
 * @author victor.chang
 * @version 2020-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/pj/prodchild/pjProdChild")
public class PjProdChildController extends BaseController {

	@Autowired
	private PjProdChildService pjProdChildService;
	
	@ModelAttribute
	public PjProdChild get(@RequestParam(required=false) String id) {
		PjProdChild entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pjProdChildService.get(id);
		}
		if (entity == null){
			entity = new PjProdChild();
		}
		return entity;
	}
	
	@RequiresPermissions("pj:prodchild:pjProdChild:view")
	@RequestMapping(value = {"list", ""})
	public String list(PjProdChild pjProdChild, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PjProdChild> page = pjProdChildService.findPage(new Page<PjProdChild>(request, response), pjProdChild); 
		model.addAttribute("page", page);
		return "modules/pj/prodchild/pjProdChildList";
	}

	@RequiresPermissions("pj:prodchild:pjProdChild:view")
	@RequestMapping(value = "form")
	public String form(PjProdChild pjProdChild, Model model) {
		model.addAttribute("pjProdChild", pjProdChild);
		return "modules/pj/prodchild/pjProdChildForm";
	}

	@RequiresPermissions("pj:prodchild:pjProdChild:edit")
	@RequestMapping(value = "save")
	public String save(PjProdChild pjProdChild, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pjProdChild)){
			return form(pjProdChild, model);
		}
		pjProdChildService.save(pjProdChild);
		addMessage(redirectAttributes, "保存评价细则信息成功");
		return "redirect:"+Global.getAdminPath()+"/pj/prodparent/pjProdParent/form?id="+pjProdChild.getParentId();
	}
	
	@RequiresPermissions("pj:prodchild:pjProdChild:edit")
	@RequestMapping(value = "delete")
	public String delete(PjProdChild pjProdChild, RedirectAttributes redirectAttributes) {
		pjProdChildService.delete(pjProdChild);
		addMessage(redirectAttributes, "删除评价细则信息成功");
		return "redirect:"+Global.getAdminPath()+"/pj/prodchild/pjProdChild/?repage";
	}

}