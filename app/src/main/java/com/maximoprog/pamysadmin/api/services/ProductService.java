package com.maximoprog.pamysadmin.api.services;


import com.maximoprog.pamysadmin.api.RetrofitInstance;
import com.maximoprog.pamysadmin.models.Product;
import com.maximoprog.pamysadmin.repository.IProductsRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class ProductService {

    private final IProductsRepository repository;

    public ProductService() {
        repository = RetrofitInstance.getInstance().createRepository(IProductsRepository.class);
    }

    public Observable<List<Product>> getProducts() {
        return repository.getProducts();
    }

    public Observable<Product> save(Product product) {
        return repository.save(product);
    }
    public Observable<Product> delete(Integer idProduct) {
        return repository.deleteProductById(idProduct);
    }

    public Observable<Product> update(Product product) {
        return repository.update(product);
    }
}
