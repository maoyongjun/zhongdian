/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.web.warehousereceipt;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.dev.entity.vehicle.DevVehicle;
import com.thinkgem.jeesite.modules.dev.entity.warehousereceipt.DevWarehouseReceipt;
import com.thinkgem.jeesite.modules.dev.service.vehicle.DevVehicleService;
import com.thinkgem.jeesite.modules.dev.service.warehousereceipt.DevWarehouseReceiptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 入库单Controller
 * @author myj
 * @version 2020-07-07
 */
@Controller
@RequestMapping(value = "${adminPath}/dev/warehousereceipt/devWarehouseReceipt")
public class DevWarehouseReceiptController extends BaseController {

	@Autowired
	private DevVehicleService devVehicleService;

	@Autowired
	private DevWarehouseReceiptService devWarehouseReceiptService;
	
	@ModelAttribute
	public DevWarehouseReceipt get(@RequestParam(required=false) String id) {
		DevWarehouseReceipt entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = devWarehouseReceiptService.get(id);
		}
		if (entity == null){
			entity = new DevWarehouseReceipt();
		}
		return entity;
	}
	
	@RequiresPermissions("dev:warehousereceipt:devWarehouseReceipt:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevWarehouseReceipt devWarehouseReceipt, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevWarehouseReceipt> page = devWarehouseReceiptService.findPage(new Page<DevWarehouseReceipt>(request, response), devWarehouseReceipt); 
		model.addAttribute("page", page);
		return "modules/dev/warehousereceipt/devWarehouseReceiptList";
	}

	@RequiresPermissions("dev:warehousereceipt:devWarehouseReceipt:view")
	@RequestMapping(value = "form")
	public String form(DevWarehouseReceipt devWarehouseReceipt, Model model) {
		model.addAttribute("devWarehouseReceipt", devWarehouseReceipt);
		return "modules/dev/warehousereceipt/devWarehouseReceiptForm";
	}

	@RequiresPermissions("dev:warehousereceipt:devWarehouseReceipt:view")
	@RequestMapping(value = "formDetail")
	public String formDetail(DevVehicle devVehicle, HttpServletRequest request, HttpServletResponse response, DevWarehouseReceipt devWarehouseReceipt, Model model) {
		model.addAttribute("devWarehouseReceipt", devWarehouseReceipt);
		devVehicle.setWarehouseReceiptId(devWarehouseReceipt.getId());
		Page<DevVehicle> page = devVehicleService.findPage(new Page<DevVehicle>(request, response), devVehicle);
		model.addAttribute("page", page);
		return "modules/dev/warehousereceipt/devWarehouseReceiptFormDetail";
	}

	@RequiresPermissions("dev:warehousereceipt:devWarehouseReceipt:edit")
	@RequestMapping(value = "save")
	public String save(DevWarehouseReceipt devWarehouseReceipt, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devWarehouseReceipt)){
			return form(devWarehouseReceipt, model);
		}
		devWarehouseReceiptService.save(devWarehouseReceipt);
		addMessage(redirectAttributes, "保存入库单成功");
		return "redirect:"+Global.getAdminPath()+"/dev/warehousereceipt/devWarehouseReceipt/?repage";
	}
	
	@RequiresPermissions("dev:warehousereceipt:devWarehouseReceipt:edit")
	@RequestMapping(value = "delete")
	public String delete(DevWarehouseReceipt devWarehouseReceipt, RedirectAttributes redirectAttributes) {
		devWarehouseReceiptService.delete(devWarehouseReceipt);
		addMessage(redirectAttributes, "删除入库单成功");
		return "redirect:"+Global.getAdminPath()+"/dev/warehousereceipt/devWarehouseReceipt/?repage";
	}

}