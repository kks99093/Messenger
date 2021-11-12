package com.my.messenger.model.param;

import com.my.messenger.model.entity.UserInfo;

import lombok.Data;

@Data
public class UserParam extends UserInfo {
	private Integer myPk;
	private Integer yourPk;
}
