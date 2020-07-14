/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.web.summary;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.modules.pj.entity.prodchild.PjProdChild;
import com.thinkgem.jeesite.modules.pj.entity.prodparent.PjProdParent;
import com.thinkgem.jeesite.modules.pj.service.prodchild.PjProdChildService;
import com.thinkgem.jeesite.modules.pj.service.prodparent.PjProdParentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pj.entity.summary.PjRaterbySummary;
import com.thinkgem.jeesite.modules.pj.service.summary.PjRaterbySummaryService;

import java.util.List;

/**
 * 被评价人汇总Controller
 * @author liu
 * @version 2020-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/pj/summary/pjRaterbySummary")
public class PjRaterbySummaryController extends BaseController {

	@Autowired
	private PjRaterbySummaryService pjRaterbySummaryService;
	@Autowired
	private PjProdChildService pjProdChildService;
	@Autowired
	private PjProdParentService pjProdParentService;
	@ModelAttribute
	public PjRaterbySummary get(@RequestParam(required=false) String id) {
		PjRaterbySummary entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pjRaterbySummaryService.get(id);
		}
		if (entity == null){
			entity = new PjRaterbySummary();
		}
		return entity;
	}
	
	@RequiresPermissions("pj:summary:pjRaterbySummary:view")
	@RequestMapping(value = {"list", ""})
	public String list(PjRaterbySummary pjRaterbySummary, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PjRaterbySummary> page = pjRaterbySummaryService.findPage(new Page<PjRaterbySummary>(request, response), pjRaterbySummary); 
		model.addAttribute("page", page);
		return "modules/pj/summary/pjRaterbySummaryList";
	}

	@RequiresPermissions("pj:summary:pjRaterbySummary:view")
	@RequestMapping(value = "form")
	public String form(PjRaterbySummary pjRaterbySummary, Model model) {
		model.addAttribute("pjRaterbySummary", pjRaterbySummary);
		return "modules/pj/summary/pjRaterbySummaryForm";
	}


	
	@RequiresPermissions("pj:summary:pjRaterbySummary:edit")
	@RequestMapping(value = "delete")
	public String delete(PjRaterbySummary pjRaterbySummary, RedirectAttributes redirectAttributes) {
		pjRaterbySummaryService.delete(pjRaterbySummary);
		addMessage(redirectAttributes, "删除评价汇总信息成功");
		return "redirect:"+Global.getAdminPath()+"/pj/summary/pjRaterbySummary/?repage";
	}

//	//保存数据接口
//	@RequestMapping(value = "save")
//	@ResponseBody
//	public String save(@RequestBody JSONObject jsonObject) {
//		String parentId = jsonObject.getString("parentId");
//		PjProdParent pjProdParent=new PjProdParent();
//		pjProdParent.setId(parentId);
//		List<PjProdParent> parentList=pjProdParentService.findList(pjProdParent);
//		for (PjProdParent pjProdParent1:parentList) {
//			PjRaterbySummary pjRaterbySummary=new PjRaterbySummary();
//			pjRaterbySummary.setRaterbyId(pjProdParent1.getRaterbyId());//
//			pjRaterbySummary.setRaterId(pjProdParent1.getRaterId());
//			pjRaterbySummary.setReterCoefficient(pjProdParent1.);
//
//		}
//		pjRaterbySummaryService.save(pjRaterbySummary);
//		addMessage(redirectAttributes, "保存评价汇总信息成功");
//		return "redirect:"+Global.getAdminPath()+"/pj/summary/pjRaterbySummary/?repage";
//	}

}