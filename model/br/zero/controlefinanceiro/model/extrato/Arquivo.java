package br.zero.controlefinanceiro.model.extrato;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.zero.customdao.EntitySetup;

@Entity
@Table(name = "arquivo", schema = "controlefinanceiro_extrato")
@EntitySetup(findAllQuery = "select e from Arquivo a order by a.id", findByIdQuery = "select a from Arquivo a where e.id=:id", idFieldName = "id")
public class Arquivo {

	@Id
//	@SequenceGenerator(name = "CONTROLEFINANCEIRO_EXTRATO.ARQUIVO_ID_SEQ", sequenceName = "CONTROLEFINANCEIRO_EXTRATO.ARQUIVO_ID_SEQ", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CONTROLEFINANCEIRO_EXTRATO.ARQUIVO_ID_SEQ")
	private Integer id;

	private String conteudo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// TODO Testar se isso vai funcionar com StringBuilder, fazer importação com
	// string vai fazer mta sujeira na memória
	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

}
