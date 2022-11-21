package net.codejava.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.codejava.services.ProductService;
import net.codejava.Usuario;
import net.codejava.entity.Formulario;
import net.codejava.entity.Imc;
import net.codejava.entity.Product;
import net.codejava.services.ImcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

    @Autowired
    private ProductService service;
    @Autowired
    private ImcService imcService;

    @RequestMapping("/")
    public String viewHomePage(HttpSession session, Model model) {

        if (session.getAttribute("mySessionAttribute") != null) {

            //List<Product> listProducts = service.listAll();
            //model.addAttribute("listProducts", listProducts);

            List<Imc> imcList = imcService.listAll();
            model.addAttribute("imcList", imcList);

            return "index";
        } else {
            model.addAttribute("formulario", new Formulario());
            return "login";
        }
        //model.addAttribute("listProducts", listProducts);
    }

    @RequestMapping("/login")
    public String login(HttpSession session) {
        session.setAttribute("mySessionAttribute", "sasas");

        // model.addAttribute("listProducts", listProducts);
        return "redirect:/";
    }

    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
     //   Product product = new Product();
       // model.addAttribute("product", product);

        Imc imc = new Imc();
        model.addAttribute("imc", imc);


        return "new_product";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("imc") Imc imc) {
        //service.save(product);
        double res = imc.getPeso() / (imc.getAltura() * imc.getAltura());
        imc.setImcres(res);
        imcService.save(imc);
        /* Imc imc = new Imc();
        Imc imc1 = new Imc( 2,  "21/01/12",  87.0,  182.0,  1.0);
        imc.setId(1);
        imc.setFecha("21/01/01");
        imc.setAltura(1.80);
        imc.setPeso(87d);
        imc.setIMC(37.0);
        imcService.save(imc);
        imcService.save(imc1);
        */

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Imc imc = imcService.get(id);
        mav.addObject("imc", imc);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
       // service.delete(id);
        imcService.delete(id);
        return "redirect:/";
    }
}
