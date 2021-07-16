package com.maximoprog.pamysadmin;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.maximoprog.pamysadmin.api.services.ProductService;
import com.maximoprog.pamysadmin.databinding.ActivityProductDetailsBinding;
import com.maximoprog.pamysadmin.enviroments.Credentials;
import com.maximoprog.pamysadmin.models.Product;
import com.maximoprog.pamysadmin.utils.Alert;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProductDetailsActivity extends AppCompatActivity {
    private static final String TAG = ProductDetailsActivity.class.getName();
    public final CompositeDisposable disposables = new CompositeDisposable();
    public final CompositeDisposable disposables2 = new CompositeDisposable();
    public ProductService productService = new ProductService();
    ActivityProductDetailsBinding binding;
    private Product product;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        context = this;
        View view = binding.getRoot();
        setContentView(view);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.get("product") != null) {
            product = bundle.getParcelable("product");
            String image = product.getThumbnailUrl();
//          muestro un msg y seteo el texto en el input
            String urlImagen = "";
            if (image == null) {
                urlImagen = Credentials.URI_IMAGE_NOT_FOUND;
            } else {
                urlImagen = Credentials.URL_API + image;
            }
            cargarFormulario(product);
//            Picasso.with(this)
//                    .load(urlImagen)
//                    .fit().into(binding.imagenNewCard);
//            binding.titleNewCard.setText(noticia.getTitle());
//            binding.sumaryNewCard.setText(noticia.getSummary());
//            binding.descriptionNewCard.setText(noticia.getContent());
//            Alert.showMessageSuccess(this, "Se cargo correctamente la noticia");

        } else {
            Alert.showMessageError(this, "Error en la carga del Producto");
        }

        binding.actualizarProductoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productService.update(getDataForm()).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Product>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(@NonNull Product product) {
                                Alert.showMessageSuccess(context, "Producto actualizado correctamente");
                                redirectHomeFragment();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Alert.showMessageError(context, "Error: " + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });


        binding.eliminarProductoBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                productService.delete(product.getIdProduct()).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Product>() {
                                       @Override
                                       public void onSubscribe(@NonNull Disposable d) {
                                           disposables2.add(d);

                                       }

                                       @Override
                                       public void onNext(@NonNull Product product) {
                                           Alert.showMessageSuccess(context, "Producto Eliminado correctamente");
                                           redirectHomeFragment();
                                       }

                                       @Override
                                       public void onError(@NonNull Throwable e) {
                                           Alert.showMessageError(context, "Error: " + e.getMessage());

                                       }

                                       @Override
                                       public void onComplete() {

                                       }
                                   }
                        );
            }
        });
    }

    private void cargarFormulario(Product product) {
        binding.nameInput.setText(product.getName());
        binding.stockInput.setText(String.valueOf(product.getStock()));
        binding.priceInput.setText(String.valueOf(product.getPrice()));
        binding.salePriceInput.setText(String.valueOf(product.getSalePrice()));
        binding.descriptionInput.setText(product.getDescription());
    }

    private Product getDataForm() {
        Product newProduct = new Product();
        newProduct.setName(binding.nameInput.getText().toString());
        newProduct.setDescription(binding.descriptionInput.getText().toString());
        newProduct.setStock(Integer.parseInt(binding.stockInput.getText().toString()));
        newProduct.setPrice(Double.parseDouble(binding.priceInput.getText().toString()));
        newProduct.setSalePrice(Double.parseDouble(binding.salePriceInput.getText().toString()));
        newProduct.setIdProduct(this.product.getIdProduct());
        newProduct.setIdCategory(this.product.getIdCategory());
        newProduct.setIdVendor(this.product.getIdVendor());

        return newProduct;
    }


    @Override
    public void onDestroy() {
        if (disposables != null) {
            disposables.clear();
        }
        if (disposables2 != null) {
            disposables2.clear();
        }
        super.onDestroy();
    }

    public void redirectHomeFragment() {

    }
}