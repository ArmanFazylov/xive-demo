package com.xive.demo.dto;

/*
 *  Created by Arman on 17 of Jan, 2020
 *  Feel free to email me: arman.fazylov@gmail.com
 */


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccountPostRequestDTO {
	@NotNull
	String login;

	@NotNull
	String password;
}
