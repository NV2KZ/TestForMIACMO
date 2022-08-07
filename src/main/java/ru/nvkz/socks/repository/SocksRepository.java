package ru.nvkz.socks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.nvkz.socks.entity.Socks;

import java.util.List;

public interface SocksRepository extends JpaRepository<Socks, Long> {
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from socks " +
            "where socks_id in (" +
            "select socks_id from socks " +
            "where socks_color = ?1 and socks_cp = ?2 " +
            "limit ?3)", nativeQuery = true)
    void deleteSocks(String color, Integer cottonPart, Integer quantity);

    @Query(value = "select s from Socks s where s.color = :color and s.cottonPart > :cottonPart")
    List<Socks> findWithMoreThanOperator(String color, Integer cottonPart);

    @Query(value = "select s from Socks s where s.color = :color and s.cottonPart < :cottonPart")
    List<Socks> findWithLessThanOperator(String color, Integer cottonPart);

    @Query(value = "select s from Socks s where s.color = :color and s.cottonPart = :cottonPart")
    List<Socks> findWithEqualOperator(String color, Integer cottonPart);
}
