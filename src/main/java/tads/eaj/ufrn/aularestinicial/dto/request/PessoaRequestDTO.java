package tads.eaj.ufrn.aularestinicial.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tads.eaj.ufrn.aularestinicial.model.Endereco;
import tads.eaj.ufrn.aularestinicial.model.Pessoa;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaRequestDTO {
	String nome;
	Integer idade;
	String username;
	String password;
	Endereco endereco;

	public Pessoa build(){
		return new Pessoa()
				.setPassword(this.password)
				.setNome(this.nome)
				.setIdade(this.idade)
				.setUsername(this.username)
				.setEndereco(this.endereco);
	}
}
