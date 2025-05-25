package com.kutuphane.controller;

import com.kutuphane.model.Kitap;
import com.kutuphane.service.KitapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kitaplar")
@CrossOrigin(origins = "*")
public class KitapRestController {
    
    @Autowired
    private KitapService kitapService;
    
    // Tüm kitapları getir
    @GetMapping
    public List<Kitap> tumKitaplariGetir() {
        return kitapService.tumKitaplariGetir();
    }
    
    // ID'ye göre kitap getir
    @GetMapping("/{id}")
    public ResponseEntity<Kitap> kitapGetir(@PathVariable Long id) {
        Optional<Kitap> kitap = kitapService.kitapGetir(id);
        return kitap.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    // Yeni kitap ekle
    @PostMapping
    public Kitap kitapEkle(@RequestBody Kitap kitap) {
        return kitapService.kitapKaydet(kitap);
    }
    
    // Kitap güncelle
    @PutMapping("/{id}")
    public ResponseEntity<Kitap> kitapGuncelle(@PathVariable Long id, @RequestBody Kitap kitap) {
        Kitap guncellenen = kitapService.kitapGuncelle(id, kitap);
        return guncellenen != null ? ResponseEntity.ok(guncellenen) : ResponseEntity.notFound().build();
    }
    
    // Kitap sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> kitapSil(@PathVariable Long id) {
        boolean silindi = kitapService.kitapSil(id);
        return silindi ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
    
    // Kategoriye göre kitapları getir
    @GetMapping("/kategori/{kategori}")
    public List<Kitap> kategoriyeGoreGetir(@PathVariable String kategori) {
        return kitapService.kategoriyeGoreGetir(kategori);
    }
    
    // Okunmuş/okunmamış kitapları getir
    @GetMapping("/okundu/{durum}")
    public List<Kitap> okunduDurumunaGore(@PathVariable boolean durum) {
        return kitapService.okunmusDurumunaGoreGetir(durum);
    }
    
    // Kitap ara
    @GetMapping("/ara")
    public List<Kitap> kitapAra(@RequestParam String q) {
        return kitapService.kitapAra(q);
    }
}