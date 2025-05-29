package com.shop.repository;

import com.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    // 상품명으로 검색
    Product findByProductName(String productName);

    //상품명 존재 여부 확인
    boolean existsByProductName(String productName);

    // 가격으로 검색
    List<Product> findByPrice(int price);

    // 가격 범위로 검색
    List<Product> findByPriceBetween(int minPrice, int maxPrice);

    // 재고가 있는 상품만 검색
    List<Product> findByStockGreaterThan(int stock);

    //상품명에 특정 문자열이 포함된 상품 검색
    List<Product> findByProductNameContaining(String keyword);

    // 가격순 정렬
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findAllByOrderByPriceDesc();
}
