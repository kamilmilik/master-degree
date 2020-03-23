package com.example.masterdegree.controllers;

import com.example.masterdegree.services.price.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PriceController {

    private PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping("/price")
    @ResponseBody
    public void fetchSelectedRangePrice(@RequestBody double[] price) {
        priceService.setSelectedRangePrice(price);
    }
}
