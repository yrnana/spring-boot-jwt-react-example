package com.example.lgcns.security;

/**
 * JwtConstant
 */
public final class JwtConstant {

	public static final String JWT_SECRET_KEY = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";

	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final int TOKEN_EXPIRE = 864000000; // 10 days

	public static final String TOKEN_TYPE = "JWT";
	public static final String TOKEN_ISSUER = "secure-api";
	public static final String TOKEN_AUDIENCE = "secure-app";

}