package com.example.lgcns.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.lgcns.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

/**
 * JwtAuthorizationFilter
 * 
 * 인증 확인 필터
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		try {
			UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
			if (authentication == null) {
				filterChain.doFilter(request, response);
				return;
			}
			SecurityContextHolder.getContext().setAuthentication(authentication);
			filterChain.doFilter(request, response);
		} catch (JwtException e) {
			setErrorResponse(response, e);
		}
	}

	/**
	 * JwtException를 캐치하면 오류를 Json으로 반환
	 * (필터 안에서 발생한 오류이므로 RestExceptionHandler에서 처리할 수 없다)
	 * 
	 * @param response
	 * @param e
	 * @throws IOException
	 */
	private void setErrorResponse(HttpServletResponse response, JwtException e) throws IOException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		ObjectMapper mapper = new ObjectMapper();
		ErrorResponse error = new ErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
		response.getWriter().write(mapper.writeValueAsString(error));
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(JwtConstant.TOKEN_HEADER);

		if (StringUtils.isNotEmpty(token) && token.startsWith(JwtConstant.TOKEN_PREFIX)) {
			byte[] signingKey = JwtConstant.JWT_SECRET_KEY.getBytes();

			Jws<Claims> parsedToken = Jwts.parser().setSigningKey(signingKey)
					.parseClaimsJws(token.replace(JwtConstant.TOKEN_PREFIX, ""));

			String username = parsedToken.getBody().getSubject();

			List<SimpleGrantedAuthority> authorities = ((List<?>) parsedToken.getBody().get("roles")).stream()
					.map(authority -> new SimpleGrantedAuthority((String) authority)).collect(Collectors.toList());

			if (StringUtils.isNotEmpty(username)) {
				return new UsernamePasswordAuthenticationToken(username, null, authorities);
			}
		}

		return null;
	}
}