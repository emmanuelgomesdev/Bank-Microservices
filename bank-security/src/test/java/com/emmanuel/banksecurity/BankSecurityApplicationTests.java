package com.emmanuel.banksecurity;

import com.emmanuel.banksecurity.jwt.JwtService;
import com.emmanuel.banksecurity.properties.BankSecurityProperties;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BankSecurityApplicationTests {

	@Test
	void shouldGenerateAndValidateToken() {
		BankSecurityProperties properties =
				new BankSecurityProperties();

		properties.setSecret(
				"minha-chave-jwt-de-teste-com-mais-de-32-caracteres"
		);
		properties.setExpiration(86400000L);

		JwtService jwtService = new JwtService(properties);

		String token = jwtService.generateToken(
				"emmanuel@email.com"
		);

		assertThat(token).isNotBlank();
		assertThat(jwtService.isTokenValid(token)).isTrue();
		assertThat(jwtService.extractUsername(token))
				.isEqualTo("emmanuel@email.com");
	}
}