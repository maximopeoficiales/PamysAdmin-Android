package com.maximoprog.pamysadmin.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.maximoprog.pamysadmin.api.services.ProductService;
import com.maximoprog.pamysadmin.databinding.FragmentDashboardBinding;
import com.maximoprog.pamysadmin.models.Product;
import com.maximoprog.pamysadmin.utils.Alert;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DashboardFragment extends Fragment {
    private static final String TAG = DashboardFragment.class.getName();
    public final CompositeDisposable disposables = new CompositeDisposable();
    public ProductService productService = new ProductService();
    public Context context;
    FragmentDashboardBinding binding;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        context = container.getContext();

        binding.guardarProductoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = getDataForm();
                productService.save(product).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Product>() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Product product) {
                                Alert.showMessageSuccess(context, "Creacion exitosa del producto");
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                Log.d(TAG, "Estoy en la creacion de Producto");
                                Alert.showMessageError(context, "Error: " + e.getMessage());

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

            }
        });


        return binding.getRoot();
    }

    private Product getDataForm() {
        Product newProduct = new Product();
        newProduct.setName(binding.nameInput.getText().toString());
        newProduct.setDescription(binding.descriptionInput.getText().toString());
        newProduct.setStock(Integer.parseInt(binding.stockInput.getText().toString()));
        newProduct.setPrice(Double.parseDouble(binding.priceInput.getText().toString()));
        newProduct.setSalePrice(Double.parseDouble(binding.salePriceInput.getText().toString()));
        newProduct.setIdVendor(1);
        newProduct.setIdCategory(1);
//        newProduct.setIdProduct(this.product.getIdProduct());

        return newProduct;
    }

    @Override
    public void onDestroy() {
        if (disposables != null) {
            disposables.clear();
        }
        super.onDestroy();
    }
}