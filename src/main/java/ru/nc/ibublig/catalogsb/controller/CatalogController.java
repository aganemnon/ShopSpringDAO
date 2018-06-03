package ru.nc.ibublig.catalogsb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/catalog")
    public String addItem(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String cost,
            @RequestParam String category,
            @RequestParam("file") MultipartFile file,
            Map<String, Object> model) throws IOException {
        Long categoryId = categoryDAO.getIdByName(category);
        Item item = new Item( name, description, (long) (Double.parseDouble(cost)*100), null, categoryId);
        if (!file.isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            item.setImage(resultFileName);
        }
        itemDAO.addItem(item);

        Iterable<Item> items = itemDAO.list();
        Iterable<Category> categories = categoryDAO.list();

        model.put("items", items);
        model.put("categories", categories);

        return "catalog";
    }
}
