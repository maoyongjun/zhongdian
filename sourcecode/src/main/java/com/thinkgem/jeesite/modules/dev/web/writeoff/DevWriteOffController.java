/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.web.writeoff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.dev.entity.vehicle.DevVehicle;
import com.thinkgem.jeesite.modules.dev.entity.writeoffdetail.DevWriteOffDetail;
import com.thinkgem.jeesite.modules.dev.service.vehicle.DevVehicleService;
import com.thinkgem.jeesite.modules.dev.service.writeoffdetail.DevWriteOffDetailService;
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
import com.thinkgem.jeesite.modules.dev.entity.writeoff.DevWriteOff;
import com.thinkgem.jeesite.modules.dev.service.writeoff.DevWriteOffService;

import java.util.List;

/**
 * 核销单Controller
 * @author myj
 * @version 2020-07-09
 */
@Controller
@RequestMapping(value = "${adminPath}/dev/writeoff/devWriteOff")
public class DevWriteOffController extends BaseController {

	@Autowired
	private DevWriteOffService devWriteOffService;

	@Autowired
	private DevWriteOffDetailService devWriteOffDetailService;

	@Autowired
	private DevVehicleService devVehicleService;

	@ModelAttribute
	public DevWriteOff get(@RequestParam(required=false) String id) {
		DevWriteOff entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = devWriteOffService.get(id);
		}
		if (entity == null){
			entity = new DevWriteOff();
		}
		return entity;
	}
	
	@RequiresPermissions("dev:writeoff:devWriteOff:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevWriteOff devWriteOff, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevWriteOff> page = devWriteOffService.findPage(new Page<DevWriteOff>(request, response), devWriteOff); 
		model.addAttribute("page", page);
		return "modules/dev/writeoff/devWriteOffList";
	}

	@RequiresPermissions("dev:writeoff:devWriteOff:view")
	@RequestMapping(value = "form")
	public String form(DevWriteOff devWriteOff, Model model) {
		model.addAttribute("devWriteOff", devWriteOff);
		return "modules/dev/writeoff/devWriteOffForm";
	}


	@RequiresPermissions("dev:writeoff:devWriteOff:view")
	@RequestMapping(value = "formDetail")
	public String formDetail(DevWriteOff devWriteOff, Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("devWriteOff", devWriteOff);
		DevWriteOffDetail devWriteOffDetailCondition = new DevWriteOffDetail();
		devWriteOffDetailCondition.setWriteoffId(devWriteOff.getId());
		Page<DevWriteOffDetail> page = devWriteOffDetailService.findPage(new Page<DevWriteOffDetail>(request, response), devWriteOffDetailCondition);
		model.addAttribute("page", page);
		return "modules/dev/writeoff/devWriteOffFormDetail";
	}

	@RequiresPermissions("dev:writeoff:devWriteOff:edit")
	@RequestMapping(value = "save")
	public String save(DevWriteOff devWriteOff, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devWriteOff)){
			return form(devWriteOff, model);
		}
		devWriteOffService.save(devWriteOff);
		addMessage(redirectAttributes, "保存核销单成功");
		return "redirect:"+Global.getAdminPath()+"/dev/writeoff/devWriteOff/?repage";
	}
	
	@RequiresPermissions("dev:writeoff:devWriteOff:edit")
	@RequestMapping(value = "delete")
	public String delete(DevWriteOff devWriteOff, RedirectAttributes redirectAttributes) {
		devWriteOffService.delete(devWriteOff);
		addMessage(redirectAttributes, "删除核销单成功");
		return "redirect:"+Global.getAdminPath()+"/dev/writeoff/devWriteOff/?repage";
	}

	@RequiresPermissions("dev:writeoff:devWriteOff:edit")
	@RequestMapping(value = "sureWriteOff")
	public String sureWriteOff(DevWriteOff devWriteOff, RedirectAttributes redirectAttributes) {
		devWriteOff.setStatus(devWriteOff.getStatus());//已核销
		devWriteOffService.save(devWriteOff);
		DevWriteOffDetail devWriteOffDetailCondition = new DevWriteOffDetail();
		devWriteOffDetailCondition.setWriteoffId(devWriteOff.getId());
		List<DevWriteOffDetail> list = devWriteOffDetailService.findList(devWriteOffDetailCondition);
		//将设备状态修改为待核销
		for(DevWriteOffDetail devWriteOffDetail:list){
			DevVehicle devVehicleCondition = new DevVehicle();
			devVehicleCondition.setId(devWriteOffDetail.getDevid());
			DevVehicle devVehicle = devVehicleService.get(devVehicleCondition);
			if(devVehicle!=null){
				devVehicle.setStatus(devWriteOff.getStatus());//待核销
				devVehicleService.save(devVehicle);
			}

		}
		if(devWriteOff.getStatus() == 2){
			addMessage(redirectAttributes, "申请核销成功");
		}else if(devWriteOff.getStatus() == 3){
			addMessage(redirectAttributes, "确认核销成功");
		}

		return "redirect:"+Global.getAdminPath()+"/dev/writeoff/devWriteOff/?repage";
	}



}