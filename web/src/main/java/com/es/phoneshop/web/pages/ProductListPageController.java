package com.es.phoneshop.web.pages;

import com.es.core.phone.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/productList")
@PropertySource("classpath:application.properties")
public class ProductListPageController {

    @Value("${limit}")
    public int limit;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String showProductList(Model model) {
        model.addAttribute("phones", productService.findAll(0, limit));
        return "productList";
    }
}
