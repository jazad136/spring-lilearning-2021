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
	public FizzbuzzPack getFizzbuzzResponse(@RequestParam String input) { 
		return new FizzbuzzPack(input, fizzbuzzService.getFizzbuzz(input));
	}
}
