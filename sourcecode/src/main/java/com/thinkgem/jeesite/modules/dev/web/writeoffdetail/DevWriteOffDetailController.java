/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.web.writeoffdetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.dev.entity.writeoff.DevWriteOff;
import com.thinkgem.jeesite.modules.dev.service.writeoff.DevWriteOffService;
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
import com.thinkgem.jeesite.modules.dev.entity.writeoffdetail.DevWriteOffDetail;
import com.thinkgem.jeesite.modules.dev.service.writeoffdetail.DevWriteOffDetailService;

/**
 * 核销单明细Controller
 * @author myj
 * @version 2020-07-09
 */
@Controller
@RequestMapping(value = "${adminPath}/dev/writeoffdetail/devWriteOffDetail")
public class DevWriteOffDetailController extends BaseController {

	@Autowired
	private DevWriteOffDetailService devWriteOffDetailService;

	@Autowired
	private DevWriteOffService devWriteOffService;
	
	@ModelAttribute
	public DevWriteOffDetail get(@RequestParam(required=false) String id) {
		DevWriteOffDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = devWriteOffDetailService.get(id);
		}
		if (entity == null){
			entity = new DevWriteOffDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("dev:writeoffdetail:devWriteOffDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevWriteOffDetail devWriteOffDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevWriteOffDetail> page = devWriteOffDetailService.findPage(new Page<DevWriteOffDetail>(request, response), devWriteOffDetail); 
		model.addAttribute("page", page);
		return "modules/dev/writeoffdetail/devWriteOffDetailList";
	}

	@RequiresPermissions("dev:writeoffdetail:devWriteOffDetail:view")
	@RequestMapping(value = "form")
	public String form(DevWriteOffDetail devWriteOffDetail, Model model) {
		model.addAttribute("devWriteOffDetail", devWriteOffDetail);
		return "modules/dev/writeoffdetail/devWriteOffDetailForm";
	}

	@RequiresPermissions("dev:writeoffdetail:devWriteOffDetail:view")
	@RequestMapping(value = "formRedict")
	public String formRedict(DevWriteOffDetail devWriteOffDetail, Model model) {
		model.addAttribute("devWriteOffDetail", devWriteOffDetail);
		return "modules/dev/writeoffdetail/devWriteOffDetailFormRedict";
	}

	@RequiresPermissions("dev:writeoffdetail:devWriteOffDetail:view")
	@RequestMapping(value = "saveRedirectDetail")
	public String saveRedirectDetail(DevWriteOffDetail devWriteOffDetail, Model model,HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devWriteOffDetail)){
			return form(devWriteOffDetail, model);
		}
		devWriteOffDetailService.save(devWriteOffDetail);
		addMessage(redirectAttributes, "保存核销单明细成功");
		redirectAttributes.addAttribute("writeoffId",devWriteOffDetail.getWriteoffId());
		redirectAttributes.addAttribute("id",devWriteOffDetail.getWriteoffId());
		return "redirect:"+Global.getAdminPath()+"/dev/writeoff/devWriteOff/formDetail";
	}

	@RequiresPermissions("dev:writeoffdetail:devWriteOffDetail:edit")
	@RequestMapping(value = "save")
	public String save(DevWriteOffDetail devWriteOffDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devWriteOffDetail)){
			return form(devWriteOffDetail, model);
		}
		devWriteOffDetailService.save(devWriteOffDetail);
		addMessage(redirectAttributes, "保存核销单明细成功");
		return "redirect:"+Global.getAdminPath()+"/dev/writeoffdetail/devWriteOffDetail/?repage";
	}
	
	@RequiresPermissions("dev:writeoffdetail:devWriteOffDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(DevWriteOffDetail devWriteOffDetail, RedirectAttributes redirectAttributes) {
		devWriteOffDetailService.delete(devWriteOffDetail);
		addMessage(redirectAttributes, "删除核销单明细成功");
		return "redirect:"+Global.getAdminPath()+"/dev/writeoffdetail/devWriteOffDetail/?repage";
	}

	@RequiresPermissions("dev:writeoffdetail:devWriteOffDetail:edit")
	@RequestMapping(value = "deleteRedirectDetail")
	public String deleteRedirectDetail(DevWriteOffDetail devWriteOffDetail, RedirectAttributes redirectAttributes) {
		devWriteOffDetailService.delete(devWriteOffDetail);
		addMessage(redirectAttributes, "删除核销单明细成功");
		redirectAttributes.addAttribute("writeoffId",devWriteOffDetail.getWriteoffId());
		redirectAttributes.addAttribute("id",devWriteOffDetail.getWriteoffId());
		return "redirect:"+Global.getAdminPath()+"/dev/writeoff/devWriteOff/formDetail";
	}

}