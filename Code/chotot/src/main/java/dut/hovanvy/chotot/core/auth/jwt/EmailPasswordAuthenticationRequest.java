package dut.hovanvy.chotot.core.auth.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailPasswordAuthenticationRequest {

    private String email;
    private String password;

}
