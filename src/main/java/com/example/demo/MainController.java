package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.Fatura;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.FaturaRepository;

@Controller 
@RequestMapping(path="/demo")
public class MainController {

	@Autowired 
	private ClienteRepository cliRepository;
	
	@Autowired 
	private FaturaRepository fatuRepository;

	@PostMapping(path="/cliente/add") //adiciona cliente
	public @ResponseBody String addNewCliente (@RequestParam Integer cpf , 
											   @RequestParam String nome) {
											   
		Cliente cli = new Cliente(); 
		cli.setCpf(cpf);
		cli.setNome(nome);
		cliRepository.save(cli);
		return "Gravado com sucesso!";
	}

	@GetMapping(path="/cliente/all")// busca todos clientes
	public @ResponseBody Iterable<Cliente> getAllCliente() {
		return cliRepository.findAll();
	}

		
	@PutMapping("/cliente/update") // procura e altera o cliente
	public @ResponseBody String updateOneCliente(@RequestParam Integer cpf , 
			                                    @RequestParam String nome) {
		
		Optional<Cliente> foundCliente = cliRepository.findById(cpf);
		if (!foundCliente.isPresent())
			throw new IllegalArgumentException();
		
		Cliente cli = foundCliente.get(); 
		cli.setCpf(cpf);
		cli.setNome(nome);
		cliRepository.save(cli);
		return "Cliente atualizado!";
	}

	@DeleteMapping(path="/cliente/delete")
	public @ResponseBody String deleteCliente(@RequestParam Integer cpf) {
		cliRepository.deleteById(cpf);
		return "Cliente Removido!";
	}
	
//////////////////////FATURA///////////////////////////////////////
	
	@GetMapping(path="/fatura/all")
	public @ResponseBody Iterable<Fatura> getAllFatura() {
		return fatuRepository.findAll();
	}
	
	@DeleteMapping(path="/fatura/delete")
	public @ResponseBody String deleteFatura(@RequestParam Integer id) {
		fatuRepository.deleteById(id);
		return "Fatura removida!";
	}
	
	@PutMapping(path="/fatura/update")
	public @ResponseBody String updateFatura( @RequestParam Integer id, 
											  @RequestParam Integer cliente_cpf, 
			                                  @RequestParam int mes, 
			                                  @RequestParam double leitura_Atual 
			                                 /* ,@RequestParam double leitura_Anterior
			                                 /* ,@RequestParam double consumo*/) {
		
		Optional<Fatura> foundFatura = fatuRepository.findById(id);
		Optional<Cliente> foundCliente = cliRepository.findById(cliente_cpf);
		
		if (!foundFatura.isPresent()) 
			throw new IllegalArgumentException();
		Fatura fat = foundFatura.get();
		Cliente cliente = foundCliente.get();
		
		fat.setId(id);
		fat.setCliente(cliente);
		fat.setMes(mes);
		fat.setLeitura_Atual(leitura_Atual);
//		fat.setLeitura_Anterior(leitura_Anterior);
		fat.setConsumo(leitura_Atual - fat.getLeitura_Anterior());
		fatuRepository.save(fat);
		
		return "Fatura atualizada!";
	}
	
	@PostMapping(path="/fatura/add")
	public @ResponseBody String addNewFatura (@RequestParam Integer id, 
			                                  @RequestParam Integer cliente_cpf, 
                                              @RequestParam int mes, 
                                              @RequestParam double leitura_Anterior,
                                              @RequestParam double leitura_Atual
                                              ) {
		
		Optional<Fatura> foundFatura = fatuRepository.findById(id);
		Optional<Cliente> foundCliente = cliRepository.findById(cliente_cpf); 
		
		if (!foundFatura.isPresent())
//			throw new IllegalArgumentException();
		
		if (!foundCliente.isPresent())
			throw new IllegalArgumentException();
		
		Cliente cliente = foundCliente.get();
		
		Fatura fatu = new Fatura();
		fatu.setId(id);
		fatu.setCliente(cliente); //pega o valor do cliente cpf
		fatu.setMes(mes); 
		fatu.setLeitura_Anterior(leitura_Anterior);
		fatu.setLeitura_Atual(leitura_Atual);
		fatu.setConsumo(leitura_Atual - leitura_Anterior);
		
		fatuRepository.save(fatu);
		return "Salvo com sucesso!";
	}
	
}
