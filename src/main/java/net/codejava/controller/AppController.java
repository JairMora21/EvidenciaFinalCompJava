package net.codejava.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import net.codejava.entity.Formulario;
import net.codejava.entity.Imc;
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
    private ImcService imcService;

    //En este metodo manda a llamar la pagina principal el cual mostrara la vista principal de la pagia
    @RequestMapping("/")
    public String viewHomePage(HttpSession session, Model model) {

        if (session.getAttribute("mySessionAttribute") != null) {

            //muestra la lista de los registros de IMC
            List<Imc> imcList = imcService.listAll();
            //se añade el atributo imcList al modelo(index.html)
            model.addAttribute("imcList", imcList);



            return "index";
        } else {
            model.addAttribute("formulario", new Formulario());
            return "login";
        }
    }

    //Es la pestaña de login, después de dar clic te llevará a la pestaña principal
    @RequestMapping("/login")
    public String login(HttpSession session) {
        session.setAttribute("mySessionAttribute", "...");

        return "redirect:/";
    }

    //Sirve para poder añadir un nuevo registro de IMC
    @RequestMapping("/new")
    public String showNewProductPage(Model model) {

        //LLamamos a la clase IMC para tener sus atributos
        Imc imc = new Imc();
        //Añadimos el atributo imc al modelo (new_product)
        model.addAttribute("imc", imc);

        return "new_product";
    }

    //Hace la accion de guadrar el registro
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("imc") Imc imc) {

        //se hace la validacion para ver si se guarda el registro o no, en caso de que le mandara
        //a una pantalla de error
        if(imc.getAltura() > 2.5 || imc.getAltura() < 1){
            return "/errorEstatura";
        }
        else if(imc.getPeso() <= 0){
            return "/errorPeso";
        }
        else {
            //En el caso que sea correcto se hace el calculo para sacar el IMC y despues e imprime
            double res = imc.getPeso() / (imc.getAltura() * imc.getAltura());
            imc.setImcres(res);
            imcService.save(imc);

            return "redirect:/";

        }


    }

    // se edita el IMC si hubo un error al registrarlo
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Imc imc = imcService.get(id);
        mav.addObject("imc", imc);

        return mav;
    }

    //borra el imc dependiendo del id
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        imcService.delete(id);
        return "redirect:/";
    }
}
