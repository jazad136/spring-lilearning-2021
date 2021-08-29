package com.jsaddlercs.lil.sbet.landon.fizzbuzzwebapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsaddlercs.lil.sbet.landon.fizzbuzzwebapp.models.FizzbuzzPack;
import com.jsaddlercs.lil.sbet.landon.fizzbuzzwebapp.service.FizzbuzzService;

@RestController
@RequestMapping("/api/fizzbuzzgame")
public class FizzbuzzRestController {
	
	private final FizzbuzzService fizzbuzzService;

	public FizzbuzzRestController(FizzbuzzService fizzbuzzService) {
		this.fizzbuzzService = fizzbuzzService;
	}
	
	@GetMapping
	public FizzbuzzPack getFizzbuzzResponse(@RequestParam(defaultValue="") String input) { 
		String result = fizzbuzzService.getFizzbuzz(input);
		if(result.equals("noInput")) { 
			return new FizzbuzzPack(input, "No input. Use ?input=<number> after the slash in the URL");
		}
		return new FizzbuzzPack(input, result);
	}
}
