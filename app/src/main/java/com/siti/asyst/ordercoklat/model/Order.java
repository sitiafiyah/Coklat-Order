package com.siti.asyst.ordercoklat.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable {
    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
    String nama;
    String selectedMenu;
    String selectedTopping;
    int hargaTotal;

    public Order(String nama, String selectedMenu, String selectedTopping, int hargaTotal) {
        this.nama = nama;
        this.selectedMenu = selectedMenu;
        this.selectedTopping = selectedTopping;
        this.hargaTotal = hargaTotal;
    }

    protected Order(Parcel in) {
        nama = in.readString();
        selectedMenu = in.readString();
        selectedTopping = in.readString();
        hargaTotal = in.readInt();
    }

    public static Creator<Order> getCREATOR() {
        return CREATOR;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSelectedMenu() {
        return selectedMenu;
    }

    public void setSelectedMenu(String selectedMenu) {
        this.selectedMenu = selectedMenu;
    }

    public String getSelectedTopping() {
        return selectedTopping;
    }

    public void setSelectedTopping(String selectedTopping) {
        this.selectedTopping = selectedTopping;
    }

    public int getHargaTotal() {
        return hargaTotal;
    }

    public void setHargaTotal(int hargaTotal) {
        this.hargaTotal = hargaTotal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(selectedMenu);
        dest.writeString(selectedTopping);
        dest.writeInt(hargaTotal);
    }
}
