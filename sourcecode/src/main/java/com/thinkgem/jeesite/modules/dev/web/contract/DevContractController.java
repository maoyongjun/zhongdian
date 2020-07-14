/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dev.web.contract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.dev.entity.contractpic.DevContractPic;
import com.thinkgem.jeesite.modules.dev.entity.upload.UploadResult;
import com.thinkgem.jeesite.modules.dev.service.contractpic.DevContractPicService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dev.entity.contract.DevContract;
import com.thinkgem.jeesite.modules.dev.service.contract.DevContractService;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 设备合同Controller
 * @author myj
 * @version 2020-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/dev/contract/devContract")
public class DevContractController extends BaseController {

	@Autowired
	private DevContractService devContractService;

	@Autowired
	private DevContractPicService devContractPicService;

	private static String basePath = Global.getConfig("basePath");
	
	@ModelAttribute
	public DevContract get(@RequestParam(required=false) String id) {
		DevContract entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = devContractService.get(id);
		}
		if (entity == null){
			entity = new DevContract();
		}
		return entity;
	}
	
	@RequiresPermissions("dev:contract:devContract:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevContract devContract, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevContract> page = devContractService.findPage(new Page<DevContract>(request, response), devContract); 
		model.addAttribute("page", page);
		return "modules/dev/contract/devContractList";
	}

	@RequiresPermissions("dev:contract:devContract:view")
	@RequestMapping(value = "form")
	public String form(DevContract devContract, Model model) {
		model.addAttribute("devContract", devContract);
		return "modules/dev/contract/devContractForm";
	}

	@RequestMapping(value = "pic")
	public String pic(Model model,String contractId) {
		model.addAttribute("contractId", contractId);
		return "modules/pic";
	}


	@RequestMapping(value = "save_photo")
	@ResponseBody
	public UploadResult savePhoto(@RequestParam("picture") MultipartFile file, HttpServletRequest request,@RequestParam("contractId")String contractId) throws Exception {
		System.out.println("contractId:"+contractId);
		String path = null;// 文件路径
		double fileSize = file.getSize();
		byte[] sizebyte=file.getBytes();
		UploadResult result = new UploadResult();

		if (file != null) {// 判断上传的文件是否为空
			String type = null;// 文件类型
			String fileName = file.getOriginalFilename();// 文件原名称
			System.out.println("上传的文件原名称:" + fileName);
			String newFileName = new Date().getTime()+"";
			// 判断文件类型
			type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
			if (type != null) {// 判断文件类型是否为空

				if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {

					// 项目在容器中实际发布运行的根路径
					String realPath = request.getSession().getServletContext().getRealPath("/");
					// 自定义的文件名称
					String trueFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
					// 设置存放图片文件的路径
					//String basePath ="F:\\工作内容\\2020.7.1中电环能电力\\code\\sourcecode\\jeesite\\src\\main\\webapp\\static\\";
					path = basePath+newFileName+"."+type;
					// 转存文件到指定的路径
					file.transferTo(new File(path));
					result.setMsg( "图片上传成功！");
					result.setStatus(0);
					result.setPicName(newFileName+"."+type);
					String picUrl = "http://localhost:8181/jeesite/static/"+newFileName+"."+type;
					result.setUrl(picUrl);
					DevContractPic devContractPic = new DevContractPic();
					devContractPic.setContractid(contractId);
					devContractPic.setPicpath(picUrl);
					devContractPic.setOrgname(fileName);
					devContractPic.setPicname(newFileName+"."+type);
					devContractPicService.save(devContractPic);
					return result;
				} else {
					result.setMsg("文件格式不正确！");
					result.setStatus(-1);
					return result;
				}

			} else {
				result.setMsg( "文件类型为空！");
				result.setStatus(-1);
				return result;
			}
		} else {
			result.setMsg( "文件为空！");
			result.setStatus(-1);
			return result;
		}
	}

	@RequestMapping(value = "loadPic")
	@ResponseBody
	public List<String> loadPic (String contractId){
		DevContractPic devContractPic = new DevContractPic();
		devContractPic.setContractid(contractId);
		List<DevContractPic> list = devContractPicService.findList(devContractPic);

		List<String> picList = new ArrayList<>();
		for(DevContractPic pic :list){
			picList.add(pic.getPicpath());
		}
		return picList;

	}


	@RequestMapping(value = "deletePhoto")
	@ResponseBody
	public String deletePhoto(String picName){
		if(StringUtils.isEmpty(picName)){
			return "图片为空";
		}
		//String basePath ="F:\\工作内容\\2020.7.1中电环能电力\\code\\sourcecode\\jeesite\\src\\main\\webapp\\static\\";
		String path = basePath+picName;
		File file = new File(path);
		file.delete();
		DevContractPic picCondition = new DevContractPic();
		picCondition.setPicname(picName);
		List<DevContractPic> list = devContractPicService.findList(picCondition);
		if(list.size()>0){
			devContractPicService.delete(list.get(0));
		}

		return "删除图片成功";
	}

	@RequiresPermissions("dev:contract:devContract:edit")
	@RequestMapping(value = "save")
	public String save(DevContract devContract, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, devContract)){
			return form(devContract, model);
		}
		devContractService.save(devContract);
		addMessage(redirectAttributes, "保存设备合同成功");
		return "redirect:"+Global.getAdminPath()+"/dev/contract/devContract/?repage";
	}
	
	@RequiresPermissions("dev:contract:devContract:edit")
	@RequestMapping(value = "delete")
	public String delete(DevContract devContract, RedirectAttributes redirectAttributes) {
		devContractService.delete(devContract);
		addMessage(redirectAttributes, "删除设备合同成功");
		return "redirect:"+Global.getAdminPath()+"/dev/contract/devContract/?repage";
	}

}