package com.icaboalo.barcodescanner.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.icaboalo.barcodescanner.R;
import com.icaboalo.barcodescanner.io.model.ProductApiModel;

import java.util.ArrayList;

/**
 * Created by icaboalo on 21/06/16.
 */
public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder> {

    Context mContext;
    private ArrayList<ProductApiModel> productList;
    private LayoutInflater inflater;

    public ProductRecyclerAdapter(Context context) {
        this.mContext = context;
        this.productList = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        ProductApiModel product = productList.get(position);
        holder.setProductName(product.getName());
        holder.setProductTrademark(product.getTrademark());
        holder.setProductType(product.getType());
        holder.setProductImage(product.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void addProduct(ProductApiModel product){
        productList.add(product);
        notifyItemInserted(productList.size() -1);
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView productName, productTrademark, productType;
        private ImageView productImage;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.product_name);
            productTrademark = (TextView) itemView.findViewById(R.id.product_trademark);
            productType = (TextView) itemView.findViewById(R.id.product_type);
            productImage = (ImageView) itemView.findViewById(R.id.product_image);
        }

        public void setProductName(String name){
            if (name != null){
                productName.setText(name);
            } else {
                productName.setText("NOT FOUND");
            }
        }

        public void setProductTrademark(String trademark){
            if (trademark != null){
                productTrademark.setText(trademark);
            } else {
                productTrademark.setText("NOT FOUND");
            }
        }

        public void setProductType(String type) {
            if (type != null){
                productType.setText(type);
            } else {
                productType.setText("NOT FOUND");
            }
        }

        public void setProductImage(String fileName){
            if (fileName != null){
                Glide.with(mContext).load("http://v2.mxgrability.rappi.com/uploads/products/high/" + fileName).placeholder(android.R.drawable.ic_menu_close_clear_cancel).into(productImage);
            } else {
                Glide.with(mContext).load(R.mipmap.ic_launcher).into(productImage);
            }
        }
    }
}
