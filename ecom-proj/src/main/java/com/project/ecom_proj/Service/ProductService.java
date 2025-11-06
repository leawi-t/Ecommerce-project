package com.project.ecom_proj.Service;

import com.project.ecom_proj.Module.Product;
import com.project.ecom_proj.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    ProductRepo productRepo;

    @Autowired
    ProductService(ProductRepo productRepo){
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public Product getProductById(int id){
        return productRepo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageDate(imageFile.getBytes());
        return productRepo.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException{
        product.setImageDate(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        productRepo.save(product);
        return product;
    }

    public void deleteProduct(int id){
        productRepo.deleteById(id);
    }

    public void deleteAllProducts(){
        productRepo.deleteAll();
    }

    public List<Product> searchProduct(String keyword){
        return productRepo.searchProduct(keyword);
    }
}
