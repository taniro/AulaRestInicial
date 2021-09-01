package tads.eaj.ufrn.aularestinicial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tads.eaj.ufrn.aularestinicial.dto.request.PessoaRequestDTO;
import tads.eaj.ufrn.aularestinicial.dto.response.PessoaResponseDTO;
import tads.eaj.ufrn.aularestinicial.model.Mensagem;
import tads.eaj.ufrn.aularestinicial.model.Pessoa;
import tads.eaj.ufrn.aularestinicial.service.PessoaService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-Total-Count")
@RequestMapping("/pessoa")
public class PessoaController {

	private PessoaService service;

	@Autowired
	public void setService(PessoaService service) {
		this.service = service;
	}

	@GetMapping
	public List<Pessoa> getAllPessoa(){
		return service.getAllPessoa();
	}

	@GetMapping(path = {"/{id}"})
	public ResponseEntity<PessoaResponseDTO> getPessoaById(@PathVariable Long id){
		Optional<Pessoa> p = service.getPessoaById(id);
		if (p.isEmpty()){
			return ResponseEntity.notFound().build();
		}else {

			PessoaResponseDTO responseDTO = new PessoaResponseDTO(p.get());

			responseDTO.add(linkTo(PessoaController.class).slash(id).withSelfRel());
			responseDTO.add(linkTo(PessoaController.class).withRel("all-pessoas"));

			return ResponseEntity.ok().body(responseDTO);
		}
	}

	/*
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Mensagem> getPessoaById(@PathVariable Long id){
		Mensagem m = new Mensagem();
		Optional<Pessoa> p = service.getPessoaById(id);
		if (p.isEmpty()){
			//m.setStatus("404");
			m.setMensagem("A pessoa de id: " + id + " não foi localizada");
			return ResponseEntity.status(404).body(m);
		}else {
			//m.setStatus("200");
			m.setMensagem("Encontrado");

			List<Pessoa> pessoas = new ArrayList<>();
			pessoas.add(p.get());
			m.setDados(pessoas);
			return ResponseEntity.ok().body(m);
		}
	}
	*/

	@PostMapping
	public ResponseEntity<PessoaResponseDTO> insert(@RequestBody PessoaRequestDTO pessoadto){
		Pessoa pessoa = service.insert(pessoadto.build());
		return ResponseEntity.created(URI.create("/pessoa/"+pessoa.getId())).body(new PessoaResponseDTO(pessoa));
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Pessoa p){
		return service.getPessoaById(id)
				.map( record -> {
					if (record.getId().equals(p.getId())){
						service.update(p);
						return ResponseEntity.ok(p);
					}else{
						return ResponseEntity.notFound().build();
					}
				}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		return service.getPessoaById(id)
				.map( record -> {
					Mensagem m = new Mensagem();
					m.setMensagem("Deletado com sucesso");
					service.delete(record.getId());
					return ResponseEntity.ok(m);
				}).orElse(ResponseEntity.notFound().build());
	}
}
