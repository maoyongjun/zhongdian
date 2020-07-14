/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.web.allocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.dev.entity.allocationdetail.DevAllocationDetail;
import com.thinkgem.jeesite.modules.dev.entity.vehicle.DevVehicle;
import com.thinkgem.jeesite.modules.dev.entity.vehicledetail.DevVehicleDetail;
import com.thinkgem.jeesite.modules.dev.service.allocationdetail.DevAllocationDetailService;
import com.thinkgem.jeesite.modules.dev.service.vehicle.DevVehicleService;
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
import com.thinkgem.jeesite.modules.dev.entity.allocation.DevAllocation;
import com.thinkgem.jeesite.modules.dev.service.allocation.DevAllocationService;

import java.util.List;

/**
 * 设备调拨Controller
 * @author myj
 * @version 2020-07-07
 */
@Controller
@RequestMapping(value = "${adminPath}/dev/allocation/devAllocation")
public class DevAllocationController extends BaseController {
	//调拨单
	@Autowired
	private DevAllocationService devAllocationService;
	//调拨单明细
	@Autowired
	private DevAllocationDetailService devAllocationDetailService;
	//设备
	@Autowired
	private DevVehicleService devVehicleService;
	
	@ModelAttribute
	public DevAllocation get(@RequestParam(required=false) String id) {
		DevAllocation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = devAllocationService.get(id);
		}
		if (entity == null){
			entity = new DevAllocation();
		}
		return entity;
	}
	
	@RequiresPermissions("dev:allocation:devAllocation:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevAllocation devAllocation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevAllocation> page = devAllocationService.findPage(new Page<DevAllocation>(request, response), devAllocation); 
		model.addAttribute("page", page);
		return "modules/dev/allocation/devAllocationList";
	}

	@RequiresPermissions("dev:allocation:devAllocation:view")
	@RequestMapping(value = "form")
	public String form(DevAllocation devAllocation, Model model) {
		model.addAttribute("devAllocation", devAllocation);
		return "modules/dev/allocation/devAllocationForm";
	}

	@RequiresPermissions("dev:allocation:devAllocation:view")
	@RequestMapping(value = "sureAllocate")
	public String sureAllocate(DevAllocation devAllocation, RedirectAttributes redirectAttributes) {
		addMessage(redirectAttributes, "确认调拨成功");
		DevAllocationDetail condition = new DevAllocationDetail();
		condition.setAllocationId(devAllocation.getId());
		List<DevAllocationDetail> list = devAllocationDetailService.findList(condition);
		DevAllocation devAllocation1 = devAllocationService.get(devAllocation.getId());
		for(DevAllocationDetail detail:list){
			DevVehicle devVehicle = devVehicleService.get(detail.getDevId());
			devVehicle.setProjectId(devAllocation1.getProjectCheckinId());
			devVehicle.setProjectName(devAllocation1.getProjectCheckin());
			devVehicleService.save(devVehicle);
		}
		devAllocation1.setStatus(2);//已审核
		devAllocationService.save(devAllocation1);
		return "redirect:" + Global.getAdminPath() + "/dev/allocation/devAllocation/?repage";
	}


	@RequiresPermissions("dev:allocation:devAllocation:view")
	@RequestMapping(value = "formDetail")
	public String formDetail( HttpServletRequest request, HttpServletResponse response, DevAllocation devAllocation, Model model) {
		model.addAttribute("devAllocation", devAllocation);
		DevAllocationDetail devAllocationDetail = new DevAllocationDetail();
		devAllocationDetail.setAllocationId(devAllocation.getId());
		Page<DevAllocationDetail> page = devAllocationDetailService.findPage(new Page<DevAllocationDetail>(request,response), devAllocationDetail);
		model.addAttribute("page", page);
		return "modules/dev/allocation/devAllocationFormDetail";
	}

	@RequiresPermissions("dev:allocation:devAllocation:edit")
	@RequestMapping(value = "save")
	public String save(DevAllocation devAllocation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devAllocation)){
			return form(devAllocation, model);
		}
		devAllocationService.save(devAllocation);
		addMessage(redirectAttributes, "保存设备调拨成功");
		return "redirect:"+Global.getAdminPath()+"/dev/allocation/devAllocation/?repage";
	}
	
	@RequiresPermissions("dev:allocation:devAllocation:edit")
	@RequestMapping(value = "delete")
	public String delete(DevAllocation devAllocation, RedirectAttributes redirectAttributes) {
		devAllocationService.delete(devAllocation);
		addMessage(redirectAttributes, "删除设备调拨成功");
		return "redirect:"+Global.getAdminPath()+"/dev/allocation/devAllocation/?repage";
	}

}