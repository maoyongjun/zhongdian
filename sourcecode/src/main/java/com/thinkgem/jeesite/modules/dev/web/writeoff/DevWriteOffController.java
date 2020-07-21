/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.web.writeoff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.dev.entity.vehicle.DevVehicle;
import com.thinkgem.jeesite.modules.dev.entity.warehouse.DevInWarehouse;
import com.thinkgem.jeesite.modules.dev.entity.writeoffdetail.DevWriteOffDetail;
import com.thinkgem.jeesite.modules.dev.service.vehicle.DevVehicleService;
import com.thinkgem.jeesite.modules.dev.service.warehouse.DevInWarehouseService;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

	@Autowired
	private DevInWarehouseService devInWarehouseService;

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


	@RequiresPermissions("dev:writeoff:devWriteOff:view")
	@RequestMapping(value = "createWriteOff")
	public String createWriteOff(String[] ids,String devtype, Model model, HttpServletRequest request, HttpServletResponse response) {
		//查询今天的核销单数量
		Date beginDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String formatBeginDate = sdf.format(beginDate);
		try {
			beginDate=sdf.parse(formatBeginDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DevWriteOff devWriteOffCondition = new DevWriteOff();
		devWriteOffCondition.setApplicantDate(beginDate);
		List<DevWriteOff> list = devWriteOffService.findList(devWriteOffCondition);
		int size = list.size();
		DevWriteOff devWriteOff = new DevWriteOff();
		devWriteOff.setId(UUID.randomUUID().toString());
		devWriteOff.setName("核销单"+formatBeginDate+String.format("%03d",size+1));
		devWriteOff.setStatus(1);

		devWriteOff.setApplicantDate(new Date());

		model.addAttribute("devWriteOff", devWriteOff);
		for(String id : ids){
			DevWriteOffDetail devWriteOffDetail = new DevWriteOffDetail();
			devWriteOffDetail.setDevid(id);
			devWriteOffDetail.setWriteoffId(devWriteOff.getId());
			if("A4".equals(devtype)){
				DevVehicle devVehicle = devVehicleService.get(id);
				devWriteOffDetail.setDevname(devVehicle.getName());
				devWriteOff.setProjectname(devVehicle.getProjectName());
				devWriteOff.setProjectid(devVehicle.getProjectId());

			}else{
				DevInWarehouse devInWarehouse = devInWarehouseService.get(id);
				devWriteOffDetail.setDevname(devInWarehouse.getName());
				devWriteOff.setProjectid(devInWarehouse.getPurchaseProjectId());
				devWriteOff.setProjectname(devInWarehouse.getPurchaseProject());

			}
			devWriteOffDetailService.save(devWriteOffDetail);

		}
		devWriteOffService.save(devWriteOff);
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
		return "redirect:"+Global.getAdminPath()+"/dev/writeoff/devWriteOff/?repage&devtype="+devWriteOff.getDevtype();
	}
	
	@RequiresPermissions("dev:writeoff:devWriteOff:edit")
	@RequestMapping(value = "delete")
	public String delete(DevWriteOff devWriteOff, RedirectAttributes redirectAttributes) {
		devWriteOffService.delete(devWriteOff);
		addMessage(redirectAttributes, "删除核销单成功");
		return "redirect:"+Global.getAdminPath()+"/dev/writeoff/devWriteOff/?repage&devtype="+devWriteOff.getDevtype();
	}

	@RequiresPermissions("dev:writeoff:devWriteOff:edit")
	@RequestMapping(value = "sureWriteOff")
	public String sureWriteOff(DevWriteOff devWriteOff, RedirectAttributes redirectAttributes) {
		devWriteOff.setStatus(devWriteOff.getStatus());//已核销
		devWriteOffService.save(devWriteOff);
		DevWriteOffDetail devWriteOffDetailCondition = new DevWriteOffDetail();
		devWriteOffDetailCondition.setWriteoffId(devWriteOff.getId());
		List<DevWriteOffDetail> list = devWriteOffDetailService.findList(devWriteOffDetailCondition);
		if("A4".equals(devWriteOff.getDevtype())){
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
		}else{
			//将设备状态修改为待核销
			for(DevWriteOffDetail devWriteOffDetail:list){
				DevInWarehouse devInWarehouseCondition = new DevInWarehouse();
				devInWarehouseCondition.setId(devWriteOffDetail.getDevid());
				DevInWarehouse devInWarehouse = devInWarehouseService.get(devInWarehouseCondition);
				if(devInWarehouse!=null){
					devInWarehouse.setDevstatus(devWriteOff.getStatus());//待核销
					devInWarehouseService.save(devInWarehouse);
				}

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