package dut.hovanvy.chotot.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.base.Strings;

import dut.hovanvy.chotot.config.JwtConfig;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Component
public class JwtVerifyTokenRequestFilter extends OncePerRequestFilter {

	private final JwtConfig jwtConfig;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
		
		String email = null;
		String jwtToken = null;
		
		if (Strings.isNullOrEmpty(authorizationHeader) || 
				!authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
			filterChain.doFilter(request, response);
			return;
		}
		
		jwtToken = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
		
		// do anythiing
		
	}
	
}
