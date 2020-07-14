/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.web.warehouse;

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
import com.thinkgem.jeesite.modules.dev.entity.warehouse.DevInWarehouse;
import com.thinkgem.jeesite.modules.dev.service.warehouse.DevInWarehouseService;

/**
 * 在库设备Controller
 * @author myj
 * @version 2020-07-13
 */
@Controller
@RequestMapping(value = "${adminPath}/dev/warehouse/devInWarehouse")
public class DevInWarehouseController extends BaseController {

	@Autowired
	private DevInWarehouseService devInWarehouseService;
	
	@ModelAttribute
	public DevInWarehouse get(@RequestParam(required=false) String id) {
		DevInWarehouse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = devInWarehouseService.get(id);
		}
		if (entity == null){
			entity = new DevInWarehouse();
		}
		return entity;
	}
	
	@RequiresPermissions("dev:warehouse:devInWarehouse:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevInWarehouse devInWarehouse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevInWarehouse> page = devInWarehouseService.findPage(new Page<DevInWarehouse>(request, response), devInWarehouse); 
		model.addAttribute("page", page);
		return "modules/dev/warehouse/devInWarehouseList";
	}


	@RequiresPermissions("dev:warehouse:devInWarehouse:view")
	@RequestMapping(value = "form")
	public String form(DevInWarehouse devInWarehouse, Model model) {
		model.addAttribute("devInWarehouse", devInWarehouse);
		return "modules/dev/warehouse/devInWarehouseForm";
	}

	@RequiresPermissions("dev:warehouse:devInWarehouse:edit")
	@RequestMapping(value = "save")
	public String save(DevInWarehouse devInWarehouse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devInWarehouse)){
			return form(devInWarehouse, model);
		}
		devInWarehouseService.save(devInWarehouse);
		addMessage(redirectAttributes, "保存在库设备成功");
		return "redirect:"+Global.getAdminPath()+"/dev/warehouse/devInWarehouse/?repage&type="+devInWarehouse.getType();
	}
	
	@RequiresPermissions("dev:warehouse:devInWarehouse:edit")
	@RequestMapping(value = "delete")
	public String delete(DevInWarehouse devInWarehouse, RedirectAttributes redirectAttributes) {
		devInWarehouseService.delete(devInWarehouse);
		addMessage(redirectAttributes, "删除在库设备成功");
		return "redirect:"+Global.getAdminPath()+"/dev/warehouse/devInWarehouse/?repage";
	}

}