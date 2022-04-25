package br.jessica.sp.cotia.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.jessica.sp.cotia.hellospring.dao.ConnectionFactory;

@Controller
public class IndexController {

		@RequestMapping("index")
		public String index() {
			System.out.println("Passou aqui" +ConnectionFactory.conectar());
			return "index";
		}
}
