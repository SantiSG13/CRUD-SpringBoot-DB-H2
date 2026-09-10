package com.example.demo.Controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Modelos.DAO.ClienteDAO_Interface;
import com.example.demo.Modelos.Entity.Cliente;

@Controller
@RequestMapping("/Cliente")
public class ClienteController {

    @Autowired
    private ClienteDAO_Interface clienteDAO;

    @GetMapping("/listar")
    public String ListarClientes(Model model) {
        model.addAttribute("titulo", "Listado de Clientes");
        List<Cliente> clientes = clienteDAO.findAll();
        model.addAttribute("clientes", clientes);
        return "listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Formulario de Cliente");
        return "form";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Model model) {
        Cliente cliente = null;
        if (id > 0) {
            cliente = clienteDAO.findOne(id);
        } else {
            return "redirect:/Cliente/listar";
        }
        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Editar Cliente");
        return "form";
    }

    @PostMapping("/form")
    public String guardar(Cliente cliente) {
        if (cliente.getCreateAt() == null) {
            cliente.setCreateAt(new Date());
        }
        clienteDAO.save(cliente);
        return "redirect:/Cliente/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            clienteDAO.delete(id);
        }
        return "redirect:/Cliente/listar";
    }
}
