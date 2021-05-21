package com.fernando.sinch.supermarket.controllers;

import com.fernando.sinch.supermarket.dto.DTOKardex;
import com.fernando.sinch.supermarket.json.JsonResponse;
import com.fernando.sinch.supermarket.services.IKardex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/kardex")
public class KardexController {
    @Autowired
    IKardex iKardex;

    @GetMapping()
    @PreAuthorize("hasRole('CASHIER') or hasRole('ADMIN')")
    public JsonResponse getInvoices() {
        List<DTOKardex> listKArdex = iKardex.getKardex();

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.put("data", listKArdex);
        jsonResponse.setMessage("Success");
        return jsonResponse;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CASHIER') or hasRole('ADMIN')")
    public JsonResponse getByProduct(@PathVariable("id") Integer id) {
        List<DTOKardex> listDtoKardex = iKardex.getByProduct(id);

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.put("data", listDtoKardex);
        jsonResponse.setMessage("Success");
        return jsonResponse;
    }

    @GetMapping("/report")
    @PreAuthorize("hasRole('CASHIER') or hasRole('ADMIN')")
    public JsonResponse showReport() {
        System.out.println(iKardex.getReport());

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.put("data", iKardex.getReport());
        jsonResponse.setMessage("Success");
        return jsonResponse;
    }
}
