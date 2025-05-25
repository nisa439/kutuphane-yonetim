
package com.kutuphane.config;

import com.kutuphane.model.Kitap;
import com.kutuphane.repository.KitapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private KitapRepository kitapRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Örnek kitaplar ekle
        if (kitapRepository.count() == 0) {
            kitapRepository.save(new Kitap("Spring Boot ile Mikroservisler", "John Doe", "Teknoloji", 320));
            kitapRepository.save(new Kitap("Java ile Programlama", "Jane Smith", "Teknoloji", 450));
            kitapRepository.save(new Kitap("Suç ve Ceza", "Dostoyevski", "Roman", 680));
            
            Kitap okunanKitap = new Kitap("Sefiller", "Victor Hugo", "Roman", 1200);
            okunanKitap.setOkundu(true);
            kitapRepository.save(okunanKitap);
            
            System.out.println("Örnek veriler yüklendi!");
        }
    }
}