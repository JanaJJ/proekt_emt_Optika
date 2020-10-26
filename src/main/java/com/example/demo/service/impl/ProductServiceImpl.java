package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findProductbyId(Long id) {
        return this.productRepository.findById(id).get();
    }

    @Override
    public void saveProductToDB(MultipartFile file,Long id, String name, String description, Long price) {
        Product product=new Product();
        product.setId(id);
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a valid file");
        }

        try {
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        this.productRepository.save(product);
    }


    @Override
    public List<Product> findAllByBrand(Long brandId) {
        List<Product> products=this.productRepository.findAll();
        List<Product> products1=null;
        for(Product p:products){
            if(p.getBrand().getId()== brandId)
            {
                products1.add(p);
            }
        }
        return products1;
    }

    @Override
    public List<Product> findAllByCategory(String name) {

        List<Product> products=this.productRepository.findAll();
        List<Product> products1=null;
        for(Product p:products){
            if(p.getCategory().getName().equals(name))
            {
                products1.add(p);
            }
        }
        return products1;
    }


    @Override
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) throws IOException {
        return null;
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }
}
