pndao
====
>一个非常简单的DAO辅助工具，可以基于DAO的命名约定帮你生成并维护SQL语句

pndao是票牛Java团队实践一年演化出来的工具。在实际生产中减少了80%以上的重复SQL编写工作，从而把关注力转移到模型本身的制定上。结合建表语句生成插件[pngen](http://git.oschina.net/piaoniu/pngen)，大部分场景只需编写一个模型类即可完成DAO层工作。

## 特性

1. 基于最主流的MyBatis框架，无学习成本
2. 基于常见DAO功能定制，提供便捷的同时也可约束命名规范
3. 修改表结构后可同步修改SQL
4. 编译期生成XML，也可便捷支持其他框架

以下是一个常见的DAO功能：

```java
public class UserDaoTest extends AbstractTest{

	public static final int USER_ID = 1;

	@Autowired
	private UserDao userDao;

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

## 使用

pndao包括几部分组成：

1. 一个编译工具，帮助自动生成SQL
2. 一套实践规范，帮你统一DAO层方法及行为

使用建议先阅读pndao-example中的样例代码。

支持的方法：

<table>
    <tr>
        <td width="100">方法</td>
        <td width="100">说明</td>
        <td width="100">样例</td>        
        <td>备注</td>
    </tr>
    <tr>
        <td>insert</td>
        <td>插入记录</td>
		 <td>`insert(User user)`</td>        
        <td>yes</td>
    </tr>
    <tr>
        <td>immediate parent</td>
        <td>/</td>
        <td>yes</td>
    </tr>
    <tr>
        <td>parent</td>
        <td>//</td>
        <td>yes</td>
    </tr>
    <tr>
        <td>attribute</td>
        <td>[@key=value]</td>
        <td>yes</td>
    </tr>
    <tr>
        <td>nth child</td>
        <td>tag[n]</td>
        <td>yes</td>
    </tr>
    <tr>
        <td>attribute</td>
        <td>/@key</td>
        <td>yes</td>
    </tr>
    <tr>
        <td>wildcard in tagname</td>
        <td>/*</td>
        <td>yes</td>
    </tr>
    <tr>
        <td>wildcard in attribute</td>
        <td>/[@*]</td>
        <td>yes</td>
    </tr>
    <tr>
        <td>function</td>
        <td>function()</td>
        <td>part</td>
    </tr>
    <tr>
        <td>or</td>
        <td>a | b</td>
        <td>yes since 0.2.0</td>
    </tr>
    <tr>
        <td>parent in path</td>
        <td>. or ..</td>
        <td>no</td>
    </tr>
    <tr>
        <td>predicates</td>
        <td>price>35</td>
        <td>no</td>
    </tr>
    <tr>
        <td>predicates logic</td>
        <td>@class=a or @class=b</td>
        <td>yes since 0.2.0</td>
    </tr>
</table>
