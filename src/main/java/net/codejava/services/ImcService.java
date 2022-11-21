/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava.services;

import net.codejava.entity.Imc;
import net.codejava.repositories.ImcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author jajimenez
 */
@Service
public class ImcService {

    //Se crean todos los servicios que puede realizar el programa
    @Autowired
    private ImcRepository imcRepository;

    //guarda los registros de IMC
    public void save(Imc imc) {
        imcRepository.save(imc);
    }

    //muestra toda la lista de los registros
    public List<Imc> listAll() {
        return imcRepository.findAll();
    }

    //obtiene el registro dependienddo el id
    public Imc get(long id) {
        return imcRepository.findById(id).get();
    }

    //borra el registro dependiendo el id
    public void delete(long id) {
        imcRepository.deleteById(id);
    }



}
