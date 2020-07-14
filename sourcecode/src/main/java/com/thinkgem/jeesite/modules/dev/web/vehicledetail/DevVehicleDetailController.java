/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.web.vehicledetail;

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
import com.thinkgem.jeesite.modules.dev.entity.vehicledetail.DevVehicleDetail;
import com.thinkgem.jeesite.modules.dev.service.vehicledetail.DevVehicleDetailService;

/**
 * 车辆相关记录Controller
 * @author myj
 * @version 2020-07-07
 */
@Controller
@RequestMapping(value = "${adminPath}/dev/vehicledetail/devVehicleDetail")
public class DevVehicleDetailController extends BaseController {

	@Autowired
	private DevVehicleDetailService devVehicleDetailService;
	
	@ModelAttribute
	public DevVehicleDetail get(@RequestParam(required=false) String id) {
		DevVehicleDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = devVehicleDetailService.get(id);
		}
		if (entity == null){
			entity = new DevVehicleDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("dev:vehicledetail:devVehicleDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevVehicleDetail devVehicleDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevVehicleDetail> page = devVehicleDetailService.findPage(new Page<DevVehicleDetail>(request, response), devVehicleDetail); 
		model.addAttribute("page", page);
		return "modules/dev/vehicledetail/devVehicleDetailList";
	}

	@RequiresPermissions("dev:vehicledetail:devVehicleDetail:view")
	@RequestMapping(value = "form")
	public String form(DevVehicleDetail devVehicleDetail, Model model) {
		model.addAttribute("devVehicleDetail", devVehicleDetail);
		return "modules/dev/vehicledetail/devVehicleDetailForm";
	}

	@RequiresPermissions("dev:vehicledetail:devVehicleDetail:edit")
	@RequestMapping(value = "save")
	public String save(DevVehicleDetail devVehicleDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devVehicleDetail)){
			return form(devVehicleDetail, model);
		}
		devVehicleDetailService.save(devVehicleDetail);
		addMessage(redirectAttributes, "保存车辆相关记录成功");
		return "redirect:"+Global.getAdminPath()+"/dev/vehicledetail/devVehicleDetail/?repage";
	}
	
	@RequiresPermissions("dev:vehicledetail:devVehicleDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(DevVehicleDetail devVehicleDetail, RedirectAttributes redirectAttributes) {
		devVehicleDetailService.delete(devVehicleDetail);
		addMessage(redirectAttributes, "删除车辆相关记录成功");
		return "redirect:"+Global.getAdminPath()+"/dev/vehicledetail/devVehicleDetail/?repage";
	}

}