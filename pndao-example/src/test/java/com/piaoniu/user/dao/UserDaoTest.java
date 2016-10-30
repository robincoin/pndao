package com.piaoniu.user.dao;

import com.piaoniu.test.AbstractTest;
import com.piaoniu.user.entity.User;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author code4crafter@gmail.com
 *         Date: 16/10/30
 *         Time: 下午5:00
 */
public class UserDaoTest extends AbstractTest{

	public static final int USER_ID = 1;

	@Autowired
	private UserDao userDao;

	@Ignore("insert语句与h2不兼容")
	@Test
	public void testInsertUser() throws Exception {
		User user = initUser();
		assertThat(userDao.insert(user)).isEqualTo(1);
	}

	@Test
	public void testFindUserById() throws Exception {
		User user = userDao.findById(USER_ID);
		assertThat(user).isNotNull();
	}

	@Test
	public void testUpdateUserName() throws Exception {
		assertThat(userDao.updateForUserName("用户13700000001",USER_ID)).isEqualTo(1);
	}

	private User initUser(){
		User user = new User();
		user.setAvatar("http://git.oschina.net/uploads/group/groups_1087185.png");
		user.setPassword("c5658357fd6d0c4a802f95cd96d83c5f");
		user.setUserName("用户13700000000");
		user.setMobileNo("13700000000");
		return user;
	}
}
