package com.maximoprog.pamysadmin.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maximoprog.pamysadmin.ProductDetailsActivity;
import com.maximoprog.pamysadmin.api.services.ProductService;
import com.maximoprog.pamysadmin.databinding.FragmentHomeBinding;
import com.maximoprog.pamysadmin.enviroments.Credentials;
import com.maximoprog.pamysadmin.models.Product;
import com.maximoprog.pamysadmin.ui.adapters.ProductAdapter;
import com.maximoprog.pamysadmin.utils.Alert;
import com.maximoprog.pamysadmin.utils.HandlerUtilitity;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getName();
    public final CompositeDisposable disposables = new CompositeDisposable();
    public ProductService productService = new ProductService();
    public Context context;
    public ProductAdapter productAdapter;
    //    products
    FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    //    adaptador
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_home, container, false);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.homeOpenLottie.setVisibility(View.VISIBLE);
        binding.productoRV.setVisibility(View.GONE);
        context = container.getContext();

        cargarAdapter();
        getProducts();
        return binding.getRoot();
    }

    private void cargarAdapter() {
        productAdapter = new ProductAdapter(context, new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product, int position) {
                Intent intentProduct = new Intent(
                        context, ProductDetailsActivity.class
                );
//                le pasa el item osea el objecto como parametro
                intentProduct.putExtra("product", product);
//                inicia la actividad
                Alert.showMessageSuccess(context, product.getName());
                startActivity(intentProduct);
            }
        });
        this.binding.productoRV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        this.binding.productoRV.setLayoutManager(layoutManager);
        this.binding.productoRV.setAdapter(productAdapter);
        this.binding.productoRV.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public void onDestroy() {
        if (disposables != null) {
            disposables.clear();
        }
        super.onDestroy();
    }

    public void getProducts() {
        productService.getProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.rxjava3.core.Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<Product> products) {
                        productAdapter.addProducts(products);
                        HandlerUtilitity.setTimeOut(new Runnable() {
                            @Override
                            public void run() {
                                Alert.showMessageSuccess(context, "Existen " + products.size() + " Productos");
                                binding.productoRV.setVisibility(View.VISIBLE);
                                binding.homeOpenLottie.setVisibility(View.GONE);

                            }
                        }, Credentials.TIMER);

                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d(TAG, "Estoy en onERROR");
                        Alert.showMessageError(context, "Ocurrio un error" + e.getMessage() + " Productos");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onResume() {
        getProducts();
        super.onResume();
    }
}