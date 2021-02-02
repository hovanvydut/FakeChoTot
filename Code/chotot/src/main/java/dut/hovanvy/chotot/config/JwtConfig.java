package dut.hovanvy.chotot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.google.common.net.HttpHeaders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {

	private String secretKey;
	private String tokenPrefix;
	private Integer tokenExpirationAfterDays;
	
	public String getAuthorizationHeader() {
		return HttpHeaders.AUTHORIZATION;
	}
	
}
