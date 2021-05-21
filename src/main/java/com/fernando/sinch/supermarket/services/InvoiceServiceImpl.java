package com.fernando.sinch.supermarket.services;

import com.fernando.sinch.supermarket.dto.DTODetail;
import com.fernando.sinch.supermarket.dto.DTOInvoice;
import com.fernando.sinch.supermarket.models.*;
import com.fernando.sinch.supermarket.payload.request.InvoiceRequest;
import com.fernando.sinch.supermarket.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceServiceImpl implements IInvoice{
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DetailRepository detailRepository;

    @Autowired
    DetailInvoiceRepository detailInvoiceRepository;

    @Autowired
    KardexRepository kardexRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, ProductRepository productRepository, DetailRepository detailRepository, DetailInvoiceRepository detailInvoiceRepository) {
        this.invoiceRepository = invoiceRepository;
        this.productRepository = productRepository;
        this.detailRepository = detailRepository;
        this.detailInvoiceRepository = detailInvoiceRepository;
    }

    @Override
    public List<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public DTOInvoice saveOrUpdate(InvoiceRequest invoiceRequest) {
        double subtotal = 0;
        double isv = 0;
        double total = 0;

        Detail detail;
        Product product;
        List<Detail> detailList = new ArrayList<Detail>();

        for(DTODetail objDtoDetail : invoiceRequest.getDetail()) {
            // search product to save in detail
            product = productRepository.findById(objDtoDetail.getIdProduct()).get();

            detail = new Detail(product, objDtoDetail.getQuantity());

            // Save detail
            Detail newDetail = detailRepository.save(detail);
            detailList.add(newDetail);

            // Set the new stock to the product
            Integer newStock = product.getStock() - objDtoDetail.getQuantity();
            product.setStock(newStock);
            productRepository.save(product);

            subtotal += objDtoDetail.getQuantity() * product.getPrice();
        }

        isv = subtotal * 0.15;
        total = subtotal + isv;

        // Save invoice
        Invoice invoice = new Invoice(invoiceRequest.getDescription(), subtotal, isv, total);
        invoice = invoiceRepository.save(invoice);

        // Process to save Detail Invoice and Kardex of the Product
        DetailInvoice detailInvoice;
        Kardex kardex;
        for (Detail objDetail : detailList) {
            // Save detail - invoice
            detailInvoice = new DetailInvoice(invoice, objDetail);
            detailInvoiceRepository.save(detailInvoice);

            // Save kardex
            product = productRepository.findById(objDetail.getProduct().getId()).get();
            kardex = new Kardex(new Date(), invoice, product, objDetail.getQuantity());
            kardexRepository.save(kardex);
        }

        DTOInvoice dtoInvoice = new DTOInvoice(invoice);

        return dtoInvoice;
    }

    @Override
    public List<DTODetail> validStock(InvoiceRequest invoiceRequest) {
        Detail detail;
        DTODetail dtoDetail;
        Product product;
        List<DTODetail> listDtoDetail = new ArrayList<DTODetail>(); // List for Details that have not stock

        for(DTODetail objDtoDetail : invoiceRequest.getDetail()) {
            // search product to save in detail
            product = productRepository.findById(objDtoDetail.getIdProduct()).get();

            if(objDtoDetail.getQuantity() > product.getStock()) {
                detail = new Detail(product, objDtoDetail.getQuantity());
                dtoDetail = new DTODetail(product.getId(), product.getDescription(), product.getPrice(), detail.getQuantity());
                dtoDetail.setId(detail.getId());
                listDtoDetail.add(dtoDetail);
            }
        }

        return listDtoDetail;
    }

    @Override
    public DTOInvoice getById(Integer id) {
        Invoice invoice = invoiceRepository.findById(id).get();
        DTOInvoice dtoInvoice = new DTOInvoice(invoice);
        return dtoInvoice;
    }
}
