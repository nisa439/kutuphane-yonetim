package com.kutuphane.controller;

import com.kutuphane.model.Kitap;
import com.kutuphane.service.KitapService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class KitapWebController {
    
    @Autowired
    private KitapService kitapService;
    
    // Ana sayfa
    @GetMapping("/")
    public String anaSayfa(Model model) {
        model.addAttribute("toplamKitap", kitapService.tumKitaplariGetir().size());
        model.addAttribute("okunmusKitap", kitapService.okunmusDurumunaGoreGetir(true).size());
        return "index";
    }
    
    // Kitap listesi sayfası
    @GetMapping("/kitaplar")
    public String kitapListesi(Model model) {
        model.addAttribute("kitaplar", kitapService.tumKitaplariGetir());
        return "kitap-listesi";
    }
    
    // Kitap ekleme sayfası
    @GetMapping("/kitap-ekle")
    public String kitapEkleSayfasi(Model model) {
        model.addAttribute("kitap", new Kitap());
        return "kitap-ekle";
    }
    
    // Kitap ekleme işlemi
    @PostMapping("/kitap-kaydet")
    public String kitapKaydet(@ModelAttribute Kitap kitap) {
        kitapService.kitapKaydet(kitap);
        return "redirect:/kitaplar";
    }
    
    // Kitap silme işlemi
    @GetMapping("/kitap-sil/{id}")
    public String kitapSil(@PathVariable Long id) {
        kitapService.kitapSil(id);
        return "redirect:/kitaplar";
    }// Kitap düzenleme sayfası
    @GetMapping("/kitap-duzenle/{id}")
    public String kitapDuzenleSayfasi(@PathVariable Long id, Model model) {
        Optional<Kitap> kitap = kitapService.kitapGetir(id);
        if (kitap.isPresent()) {
            model.addAttribute("kitap", kitap.get());
            return "kitap-duzenle";
        }
        return "redirect:/kitaplar";
    }

    // Kitap güncelleme işlemi
    @PostMapping("/kitap-guncelle/{id}")
    public String kitapGuncelle(@PathVariable Long id, @ModelAttribute Kitap kitap) {
        kitapService.kitapGuncelle(id, kitap);
        return "redirect:/kitaplar";
    }
 // Kitap arama
    @GetMapping("/ara")
    public String kitapAra(@RequestParam(required = false) String q, Model model) {
        if (q != null && !q.trim().isEmpty()) {
            model.addAttribute("kitaplar", kitapService.kitapAra(q));
            model.addAttribute("aramaKelimesi", q);
        } else {
            model.addAttribute("kitaplar", kitapService.tumKitaplariGetir());
        }
        return "kitap-listesi";
    }
}