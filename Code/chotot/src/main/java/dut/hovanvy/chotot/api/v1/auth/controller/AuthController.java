package dut.hovanvy.chotot.api.v1.auth.controller;

import javax.validation.Valid;

import dut.hovanvy.chotot.core.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import dut.hovanvy.chotot.api.v1.auth.dto.LoginRequestDto;
import dut.hovanvy.chotot.api.v1.auth.dto.LoginResponseDto;
import dut.hovanvy.chotot.api.v1.auth.dto.RegisterRequestDto;
import dut.hovanvy.chotot.api.v1.auth.dto.RegisterResponseDto;

@AllArgsConstructor
@RestController()
@RequestMapping(path = "api/v1/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public LoginResponseDto login(@Valid @RequestBody LoginRequestDto requestDto) {
		this.authService.login(requestDto);
		return null;
	}
	
	@PostMapping("/register")
	public RegisterResponseDto register(@Valid @RequestBody RegisterRequestDto requestDto) {
		this.authService.register(requestDto);
		// return link confirmation token
		return null;
	}

	@GetMapping("/register/confirm")
	public void confirmToken(@RequestParam("token") String token) {
		if (token != null) {
			this.authService.confirmTokenRegister(token);
		}
	}
	
}
