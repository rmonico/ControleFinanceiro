package br.zero.controlefinanceiro.model.extrato;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.zero.controlefinanceiro.model.Conta;
import br.zero.customdao.EntitySetup;

@Entity
@Table(name = "extrato_lancamento")
@EntitySetup(findAllQuery = "select e from ExtratoLancamento e order by e.data, e.id", findByIdQuery = "select e from ExtratoLancamento e where e.id=:id", idFieldName = "id")
public class ExtratoLancamento {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "BancoID")
	private Conta banco;
		
	@ManyToOne
	@JoinColumn(name = "ArquivoID")
	private Arquivo arquivo;

	private String original;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Conta getBanco() {
		return banco;
	}

	public void setBanco(Conta banco) {
		this.banco = banco;
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	/**
	 * Linha original do extrato
	 * 
	 * @return
	 */
	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

}
