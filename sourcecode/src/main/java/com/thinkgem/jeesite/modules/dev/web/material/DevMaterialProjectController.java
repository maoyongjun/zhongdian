/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.web.material;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dev.entity.material.DevMaterialProject;
import com.thinkgem.jeesite.modules.dev.service.material.DevMaterialProjectService;

/**
 * 工程项目Controller
 * @author myj
 * @version 2020-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/dev/material/devMaterialProject")
public class DevMaterialProjectController extends BaseController {

	@Autowired
	private DevMaterialProjectService devMaterialProjectService;
	
	@ModelAttribute
	public DevMaterialProject get(@RequestParam(required=false) String id) {
		DevMaterialProject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = devMaterialProjectService.get(id);
		}
		if (entity == null){
			entity = new DevMaterialProject();
		}
		return entity;
	}
	
	@RequiresPermissions("dev:material:devMaterialProject:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevMaterialProject devMaterialProject, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<DevMaterialProject> list = devMaterialProjectService.findList(devMaterialProject); 
		model.addAttribute("list", list);
		return "modules/dev/material/devMaterialProjectList";
	}

	@RequiresPermissions("dev:material:devMaterialProject:view")
	@RequestMapping(value = "form")
	public String form(DevMaterialProject devMaterialProject, Model model) {
		if (devMaterialProject.getParent()!=null && StringUtils.isNotBlank(devMaterialProject.getParent().getId())){
			devMaterialProject.setParent(devMaterialProjectService.get(devMaterialProject.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(devMaterialProject.getId())){
				DevMaterialProject devMaterialProjectChild = new DevMaterialProject();
				devMaterialProjectChild.setParent(new DevMaterialProject(devMaterialProject.getParent().getId()));
				List<DevMaterialProject> list = devMaterialProjectService.findList(devMaterialProject); 
				if (list.size() > 0){
					devMaterialProject.setSort(list.get(list.size()-1).getSort());
					if (devMaterialProject.getSort() != null){
						devMaterialProject.setSort(devMaterialProject.getSort() + 30);
					}
				}
			}
		}
		if (devMaterialProject.getSort() == null){
			devMaterialProject.setSort(30);
		}
		model.addAttribute("devMaterialProject", devMaterialProject);
		return "modules/dev/material/devMaterialProjectForm";
	}

	@RequiresPermissions("dev:material:devMaterialProject:edit")
	@RequestMapping(value = "save")
	public String save(DevMaterialProject devMaterialProject, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devMaterialProject)){
			return form(devMaterialProject, model);
		}
		devMaterialProjectService.save(devMaterialProject);
		addMessage(redirectAttributes, "保存工程项目成功");
		return "redirect:"+Global.getAdminPath()+"/dev/material/devMaterialProject/?repage";
	}
	
	@RequiresPermissions("dev:material:devMaterialProject:edit")
	@RequestMapping(value = "delete")
	public String delete(DevMaterialProject devMaterialProject, RedirectAttributes redirectAttributes) {
		devMaterialProjectService.delete(devMaterialProject);
		addMessage(redirectAttributes, "删除工程项目成功");
		return "redirect:"+Global.getAdminPath()+"/dev/material/devMaterialProject/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<DevMaterialProject> list = devMaterialProjectService.findList(new DevMaterialProject());
		for (int i=0; i<list.size(); i++){
			DevMaterialProject e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}