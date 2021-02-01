package dut.hovanvy.chotot.api.v1.auth.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Size(min = 5, max = 50)
	private String email;
	
	@Size(min = 8, max = 256)
	private String password;
}
