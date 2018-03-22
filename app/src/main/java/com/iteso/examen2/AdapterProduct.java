package com.iteso.examen2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iteso.examen2.beans.ItemProduct;
import com.iteso.examen2.tools.Constant;

import java.util.ArrayList;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder>{
    private ArrayList<ItemProduct> products;
    private Context context;
    private int fragment;

    AdapterProduct(int fragment, Context context, ArrayList<ItemProduct> products){
        this.fragment = fragment;
        this.products = products;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTitle;
        TextView mStore;
        TextView mLocation;
        TextView mPhone;
        RelativeLayout mDetail;
        ImageView mImage;
        ImageView mThumbnail;


        ViewHolder(View v){
            super(v);
            mTitle = v.findViewById(R.id.item_product_title);
            mStore = v.findViewById(R.id.item_product_store);
            mLocation = v.findViewById(R.id.item_product_location);
            mPhone = v.findViewById(R.id.item_product_phone);
            mImage = v.findViewById(R.id.item_product_image);
            mDetail = v.findViewById(R.id.item_product_layout);
            mThumbnail = v.findViewById(R.id.item_product_thumbnail);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTitle.setText(products.get(holder.getAdapterPosition()).getTitle());
        holder.mStore.setText(products.get(holder.getAdapterPosition()).getStore().getName());
        holder.mLocation.setText(products.get(holder.getAdapterPosition()).getStore().getCity().getName());
        holder.mPhone.setText(products.get(holder.getAdapterPosition()).getStore().getPhone());

        switch(products.get(holder.getAdapterPosition()).getImage()){
            case Constant.TYPE_MAC:
                holder.mImage.setImageResource(R.drawable.mac); break;
            case Constant.TYPE_ALIENWARE:
                holder.mImage.setImageResource(R.drawable.alienware); break;
            case Constant.TYPE_SHEETS:
                holder.mImage.setImageResource(R.drawable.sheets); break;
            case Constant.TYPE_PILLOWS:
                holder.mImage.setImageResource(R.drawable.pillows); break;
            case Constant.TYPE_REFRI:
                holder.mImage.setImageResource(R.drawable.refrigerator); break;
            case Constant.TYPE_MICRO:
                holder.mImage.setImageResource(R.drawable.micro); break;
            case Constant.TYPE_BLENDER:
                    holder.mImage.setImageResource(R.drawable.blender); break;
            case Constant.TYPE_LANIX:
                    holder.mImage.setImageResource(R.drawable.lanix); break;
            case Constant.TYPE_TV:
                    holder.mImage.setImageResource(R.drawable.tv); break;
            case Constant.TYPE_SPEAKERS:
                    holder.mImage.setImageResource(R.drawable.speakers); break;
        }

        switch(products.get(holder.getAdapterPosition()).getStore().getThumbnail()){
            case Constant.TYPE_BESTBUY:
                holder.mThumbnail.setImageResource(R.drawable.bestbuy); break;
            case Constant.TYPE_SEARS:
                holder.mThumbnail.setImageResource(R.drawable.sears); break;
            case Constant.TYPE_FRYS:
                holder.mThumbnail.setImageResource(R.drawable.frys); break;
        }

        Bitmap bitmap = ((BitmapDrawable)holder.mImage.getDrawable()).getBitmap();
        holder.mImage.setImageBitmap(bitmap);
        holder.mPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + products.get(holder.getAdapterPosition()).getStore().getPhone()));
                context.startActivity(intent);

            }
        });`

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
