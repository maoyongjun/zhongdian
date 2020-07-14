package com.thinkgem.jeesite.modules.wxapp.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pj.dao.prodparent.PjProdParentDao;
import com.thinkgem.jeesite.modules.pj.entity.prodparent.PjProdParent;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.wxapp.dao.WxUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chang
 * @version 2020-06-10
 */
@Service
@Transactional(readOnly = true)
public class WxUserService extends CrudService<WxUserDao, User> {

    @Autowired
    private WxUserDao wxUserDao;
    @Autowired
    private PjProdParentDao pjProdParentDao;

    public User getUserByOpenId(String openId) {
        return wxUserDao.getUserByOpenId(openId);
    }

    public List<PjProdParent> getEvalList(String raterId){
        List<PjProdParent> pjProdParentList = pjProdParentDao.getPjProdParentByRaterId(raterId);

        return pjProdParentList;
    }


}