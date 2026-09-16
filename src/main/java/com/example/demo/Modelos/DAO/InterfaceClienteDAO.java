package com.example.demo.Modelos.DAO;

import java.util.List;

import com.example.demo.Modelos.Entity.Cliente;

// Esta interfaz define los metodos que se van a utilizar para la interacción con la base de datos
public interface InterfaceClienteDAO {

    public List<Cliente> findAll();

    public void save(Cliente cliente);

    public Cliente findOne(Long id);

    public void delete(Long id);

}
