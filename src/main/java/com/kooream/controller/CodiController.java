package com.kooream.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kooream.domain.CodiVO;
import com.kooream.domain.Criteria;
import com.kooream.service.CodiService;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/codishop/*")
@AllArgsConstructor
public class CodiController {
	


	private CodiService service;

	@GetMapping("/list")
	public String list() {
		return "/codishop/list";
	}

	@PostMapping(value = "/list", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<CodiVO>> list(@RequestBody Criteria cri){
		List<CodiVO> list = service.getList(cri);
		log.info(cri.getAmount()+""+cri.getPageNum());
		return new ResponseEntity<List<CodiVO>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/register")
	public String register() {
		log.info("codiController - 게시글 등록 페이지 이동 " );
		
		return "/codishop/register";
	}
	
	@PostMapping("/register")
	public String register(CodiVO vo, RedirectAttributes rttr) {
		log.info("register........" + vo);
		service.register(vo);
		
		rttr.addFlashAttribute("result", "ok");

		return "redirect:/codishop/list";
	}
	
	@GetMapping("/get")
	public String get(@RequestParam("codi_no") int codi_no, Model model, Criteria cri) {
		log.info("/get....gogo");
		model.addAttribute("board", service.get(codi_no));
		model.addAttribute("cri", cri);
	
		return "/codishop/get";
	}
	
	
	
//	// 삭제 
//	@PostMapping("/remove")
//	public String remove(@RequestParam("codi_no") int codi_no, Criteria cri, RedirectAttributes rttr) {
//		log.info("remove ..... " + codi_no);
//		if(service.remove(codi_no)) {
//			rttr.addFlashAttribute("result","success");
//		}
//		
//		return "redirect:/codishop/list";
//		
//	}// remove() .... end
	
	// 삭제 
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(@RequestParam("codi_no") int codi_no)throws Exception{
		service.remove(codi_no);
		
		return "redirect:/codishop/list";
	}
	
	
	
	
}
