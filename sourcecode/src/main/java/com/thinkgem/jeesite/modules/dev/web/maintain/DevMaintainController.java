/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.web.maintain;

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
import com.thinkgem.jeesite.modules.dev.entity.maintain.DevMaintain;
import com.thinkgem.jeesite.modules.dev.service.maintain.DevMaintainService;

/**
 * 设备保养Controller
 * @author myj
 * @version 2020-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/dev/maintain/devMaintain")
public class DevMaintainController extends BaseController {

	@Autowired
	private DevMaintainService devMaintainService;
	
	@ModelAttribute
	public DevMaintain get(@RequestParam(required=false) String id) {
		DevMaintain entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = devMaintainService.get(id);
		}
		if (entity == null){
			entity = new DevMaintain();
		}
		return entity;
	}
	
	@RequiresPermissions("dev:maintain:devMaintain:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevMaintain devMaintain, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevMaintain> page = devMaintainService.findPage(new Page<DevMaintain>(request, response), devMaintain); 
		model.addAttribute("page", page);
		return "modules/dev/maintain/devMaintainList";
	}

	@RequiresPermissions("dev:maintain:devMaintain:view")
	@RequestMapping(value = "form")
	public String form(DevMaintain devMaintain, Model model) {
		model.addAttribute("devMaintain", devMaintain);
		return "modules/dev/maintain/devMaintainForm";
	}

	@RequiresPermissions("dev:maintain:devMaintain:edit")
	@RequestMapping(value = "save")
	public String save(DevMaintain devMaintain, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devMaintain)){
			return form(devMaintain, model);
		}
		devMaintainService.save(devMaintain);
		addMessage(redirectAttributes, "保存设备保养成功");
		return "redirect:"+Global.getAdminPath()+"/dev/maintain/devMaintain/?repage";
	}
	
	@RequiresPermissions("dev:maintain:devMaintain:edit")
	@RequestMapping(value = "delete")
	public String delete(DevMaintain devMaintain, RedirectAttributes redirectAttributes) {
		devMaintainService.delete(devMaintain);
		addMessage(redirectAttributes, "删除设备保养成功");
		return "redirect:"+Global.getAdminPath()+"/dev/maintain/devMaintain/?repage";
	}

}