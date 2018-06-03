package ru.nc.ibublig.catalogsb.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nc.ibublig.catalogsb.dao.CategoryDAO;
import ru.nc.ibublig.catalogsb.dao.ItemDAO;

import java.util.Map;

@Controller
@RequestMapping("/cataloglist")
public class CatalogListController {

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String catalogList(Map<String,Object> model){
        model.put("items", itemDAO.list());
        return "admin/cataloglist";
    }

    @GetMapping("editcategory/{categoryId}")
    public String editCategory(@PathVariable Long categoryId, Map<String,Object> model){
        return "admin/editcategory";
    }
    @GetMapping("edititem/{itemId}")
    public String editItem(@PathVariable Long itemId, Map<String,Object> model){
        return "admin/edititem";
    }
}
