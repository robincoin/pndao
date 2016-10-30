package com.piaoniu.user.dao;

import com.piaoniu.common.EntityDao;
import com.piaoniu.pndao.annotations.DaoGen;
import com.piaoniu.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author code4crafter@gmail.com
 *         Date: 16/10/30
 *         Time: 下午4:59
 */
@DaoGen
public interface UserDao extends EntityDao<User> {

	List<User> queryByUserName(@Param("userName") String userName,RowBounds rowBounds);

	int updateForUserName(@Param("userName") String userName, @Param("id") int id);

}
