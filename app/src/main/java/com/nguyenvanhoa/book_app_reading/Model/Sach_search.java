package com.nguyenvanhoa.book_app_reading.Model;

import java.io.Serializable;

public class Sach_search implements Serializable {
    private int Hinhanh;
    private String tensach;
    private String Tacgia;
    private String ngay;
    private String theloai;

    public Sach_search(int hinhanh, String tensach, String tacgia, String ngay, String theloai) {
        Hinhanh = hinhanh;
        this.tensach = tensach;
        Tacgia = tacgia;
        this.ngay = ngay;
        this.theloai = theloai;
    }

    public int getHinhanh() {
        return Hinhanh;
    }

    public void setHinhanh(int hinhanh) {
        Hinhanh = hinhanh;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTacgia() {
        return Tacgia;
    }

    public void setTacgia(String tacgia) {
        Tacgia = tacgia;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }
}
