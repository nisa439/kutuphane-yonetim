package com.kutuphane.repository;

import com.kutuphane.model.Kitap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KitapRepository extends JpaRepository<Kitap, Long> {
    
    // Kategoriye göre kitap arama
    List<Kitap> findByKategori(String kategori);
    
    // Yazara göre kitap arama
    List<Kitap> findByYazarContainingIgnoreCase(String yazar);
    
    // Başlığa göre kitap arama
    List<Kitap> findByBaslikContainingIgnoreCase(String baslik);
    
    // Okunmuş kitapları getirme
    List<Kitap> findByOkunduTrue();
    
    // Okunmamış kitapları getirme
    List<Kitap> findByOkunduFalse();
    
    // Özel sorgu: Sayfa sayısına göre kitap arama
    @Query("SELECT k FROM Kitap k WHERE k.sayfaSayisi >= :minSayfa AND k.sayfaSayisi <= :maxSayfa")
    List<Kitap> findBySayfaSayisiBetween(@Param("minSayfa") Integer minSayfa, @Param("maxSayfa") Integer maxSayfa);
}