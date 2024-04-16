package dev.umang.productservice09april.services;

import dev.umang.productservice09april.dtos.FakeStoreProductdto;
import dev.umang.productservice09april.models.Category;
import dev.umang.productservice09april.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakestore")
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) {

        if(productId == 0) {
            throw new IllegalArgumentException("Invalid ProductId, Please try out some other productId");
        }

        ResponseEntity<FakeStoreProductdto> fakeStoreProductResponse = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductdto.class);

        return fakeStoreProductResponse.getBody().toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        FakeStoreProductdto[] fakeStoreProductdtos = restTemplate.
                getForObject("https://fakestoreapi.com/products",FakeStoreProductdto[].class);

       for(FakeStoreProductdto fakeStoreProductdto : fakeStoreProductdtos) {
            products.add(fakeStoreProductdto.toProduct());
       }

       return products;
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 double price,
                                 String category,
                                 String image) {
        FakeStoreProductdto fakeStoreProductdto = new FakeStoreProductdto();
        fakeStoreProductdto.setTitle(title);
        fakeStoreProductdto.setDescription(description);
        fakeStoreProductdto.setPrice(price);
        fakeStoreProductdto.setCategory(category);
        fakeStoreProductdto.setImage(image);
        FakeStoreProductdto fakeStoreProductdto1 = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreProductdto,
                FakeStoreProductdto.class);

        return fakeStoreProductdto1.toProduct();
    }
}

/*
/products/1

Rest Template(like a utility) - help you allow sending http requests to external apis and get response
 Inversion of Control(IoC)
 */