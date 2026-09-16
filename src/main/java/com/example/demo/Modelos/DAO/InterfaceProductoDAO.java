package com.example.demo.Modelos.DAO;

import java.util.List;

import com.example.demo.Modelos.Entity.Producto;

// Esta interfaz define los metodos que se van a utilizar para la interacción con la base de datos
public interface InterfaceProductoDAO {
    public List<Producto> findAll();

    public void save(Producto producto);

    public Producto findOne(Long id);

    public void delete(Long id);
}