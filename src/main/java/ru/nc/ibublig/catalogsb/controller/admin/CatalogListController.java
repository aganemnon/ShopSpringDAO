package ru.nc.ibublig.catalogsb.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        model.put("categories", categoryDAO.list());
        return "admin/cataloglist";
    }
    @GetMapping("addcategory")
    public String addCategory(){
        return "admin/addcategory";
    }
    @PostMapping("addcategory")
    public String addCategory(@RequestParam String name){
        categoryDAO.addCategory(name);
        return "redirect:/cataloglist";
    }
    @GetMapping("{categoryId}")
    public String catalogList(@PathVariable Long categoryId, Map<String,Object> model){
        model.put("items", itemDAO.getByIdCategory(categoryId));
        model.put("categories", categoryDAO.list());
        return "admin/cataloglist";
    }

    @GetMapping("editcategory/{categoryId}")
    public String editCategory(@PathVariable Long categoryId, Map<String,Object> model){
        model.put("items", itemDAO.getByIdCategory(categoryId));
        model.put("category", categoryDAO.getCategoryById(categoryId));
        return "admin/editcategory";
    }
    @PostMapping("editcategory/{categoryId}")
    public String editCategory(@PathVariable Long categoryId,
                               @RequestParam String name){
        categoryDAO.setNameById(categoryId, name);
        return "redirect:/cataloglist";
    }
    @GetMapping("edititem/{itemId}")
    public String editItem(@PathVariable Long itemId, Map<String,Object> model){
        Item item = itemDAO.getById(itemId);
        model.put("selectCategory", categoryDAO.getCategoryById(item.getCategoryId()));
        model.put("categories", categoryDAO.list());
        model.put("item", item);
        return "admin/edititem";
    }
    @PostMapping("edititem/{itemId}")
    public String editItem(@PathVariable Long itemId,
                           @RequestParam String name,
                           @RequestParam String description,
                           @RequestParam String cost,
                           @RequestParam String category,
                           @RequestParam("file") MultipartFile file,
                           Map<String, Object> model) throws IOException {
        Long categoryId = categoryDAO.getIdByName(category);
        Item item = new Item( name, description, (long) (Double.parseDouble(cost)*100), null, categoryId);
        item.setId(itemId);
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
        itemDAO.saveItem(item);
        return "redirect:/cataloglist";
    }
    @PostMapping
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

        return "redirect:/cataloglist";
    }
}
