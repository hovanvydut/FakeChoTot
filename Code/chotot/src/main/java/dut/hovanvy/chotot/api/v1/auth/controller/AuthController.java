package dut.hovanvy.chotot.api.v1.auth.controller;

import javax.validation.Valid;

import dut.hovanvy.chotot.core.auth.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dut.hovanvy.chotot.api.v1.auth.dto.LoginRequestDto;
import dut.hovanvy.chotot.api.v1.auth.dto.LoginResponseDto;
import dut.hovanvy.chotot.api.v1.auth.dto.RegisterRequestDto;
import dut.hovanvy.chotot.api.v1.auth.dto.RegisterResponseDto;

@AllArgsConstructor
@NoArgsConstructor
@RestController()
@RequestMapping(path = "api/v1/auth")
public class AuthController {

	private AuthService authService;

	@PostMapping("/login")
	public LoginResponseDto login(@Valid @RequestBody LoginRequestDto requestDto) {
		return null;
	}
	
	@PostMapping("/register")
	public RegisterResponseDto register(@Valid @RequestBody RegisterRequestDto requestDto) {
		this.authService.register(requestDto);
		// return link confirmation token
		return null;
	}
	
}
