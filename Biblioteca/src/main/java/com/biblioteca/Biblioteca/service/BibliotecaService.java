/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.Biblioteca.service;

import com.biblioteca.Biblioteca.data.BibliotecaEntity;
import com.biblioteca.Biblioteca.data.BibliotecaRepository;
import com.biblioteca.Biblioteca.data.EmprestimoEntity;
import com.biblioteca.Biblioteca.data.EmprestimoRepository;
import com.biblioteca.Biblioteca.data.LivrosEntity;
import com.biblioteca.Biblioteca.data.LivrosRepository;
import com.biblioteca.Biblioteca.data.PostagemEntity;
import com.biblioteca.Biblioteca.data.PostagemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author walla
 */

@Service
public class BibliotecaService {
    
    @Autowired
    BibliotecaRepository bibliotecaRepository;
    
    @Autowired
    LivrosRepository livrosRepository;
    
    @Autowired
    PostagemRepository postagemRepository;
    
    @Autowired
    EmprestimoRepository emprestimoRepository;
    
    public BibliotecaEntity criarUsuario(BibliotecaEntity user) {
        user.setId(null);
        user.setTipo("Comum");
        bibliotecaRepository.save(user);
        return user;

    }
    
    public BibliotecaEntity searchUserByRG(String RG) {
        return bibliotecaRepository.findByRg(RG);
    }
    
    public BibliotecaEntity atualizarFuncionario(Integer userId, BibliotecaEntity request) {
        BibliotecaEntity user = getUserId(userId);
        user.setUsername(request.getUsername());
        user.setPass(request.getPass());
        user.setTipo("Comum");
        user.setRg(request.getRg());
        user.setTelefone(request.getTelefone());
        return user;
    }
    
    public BibliotecaEntity getUserId(Integer userId) {
        return bibliotecaRepository.findById(userId).orElse(null);
    }
    
    public List<LivrosEntity> findByTitleContaining(String title) {
        return livrosRepository.findByTitleContaining(title);
    }
    
    public LivrosEntity registrarLivro(LivrosEntity livro) {
        livro.setId(null);
        livrosRepository.save(livro);
        return livro;
    }
    public PostagemEntity registrarPost(PostagemEntity postagem) {
        postagem.setId(null);
        postagemRepository.save(postagem);
        return postagem;
    }
    
    public List<PostagemEntity> listarTodasAsPostagens() {
        return postagemRepository.findAll();
    }
    
    public LivrosEntity bookById(Integer livroId) {
        return livrosRepository.findById(livroId).orElse(null);
    }
    
    public EmprestimoEntity registrarEmprestimo(EmprestimoEntity emp) {
        emp.setId(null);
        emprestimoRepository.save(emp);
        return emp;
    }
    
    public List<EmprestimoEntity> allEmp() {
        return emprestimoRepository.findAll();
    }
    
    public EmprestimoEntity getIdEmp(Integer id) {
        return emprestimoRepository.findById(id).orElse(null);
    }
    
    public void deletarEmp(Integer id) {
        emprestimoRepository.deleteById(getIdEmp(id).getId());
    }
}
