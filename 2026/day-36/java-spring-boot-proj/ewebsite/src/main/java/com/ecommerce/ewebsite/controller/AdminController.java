package com.ecommerce.ewebsite.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.ewebsite.dto.ProductDTO;
import com.ecommerce.ewebsite.model.Category;
import com.ecommerce.ewebsite.model.Product;
import com.ecommerce.ewebsite.service.CategoryService;
import com.ecommerce.ewebsite.service.ProductService;

@Controller
public class AdminController {
	
	public static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/productImages";

	@Autowired
	private CategoryService categoryservice;
	
	@Autowired
	private ProductService productservice;

	@GetMapping("/admin")
	public String getAdmin() {
		return "adminHome";
	}
	
//	category section 

	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories", categoryservice.getAllCat());
		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String getcatAdd(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "categoriesAdd";
	}

	@PostMapping("/admin/categories/add")
	public String postcatAdd(@ModelAttribute("category") Category category) {
		categoryservice.catadd(category);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id) {
		categoryservice.deleteCatById(id);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id, Model model) {

		Optional<Category> c = categoryservice.getCatById(id);
		if (c.isPresent()) {
			model.addAttribute("category", c.get());
			return "categoriesAdd";
		} else {
			return "404";
		}

	}
	
//	product section 
	
	@GetMapping("/admin/products")
	public String getProduct(Model model) {
		model.addAttribute("products",productservice.getAllProduct());
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String addProduct(Model model) {
		model.addAttribute("productDTO",new ProductDTO());
		model.addAttribute("categories",categoryservice.getAllCat());
		return "productsAdd";
		}
	
		@PostMapping("/admin/products/add")
		public String addProductSubmit(@ModelAttribute("productDTO") ProductDTO productDTO,
				                       @RequestParam("productImage") MultipartFile file, 
				                       @RequestParam("imgName") String imgName)throws IOException {
			
			Product product = new Product();
			
			product.setId(productDTO.getId());
			product.setName(productDTO.getName());
			product.setCategory(categoryservice.getCatById(productDTO.getCategoryId()).get());
			product.setPrice(productDTO.getPrice());
			product.setWeight(productDTO.getWeight());
			product.setDescription(productDTO.getDescription());
			
			String imageUUID;
			
			if(!file.isEmpty()) {
				imageUUID = file.getOriginalFilename();
				Path fileNameAndPath = Paths.get(uploadDir,imageUUID);
				Files.write(fileNameAndPath, file.getBytes());
			}
			else {
				imageUUID = imgName;
			}
			
			product.setImageName(imageUUID);
			productservice.productadd(product);
			
			
			return "redirect:/admin/products";

		}
	
		
		@GetMapping("/admin/product/delete/{id}")
		public String deleteProduct(@PathVariable Long id) {
			productservice.deleteProductById(id);
			return "redirect:/admin/products";
		}
		
		@GetMapping("/admin/product/update/{id}")
		public String updateProduct(@PathVariable Long id , Model model) {
			
			Product product = productservice.getProductById(id).get();
			
			ProductDTO productDTO = new ProductDTO();
			
			productDTO.setId(product.getId());
			productDTO.setName(product.getName());
			productDTO.setCategoryId(product.getCategory().getId());
			productDTO.setPrice(product.getPrice());
			productDTO.setWeight(product.getWeight());
			productDTO.setDescription(product.getDescription());
			productDTO.setImageName(product.getImageName());
			
			
			model.addAttribute("categories",categoryservice.getAllCat());
			model.addAttribute("productDTO" , productDTO);
			
			return "productsAdd";
			
		}

}
