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

            List<Imc> imcList = imcService.listAll();
            model.addAttribute("imcList", imcList);

            return "index";
        } else {
            model.addAttribute("formulario", new Formulario());
            return "login";
        }
    }

    @RequestMapping("/login")
    public String login(HttpSession session) {
        session.setAttribute("mySessionAttribute", "sasas");

        return "redirect:/";
    }

    @RequestMapping("/new")
    public String showNewProductPage(Model model) {


        Imc imc = new Imc();
        model.addAttribute("imc", imc);


        return "new_product";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("imc") Imc imc) {
        if(imc.getAltura() > 2.5 || imc.getAltura() < 1){
            return "/errorEstatura";
        }
        else if(imc.getPeso() <= 0){
            return "/errorPeso";
        }

        else {

            double res = imc.getPeso() / (imc.getAltura() * imc.getAltura());
            imc.setImcres(res);
            imcService.save(imc);

            return "redirect:/";

        }


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
        imcService.delete(id);
        return "redirect:/";
    }
}
