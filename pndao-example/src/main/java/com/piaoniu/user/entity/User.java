package com.piaoniu.user.entity;

import com.piaoniu.common.Entity;
import lombok.Data;

/**
 * @author code4crafter@gmail.com
 *         Date: 16/10/30
 *         Time: 下午4:57
 */
@Data
public class User extends Entity {

	private String userName;

	private String password;

	private String mobileNo;

	private String avatar;
}
