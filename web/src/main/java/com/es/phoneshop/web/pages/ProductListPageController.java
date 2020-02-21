package com.es.phoneshop.web.pages;

import com.es.core.phone.Phone;
import com.es.core.phone.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/productList")
public class ProductListPageController {

    @Resource
    private PhoneService phoneService;

    @GetMapping
    public String showProductList(Model model,
                                  @RequestParam(required = false, defaultValue = "") String query,
                                  @RequestParam(required = false, defaultValue = "") String order,
                                  @RequestParam(required = false, defaultValue = "") String sort,
                                  @RequestParam(required = false, defaultValue = "0") String page) {
        page = page.equals("") ? "0" : page;
        List<Phone> phones = phoneService.findAll(Integer.parseInt(page), query, order, sort);
        int countPage = phoneService.countPage(query);
        model.addAttribute("countPage", countPage);
        model.addAttribute("phones", phones);
        return "productList";
    }
}
