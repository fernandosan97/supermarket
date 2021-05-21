package com.fernando.sinch.supermarket.services;

import com.fernando.sinch.supermarket.dto.DTOKardex;
import com.fernando.sinch.supermarket.models.Invoice;
import com.fernando.sinch.supermarket.models.Kardex;
import com.fernando.sinch.supermarket.models.Product;
import com.fernando.sinch.supermarket.repository.InvoiceRepository;
import com.fernando.sinch.supermarket.repository.KardexRepository;
import com.fernando.sinch.supermarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class KardexServiceImpl implements IKardex{
    @Autowired
    KardexRepository kardexRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    EntityManager entityManager;

    public KardexServiceImpl(KardexRepository kardexRepository, ProductRepository productRepository, InvoiceRepository invoiceRepository, EntityManager entityManager) {
        this.kardexRepository = kardexRepository;
        this.productRepository = productRepository;
        this.invoiceRepository = invoiceRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<DTOKardex> getKardex() {
        List<Kardex> listKardex =  kardexRepository.findAll();

        List<DTOKardex> listDtoKardex = new ArrayList<DTOKardex>();
        DTOKardex dtoKardex;
        Invoice invoice;
        Product product;

        for(Kardex objKardex : listKardex) {
            invoice = invoiceRepository.findById(objKardex.getInvoice().getId()).get();
            product = productRepository.findById(objKardex.getProduct().getId()).get();

            dtoKardex = new DTOKardex();
            dtoKardex.setId(objKardex.getId());
            dtoKardex.setDate(objKardex.getDate());
            dtoKardex.setIdInvoice(invoice.getId());
            dtoKardex.setProductDescription(product.getDescription());
            dtoKardex.setQuantity(objKardex.getQuantity());
            listDtoKardex.add(dtoKardex);
        }

        return listDtoKardex;
    }

    @Override
    public List<DTOKardex> getByProduct(Integer idProduct) {
        Product objProduct = productRepository.findById(idProduct).get();
        List<Kardex> listKardex = kardexRepository.getByProduct(objProduct);

        List<DTOKardex> listDtoKardex = new ArrayList<DTOKardex>();
        DTOKardex dtoKardex;

        for(Kardex objKardex : listKardex) {
            dtoKardex = new DTOKardex();
            dtoKardex.setId(objKardex.getId());
            dtoKardex.setDate(objKardex.getDate());
            dtoKardex.setIdInvoice(objKardex.getInvoice().getId());
            dtoKardex.setProductDescription(objProduct.getDescription());
            dtoKardex.setQuantity(objKardex.getQuantity());
            listDtoKardex.add(dtoKardex);
        }

        return listDtoKardex;
    }

    @Override
    public List<Map<String,Object>> getReport() {
        List<Map<String,Object>> list = kardexRepository.getReport();
//        for(Object obj : list) {
//            System.out.println(list.get(1));
//        }
        return list;
    }
}
