package com.siti.asyst.ordercoklat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.siti.asyst.ordercoklat.adapter.OrderAdapter;
import com.siti.asyst.ordercoklat.model.Order;
import com.siti.asyst.ordercoklat.utility.Constant;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText namaET;

    ListView listView;
    TextView namaTV, menuTV, toppingTV, totalTV, ppnTV;

    Button addButton, closeButton;
    ArrayList<Order> listOrder = new ArrayList<>();

    int requestOrder = 100;

    OrderAdapter orderAdapter;

    int total = 0;
    int ppn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namaET = findViewById(R.id.nama_edittext);

        namaTV = findViewById(R.id.nama_textview);
        menuTV = findViewById(R.id.menu_textview);
        toppingTV = findViewById(R.id.topping_textview);
        totalTV = findViewById(R.id.hargatotal_textview);
        ppnTV = findViewById(R.id.ppn_textview);

        listView = findViewById(R.id.listview);

        addButton = findViewById(R.id.add_button);
        closeButton = findViewById(R.id.close_button);

        addButton.setOnClickListener(this);
        closeButton.setOnClickListener(this);

        orderAdapter = new OrderAdapter(this, listOrder);
        listView.setAdapter(orderAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_button:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivityForResult(intent, requestOrder); //reqcode
                break;

            case R.id.close_button:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == requestOrder) {
            if (resultCode == Activity.RESULT_OK) {
                // Order order = data.getExtras().getParcelable(nama);

                Bundle bundle = data.getExtras();
                //String result = bundle.getString(Constant.KEY_RESULT, "");
                String name = bundle.getString(Constant.KEY_NAMA, "");
                String menu = bundle.getString(Constant.KEY_MENU, "");
                String topping = bundle.getString(Constant.KEY_TOPPING, "");
                int hargaTotal = bundle.getInt(Constant.KEY_HARGA_TOTAL, 0);

                total += hargaTotal;
                ppn = total * 10 / 100;
                //Log.d("total", "total"+ hargaTotal);

                Order order = new Order(name, menu, topping, hargaTotal);
                listOrder.add(order);
                orderAdapter.notifyDataSetChanged();
                totalTV.setText(total + ppn + "");
                ppnTV.setText(ppn + "");

            }
        }
    }
}
