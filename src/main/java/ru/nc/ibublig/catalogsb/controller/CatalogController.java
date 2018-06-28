package ru.nc.ibublig.catalogsb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.nc.ibublig.catalogsb.dao.CategoryDAO;
import ru.nc.ibublig.catalogsb.dao.ItemDAO;
import ru.nc.ibublig.catalogsb.model.Category;
import ru.nc.ibublig.catalogsb.model.Item;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class CatalogController {

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/catalog")
    public String catalog(Map<String, Object> model) {
        Iterable<Item> items = itemDAO.list();
        Iterable<Category> categories = categoryDAO.list();

        model.put("items", items);
        model.put("categories", categories);
        return "catalog";
    }
    @GetMapping("catalog/{catalogId}")
    public String catalog(@PathVariable Long catalogId, Map<String, Object> model){
        Iterable<Item> items = itemDAO.getByIdCategory(catalogId);
        Iterable<Category> categories = categoryDAO.list();

        model.put("items", items);
        model.put("categories", categories);
        return "catalog";
    }
    @PostMapping("/catalog")
    public String find(@RequestParam String name, Map<String, Object> model){
        model.put("items", itemDAO.getByName(name));
        model.put("categories", categoryDAO.list());
        return "catalog";
    }
}
