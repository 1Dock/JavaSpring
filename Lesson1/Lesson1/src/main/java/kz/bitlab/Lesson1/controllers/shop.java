package kz.bitlab.Lesson1.controllers;

import kz.bitlab.Lesson1.db.DBManager;
import kz.bitlab.Lesson1.db.Items;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class shop {
    @GetMapping
    public String openCatalog(Model model) {
        ArrayList<Items> items = DBManager.getItems();
        model.addAttribute("items", items);

        return "catalog";
    }

    @GetMapping(value = "additem")
    public String openAddItem() {
        return "additem";
    }

    @GetMapping(value = "details/{id}")
    public String openDetailsItem(Model model, @PathVariable(name = "id") Long id) {
        Items item = DBManager.getItem(id);
        model.addAttribute("item", item);

        return "details";
    }

    @PostMapping(value = "additem")
    public String addItem(@RequestParam(name = "name", defaultValue = "No name") String name,
                          @RequestParam(name = "description", defaultValue = "No description") String description,
                          @RequestParam(name = "price", defaultValue = "0") double price) {

        Items item = new Items();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);

        String redirect = "/?error";

        if (DBManager.addItem(item) != null) {
            redirect = "/?success";
        }

        return "redirect:" + redirect;
    }
}
