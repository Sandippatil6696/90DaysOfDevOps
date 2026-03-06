package com.ecommerce.ewebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.ewebsite.service.CategoryService;
import com.ecommerce.ewebsite.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	private ProductService productservice;
	
	@Autowired
	private CategoryService categoryservice;
	
//	access with / or /home url
	@GetMapping({"/","/home"})
	public String home(Model model) {
		return "index";
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories",categoryservice.getAllCat());
		model.addAttribute("products",productservice.getAllProduct());
		return "shop";
	}
	
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model,@PathVariable int id) {
		model.addAttribute("categories",categoryservice.getAllCat());
		model.addAttribute("products",productservice.getAllProductsByCategoryId(id));
		return "shop";
	}
	
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model,@PathVariable long id) {
		model.addAttribute("product",productservice.getProductById(id).get());
		return "viewProduct";
	}

}
