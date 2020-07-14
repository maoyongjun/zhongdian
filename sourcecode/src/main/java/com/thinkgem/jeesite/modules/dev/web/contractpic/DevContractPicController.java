/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.web.contractpic;

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
import com.thinkgem.jeesite.modules.dev.entity.contractpic.DevContractPic;
import com.thinkgem.jeesite.modules.dev.service.contractpic.DevContractPicService;

/**
 * 合同图片Controller
 * @author myj
 * @version 2020-07-13
 */
@Controller
@RequestMapping(value = "${adminPath}/dev/contractpic/devContractPic")
public class DevContractPicController extends BaseController {

	@Autowired
	private DevContractPicService devContractPicService;
	
	@ModelAttribute
	public DevContractPic get(@RequestParam(required=false) String id) {
		DevContractPic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = devContractPicService.get(id);
		}
		if (entity == null){
			entity = new DevContractPic();
		}
		return entity;
	}
	
	@RequiresPermissions("dev:contractpic:devContractPic:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevContractPic devContractPic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevContractPic> page = devContractPicService.findPage(new Page<DevContractPic>(request, response), devContractPic); 
		model.addAttribute("page", page);
		return "modules/dev/contractpic/devContractPicList";
	}

	@RequiresPermissions("dev:contractpic:devContractPic:view")
	@RequestMapping(value = "form")
	public String form(DevContractPic devContractPic, Model model) {
		model.addAttribute("devContractPic", devContractPic);
		return "modules/dev/contractpic/devContractPicForm";
	}

	@RequiresPermissions("dev:contractpic:devContractPic:edit")
	@RequestMapping(value = "save")
	public String save(DevContractPic devContractPic, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devContractPic)){
			return form(devContractPic, model);
		}
		devContractPicService.save(devContractPic);
		addMessage(redirectAttributes, "保存合同图片成功");
		return "redirect:"+Global.getAdminPath()+"/dev/contractpic/devContractPic/?repage";
	}
	
	@RequiresPermissions("dev:contractpic:devContractPic:edit")
	@RequestMapping(value = "delete")
	public String delete(DevContractPic devContractPic, RedirectAttributes redirectAttributes) {
		devContractPicService.delete(devContractPic);
		addMessage(redirectAttributes, "删除合同图片成功");
		return "redirect:"+Global.getAdminPath()+"/dev/contractpic/devContractPic/?repage";
	}

}