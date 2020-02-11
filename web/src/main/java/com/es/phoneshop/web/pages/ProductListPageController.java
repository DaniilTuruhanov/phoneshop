package com.es.phoneshop.web.pages;

import javax.annotation.Resource;

import com.es.core.phone.PhoneDao;
import com.es.core.phone.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/productList")
@PropertySource("classpath:product-list-info.properties")
public class ProductListPageController {

    @Autowired
    private ProductService productService;

    @Value("${offset}")
    private int offset;

    @Value("${limit}")
    private int limit;

    @GetMapping
    public String showProductList(Model model) {
        model.addAttribute("phones", productService.findAll(offset, limit));
        return "productList";
    }
}
