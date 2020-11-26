package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

}
