package ru.nc.ibublig.catalogsb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class CatalogController {

    @GetMapping("/catalog")
    public String catalog() {
        return "catalog";
    }

    @PostMapping("/catalog")
    public String addItem(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String cost,
            @RequestParam String category,
            @RequestParam("file") MultipartFile file,
            Map<String, Object> model) {

        System.out.println(name);
        System.out.println(description);
        System.out.println(cost);
        System.out.println(category);
        return "catalog";
    }
}
