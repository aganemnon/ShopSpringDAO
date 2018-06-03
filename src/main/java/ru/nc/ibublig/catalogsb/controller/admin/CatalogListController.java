package ru.nc.ibublig.catalogsb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogListController {

    @GetMapping("/cataloglist")
    public String catalogList(){
        return "admin/cataloglist";
    }
}
