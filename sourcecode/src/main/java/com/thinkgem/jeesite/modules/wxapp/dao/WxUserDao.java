package com.thinkgem.jeesite.modules.wxapp.dao;


import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * @author chang
 * @version 2020-06-10
 */
@MyBatisDao
public interface WxUserDao extends CrudDao<User> {
    User getUserByOpenId(String openId);
}
