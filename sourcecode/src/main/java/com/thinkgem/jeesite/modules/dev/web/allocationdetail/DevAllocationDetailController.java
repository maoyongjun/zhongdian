/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.web.allocationdetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.dev.entity.allocation.DevAllocation;
import com.thinkgem.jeesite.modules.dev.service.allocation.DevAllocationService;
import com.thinkgem.jeesite.modules.util.BeanUtils;
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
import com.thinkgem.jeesite.modules.dev.entity.allocationdetail.DevAllocationDetail;
import com.thinkgem.jeesite.modules.dev.service.allocationdetail.DevAllocationDetailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调拨细项Controller
 * @author myj
 * @version 2020-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/dev/allocationdetail/devAllocationDetail")
public class DevAllocationDetailController extends BaseController {

	@Autowired
	private DevAllocationDetailService devAllocationDetailService;

	@Autowired
	private DevAllocationService devAllocationService;
	
	@ModelAttribute
	public DevAllocationDetail get(@RequestParam(required=false) String id) {
		DevAllocationDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = devAllocationDetailService.get(id);
		}
		if (entity == null){
			entity = new DevAllocationDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("dev:allocationdetail:devAllocationDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevAllocationDetail devAllocationDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevAllocationDetail> page = devAllocationDetailService.findPage(new Page<DevAllocationDetail>(request, response), devAllocationDetail); 
		model.addAttribute("page", page);
		return "modules/dev/allocationdetail/devAllocationDetailList";
	}

	@RequiresPermissions("dev:allocationdetail:devAllocationDetail:view")
	@RequestMapping(value = "form")
	public String form(DevAllocationDetail devAllocationDetail, Model model) {
		model.addAttribute("devAllocationDetail", devAllocationDetail);
		return "modules/dev/allocationdetail/devAllocationDetailForm";
	}

	@RequiresPermissions("dev:allocationdetail:devAllocationDetail:view")
	@RequestMapping(value = "formByProjectOfDev")
	public String formByProjectOfDev(DevAllocationDetail devAllocationDetail, Model model,String projectId) {
		model.addAttribute("devAllocationDetail", devAllocationDetail);
		model.addAttribute("projectId", projectId);
		return "modules/dev/allocationdetail/devAllocationDetailForm";
	}

	@RequiresPermissions("dev:allocationdetail:devAllocationDetail:edit")
	@RequestMapping(value = "save")
	public String save(DevAllocationDetail devAllocationDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devAllocationDetail)){
			return form(devAllocationDetail, model);
		}
		devAllocationDetailService.save(devAllocationDetail);
		addMessage(redirectAttributes, "保存调拨细项成功");
		return "redirect:"+Global.getAdminPath()+"/dev/allocationdetail/devAllocationDetail/?repage";
	}

	@RequiresPermissions("dev:allocationdetail:devAllocationDetail:edit")
	@RequestMapping(value = "saveRedirectFormDetail")
	public String saveRedirectFormDetail(DevAllocationDetail devAllocationDetail, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, devAllocationDetail)){
			return form(devAllocationDetail, model);
		}
		//判断是否添加郭
		DevAllocationDetail condition = new DevAllocationDetail();
		condition.setDevId(devAllocationDetail.getDevId());
		List<DevAllocationDetail> list = devAllocationDetailService.findList(condition);
		if(list.size()>0){
			addMessage(redirectAttributes, "设备已添加，请勿重复添加！");

			redirectAttributes.addAttribute("id",devAllocationDetail.getAllocationId());
			//设备添加郭
			return "redirect:"+Global.getAdminPath()+"/dev/allocation/devAllocation/formDetail";
		}

		devAllocationDetailService.save(devAllocationDetail);
		DevAllocation devAllocation = devAllocationService.get(devAllocationDetail.getAllocationId());
		addMessage(redirectAttributes, "保存调拨细项成功");
		model.addAttribute("devAllocation", devAllocation);
		DevAllocationDetail devAllocationDetailCondition = new DevAllocationDetail();
		devAllocationDetailCondition.setAllocationId(devAllocation.getId());
		Page<DevAllocationDetail> page = devAllocationDetailService.findPage(new Page<DevAllocationDetail>(request,response), devAllocationDetailCondition);
		model.addAttribute("page", page);
		return "modules/dev/allocation/devAllocationFormDetail";
	}


	@RequiresPermissions("dev:allocationdetail:devAllocationDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(DevAllocationDetail devAllocationDetail, RedirectAttributes redirectAttributes) {
		devAllocationDetailService.delete(devAllocationDetail);
		addMessage(redirectAttributes, "删除调拨细项成功");
		return "redirect:"+Global.getAdminPath()+"/dev/allocationdetail/devAllocationDetail/?repage";
	}

	@RequiresPermissions("dev:allocation:devAllocation:edit")
	@RequestMapping(value = "deleteRedirectFormDetail")
	public String deleteRedirectFormDetail(DevAllocationDetail devAllocationDetail, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request, HttpServletResponse response) {
		devAllocationDetailService.delete(devAllocationDetail);
		addMessage(redirectAttributes, "删除调拨细项成功");
		DevAllocation devAllocation = devAllocationService.get(devAllocationDetail.getAllocationId());
		model.addAttribute("devAllocation", devAllocation);
		DevAllocationDetail devAllocationDetailCondition = new DevAllocationDetail();
		devAllocationDetailCondition.setAllocationId(devAllocation.getId());
		Page<DevAllocationDetail> page = devAllocationDetailService.findPage(new Page<DevAllocationDetail>(request,response), devAllocationDetailCondition);
		model.addAttribute("page", page);
		return "modules/dev/allocation/devAllocationFormDetail";
	}

}