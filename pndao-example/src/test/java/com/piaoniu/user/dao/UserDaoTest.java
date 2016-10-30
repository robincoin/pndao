package com.piaoniu.user.dao;

import com.piaoniu.user.entity.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author code4crafter@gmail.com
 *         Date: 16/10/30
 *         Time: 下午5:00
 */
public class UserDaoTest {

	private UserDao userDao;

	@Test
	public void testInsertUser() throws Exception {
		User user = new User();
		user.setAvatar("http://git.oschina.net/uploads/group/groups_1087185.png");
		user.setPassword("c5658357fd6d0c4a802f95cd96d83c5f");
		user.setUserName("票牛用户");
		user.setMobileNo("13700000000");
		assertThat(userDao.insert(user)).isEqualTo(1);
	}

	public static final int USER_ID = 1;

	@Test
	public void testFindUserById() throws Exception {
		User user = userDao.findById(USER_ID);
		assertThat(user).isNotNull();
	}
}
