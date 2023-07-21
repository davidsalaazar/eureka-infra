package com.experience.scloud.service.impl;

import com.experience.scloud.dao.ProductDao;
import com.experience.scloud.model.ProductModel;
import com.experience.scloud.model.response.ProductsResponse;
import com.experience.scloud.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;

    @Override
    public ResponseEntity<ProductsResponse> getAll() {
        List<ProductModel> lProduct = (List<ProductModel>) this.productDao.findAll();
        if (lProduct.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        ProductsResponse response = new ProductsResponse();
        response.setProducts(lProduct.stream().peek(product -> product.setPort(this.webServerAppCtxt.getWebServer().getPort())).collect(Collectors.toList()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductModel> getOne(Long id) {
        Optional<ProductModel> product = this.productDao.findById(id);
        if (product.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        product.get().setPort(this.webServerAppCtxt.getWebServer().getPort());
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }
}