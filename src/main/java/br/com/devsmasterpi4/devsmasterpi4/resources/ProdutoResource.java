/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devsmasterpi4.devsmasterpi4.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nails
 */

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
    
    @RequestMapping(method = RequestMethod.GET)
    public String listar(){
        return "teste";
    }
    
    
    
}
