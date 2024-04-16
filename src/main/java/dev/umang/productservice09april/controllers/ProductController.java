package dev.umang.productservice09april.controllers;

import dev.umang.productservice09april.dtos.RequestBodyProductDto;
import dev.umang.productservice09april.models.Product;
import dev.umang.productservice09april.services.FakeStoreProductService;
import dev.umang.productservice09april.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    /*
    ProductService productService
     */
    /*
    POST /products/
    Request body
    {
        "id" :
        "title:
        "descr":
        "category":
        "price"
     }
     */
    ProductService productService;
    public ProductController(@Qualifier("fakestore") ProductService productService){
        this.productService = productService;
    }
    /*
    Qualifier is used to identify the depedency to be injected here
     */
    @PostMapping("/products")
    public Product createProduct(@RequestBody RequestBodyProductDto request){
        return productService.createProduct(request.getTitle(),
                request.getDescription(),
                request.getPrice(),
                request.getCategory(),
                request.getImage());
    }

    // /product/1 - get details of a particular product
    /*
    id:
    title:
    price:
     */
    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        throw new RuntimeException();
       // List<Product> products = productService.getAllProducts();
       // return new ResponseEntity<>(products, HttpStatus.CONFLICT);
    }




    public void updateProduct(){

    }
}



/*
Every api call at the end of the day is a method call inside a controller
 */