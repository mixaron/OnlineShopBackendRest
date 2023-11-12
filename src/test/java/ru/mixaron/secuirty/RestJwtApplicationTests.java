package ru.mixaron.secuirty;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class RestJwtApplicationTests {

	@Test
	void contextLoads() {
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
