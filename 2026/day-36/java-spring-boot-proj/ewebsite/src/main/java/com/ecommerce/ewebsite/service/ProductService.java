package com.ecommerce.ewebsite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.ewebsite.model.Product;
import com.ecommerce.ewebsite.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository  productrepository ;
	
//	to get all category
	public List<Product> getAllProduct(){
		return productrepository.findAll();
		}

//	to add product
	public void productadd(Product p) {
		productrepository.save(p);
	}
	
//	to delete product
	public void deleteProductById(Long id) {
		productrepository.deleteById(id);
	}
// to get product by id
	public Optional<Product> getProductById(Long id){
		return productrepository.findById(id);
		}
	
//	to get all product of single category
	public List<Product>getAllProductsByCategoryId(int id){
		return productrepository.findAllByCategory_Id(id);
		
	}
}
