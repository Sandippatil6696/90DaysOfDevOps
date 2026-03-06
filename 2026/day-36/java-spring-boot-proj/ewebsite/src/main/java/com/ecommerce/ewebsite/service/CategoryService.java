package com.ecommerce.ewebsite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ewebsite.model.Category;
import com.ecommerce.ewebsite.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryrepository ;
	
//	to get all category
	public List<Category> getAllCat(){
		return categoryrepository.findAll();
		
	}
//	to add category
	public void catadd(Category c) {
		categoryrepository.save(c);
	}
	
//	to delete
	public void deleteCatById(int id) {
		categoryrepository.deleteById(id);
	}
	
// get by id
	public Optional<Category> getCatById(int id){
		return categoryrepository.findById(id);
		}


}
