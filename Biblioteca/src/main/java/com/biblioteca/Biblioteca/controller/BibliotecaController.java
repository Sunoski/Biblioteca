/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.Biblioteca.controller;

import com.biblioteca.Biblioteca.data.BibliotecaEntity;
import com.biblioteca.Biblioteca.data.EmprestimoEntity;
import com.biblioteca.Biblioteca.data.LivrosEntity;
import com.biblioteca.Biblioteca.data.PostagemEntity;
import com.biblioteca.Biblioteca.service.BibliotecaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author walla
 */

@RestController
@RequestMapping("/Biblioteca")
public class BibliotecaController {
    
   @Autowired
   BibliotecaService bibliotecaService;
   
   @PostMapping("/cadastrar")
   public ResponseEntity<BibliotecaEntity> addUser(@RequestBody BibliotecaEntity user) {
      var newUser = bibliotecaService.criarUsuario(user);
      return new ResponseEntity<>(newUser, HttpStatus.CREATED);
   }
   
   @PostMapping("/novo-livro")
   public ResponseEntity<LivrosEntity> addLivro(@RequestBody LivrosEntity livro) {
      var newBook = bibliotecaService.registrarLivro(livro);
      return new ResponseEntity<>(newBook, HttpStatus.CREATED);
   }
   
   @PostMapping("/nova-postagem")
   public ResponseEntity<PostagemEntity> addPostagem(@RequestBody PostagemEntity post) {
      var newPost = bibliotecaService.registrarPost(post);
      return new ResponseEntity<>(newPost, HttpStatus.CREATED);
   }
   
   @GetMapping("/user/{rg}")
   public ResponseEntity<String> searchUserByRg(@PathVariable String rg) {
       BibliotecaEntity user = bibliotecaService.searchUserByRG(rg);
       return new ResponseEntity<>(user.getRg(), HttpStatus.OK);
   }
   
   @GetMapping("/livro/{title}")
   public ResponseEntity<List> findByTitleContaining(@PathVariable String title) {
       List<LivrosEntity> livros = bibliotecaService.findByTitleContaining(title);
       return new ResponseEntity<>(livros, HttpStatus.OK);
   }
   
   @GetMapping("/livro-get/{id}")
   public ResponseEntity<LivrosEntity> BookByID(@PathVariable Integer id) {
       LivrosEntity livro = bibliotecaService.bookById(id);
       return new ResponseEntity<>(livro, HttpStatus.OK);
   }
   
   @PostMapping("/novo-emprestimo")
   public ResponseEntity<EmprestimoEntity> addEmprestimo(@RequestBody EmprestimoEntity emp) {
      var newEmp = bibliotecaService.registrarEmprestimo(emp);
      return new ResponseEntity<>(newEmp, HttpStatus.CREATED);
   }
   
   @GetMapping("/emprestimos")
    public ResponseEntity<List> allEmp() {
        List<EmprestimoEntity> emps = bibliotecaService.allEmp();
        return new ResponseEntity<>(emps, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<EmprestimoEntity> deletarEmp(@PathVariable Integer id) {
        bibliotecaService.deletarEmp(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
   
}
