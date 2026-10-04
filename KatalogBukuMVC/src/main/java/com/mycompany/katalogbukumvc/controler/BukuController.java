/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.katalogbukumvc.controler;

import com.mycompany.katalogbukumvc.model.Buku;
import com.mycompany.katalogbukumvc.model.BukuModel;
import com.mycompany.katalogbukumvc.view.BukuView;

/**
 *
 * @author YodhaAce
 */
public class BukuController {

    private final BukuModel model;
    private final BukuView view;

    public BukuController(BukuModel model, BukuView view) {
        this.model = model;
        this.view = view;

        view.addSimpanListener(event -> simpanBuku());
        view.addHapusListener(event -> hapusBuku(view.getBarisTerpilih()));
        view.addClearListener(event -> clearBuku());

        view.tampilkanData(model.getSemuaBuku());
    }

    public void simpanBuku() {
        String judul = view.getJudul().trim();
        String penulis = view.getPenulis().trim();
        String teksTahun = view.getTahun().trim();
        if (judul.isEmpty() || penulis.isEmpty() || teksTahun.isEmpty()) {
            view.tampilkanPeringatan("Input Belum Lengkap", "Judul, Penulis, Tahun terbit harus di isi");
            return;
        }
        int tahunTerbit;
        {
            try {
                tahunTerbit = Integer.parseInt(teksTahun);
            } catch (Exception e) {
                view.tampilkanPeringatan("Input tidak valid", "Tahun terbit harus berupa angka.");
                return;
            }
            model.tambahBuku(new Buku(judul, penulis, tahunTerbit));
            view.tampilkanData(model.getSemuaBuku());
            view.tampilkanInfo("Sukses", "Buku berhasil disimpan.");
            view.kosongkanForm();
        }
    }

    public void hapusBuku(int index) {
        int baris = view.getBarisTerpilih();

        if (baris == -1) {
            view.tampilkanPeringatan("Hapus Buku", "Silakan pilih buku yang ingin dihapus.");
            return;
        }
        model.hapusBuku(baris);
        view.tampilkanData(model.getSemuaBuku());
    }

    public void clearBuku() {
        model.hapusSemua();
        view.tampilkanData(model.getSemuaBuku());
    }
}
