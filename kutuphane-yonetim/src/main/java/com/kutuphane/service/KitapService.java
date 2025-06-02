package com.kutuphane.service;
import com.kutuphane.model.Kitap;
import com.kutuphane.repository.KitapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class KitapService {
    
    @Autowired
    private KitapRepository kitapRepository;
    
    // Tüm kitapları getir
    public List<Kitap> tumKitaplariGetir() {
        List<Kitap> kitaplar = kitapRepository.findAll();
        System.out.println("Service'den dönen kitap sayısı: " + kitaplar.size());
        return kitaplar;
    }
    
    // ID'ye göre kitap getir
    public Optional<Kitap> kitapGetir(Long id) {
        return kitapRepository.findById(id);
    }
    
    // Yeni kitap kaydet
    public Kitap kitapKaydet(Kitap kitap) {
        return kitapRepository.save(kitap);
    }
    
    // Kitap güncelle
    public Kitap kitapGuncelle(Long id, Kitap yeniKitap) {
        Optional<Kitap> mevcut = kitapRepository.findById(id);
        if (mevcut.isPresent()) {
            Kitap kitap = mevcut.get();
            kitap.setBaslik(yeniKitap.getBaslik());
            kitap.setYazar(yeniKitap.getYazar());
            kitap.setKategori(yeniKitap.getKategori());
            kitap.setSayfaSayisi(yeniKitap.getSayfaSayisi());
            kitap.setYayinTarihi(yeniKitap.getYayinTarihi());
            kitap.setOkundu(yeniKitap.getOkundu());
            return kitapRepository.save(kitap);
        }
        return null;
    }
    
    // Kitap sil
    public boolean kitapSil(Long id) {
        if (kitapRepository.existsById(id)) {
            kitapRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Kategoriye göre kitapları getir
    public List<Kitap> kategoriyeGoreGetir(String kategori) {
        return kitapRepository.findByKategori(kategori);
    }
    
    // Okunmuş/okunmamış kitapları getir
    public List<Kitap> okunmusDurumunaGoreGetir(boolean okundu) {
        return okundu ? kitapRepository.findByOkunduTrue() : kitapRepository.findByOkunduFalse();
    }
    
    // Kitap arama
    public List<Kitap> kitapAra(String anahtar) {
        List<Kitap> baslikSonuclari = kitapRepository.findByBaslikContainingIgnoreCase(anahtar);
        List<Kitap> yazarSonuclari = kitapRepository.findByYazarContainingIgnoreCase(anahtar);
        
        // Sonuçları birleştir ve tekrarları kaldır
        baslikSonuclari.addAll(yazarSonuclari);
        return baslikSonuclari.stream().distinct().toList();
    }
}