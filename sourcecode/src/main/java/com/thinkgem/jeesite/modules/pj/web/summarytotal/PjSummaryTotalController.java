/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.web.summarytotal;

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
import com.thinkgem.jeesite.modules.pj.entity.summarytotal.PjSummaryTotal;
import com.thinkgem.jeesite.modules.pj.service.summarytotal.PjSummaryTotalService;

/**
 * 评价总分汇总Controller
 * @author chang
 * @version 2020-07-08
 */
@Controller
@RequestMapping(value = "${adminPath}/pj/summarytotal/pjSummaryTotal")
public class PjSummaryTotalController extends BaseController {

	@Autowired
	private PjSummaryTotalService pjSummaryTotalService;
	
	@ModelAttribute
	public PjSummaryTotal get(@RequestParam(required=false) String id) {
		PjSummaryTotal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pjSummaryTotalService.get(id);
		}
		if (entity == null){
			entity = new PjSummaryTotal();
		}
		return entity;
	}
	
	@RequiresPermissions("pj:summarytotal:pjSummaryTotal:view")
	@RequestMapping(value = {"list", ""})
	public String list(PjSummaryTotal pjSummaryTotal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PjSummaryTotal> page = pjSummaryTotalService.findPage(new Page<PjSummaryTotal>(request, response), pjSummaryTotal); 
		model.addAttribute("page", page);
		return "modules/pj/summarytotal/pjSummaryTotalList";
	}

	@RequiresPermissions("pj:summarytotal:pjSummaryTotal:view")
	@RequestMapping(value = "form")
	public String form(PjSummaryTotal pjSummaryTotal, Model model) {
		model.addAttribute("pjSummaryTotal", pjSummaryTotal);
		return "modules/pj/summarytotal/pjSummaryTotalForm";
	}

	@RequiresPermissions("pj:summarytotal:pjSummaryTotal:edit")
	@RequestMapping(value = "save")
	public String save(PjSummaryTotal pjSummaryTotal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pjSummaryTotal)){
			return form(pjSummaryTotal, model);
		}
		pjSummaryTotalService.save(pjSummaryTotal);
		addMessage(redirectAttributes, "保存评价总分成功");
		return "redirect:"+Global.getAdminPath()+"/pj/summarytotal/pjSummaryTotal/?repage";
	}
	
	@RequiresPermissions("pj:summarytotal:pjSummaryTotal:edit")
	@RequestMapping(value = "delete")
	public String delete(PjSummaryTotal pjSummaryTotal, RedirectAttributes redirectAttributes) {
		pjSummaryTotalService.delete(pjSummaryTotal);
		addMessage(redirectAttributes, "删除评价总分成功");
		return "redirect:"+Global.getAdminPath()+"/pj/summarytotal/pjSummaryTotal/?repage";
	}

}