package tads.eaj.ufrn.aularestinicial.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tads.eaj.ufrn.aularestinicial.model.Endereco;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnderecoResponseDTO {
	String rua;
	String bairro;

	public EnderecoResponseDTO(Endereco e){
		this.rua = e.getRua();
		this.bairro = e.getBairro();
	}

}
