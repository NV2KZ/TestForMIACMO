package ru.nvkz.socks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.nvkz.socks.entity.Socks;

public interface SocksRepository extends JpaRepository<Socks, Long> {
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from socks " +
            "where socks_id in (" +
            "select socks_id from socks " +
            "where socks_color = ?1 and socks_cp = ?2 " +
            "limit ?3)", nativeQuery = true)
    void deleteSocks(String color, Integer cottonPart, Integer quantity);
}
