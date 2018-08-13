package com.siti.asyst.ordercoklat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.siti.asyst.ordercoklat.R;
import com.siti.asyst.ordercoklat.model.Order;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Order> {

    ArrayList<Order> listOrder;

    public OrderAdapter(Context context, ArrayList<Order> listOrder) {
        super(context, R.layout.item_order, listOrder);
        this.listOrder = listOrder;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, null);
        TextView nameTV = rootView.findViewById(R.id.nama_textview);
        TextView menuTV = rootView.findViewById(R.id.menu_textview);
        TextView toppingTV = rootView.findViewById(R.id.topping_textview);

        TextView harga1TV = rootView.findViewById(R.id.harga1_textview);

        nameTV.setText(listOrder.get(position).getNama());
        harga1TV.setText("" + listOrder.get(position).getHargaTotal());
        menuTV.setText(listOrder.get(position).getSelectedMenu());
        toppingTV.setText(listOrder.get(position).getSelectedTopping());

        return rootView;
    }

}
