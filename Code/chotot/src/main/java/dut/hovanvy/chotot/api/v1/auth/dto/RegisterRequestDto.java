package dut.hovanvy.chotot.api.v1.auth.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RegisterRequestDto implements Serializable {

	private static final Long serialVersionUID = 1L;
	
	@Size(min = 2)
	private String firstName;
	
	@Size(min = 2)
	private String lastName;
	
	@Email(message = "Email should be valid")
	private String email;
	
	@Size(min = 8)
	private String password;
	
}
