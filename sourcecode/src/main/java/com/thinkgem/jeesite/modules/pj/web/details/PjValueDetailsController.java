/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pj.web.details;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import com.thinkgem.jeesite.modules.pj.entity.details.PjValueDetails;
import com.thinkgem.jeesite.modules.pj.service.details.PjValueDetailsService;

import java.util.List;
import java.util.Map;

/**
 * 评定细则Controller
 * @author chang
 * @version 2020-06-19
 */
@Controller
@RequestMapping(value = "${adminPath}/pj/details/pjValueDetails")
public class PjValueDetailsController extends BaseController {

	@Autowired
	private PjValueDetailsService pjValueDetailsService;
	
	@ModelAttribute
	public PjValueDetails get(@RequestParam(required=false) String id) {
		PjValueDetails entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pjValueDetailsService.get(id);
		}
		if (entity == null){
			entity = new PjValueDetails();
		}
		return entity;
	}
	
	@RequiresPermissions("pj:details:pjValueDetails:view")
	@RequestMapping(value = {"list", ""})
	public String list(PjValueDetails pjValueDetails, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PjValueDetails> queryPage = new Page<PjValueDetails>(request, response);
		queryPage.setOrderBy("cate_id");
		Page<PjValueDetails> page = pjValueDetailsService.findPage(queryPage, pjValueDetails);

		model.addAttribute("page", page);
		return "modules/pj/details/pjValueDetailsList";
	}

	@RequiresPermissions("pj:details:pjValueDetails:view")
	@RequestMapping(value = "form")
	public String form(PjValueDetails pjValueDetails, Model model) {
		model.addAttribute("pjValueDetails", pjValueDetails);
		return "modules/pj/details/pjValueDetailsForm";
	}

	@RequiresPermissions("pj:details:pjValueDetails:edit")
	@RequestMapping(value = "save")
	public String save(PjValueDetails pjValueDetails, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pjValueDetails)){
			return form(pjValueDetails, model);
		}
		pjValueDetailsService.save(pjValueDetails);
		addMessage(redirectAttributes, "保存评定细则成功");
		return "redirect:"+Global.getAdminPath()+"/pj/details/pjValueDetails/?repage";
	}
	
	@RequiresPermissions("pj:details:pjValueDetails:edit")
	@RequestMapping(value = "delete")
	public String delete(PjValueDetails pjValueDetails, RedirectAttributes redirectAttributes) {
		pjValueDetailsService.delete(pjValueDetails);
		addMessage(redirectAttributes, "删除评定细则成功");
		return "redirect:"+Global.getAdminPath()+"/pj/details/pjValueDetails/?repage";
	}

	@RequiresPermissions("pj:details:pjValueDetails:view")
	@ResponseBody
	@RequestMapping(value = "treeDataAll")
	public List<Map<String, Object>> treeDataAll(HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<PjValueDetails> list = pjValueDetailsService.findList(new PjValueDetails());
		for (int i=0; i<list.size(); i++){
			PjValueDetails e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", "u_"+e.getId());
			map.put("pId", "0");
			map.put("name", StringUtils.replace(e.getName(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}

}