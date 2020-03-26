package com.example.masterdegree.controllers;

import com.example.masterdegree.core.price.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PriceController {

    private final PriceService priceService;

    @PostMapping("/price")
    @ResponseBody
    public void fetchSelectedRangePrice(@RequestBody double[] price) {
        priceService.setSelectedRangePrice(price);
    }
}
