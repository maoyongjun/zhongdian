/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.web.devallwriteoff;

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
import com.thinkgem.jeesite.modules.dev.entity.devallwriteoff.DevAllWriteoff;
import com.thinkgem.jeesite.modules.dev.service.devallwriteoff.DevAllWriteoffService;

/**
 * 核销设备Controller
 * @author myj
 * @version 2020-07-23
 */
@Controller
@RequestMapping(value = "${adminPath}/dev/devallwriteoff/devAllWriteoff")
public class DevAllWriteoffController extends BaseController {

	@Autowired
	private DevAllWriteoffService devAllWriteoffService;
	
	@ModelAttribute
	public DevAllWriteoff get(@RequestParam(required=false) String id) {
		DevAllWriteoff entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = devAllWriteoffService.get(id);
		}
		if (entity == null){
			entity = new DevAllWriteoff();
		}
		return entity;
	}
	
	@RequiresPermissions("dev:devallwriteoff:devAllWriteoff:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevAllWriteoff devAllWriteoff, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevAllWriteoff> page = devAllWriteoffService.findPage(new Page<DevAllWriteoff>(request, response), devAllWriteoff); 
		model.addAttribute("page", page);
		return "modules/dev/devallwriteoff/devAllWriteoffList";
	}

	@RequiresPermissions("dev:devallwriteoff:devAllWriteoff:view")
	@RequestMapping(value = "form")
	public String form(DevAllWriteoff devAllWriteoff, Model model) {
		model.addAttribute("devAllWriteoff", devAllWriteoff);
		return "modules/dev/devallwriteoff/devAllWriteoffForm";
	}

	@RequiresPermissions("dev:devallwriteoff:devAllWriteoff:edit")
	@RequestMapping(value = "save")
	public String save(DevAllWriteoff devAllWriteoff, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devAllWriteoff)){
			return form(devAllWriteoff, model);
		}
		devAllWriteoffService.save(devAllWriteoff);
		addMessage(redirectAttributes, "保存核销设备成功");
		return "redirect:"+Global.getAdminPath()+"/dev/devallwriteoff/devAllWriteoff/?repage";
	}
	
	@RequiresPermissions("dev:devallwriteoff:devAllWriteoff:edit")
	@RequestMapping(value = "delete")
	public String delete(DevAllWriteoff devAllWriteoff, RedirectAttributes redirectAttributes) {
		devAllWriteoffService.delete(devAllWriteoff);
		addMessage(redirectAttributes, "删除核销设备成功");
		return "redirect:"+Global.getAdminPath()+"/dev/devallwriteoff/devAllWriteoff/?repage";
	}

}