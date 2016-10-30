pndao
====
>一个非常简单的ORM框架，可以基于DAO的命名帮你翻译SQL语句

pndao是票牛Java团队实践一年演化出来的工具。他包括几部分组成：

1. 一套实践规范，帮你统一DAO层方法及行为
2. 一个编译工具，帮助自动生成SQL

```java
@DaoGen
public interface UserDao extends EntityDao<User> {

	List<User> queryByUserName(@Param("userName") String userName,RowBounds rowBounds);

	@Select("select userName from PN_User where id = #{id}")
	String findUserNameById(@Param("id") int id);
	
}
```

```java
	@Test
	public void testFindUserById() throws Exception {
		User user = userDao.findById(USER_ID);
		assertThat(user).isNotNull();
	}

	@Test
	public void testQueryByUserName() throws Exception {
		List<User> users = userDao.queryByUserName("票牛用户",new RowBounds(0,1));
		assertThat(users).isNotEmpty();
	}

	@Test
	public void testFindUserNameById() throws Exception {
		String userName = userDao.findUserNameById(1);
		assertThat(userName).isEqualTo("票牛用户");
	}
```
