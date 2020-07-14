/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.web.maintaindetail;

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
import com.thinkgem.jeesite.modules.dev.entity.maintaindetail.DevMaintainDetail;
import com.thinkgem.jeesite.modules.dev.service.maintaindetail.DevMaintainDetailService;

/**
 * 保养项次Controller
 * @author myj
 * @version 2020-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/dev/maintaindetail/devMaintainDetail")
public class DevMaintainDetailController extends BaseController {

	@Autowired
	private DevMaintainDetailService devMaintainDetailService;
	
	@ModelAttribute
	public DevMaintainDetail get(@RequestParam(required=false) String id) {
		DevMaintainDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = devMaintainDetailService.get(id);
		}
		if (entity == null){
			entity = new DevMaintainDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("dev:maintaindetail:devMaintainDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevMaintainDetail devMaintainDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevMaintainDetail> page = devMaintainDetailService.findPage(new Page<DevMaintainDetail>(request, response), devMaintainDetail); 
		model.addAttribute("page", page);
		return "modules/dev/maintaindetail/devMaintainDetailList";
	}

	@RequiresPermissions("dev:maintaindetail:devMaintainDetail:view")
	@RequestMapping(value = "form")
	public String form(DevMaintainDetail devMaintainDetail, Model model) {
		model.addAttribute("devMaintainDetail", devMaintainDetail);
		return "modules/dev/maintaindetail/devMaintainDetailForm";
	}

	@RequiresPermissions("dev:maintaindetail:devMaintainDetail:edit")
	@RequestMapping(value = "save")
	public String save(DevMaintainDetail devMaintainDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devMaintainDetail)){
			return form(devMaintainDetail, model);
		}
		devMaintainDetailService.save(devMaintainDetail);
		addMessage(redirectAttributes, "保存保养项次成功");
		return "redirect:"+Global.getAdminPath()+"/dev/maintaindetail/devMaintainDetail/?repage";
	}
	
	@RequiresPermissions("dev:maintaindetail:devMaintainDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(DevMaintainDetail devMaintainDetail, RedirectAttributes redirectAttributes) {
		devMaintainDetailService.delete(devMaintainDetail);
		addMessage(redirectAttributes, "删除保养项次成功");
		return "redirect:"+Global.getAdminPath()+"/dev/maintaindetail/devMaintainDetail/?repage";
	}

}