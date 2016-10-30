pndao
====
>一个非常简单的MyBatis辅助工具，可以基于DAO的命名约定帮你生成并维护SQL语句

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

1. 如果你正在使用MyBatis，那么只需在Dao上增加@DaoGen注释即可。
2. pndao会优先使用你自己定义的SQL（包括注解和XML），所以不用担心原有的Dao不能用了。
3. 在新编写方法时，按照pndao的方法命名规范即可享受代码生成。
4. 目前只支持Java 8

使用建议先阅读pndao-example中的样例代码。

支持的方法：

<table>
    <tr>
        <td width="20%">方法</td>
        <td width="20%">说明</td>
        <td width="40%">样例</td>        
        <td>备注</td>
    </tr>
    <tr>
        <td>insert</td>
        <td>插入记录</td>
		 <td>insert(User user)</td>        
        <td>ID会回写入user的主键</td>
    </tr>
     <tr>
        <td>batchInsert</td>
        <td>批量插入记录</td>
		 <td>batchInsert(List<User> user)</td>        
        <td>返回插入记录数</td>
    </tr>
    <tr>
        <td>findBy</td>
        <td>按某列查询单个元素</td>
		 <td>findByUserName(String userName)</td>        
        <td>返回单条记录</td>
    </tr>
     <tr>
        <td>queryBy</td>
        <td>按某列查询一些元素</td>
		 <td>queryByUserName(String userName)</td>        
        <td>返回多条记录</td>
    </tr>
     <tr>
        <td>countBy</td>
        <td>按某列查询总数</td>
		 <td>countBy(String userName)</td>        
        <td>返回总数</td>
    </tr>
     <tr>
        <td>updateFor</td>
        <td>按照主键更新一列</td>
		 <td>updateForUserName(String userName,int id)</td>        
        <td>返回更新记录数</td>
    </tr>
</table>


