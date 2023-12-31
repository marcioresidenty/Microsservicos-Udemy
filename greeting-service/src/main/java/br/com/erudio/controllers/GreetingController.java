package br.com.erudio.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.configuration.GreetingConfiguration;
import br.com.erudio.model.Greeting;

@RestController // o spring reconhecer a classe como controladora
public class GreetingController {

	
	private static final String template = " %s, %s!"; // %s recebe um parametro
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired //injeta a configuração 
	private GreetingConfiguration configutation;
	
	@RequestMapping("/greeting")
	public Greeting greeting(
			@RequestParam(value = "name",
			defaultValue ="") String name) {
		//name.isEmpty() verificar se o tamanho dessa string é vazio ou vazia retorna true
		if (name.isEmpty()) name = configutation.getDefaultValue();
		
		return new Greeting(
				counter.incrementAndGet(),
				String.format(template, configutation.getGreeting(), name));
	}
}
