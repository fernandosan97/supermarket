package com.fernando.sinch.supermarket.controllers;

import com.fernando.sinch.supermarket.dto.DTODetail;
import com.fernando.sinch.supermarket.dto.DTOInvoice;
import com.fernando.sinch.supermarket.json.JsonResponse;
import com.fernando.sinch.supermarket.models.Invoice;
import com.fernando.sinch.supermarket.payload.request.InvoiceRequest;
import com.fernando.sinch.supermarket.services.IDetailInvoice;
import com.fernando.sinch.supermarket.services.IInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Autowired
    IInvoice iInvoice;

    @Autowired
    IDetailInvoice iDetailInvoice;

    @GetMapping()
    @PreAuthorize("hasRole('CASHIER') or hasRole('ADMIN')")
    public JsonResponse getInvoices() {
        List<Invoice> invoices = iInvoice.getInvoices();

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.put("data", invoices);
        jsonResponse.setMessage("Success");
        return jsonResponse;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CASHIER') or hasRole('ADMIN')")
    public JsonResponse getDetailInvoice(@PathVariable("id") Integer id) {
        DTOInvoice dtoInvoice = iInvoice.getById(id);
        System.out.print(dtoInvoice);
        List<DTODetail> listDtoDetail = iDetailInvoice.getDetailInvoice(dtoInvoice);

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.put("invoice", dtoInvoice);
        jsonResponse.put("detail", listDtoDetail);
        jsonResponse.setMessage("Success");
        return jsonResponse;
    }

    @PostMapping()
    @PreAuthorize("hasRole('CASHIER') or hasRole('ADMIN')")
    public JsonResponse save(@RequestBody InvoiceRequest invoice) {
        String message = "";
        List<DTODetail> listDtoDetail = iInvoice.validStock(invoice);

        DTOInvoice dtoInvoice;
        Object response;
        // If return > 0, means there are product out of stock
        if(listDtoDetail.size() > 0) {
            response = listDtoDetail;
            message = "There are product out of stock";
        } else {
            dtoInvoice = iInvoice.saveOrUpdate(invoice);
            response = dtoInvoice;
            message = "Success";
        }

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.put("data", response);
        jsonResponse.setMessage(message);
        return jsonResponse;
    }
}
