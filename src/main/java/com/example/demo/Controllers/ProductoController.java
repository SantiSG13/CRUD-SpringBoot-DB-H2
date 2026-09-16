package com.example.demo.Controllers;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Modelos.DAO.InterfaceProductoDAO;
import com.example.demo.Modelos.Entity.Cliente;
import com.example.demo.Modelos.Entity.Producto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Producto")
public class ProductoController {

    @Autowired
    private InterfaceProductoDAO productoDAO;

    @GetMapping("/listar")
    public String ListarProductos(Model model) {
        model.addAttribute("titulo", "Listado de Productos");
        List<Producto> productos = productoDAO.findAll();
        model.addAttribute("productos", productos);
        return "listarProductos";
    }

    @GetMapping("/formulario")
    public String crear(Model model) {
        Producto producto = new Producto();
        model.addAttribute("producto", producto);
        model.addAttribute("titulo", "Formulario de Producto");
        return "formularioProducto";
    }

    @GetMapping("/formulario/{id}")
    public String editar(@PathVariable(value = "id") Long id, Model model) {
        Producto producto = null;
        if (id > 0) {
            producto = productoDAO.findOne(id);
        } else {
            return "redirect:/Producto/listar";
        }
        model.addAttribute("producto", producto);
        model.addAttribute("titulo", "Editar Producto");
        return "formularioProducto";
    }

    @PostMapping("/formulario")
    public String guardar(Producto producto) {
        productoDAO.save(producto);
        return "redirect:/Producto/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            productoDAO.delete(id);
        }
        return "redirect:/Producto/listar";
    }

}
