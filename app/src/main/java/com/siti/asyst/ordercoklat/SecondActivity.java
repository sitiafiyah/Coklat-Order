package com.siti.asyst.ordercoklat;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.siti.asyst.ordercoklat.utility.Constant;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener,
        CompoundButton.OnCheckedChangeListener {

    EditText namaET;

    RadioGroup menuRG;

    CheckBox oreoCB, kejuCB, kacangCB;

    Button orderButton;
    String selectedMenu = "Choco Original";
    String topping;

    ArrayList<String> listTopping = new ArrayList<String>();

    int hargaTopping = 0, hargaMenu = 0, hargaTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        namaET = findViewById(R.id.nama_edittext);

        menuRG = findViewById(R.id.menu_radiogroup);

        menuRG.setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.original_radiobutton)).setChecked(true);

        oreoCB = findViewById(R.id.oreo_checkbox);
        kejuCB = findViewById(R.id.keju_checkbox);
        kacangCB = findViewById(R.id.kacang_checkbox);

        orderButton = findViewById(R.id.order_button);

        oreoCB.setOnCheckedChangeListener(this);
        kejuCB.setOnCheckedChangeListener(this);
        kacangCB.setOnCheckedChangeListener(this);

        orderButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_button:
                String name = namaET.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Isikan Nama Anda", Toast.LENGTH_SHORT).show();
                } else if (!namaET.getText().toString().matches("[a-zA-Z ]+")) {
                    namaET.setError("Isikan nama anda dengan huruf Alfabet");
                } else {

                    hargaTotal = hargaMenu + hargaTopping;
                    Log.d("bayar", "bayar" + hargaTotal);
                    topping = "";
                    for (int i = 0; i < listTopping.size(); i++) {
                        topping = topping + " " + listTopping.get(i);
                    }

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("Confirmation")
                            .setCancelable(false)
                            .setMessage("Are you sure?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent returnIntent = new Intent(SecondActivity.this, MainActivity.class);

                                    String nama = namaET.getText().toString();
                                    //String result = "Nama " + nama + "Menu " + selectedMenu + "Topping " + topping;
                                    //Ambil Data
                                    //returnIntent.putExtra(Constant.KEY_RESULT, result);
                                    returnIntent.putExtra(Constant.KEY_NAMA, nama);
                                    returnIntent.putExtra(Constant.KEY_MENU, selectedMenu);
                                    returnIntent.putExtra(Constant.KEY_TOPPING, topping);
                                    returnIntent.putExtra(Constant.KEY_HARGA_TOTAL, hargaTotal);

                                    setResult(Activity.RESULT_OK, returnIntent);
                                    finish();
                                    //startActivity(returnIntent);
                                }
                            })
                            .setNegativeButton("NO", null)
                            .show();
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();

        switch (id) {
            case R.id.oreo_checkbox:
                if (isChecked) {
                    listTopping.add("Oreo");
                    hargaTopping += 4000;
                } else {
                    listTopping.remove("Oreo");
                }
                break;
            case R.id.keju_checkbox:
                if (isChecked) {
                    listTopping.add("Keju");
                    hargaTopping += 3000;
                } else {
                    listTopping.remove("Keju");
                }
                break;
            case R.id.kacang_checkbox:
                if (isChecked) {
                    listTopping.add("Kacang");
                    hargaTopping += 3000;
                } else {
                    listTopping.remove("Kacang");
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.original_radiobutton:
                selectedMenu = "Choco Original";
                hargaMenu = 15000;
                break;
            case R.id.milk_radiobutton:
                selectedMenu = "Choco Milk";
                hargaMenu = 20000;
                break;
            case R.id.tiramizu_radiobutton:
                selectedMenu = "Choco Tiramizu";
                hargaMenu = 23000;
                break;
        }
    }
}
