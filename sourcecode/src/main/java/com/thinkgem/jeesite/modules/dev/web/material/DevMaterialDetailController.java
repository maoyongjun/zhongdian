/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.web.material;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.dev.entity.material.DevMaterialProject;
import com.thinkgem.jeesite.modules.dev.service.material.DevMaterialProjectService;
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
import com.thinkgem.jeesite.modules.dev.entity.material.DevMaterialDetail;
import com.thinkgem.jeesite.modules.dev.service.material.DevMaterialDetailService;

/**
 * 工程物资Controller
 * @author myj
 * @version 2020-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/dev/material/devMaterialDetail")
public class DevMaterialDetailController extends BaseController {

	@Autowired
	private DevMaterialDetailService devMaterialDetailService;

	@Autowired
	private DevMaterialProjectService devMaterialProjectService;
	
	@ModelAttribute
	public DevMaterialDetail get(@RequestParam(required=false) String id) {
		DevMaterialDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = devMaterialDetailService.get(id);
		}
		if (entity == null){
			entity = new DevMaterialDetail();
		}else {
			DevMaterialProject devMaterialProject = devMaterialProjectService.get(entity.getProjectId());
			entity.setProject(devMaterialProject);
		}
		return entity;
	}
	
	@RequiresPermissions("dev:material:devMaterialDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevMaterialDetail devMaterialDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevMaterialDetail> page = devMaterialDetailService.findPage(new Page<DevMaterialDetail>(request, response), devMaterialDetail);
		for(DevMaterialDetail detail:page.getList()){
			DevMaterialProject devMaterialProject = devMaterialProjectService.get(detail.getProjectId());
			detail.setProject(devMaterialProject);
		}
		model.addAttribute("page", page);
		return "modules/dev/material/devMaterialDetailList";
	}

	@RequiresPermissions("dev:material:devMaterialDetail:view")
	@RequestMapping(value = "form")
	public String form(DevMaterialDetail devMaterialDetail, Model model) {
		model.addAttribute("devMaterialDetail", devMaterialDetail);
		return "modules/dev/material/devMaterialDetailForm";
	}

	@RequiresPermissions("dev:material:devMaterialDetail:edit")
	@RequestMapping(value = "save")
	public String save(DevMaterialDetail devMaterialDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devMaterialDetail)){
			return form(devMaterialDetail, model);
		}
		devMaterialDetailService.save(devMaterialDetail);
		addMessage(redirectAttributes, "保存工程物资成功");
		return "redirect:"+Global.getAdminPath()+"/dev/material/devMaterialDetail/?repage";
	}
	
	@RequiresPermissions("dev:material:devMaterialDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(DevMaterialDetail devMaterialDetail, RedirectAttributes redirectAttributes) {
		devMaterialDetailService.delete(devMaterialDetail);
		addMessage(redirectAttributes, "删除工程物资成功");
		return "redirect:"+Global.getAdminPath()+"/dev/material/devMaterialDetail/?repage";
	}

}