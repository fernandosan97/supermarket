package com.fernando.sinch.supermarket.repository;

import com.fernando.sinch.supermarket.models.Kardex;
import com.fernando.sinch.supermarket.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface KardexRepository extends JpaRepository<Kardex, Integer> {
    List<Kardex> getByProduct(Product product);

    @Query(value = "SELECT product_id as idProduct, p.description, SUM(quantity) AS quantity  FROM kardex INNER JOIN product p ON kardex.product_id = p.id_product GROUP BY product_id ORDER BY quantity desc", nativeQuery = true)
    List<Map<String,Object>> getReport();
}
