/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.web.money;

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
import com.thinkgem.jeesite.modules.pj.entity.money.PjCommitmentMoney;
import com.thinkgem.jeesite.modules.pj.service.money.PjCommitmentMoneyService;

/**
 * 担当金基数维护Controller
 * @author liu
 * @version 2020-06-23
 */
@Controller
@RequestMapping(value = "${adminPath}/pj/money/pjCommitmentMoney")
public class PjCommitmentMoneyController extends BaseController {

	@Autowired
	private PjCommitmentMoneyService pjCommitmentMoneyService;
	
	@ModelAttribute
	public PjCommitmentMoney get(@RequestParam(required=false) String id) {
		PjCommitmentMoney entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pjCommitmentMoneyService.get(id);
		}
		if (entity == null){
			entity = new PjCommitmentMoney();
		}
		return entity;
	}
	
	@RequiresPermissions("pj:money:pjCommitmentMoney:view")
	@RequestMapping(value = {"list", ""})
	public String list(PjCommitmentMoney pjCommitmentMoney, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PjCommitmentMoney> page = pjCommitmentMoneyService.findPage(new Page<PjCommitmentMoney>(request, response), pjCommitmentMoney); 
		model.addAttribute("page", page);
		return "modules/pj/money/pjCommitmentMoneyList";
	}

	@RequiresPermissions("pj:money:pjCommitmentMoney:view")
	@RequestMapping(value = "form")
	public String form(PjCommitmentMoney pjCommitmentMoney, Model model) {
		model.addAttribute("pjCommitmentMoney", pjCommitmentMoney);
		return "modules/pj/money/pjCommitmentMoneyForm";
	}

	@RequiresPermissions("pj:money:pjCommitmentMoney:edit")
	@RequestMapping(value = "save")
	public String save(PjCommitmentMoney pjCommitmentMoney, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pjCommitmentMoney)){
			return form(pjCommitmentMoney, model);
		}
		pjCommitmentMoneyService.save(pjCommitmentMoney);
		addMessage(redirectAttributes, "保存担当金基数成功");
		return "redirect:"+Global.getAdminPath()+"/pj/money/pjCommitmentMoney/?repage";
	}
	
	@RequiresPermissions("pj:money:pjCommitmentMoney:edit")
	@RequestMapping(value = "delete")
	public String delete(PjCommitmentMoney pjCommitmentMoney, RedirectAttributes redirectAttributes) {
		pjCommitmentMoneyService.delete(pjCommitmentMoney);
		addMessage(redirectAttributes, "删除担当金基数成功");
		return "redirect:"+Global.getAdminPath()+"/pj/money/pjCommitmentMoney/?repage";
	}

}