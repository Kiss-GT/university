package com.kgt.university.drinks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class DrinkController {

    @GetMapping("/drinks")
    public String getDrinks(Model model){
        Person person=new Person(30L,"John Smith");

        List<Drink>selectableDrinks= Arrays.asList(
                new Drink(1L,"Coke"),
                new Drink(2L,"Fanta"),
                new Drink(3L,"Sprite"));

        model.addAttribute("person",person);
        model.addAttribute("selectableDrinks",selectableDrinks);

        return "drinks";

    }
    @PostMapping("/drinks")
    public String postDrinks(@ModelAttribute("person")Person person){
        System.out.println("Person has been posted");
        System.out.println(person);

        return "drinks";
    }
}
