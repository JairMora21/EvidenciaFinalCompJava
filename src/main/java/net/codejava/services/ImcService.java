/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava.services;

import net.codejava.entity.Imc;
import net.codejava.entity.Product;
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

    @Autowired
    private ImcRepository imcRepository;

    public void save(Imc imc) {
        imcRepository.save(imc);
    }

    public List<Imc> listAll() {
        return imcRepository.findAll();
    }

    public Imc get(long id) {
        return imcRepository.findById(id).get();
    }

    public void delete(long id) {
        imcRepository.deleteById(id);
    }



}
