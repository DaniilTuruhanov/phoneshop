package com.es.phoneshop.web.controllers;

import com.es.core.data.PlpData;
import com.es.core.facades.FindPlpFacade;
import com.es.core.forms.FindAndSortForm;
import com.es.core.models.Phone;
import com.es.core.services.impl.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/productList")
public class ProductListPageController {

    @Resource
    private FindPlpFacade plpFacade;

    @GetMapping
    @ModelAttribute
    public String showProductList(Model model, @ModelAttribute FindAndSortForm findAndSortForm) {
        List<PlpData> phones = plpFacade.findAll(findAndSortForm);
        int countPage = plpFacade.countPage(findAndSortForm);
        model.addAttribute("countPage", countPage);
        model.addAttribute("phones", phones);
        return "productList";
    }
}
