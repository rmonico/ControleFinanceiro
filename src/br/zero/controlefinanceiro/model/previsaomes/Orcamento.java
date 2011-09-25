package br.zero.controlefinanceiro.model.previsaomes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.zero.customdao.EntitySetup;

@Entity
@Table(schema = "controlefinanceiro_previsaomes")
@EntitySetup(findAllQuery = "select o from Orcamento o order by o.nome", findByIdQuery = "select o from Orcamento o where o.id=:id", idFieldName = "id")
public class Orcamento {
	
	@Id
	@SequenceGenerator(name = "CONTROLEFINANCEIRO_PREVISAOMES.ORCAMENTO_ID_SEQ", sequenceName = "CONTROLEFINANCEIRO_PREVISAOMES.ORCAMENTO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTROLEFINANCEIRO_PREVISAOMES.ORCAMENTO_ID_SEQ")
	private Integer id;
	
	private String nome;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
