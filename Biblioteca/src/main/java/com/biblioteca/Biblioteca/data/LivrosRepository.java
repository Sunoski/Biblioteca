/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.biblioteca.Biblioteca.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author walla
 */

@Repository
public interface LivrosRepository extends JpaRepository<LivrosEntity, Integer>{
    List<LivrosEntity> findByTitleContaining(String title);
}
