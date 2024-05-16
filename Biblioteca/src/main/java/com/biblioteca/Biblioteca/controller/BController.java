/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.Biblioteca.controller;

import com.biblioteca.Biblioteca.data.BibliotecaEntity;
import com.biblioteca.Biblioteca.service.BibliotecaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author walla
 */

@Controller
public class BController {
    @Autowired
    BibliotecaService bibliotecaService;
    
    @GetMapping("/livro/{id}")
    public String viewLivro(Model model, @PathVariable Integer id) {
        model.addAttribute("livros", bibliotecaService.bookById(id));
        return "livro";
    }
    @GetMapping("/")
    public String viewHomePage(Model model, HttpServletRequest r) {
        HttpSession s = r.getSession();
        if (s.getAttribute("tipo") != null) {
            if (s.getAttribute("tipo").equals("Comum")) {
                return "inicial";
            }
            if (s.getAttribute("tipo").equals("Admin")) {
                return "PanelAdmin";
            }
        }
        return "index";
    }
    
    @GetMapping("/cadastrar")
    public String registerPage(Model model, HttpServletRequest r) {
        HttpSession s = r.getSession();
        if (s.getAttribute("tipo") != null) {
            if (s.getAttribute("tipo").equals("Comum")) {
                return "inicial";
            }
            if (s.getAttribute("tipo").equals("Admin")) {
                return "PanelAdmin";
            }
        }
        return "cadastrar";
    }
    
    @PostMapping("/inicial")
    public String login(HttpServletRequest request, BibliotecaEntity user) {
        if (user.getRg().length() <= 0) {
            return "cadastrar";
        }
        HttpSession sessao = request.getSession();
        
        if (bibliotecaService.searchUserByRG(user.getRg()) == null) {
            return "cadastrar";
        } else {
            BibliotecaEntity myUser = bibliotecaService.searchUserByRG(user.getRg());
            if (user.getPass().equals(myUser.getPass())) {
                if (sessao != null && myUser.getTipo().equals("Comum")) {
                    sessao.setAttribute("usuario", myUser.getUsername());
                    sessao.setAttribute("tipo", myUser.getTipo());
                    return "inicial";
                }
                if (sessao != null && myUser.getTipo().equals("Admin")) {
                    sessao.setAttribute("usuario", myUser.getUsername());
                    sessao.setAttribute("tipo", myUser.getTipo());
                    return "PanelAdmin";
                }
            }
        }
        return "cadastrar";
    }
    
    @GetMapping("/inicial")
    public ModelAndView acessaHomePageLogin(HttpServletRequest request) {
        HttpSession sessao = request.getSession();
        if (sessao != null && sessao.getAttribute("usuario") != null && sessao.getAttribute("tipo").equals("Comum")) {
            return new ModelAndView("inicial");
        }
        return new ModelAndView("redirect:/");
    }
    
    @GetMapping("/postagens")
    public ModelAndView acessaPostagens(HttpServletRequest r, Model model) {
        model.addAttribute("postagens", bibliotecaService.listarTodasAsPostagens());
        HttpSession s = r.getSession();
        if (s.getAttribute("tipo") != null) {
            if (s != null && s.getAttribute("tipo").equals("Comum")) {
                return new ModelAndView("postagens");
            }
        }
        return new ModelAndView("redirect:/");
    }
    
    @RequestMapping("/logoff")
    public ModelAndView fazLogoff(HttpServletRequest r) {
        HttpSession sessao = r.getSession();
        if(sessao != null && sessao.getAttribute("usuario") != null) {
            sessao.removeAttribute("usuario");
            sessao.removeAttribute("tipo");
        }
        return new ModelAndView("redirect:/");
    }
}
