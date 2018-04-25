package com.buzilov.lab4db.controller;

import com.buzilov.lab4db.model.Impresario;
import com.buzilov.lab4db.service.impresario.ImpresarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/impresario")
public class ImpresarioController {
    @Autowired
    ImpresarioServiceImpl service;

    @RequestMapping("/showAll")
    public List<Impresario> getAll() throws SQLException  {
        return service.getAll();
    }

    @RequestMapping("/delete")
    public void delete(int id) throws SQLException {
        System.out.println("kek");
        service.delete(id);
    }

    @RequestMapping("/create")
    public Impresario create(String name) throws SQLException {
        Impresario Impresario = new Impresario(name);
        System.out.println("create impresario " + Impresario);
        return service.insert(Impresario);
    }

    @RequestMapping("/update")
    public Impresario update(@RequestParam("id") Integer id, @RequestBody Impresario Impresario) throws SQLException {
        Impresario.setId(id);
        return service.update(Impresario);
    }
}
