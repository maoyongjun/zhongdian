/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.web.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.modules.pj.entity.details.PjValueDetails;
import com.thinkgem.jeesite.modules.pj.service.details.PjValueDetailsService;
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
import com.thinkgem.jeesite.modules.pj.entity.category.PjValueCategory;
import com.thinkgem.jeesite.modules.pj.service.category.PjValueCategoryService;

import java.util.List;
import java.util.Map;

/**
 * 类目维护Controller
 * @author liu
 * @version 2020-06-19
 */
@Controller
@RequestMapping(value = "${adminPath}/pj/category/pjValueCategory")
public class PjValueCategoryController extends BaseController {

	@Autowired
	private PjValueCategoryService pjValueCategoryService;
	@Autowired
	private PjValueDetailsService pjValueDetailsService;
	@ModelAttribute
	public PjValueCategory get(@RequestParam(required=false) String id) {
		PjValueCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pjValueCategoryService.get(id);
		}
		if (entity == null){
			entity = new PjValueCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("pj:category:pjValueCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(PjValueCategory pjValueCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PjValueCategory> page = pjValueCategoryService.findPage(new Page<PjValueCategory>(request, response), pjValueCategory); 
		model.addAttribute("page", page);
		return "modules/pj/category/pjValueCategoryList";
	}

	@RequiresPermissions("pj:category:pjValueCategory:view")
	@RequestMapping(value = "form")
	public String form(PjValueCategory pjValueCategory, Model model) {
		model.addAttribute("pjValueCategory", pjValueCategory);
		return "modules/pj/category/pjValueCategoryForm";
	}
	//类目合并
	@RequiresPermissions("pj:category:pjValueCategory:view")
	@RequestMapping(value = "merge")
	public String merge(PjValueCategory pjValueCategory, Model model) {
		model.addAttribute("pjValueCategory", pjValueCategory);
		return "modules/pj/category/pjValueCategoryMerge";
	}
	@RequiresPermissions("pj:category:pjValueCategory:edit")
	@RequestMapping(value = "savemerge")
	public String saveMerge(PjValueCategory pjValueCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pjValueCategory)){
			return form(pjValueCategory, model);
		}
		String regex = "^,*|,*$";//去逗号
		String id1 = pjValueCategory.getId().replaceAll(regex, "");//类目1id
		String id2=pjValueCategory.getNote1();//类目2id
		String name=pjValueCategory.getNote2();
		pjValueCategory.setId(id2);
		pjValueCategoryService.delete(pjValueCategory);//删除类目2
		String cateId=id2;
		PjValueDetails pd=new PjValueDetails();
		pd.setCateId(cateId);
		List<PjValueDetails> plist=pjValueDetailsService.findList(pd);
		for (PjValueDetails p1:plist) {
			p1.setCateId(id1);
			pjValueDetailsService.save(p1);
		}
		addMessage(redirectAttributes, "类目合并成功");
		return "redirect:"+Global.getAdminPath()+"/pj/category/pjValueCategory/?repage";
	}
	@RequiresPermissions("pj:category:pjValueCategory:edit")
	@RequestMapping(value = "save")
	public String save(PjValueCategory pjValueCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pjValueCategory)){
			return form(pjValueCategory, model);
		}
		pjValueCategoryService.save(pjValueCategory);
		addMessage(redirectAttributes, "保存类目维护成功");
		return "redirect:"+Global.getAdminPath()+"/pj/category/pjValueCategory/?repage";
	}
	
	@RequiresPermissions("pj:category:pjValueCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(PjValueCategory pjValueCategory, RedirectAttributes redirectAttributes) {
		pjValueCategoryService.delete(pjValueCategory);
		addMessage(redirectAttributes, "删除类目维护成功");
		return "redirect:"+Global.getAdminPath()+"/pj/category/pjValueCategory/?repage";
	}

	//开启类目
	@RequiresPermissions("pj:category:pjValueCategory:edit")
	@RequestMapping(value = "openStatus")
	public String openStatus(PjValueCategory pjValueCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pjValueCategory)){
			return form(pjValueCategory, model);
		}
		if(pjValueCategory.getStatu().equals("0")){
			addMessage(redirectAttributes, "该类目处于开启状态，无需再次开启！");
		}else{
			pjValueCategory.setStatu("0");
			addMessage(redirectAttributes, "开启类目成功！");
		}
		pjValueCategoryService.save(pjValueCategory);
		return "redirect:"+Global.getAdminPath()+"/pj/category/pjValueCategory/?repage";
	}
	//关闭类目
	@RequiresPermissions("pj:category:pjValueCategory:edit")
	@RequestMapping(value = "closeStatus")
	public String closeStatus(PjValueCategory pjValueCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pjValueCategory)){
			return form(pjValueCategory, model);
		}
		if(pjValueCategory.getStatu().equals("1")){
			addMessage(redirectAttributes, "该类目处于关闭状态，无需再次关闭！");
		}else{
			pjValueCategory.setStatu("1");
			addMessage(redirectAttributes, "关闭类目成功！");
		}
		pjValueCategoryService.save(pjValueCategory);
		return "redirect:"+Global.getAdminPath()+"/pj/category/pjValueCategory/?repage";
	}
	//类目树
	@RequiresPermissions("pj:category:pjValueCategory:edit")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		PjValueCategory category = new PjValueCategory();
		List<PjValueCategory> list = pjValueCategoryService.findList(category);
		for (int i=0; i<list.size(); i++){
			PjValueCategory e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", "u_"+e.getId());
			map.put("pId", "0");
			map.put("name", StringUtils.replace(e.getName(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}

	//类目树
	@RequiresPermissions("pj:category:pjValueCategory:edit")
	@ResponseBody
	@RequestMapping(value = "treeDataStatus")
	public List<Map<String, Object>> treeDataStatus(HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		PjValueCategory category = new PjValueCategory();
		category.setStatu("0");
		List<PjValueCategory> list = pjValueCategoryService.findList(category);
		for (int i=0; i<list.size(); i++){
			PjValueCategory e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", "u_"+e.getId());
			map.put("pId", "0");
			map.put("name", StringUtils.replace(e.getName(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}

}