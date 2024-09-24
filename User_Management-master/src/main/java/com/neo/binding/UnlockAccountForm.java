package com.yash.binding;

import lombok.Data;

@Data
public class UnlockAccountForm {
	
	public String email;
	public String tempPwd;
	public String newPwd;
	public String confirmPwd;
}
