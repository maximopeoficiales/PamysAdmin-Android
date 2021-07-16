package com.maximoprog.pamysadmin;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.maximoprog.pamysadmin.databinding.ActivityProductDetailsBinding;
import com.maximoprog.pamysadmin.enviroments.Credentials;
import com.maximoprog.pamysadmin.models.Product;
import com.maximoprog.pamysadmin.utils.Alert;

public class ProductDetailsActivity extends AppCompatActivity {
    ActivityProductDetailsBinding binding;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
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
    }

    private void cargarFormulario(Product product) {
        binding.nameInput.setText(product.getName());
        binding.stockInput.setText(product.getStock());
        binding.priceInput.setText(String.valueOf(product.getPrice()));
        binding.salePriceInput.setText(String.valueOf(product.getSalePrice()));
        binding.descriptionInput.setText(product.getDescription());
    }

    private Product getDataForm() {
        Product newProduct = new Product();
        newProduct.setName(binding.nameInput.getText().toString());
        newProduct.setDescription(binding.descriptionInput.getText().toString());
        newProduct.setPrice(Double.parseDouble(binding.priceInput.getText().toString()));
        newProduct.setSalePrice(Double.parseDouble(binding.salePriceInput.getText().toString()));
        newProduct.setIdProduct(this.product.getIdProduct());

        return newProduct;
    }


}