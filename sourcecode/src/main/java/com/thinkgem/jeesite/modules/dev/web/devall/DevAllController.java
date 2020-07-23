/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.web.devall;

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
import com.thinkgem.jeesite.modules.dev.entity.devall.DevAll;
import com.thinkgem.jeesite.modules.dev.service.devall.DevAllService;

/**
 * 设备Controller
 * @author myj
 * @version 2020-07-23
 */
@Controller
@RequestMapping(value = "${adminPath}/dev/devall/devAll")
public class DevAllController extends BaseController {

	@Autowired
	private DevAllService devAllService;
	
	@ModelAttribute
	public DevAll get(@RequestParam(required=false) String id) {
		DevAll entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = devAllService.get(id);
		}
		if (entity == null){
			entity = new DevAll();
		}
		return entity;
	}
	
	@RequiresPermissions("dev:devall:devAll:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevAll devAll, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevAll> page = devAllService.findPage(new Page<DevAll>(request, response), devAll); 
		model.addAttribute("page", page);
		return "modules/dev/devall/devAllList";
	}

	@RequiresPermissions("dev:devall:devAll:view")
	@RequestMapping(value = "form")
	public String form(DevAll devAll, Model model) {
		model.addAttribute("devAll", devAll);
		return "modules/dev/devall/devAllForm";
	}

	@RequiresPermissions("dev:devall:devAll:edit")
	@RequestMapping(value = "save")
	public String save(DevAll devAll, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devAll)){
			return form(devAll, model);
		}
		devAllService.save(devAll);
		addMessage(redirectAttributes, "保存设备成功");
		return "redirect:"+Global.getAdminPath()+"/dev/devall/devAll/?repage";
	}
	
	@RequiresPermissions("dev:devall:devAll:edit")
	@RequestMapping(value = "delete")
	public String delete(DevAll devAll, RedirectAttributes redirectAttributes) {
		devAllService.delete(devAll);
		addMessage(redirectAttributes, "删除设备成功");
		return "redirect:"+Global.getAdminPath()+"/dev/devall/devAll/?repage";
	}

}