package com.fernando.sinch.supermarket.services;

import com.fernando.sinch.supermarket.dto.DTODetail;
import com.fernando.sinch.supermarket.dto.DTOInvoice;
import com.fernando.sinch.supermarket.models.Detail;
import com.fernando.sinch.supermarket.models.DetailInvoice;
import com.fernando.sinch.supermarket.models.Invoice;
import com.fernando.sinch.supermarket.models.Product;
import com.fernando.sinch.supermarket.repository.CategoryRepository;
import com.fernando.sinch.supermarket.repository.DetailInvoiceRepository;
import com.fernando.sinch.supermarket.repository.DetailRepository;
import com.fernando.sinch.supermarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailInvoiceServiceImpl implements IDetailInvoice {
    @Autowired
    DetailRepository detailRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DetailInvoiceRepository detailInvoiceRepository;

    @Autowired
    public DetailInvoiceServiceImpl(DetailRepository detailRepository, ProductRepository productRepository, DetailInvoiceRepository detailInvoiceRepository) {
        this.detailRepository = detailRepository;
        this.productRepository = productRepository;
        this.detailInvoiceRepository = detailInvoiceRepository;
    }

    @Override
    public List<DTODetail> getDetailInvoice(DTOInvoice dtoInvoice) {
        Invoice invoice = new Invoice();
        invoice.setId(dtoInvoice.getId());
        invoice.setDescription(dtoInvoice.getDescription());
        invoice.setSubtotal(dtoInvoice.getSubtotal());
        invoice.setIsv(dtoInvoice.getIsv());
        invoice.setTotal(dtoInvoice.getTotal());

        List<DetailInvoice> listDetailInvoice = detailInvoiceRepository.getByInvoice(invoice);
        List<DTODetail> listDtoDetail = new ArrayList<DTODetail>();
        Detail detail;
        DTODetail dtoDetail;
        Product product;
        for(DetailInvoice detailInvoice : listDetailInvoice) {
            detail = detailRepository.findById(detailInvoice.getDetail().getId()).get();
            product = productRepository.findById(detail.getProduct().getId()).get();

            System.out.print(detail);
            System.out.print(product);
            dtoDetail = new DTODetail();
            dtoDetail.setId(detail.getId());
            dtoDetail.setIdProduct(product.getId());
            dtoDetail.setDescription(product.getDescription());
            dtoDetail.setPrice(product.getPrice());
            dtoDetail.setQuantity(detail.getQuantity());

            listDtoDetail.add(dtoDetail);
        }

        return listDtoDetail;
    }
}
