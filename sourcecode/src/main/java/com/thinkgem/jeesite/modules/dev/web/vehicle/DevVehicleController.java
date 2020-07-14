/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.web.vehicle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.modules.dev.entity.material.DevMaterialProject;
import com.thinkgem.jeesite.modules.dev.entity.warehousereceipt.DevWarehouseReceipt;
import com.thinkgem.jeesite.modules.dev.service.warehousereceipt.DevWarehouseReceiptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dev.entity.vehicle.DevVehicle;
import com.thinkgem.jeesite.modules.dev.service.vehicle.DevVehicleService;

import java.util.List;
import java.util.Map;

/**
 * 车辆管理Controller
 * @author myj
 * @version 2020-07-07
 */
@Controller
@RequestMapping(value = "${adminPath}/dev/vehicle/devVehicle")
public class DevVehicleController extends BaseController {

	@Autowired
	private DevVehicleService devVehicleService;

	@Autowired
	private DevWarehouseReceiptService devWarehouseReceiptService;
	
	@ModelAttribute
	public DevVehicle get(@RequestParam(required=false) String id) {
		DevVehicle entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = devVehicleService.get(id);
		}
		if (entity == null){
			entity = new DevVehicle();
		}
		return entity;
	}
	
	@RequiresPermissions("dev:vehicle:devVehicle:view")
	@RequestMapping(value = {"list"})
	public String list(DevVehicle devVehicle, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevVehicle> page = devVehicleService.findPage(new Page<DevVehicle>(request, response), devVehicle); 
		model.addAttribute("page", page);
		return "modules/dev/vehicle/devVehicleList";
	}

	@RequiresPermissions("dev:vehicle:devVehicle:view")
	@RequestMapping(value = {"listByDevStatus", ""})
	public String listByDevStatus(DevVehicle devVehicle, HttpServletRequest request, HttpServletResponse response, Model model,String devStatus) {
		if(devVehicle.getStatus()==null){
			devVehicle.setStatus(Integer.valueOf(devStatus));
		}

		Page<DevVehicle> page = devVehicleService.findPage(new Page<DevVehicle>(request, response), devVehicle);
		model.addAttribute("page", page);
		model.addAttribute("devStatus", devStatus);
		return "modules/dev/vehicle/devVehicleList";
	}

	@RequiresPermissions("dev:vehicle:devVehicle:view")
	@RequestMapping(value = "form")
	public String form(DevVehicle devVehicle, Model model) {
		model.addAttribute("devVehicle", devVehicle);
		return "modules/dev/vehicle/devVehicleForm";
	}

	@RequiresPermissions("dev:vehicle:devVehicle:view")
	@RequestMapping(value = "formForPurchase")
	public String formForPurchase(DevVehicle devVehicle, Model model) {
		model.addAttribute("devVehicle", devVehicle);
		return "modules/dev/vehicle/devVehicleFormForPurchase";
	}

	@RequiresPermissions("dev:vehicle:devVehicle:edit")
	@RequestMapping(value = "save")
	public String save(DevVehicle devVehicle, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devVehicle)){
			return form(devVehicle, model);
		}
		devVehicleService.save(devVehicle);
		addMessage(redirectAttributes, "保存车辆管理成功");
		return "redirect:"+Global.getAdminPath()+"/dev/vehicle/devVehicle/?repage&devStatus=1";
	}
	@RequiresPermissions("dev:vehicle:devVehicle:edit")
	@RequestMapping(value = "saveRedirectWarehousereceipt")
	public String saveRedirectWarehousereceipt(DevVehicle devVehicle, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, devVehicle)){
			return form(devVehicle, model);
		}
		devVehicleService.save(devVehicle);
		DevWarehouseReceipt devWarehouseReceipt = devWarehouseReceiptService.get(devVehicle.getWarehouseReceiptId());
		model.addAttribute("devWarehouseReceipt", devWarehouseReceipt);
		DevVehicle devVehicleCondition = new DevVehicle();
		devVehicleCondition.setWarehouseReceiptId(devWarehouseReceipt.getId());
		Page<DevVehicle> page = devVehicleService.findPage(new Page<DevVehicle>(request, response), devVehicleCondition);
		model.addAttribute("page", page);
		return "modules/dev/warehousereceipt/devWarehouseReceiptFormDetail";
	}



	@RequiresPermissions("dev:vehicle:devVehicle:edit")
	@RequestMapping(value = "writeOff")
	public String writeOff(DevVehicle devVehicle, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devVehicle)){
			return form(devVehicle, model);
		}
		DevVehicle condition = new DevVehicle();
		condition.setId(devVehicle.getId());
		List<DevVehicle> list = devVehicleService.findList(condition);
		if(list.size()>0){
			DevVehicle devVehicleForUpdate = list.get(0);
			devVehicleForUpdate.setStatus(devVehicle.getStatus());
			devVehicleService.save(devVehicleForUpdate);
		}
		if(devVehicle.getStatus()==2){
			addMessage(redirectAttributes, "车辆状态成功变更为待核销");
		}else{
			addMessage(redirectAttributes, "车辆状态成功变更为已核销");
		}

		return "redirect:"+Global.getAdminPath()+"/dev/vehicle/devVehicle/?repage&devStatus=3";
	}


	
	@RequiresPermissions("dev:vehicle:devVehicle:edit")
	@RequestMapping(value = "delete")
	public String delete(DevVehicle devVehicle, RedirectAttributes redirectAttributes) {
		devVehicleService.delete(devVehicle);
		addMessage(redirectAttributes, "删除车辆管理成功");
		return "redirect:"+Global.getAdminPath()+"/dev/vehicle/devVehicle/?repage&devStatus=1";
	}

	@RequiresPermissions("dev:vehicle:devVehicle:edit")
	@RequestMapping(value = "deleteRedirectWarehousereceipt")
	public String deleteRedirectWarehousereceipt(DevVehicle devVehicle, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request, HttpServletResponse response) {
		devVehicleService.delete(devVehicle);
		addMessage(redirectAttributes, "删除车辆管理成功");
		DevWarehouseReceipt devWarehouseReceipt = devWarehouseReceiptService.get(devVehicle.getWarehouseReceiptId());
		model.addAttribute("devWarehouseReceipt", devWarehouseReceipt);
		DevVehicle devVehicleCondition = new DevVehicle();
		devVehicleCondition.setWarehouseReceiptId(devWarehouseReceipt.getId());
		Page<DevVehicle> page = devVehicleService.findPage(new Page<DevVehicle>(request, response), devVehicleCondition);
		model.addAttribute("page", page);
		return "modules/dev/warehousereceipt/devWarehouseReceiptFormDetail";
	}


	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String projectId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		DevVehicle devVehicle = new DevVehicle();
		devVehicle.setProjectId(projectId);
		devVehicle.setStatus(1);//正常的
		List<DevVehicle> list = devVehicleService.findList(devVehicle);
		for (int i=0; i<list.size(); i++){
			DevVehicle e = list.get(i);
			if (!StringUtils.isBlank(e.getId())){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", "0");
				map.put("name", e.getProjectName()+"_"+e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
}