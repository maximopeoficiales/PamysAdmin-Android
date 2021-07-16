package com.maximoprog.pamysadmin.ui.notifications;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.maximoprog.pamysadmin.R;
import com.maximoprog.pamysadmin.api.services.ProductService;
import com.maximoprog.pamysadmin.databinding.FragmentNotificationsBinding;
import com.maximoprog.pamysadmin.enviroments.Credentials;
import com.maximoprog.pamysadmin.models.Product;
import com.maximoprog.pamysadmin.utils.Alert;
import com.maximoprog.pamysadmin.utils.HandlerUtilitity;
import com.squareup.picasso.Picasso;

import java.util.Comparator;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NotificationsFragment extends Fragment {
    private static final String TAG = NotificationsFragment.class.getName();
    public final CompositeDisposable disposables = new CompositeDisposable();
    public ProductService productService = new ProductService();
    public Context context;
    public Product productMayor;
    public Product productMenor;
    FragmentNotificationsBinding binding;
    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        context = container.getContext();
        binding.infoOpenLottie.setVisibility(View.VISIBLE);
        binding.cardContenedorInfo.setVisibility(View.GONE);
        getProducts();

        return binding.getRoot();
    }

    public void getProducts() {
        productService.getProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.rxjava3.core.Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<Product> products) {
                        productMayor = products.stream().max(Comparator.comparing(Product::getStock)).orElse(null);
                        productMenor = products.stream().min(Comparator.comparing(Product::getStock)).orElse(null);


                        assert productMayor != null;
//                        Alert.showMessageSuccess(context, "  " + productMayor.getStock());
                        HandlerUtilitity.setTimeOut(new Runnable() {
                            @Override
                            public void run() {
                                cargarProducts(productMayor, productMenor);
                                binding.infoOpenLottie.setVisibility(View.GONE);
                                binding.cardContenedorInfo.setVisibility(View.VISIBLE);
                                Alert.showMessageSuccess(context, "Carga Exitosa");
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

    void cargarProducts(Product productMayor, Product productMenor) {
        String image = productMayor.getThumbnailUrl();
        String urlImagen = "";
        if (image == null) {
            urlImagen = Credentials.URI_IMAGE_NOT_FOUND;
        } else {
            urlImagen = Credentials.URI_IMAGES + image;
        }
        String image2 = productMenor.getThumbnailUrl();
        String urlImagen2 = "";
        if (image == null) {
            urlImagen2 = Credentials.URI_IMAGE_NOT_FOUND;
        } else {
            urlImagen2 = Credentials.URI_IMAGES + image2;
        }

        Picasso.with(context)
                .load(urlImagen).placeholder(R.mipmap.ic_pamys_launcher_background)
                .fit().into(binding.imageViewProductMayor);
        Picasso.with(context)
                .load(urlImagen2).placeholder(R.mipmap.ic_pamys_launcher_background)
                .fit().into(binding.imageViewProductMenor);

        binding.mayorStockNameProduct.setText(productMayor.getName());
        binding.mayorStockValueProduct.setText("Stock: " + productMayor.getStock());

        binding.menorStockNameProduct.setText(productMenor.getName());
        binding.menorStockValueProduct.setText("Stock: " + productMenor.getStock());


    }

}