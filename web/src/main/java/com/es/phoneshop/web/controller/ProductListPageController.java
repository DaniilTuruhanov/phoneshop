package com.es.phoneshop.web.controller;

import com.es.core.data.PlpData;
import com.es.core.facade.FindPlpFacade;
import com.es.core.form.FindAndSortForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ProductListPageController {

    @Resource
    private FindPlpFacade plpFacade;

    @GetMapping
    public String showProductList(Model model, @ModelAttribute FindAndSortForm findAndSortForm) {
        PlpData phones = plpFacade.findAll(findAndSortForm);
        int countPage = plpFacade.countPage(findAndSortForm);
        model.addAttribute("countPage", countPage);
        model.addAttribute("phones", phones.getPhoneList());
        return "productList";
    }
}
