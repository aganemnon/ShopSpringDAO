package ru.nc.ibublig.catalogsb.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.nc.ibublig.catalogsb.dao.CategoryDAO;
import ru.nc.ibublig.catalogsb.dao.ItemDAO;
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
        model.put("categories", categoryDAO.list());
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
        Double cost = (double) item.getCost()/100;
        model.put("cost", cost.toString().replace(",","."));
        return "admin/edititem";
    }
    @GetMapping("remove/{itemId}")
    public String removeItem(@PathVariable Long itemId){
        itemDAO.removeById(itemId);
        return "redirect:/cataloglist";
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
    @PostMapping("editcategory/remove/{categoryId}")
    public String removeCategory(@PathVariable Long categoryId,
                                 @RequestParam String newCategory,
                                 Map<String, Object> model){
        if (newCategory.equals("-1")){
            model.put("error_r", "Выберите категорию");
            model.put("items", itemDAO.getByIdCategory(categoryId));
            model.put("category", categoryDAO.getCategoryById(categoryId));
            model.put("categories", categoryDAO.list());
            return "admin/editcategory";
        } else {
            itemDAO.setNewCategoryById(categoryId, newCategory);
            categoryDAO.removeById(categoryId);
        }
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
        if (cost.contains(",")){
            cost = cost.replace(",",".");
        }
        if (name.equals("") || description.equals("") || cost.equals("") || category.equals("-1")){
            model.put("error","Нужно заполнить все поля");
            model.put("items", itemDAO.list());
            model.put("categories", categoryDAO.list());
            return "admin/cataloglist";
        } else {
            if (validateDouble(cost)){
                Long categoryId = categoryDAO.getIdByName(category);
                Item item = new Item(name, description, (long) (Double.parseDouble(cost)*100), null, categoryId);
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
            } else {
                model.put("error","Цена должна содержать только положительные цифры");
                model.put("items", itemDAO.list());
                model.put("categories", categoryDAO.list());
                return "admin/cataloglist";
            }

        }
    }

    private boolean validateDouble(String cost){
        try {
            double d = Double.parseDouble(cost)*100;
            if (d<0.1d){
                return false;
            }
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
