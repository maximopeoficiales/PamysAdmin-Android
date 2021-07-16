package com.maximoprog.pamysadmin.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maximoprog.pamysadmin.R;
import com.maximoprog.pamysadmin.enviroments.Credentials;
import com.maximoprog.pamysadmin.models.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<Product> productList;
    private OnItemClickListener onItemClickListener;

    public ProductAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.productList = new ArrayList<Product>();
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String image = productList.get(position).getThumbnailUrl();
        String urlImagen = "";
        if (image == null) {
            urlImagen = Credentials.URI_IMAGE_NOT_FOUND;
        } else {
            urlImagen = Credentials.URI_IMAGES + image;
        }

        Picasso.with(context)
                .load(urlImagen).placeholder(R.mipmap.ic_pamys_launcher_background)
                .fit().into(holder.productImageView);
        holder.bind(productList.get(position), this.onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void addProducts(List<Product> products) {
//        newArrayList.clear();
        productList.addAll(products);
        notifyDataSetChanged();
    }

    //    implementacion sencilla
    public interface OnItemClickListener {
        void onItemClick(Product product, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImageView;
        private TextView productNameTextView;
        private TextView descriptionTextView;
        private TextView stockTextView;
        private TextView categoryTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = (ImageView) itemView.findViewById(R.id.productItemImage);
            productNameTextView = (TextView) itemView.findViewById(R.id.productNameItemTxt);
            descriptionTextView = (TextView) itemView.findViewById(R.id.productDescriptionItemTxt);
            stockTextView = (TextView) itemView.findViewById(R.id.productStockItemTxt);
            categoryTextView = (TextView) itemView.findViewById(R.id.productCategoryItemTxt);

        }

        @SuppressLint("SetTextI18n")
        public void bind(final Product product, final OnItemClickListener listener) {
            this.productNameTextView.setText(product.getName());
            this.descriptionTextView.setText(product.getDescription());
            this.stockTextView.setText("Stock: " + product.getStock());
            this.categoryTextView.setText(product.getCategory().getDescription());

//            itemView instancia de la vista entera
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(product, getAdapterPosition());

                }
            });
        }

    }
}
