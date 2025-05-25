package com.kutuphane.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "kitaplar")
public class Kitap {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String baslik;
    
    @Column(nullable = false)
    private String yazar;
    
    private String kategori;
    
    @Column(name = "sayfa_sayisi")
    private Integer sayfaSayisi;
    
    @Column(name = "yayin_tarihi")
    private LocalDate yayinTarihi;
    
    private Boolean okundu = false;
    
    // Constructors
    public Kitap() {}
    
    public Kitap(String baslik, String yazar, String kategori, Integer sayfaSayisi) {
        this.baslik = baslik;
        this.yazar = yazar;
        this.kategori = kategori;
        this.sayfaSayisi = sayfaSayisi;
        this.yayinTarihi = LocalDate.now();
    }
    
    // Getter ve Setter metodları
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getBaslik() { return baslik; }
    public void setBaslik(String baslik) { this.baslik = baslik; }
    
    public String getYazar() { return yazar; }
    public void setYazar(String yazar) { this.yazar = yazar; }
    
    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }
    
    public Integer getSayfaSayisi() { return sayfaSayisi; }
    public void setSayfaSayisi(Integer sayfaSayisi) { this.sayfaSayisi = sayfaSayisi; }
    
    public LocalDate getYayinTarihi() { return yayinTarihi; }
    public void setYayinTarihi(LocalDate yayinTarihi) { this.yayinTarihi = yayinTarihi; }
    
    public Boolean getOkundu() { return okundu; }
    public void setOkundu(Boolean okundu) { this.okundu = okundu; }
}