/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.biblioteca.Biblioteca.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author walla
 */

@Repository
public interface BibliotecaRepository extends JpaRepository<BibliotecaEntity, Integer> {
    BibliotecaEntity findByRg(String rg);
}
