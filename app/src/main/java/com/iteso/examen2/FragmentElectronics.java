package com.iteso.examen2;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.examen2.beans.ItemProduct;
import com.iteso.examen2.database.DataBaseHandler;
import com.iteso.examen2.database.ItemProductControl;
import com.iteso.examen2.tools.Constant;

import java.util.ArrayList;
import java.util.Iterator;


public class FragmentElectronics extends Fragment {
    RecyclerView recyclerView;
    ArrayList<ItemProduct> products;
    AdapterProduct adapterProduct;

    public FragmentElectronics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_electronics, container, false);
        recyclerView = rootView.findViewById(R.id.fragment_recycler);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        DataBaseHandler dh = DataBaseHandler.getInstance(getActivity());
        products = new ItemProductControl().getItemProductsByCategory(3, dh);
        adapterProduct = new AdapterProduct(Constant.FRAGMENT_ELECTRONICS, getActivity(), products);
        recyclerView.setAdapter(adapterProduct);
    }
    public void notifyDataSetChanged(ItemProduct itemProduct){
        products.add(itemProduct);
        adapterProduct.notifyDataSetChanged();
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        ItemProduct itemProduct = data.getParcelableExtra(Constant.EXTRA_PRODUCT);
//        Iterator<ItemProduct> iterator = products.iterator();
//        int position = 0;
//        while (iterator.hasNext()) {
//            ItemProduct item = iterator.next();
//            if (item.getCode() == itemProduct.getCode()) {
//                products.set(position, itemProduct);
//                break;
//            }
//            position++;
//        }
//        adapterProduct.notifyDataSetChanged();
//    }

}
