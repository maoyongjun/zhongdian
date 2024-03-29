
package com.thinkgem.jeesite.modules.wxapp.web;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkgem.jeesite.common.security.shiro.session.SessionDAO;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.pj.entity.prodparent.PjProdParent;
import com.thinkgem.jeesite.modules.pj.service.category.PjValueCategoryService;
import com.thinkgem.jeesite.modules.pj.service.details.PjValueDetailsService;
import com.thinkgem.jeesite.modules.pj.service.eval.EvalService;
import com.thinkgem.jeesite.modules.pj.service.prodbase.PjProdBaseService;
import com.thinkgem.jeesite.modules.pj.service.prodchild.PjProdChildService;
import com.thinkgem.jeesite.modules.pj.service.prodparent.PjProdParentService;
import com.thinkgem.jeesite.modules.pj.vo.eval.WXChildVo;
import com.thinkgem.jeesite.modules.pj.vo.eval.WXParentVo;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm;
import com.thinkgem.jeesite.modules.sys.security.UsernamePasswordToken;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wxapp.service.WxUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信Controller
 *
 * @author chang
 * @version 2020-06-20
 */
@Controller
@RequestMapping(value = "wx")
public class WXController extends BaseController {
    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private SystemService systemService;
    @Autowired
    private WxUserService wxUserService;
    @Autowired
    private PjProdParentService pjProdParentService;
    @Autowired
    private PjProdChildService pjProdChildService;
    @Autowired
    private PjProdBaseService pjProdBaseService;
    @Autowired
    private PjValueCategoryService pjValueCategoryService;
    @Autowired
    private PjValueDetailsService pjValueDetailsService;
    @Autowired
    private EvalService evalService;


    /**
     * 微信账户登录
     * 账户登录成功进入，失败提示账户或密码错误
     *
     * @param jsonStr
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "accountlogin", method = RequestMethod.POST)
    public Map<String, Object> accountlogin(@RequestBody String jsonStr, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        User loginUser = mapper.readValue(jsonStr, User.class);
        JSONObject object = JSONObject.parseObject(jsonStr);
        String plaintPassword = object.getString("password");

        User user = systemService.getUserByLoginName(loginUser.getLoginName());

        try {
            boolean rememberMe = true;
            String host = StringUtils.getRemoteAddr((HttpServletRequest) request);
            String captcha = "";
            boolean mobile = false;
            UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getLoginName(), plaintPassword.toCharArray(), rememberMe, host, captcha, mobile);
            UserUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            if (!e.getMessage().startsWith("msg:")) {
                throw new AuthenticationException("msg:登录失败，请联系管理员。", e);
            }
            throw e;
        }

        boolean pwdValid = systemService.validPassword(loginUser.getLoginName(), plaintPassword);
        if (pwdValid) {
            String userType = user.getUserType();
            if("9".equals(userType)){
                map.put("isManager", "1");
            }
            map.put("code", "ok");
            map.put("user", user);
        } else {
            map.put("code", "error");
            map.put("msg", "账户或密码错误");
        }
        return map;
    }


    @ResponseBody
    @RequestMapping(value = "getEvalList", method = RequestMethod.POST)
    public Map<String, Object> getEvalList(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String loginName = jsonObject.getString("loginName");
        User user = systemService.getUserByLoginName(loginName);
        PjProdParent pjProdParent = new PjProdParent();
        pjProdParent.setRaterId(user.getId());
        List<PjProdParent> evalList = wxUserService.getEvalList(user.getId());
        map.put("code", "ok");
        map.put("evalList", evalList);
        return map;
    }

    /**
     * 获取微信父级评价列表
     *
     * @param object
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"getEvalListById"})
    public Map<String, Object> getEvalListById(@RequestBody JSONObject object) {
        String id = object.getString("id");
        String title = object.getString("title");
        List<WXParentVo> wxParentVoList = evalService.getEvalListById(id, title);
        Map<String, Object> map = new HashMap<>();
        map.put("code", "ok");
        map.put("wxParentVoList", wxParentVoList);

        return map;
    }

    /**
     * 获取打分内容列表
     *
     * @param object
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"getChildListById"})
    public Map<String, Object> getChildListById(@RequestBody JSONObject object) {
        String parentId = object.getString("parentId");
        List<WXChildVo> wxChildVoList = evalService.getWXChildListByParentId(parentId);
        Map<String, Object> map = new HashMap<>();
        map.put("code", "ok");
        map.put("wxChildVoList", wxChildVoList);
        return map;
    }


    /**
     * 微信提交分数
     *
     * @param object
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"commitRealScore"})
    public Map<String, Object> commitRealScore(@RequestBody JSONObject object) {
        String commitType = object.getString("commitType");
        List<Map<String, Object>> wxChildVoList = (ArrayList<Map<String, Object>>) object.get("wxChildVoList");
        int count = evalService.commitRealScore(commitType, wxChildVoList);
        Map<String, Object> map = new HashMap<>();

        map.put("code", "ok");

        return map;
    }


    @RequestMapping(value = "sysLoginWX", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();

        if (principal != null) {
            UserUtils.getSubject().logout();
        }

        String loginName = request.getParameter("loginName");
        String passWord = request.getParameter("passWord");

        model.addAttribute("loginName", loginName);
        model.addAttribute("passWord", passWord);

        return "modules/sys/sysLoginWX";
    }

    @ResponseBody
    @RequestMapping(value = {"updatePassword"})
    public Map<String, Object> updatePassword(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        String oldPwd = jsonObject.getString("oldPwd");
        String newPwd = jsonObject.getString("newPwd");
        String cfmPwd = jsonObject.getString("cfmPwd");
        String id = jsonObject.getString("id");
        User user = UserUtils.get(id);
        if (user != null) {
            boolean pwdValid = systemService.validPassword(user.getLoginName(), oldPwd);
            if (pwdValid) {
                if (newPwd != null && newPwd.equals(cfmPwd)) {
                    user.setPassword(SystemService.entryptPassword(newPwd));
                    systemService.saveUser(user);
                    map.put("code", "ok");
                } else {
                    map.put("code", "error");
                    map.put("msg", "两次输入密码不一样");
                }
            } else {
                map.put("code", "error");
                map.put("msg", "旧密码输入错误");
            }
        }

        return map;
    }


}