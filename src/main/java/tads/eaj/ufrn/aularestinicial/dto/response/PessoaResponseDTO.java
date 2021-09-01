package tads.eaj.ufrn.aularestinicial.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import tads.eaj.ufrn.aularestinicial.model.Pessoa;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponseDTO extends RepresentationModel<PessoaResponseDTO> {
	String nome;
	Integer idade;
	String username;
	EnderecoResponseDTO endereco;

	public PessoaResponseDTO(Pessoa p){
		this.nome = p.getNome();
		this.idade = p.getIdade();
		this.username = p.getUsername();
		this.endereco = new EnderecoResponseDTO(p.getEndereco());
	}
}