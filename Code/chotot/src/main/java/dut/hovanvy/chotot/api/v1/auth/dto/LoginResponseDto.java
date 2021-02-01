package dut.hovanvy.chotot.api.v1.auth.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String token;
}
