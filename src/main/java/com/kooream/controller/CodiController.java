package com.kooream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kooream.domain.CodiVO;
import com.kooream.domain.Criteria;
import com.kooream.service.CodiService;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/codishop/*")
@AllArgsConstructor
public class CodiController {
	
	private CodiService service;


	@GetMapping("/list")
	public String list(Criteria cri,Model model) {
		log.info("index.... 게시글 목록 이동");
		
		model.addAttribute("list", service.getListWithPaging(cri));
		return "codishop/list";
		
	}
	
	@GetMapping("/register")
	public void register() {
		log.info("codiController - 게시글 등록 페이지 이동 " );
	}
	
	@PostMapping("register")
	public String register(CodiVO vo, RedirectAttributes rttr) {
		log.info("register........" + vo);
		service.register(vo);
		
		rttr.addFlashAttribute("result", "ok");
		
		return "redirect:/codishop/index";
	}
	
	
}
