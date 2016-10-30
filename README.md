pndao
====
>一个非常简单的ORM辅助工具，可以基于DAO的命名约定帮你生成SQL语句

pndao是票牛Java团队实践一年演化出来的工具。他包括几部分组成：

1. 一个编译工具，帮助自动生成SQL
2. 一套实践规范，帮你统一DAO层方法及行为

## 特性

1. 基于主流的MyBatis框架，无学习成本
2. 提供

以下是一个常见的DAO功能：

```java
public class UserDaoTest extends AbstractTest{

	public static final int USER_ID = 1;

	@Autowired
	private UserDao userDao;

	@Ignore("insert语句与h2不兼容")
	@Test
	public void testInsertUser() throws Exception {
		User user = new User();
		user.setAvatar("http://git.oschina.net/uploads/group/groups_1087185.png");
		user.setPassword("c5658357fd6d0c4a802f95cd96d83c5f");
		user.setUserName("用户13700000000");
		user.setMobileNo("13700000000");
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
}
```

基于pndao，所有需要开发的DAO只有这些：

```java
@DaoGen
public interface UserDao {

	int updateForUserName(@Param("userName") String userName, @Param("id") int id);

	int insert(User t);

	User findById(int id);

}
```

