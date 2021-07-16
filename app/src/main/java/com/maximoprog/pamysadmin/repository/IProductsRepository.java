package com.maximoprog.pamysadmin.repository;


import com.maximoprog.pamysadmin.enviroments.Credentials;
import com.maximoprog.pamysadmin.models.Product;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IProductsRepository {

    @GET(Credentials.URI_PRODUCTS)
    Observable<List<Product>> getProducts();

    @GET(Credentials.URI_PRODUCTS + "/{idProduct}")
    Observable<Product> getProductById(@Path("idProduct") int idProduct);

    @DELETE(Credentials.URI_PRODUCTS + "/{idProduct}")
    Observable<String> deleteProductById(@Path("idProduct") int idProduct);

    @POST(Credentials.URI_PRODUCTS)
    Observable<Product> save(@Body Product product);

    @PUT(Credentials.URI_PRODUCTS)
    Observable<Product> update(@Body Product product);

}
