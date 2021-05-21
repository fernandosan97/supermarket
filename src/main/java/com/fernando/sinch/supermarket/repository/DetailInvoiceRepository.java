package com.fernando.sinch.supermarket.repository;

import com.fernando.sinch.supermarket.models.DetailInvoice;
import com.fernando.sinch.supermarket.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailInvoiceRepository extends JpaRepository<DetailInvoice, Integer> {
    List<DetailInvoice> getByInvoice(Invoice invoice);
}
