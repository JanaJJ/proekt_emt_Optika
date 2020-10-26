package com.example.demo.controller;

import com.example.demo.model.Brand;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.BrandService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;

    //vrati home
    @RequestMapping("/home")
    public String viewHomePage(){
        return "Home";
    }

    //vrati about us
    @RequestMapping("/us")
    public String viewAboutUs(){
        return "AboutUs";
    }

    //vrati gi site dioptriski
    @RequestMapping("/dioptric")
    public String viewAllDioptric(Model model){
        List<Product> products=productService.findAllProducts();
        model.addAttribute("products",products);
        return "Dioptricglasses";
    }

    //vrati gi site za sonce
    @RequestMapping("/sunglasses")
    public String viewAllSunglasses(Model model){
        List<Product> products=productService.findAllProducts();
        model.addAttribute("products",products);
        return "Sunglasses";
    }

    //vrati gi site za deca
    @RequestMapping("/children")
    public String viewAllChildren(Model model){
        List<Product> products=productService.findAllProducts();
        model.addAttribute("products",products);
        return "ChildrenGlasses";
    }

    //vrati gi site leki
    @RequestMapping("/lenses")
    public String viewAllLenses(Model model){
        List<Product> products=productService.findAllProducts();
        model.addAttribute("products",products);
        return "ContactLenses";
    }

    //kreiraj nov produkt
    @RequestMapping("/new")
    public String showNewProduct(Model model){
        Product product=new Product();
        model.addAttribute("product",product);
        List<Category> category =  this.categoryService.findAll();
        model.addAttribute("category",category);
        List<Brand> brand = this.brandService.findAll();
        model.addAttribute("brand",brand);
        return "new_p";
    }
    //zacuvaj za sonce
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveForSunglasses(@ModelAttribute("product") Product product){
        productService.saveProduct(product);
        return "redirect:/dioptric";
    }

    @RequestMapping(value = "/saveImage",method = RequestMethod.POST)
    public String saveWithImage(@RequestParam("file") MultipartFile file,@RequestParam("id")Long id,@RequestParam("pname")String name,@RequestParam("desc")String description,
                                @RequestParam("price")Long price){
        productService.saveProductToDB(file,id, name, description,price);
        return "redirect:/dioptric";
    }


    @RequestMapping("/edit/C/{id}")
    public ModelAndView editNewProductforChildren(Model model, @PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView("edit_product");
        Product product=productService.findProductbyId(id);
        mav.addObject("product",product);
        List<Category> category =  this.categoryService.findAll();
        model.addAttribute("category",category);
        List<Brand> brand = this.brandService.findAll();
        model.addAttribute("brand",brand);
        return mav;
    }
    @RequestMapping("/edit/L/{id}")
    public ModelAndView editNewProductForLenses(Model model, @PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView("edit_product");
        Product product=productService.findProductbyId(id);
        mav.addObject("product",product);
        List<Category> category =  this.categoryService.findAll();
        model.addAttribute("category",category);
        List<Brand> brand = this.brandService.findAll();
        model.addAttribute("brand",brand);
        return mav;
    }

    @RequestMapping("/edit/D/{id}")
    public ModelAndView editNewProductForDioptric(Model model, @PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView("edit_product");
        Product product=productService.findProductbyId(id);
        mav.addObject("product",product);
        List<Category> category =  this.categoryService.findAll();
        model.addAttribute("category",category);
        List<Brand> brand = this.brandService.findAll();
        model.addAttribute("brand",brand);
        return mav;
    }
    @RequestMapping("/edit/S/{id}")
    public ModelAndView editNewProductForSunglasses(Model model, @PathVariable(name="id")Long id){
        ModelAndView mav=new ModelAndView("edit_product");
        Product product=productService.findProductbyId(id);
        mav.addObject("product",product);
        List<Category> category =  this.categoryService.findAll();
        model.addAttribute("category",category);
        List<Brand> brand = this.brandService.findAll();
        model.addAttribute("brand",brand);
        return mav;
    }

    @RequestMapping("/delete/C/{id}")
    public String deleteProductforChildren(@PathVariable(name = "id")Long id)
    {
        productService.deleteProduct(id);
        return "redirect:/children";
    }
    @RequestMapping("/delete/L/{id}")
    public String deleteProductforLenses(@PathVariable(name = "id")Long id)
    {
        productService.deleteProduct(id);
        return "redirect:/lenses";
    }
    @RequestMapping("/delete/D/{id}")
    public String deleteProductforDioptric(@PathVariable(name = "id")Long id)
    {
        productService.deleteProduct(id);
        return "redirect:/dioptric";
    }
    @RequestMapping("/delete/S/{id}")
    public String deleteProductforSunglasses(@PathVariable(name = "id")Long id)
    {
        productService.deleteProduct(id);
        return "redirect:/sunglasses";
    }


    //////////////////////////////////////////////////////////////////////////
 /*
 @RequestMapping("/brand/{id}")
    public String brand(Model model,@PathVariable String id){

        List<Product> products = this.productService.findAllProducts();
        List<Product> productList = new ArrayList<>();
        for (Product p : products){
            String name = p.getBrand().getName();
            if (name.equals(id) ){
                productList.add(p);
            }
        }
        model.addAttribute("products",productList);
        return "index";
    }

 @RequestMapping("/{brandId}")
    public String showbyBrand(Model model ,@PathVariable Long brandId){
        List<Product> products=productService.findAllByBrand(brandId);
        model.addAttribute("products",products);
        return "index";
    }
    @RequestMapping("/{categoryId}")
    public String showbyCategory(Model model ,@PathVariable Long categoryId){
        Product products=productService.findProductbyId(categoryId);
        model.addAttribute("products",products);
        return "index";
    }


    @RequestMapping("/{id}")
    public String findbyId(Model model ,@PathVariable Long id){
       Product products=productService.findProductbyId(id);
       model.addAttribute("products",products);
        return "index";
    }

    */

}
