package br.zero.controlefinanceiro.model.extrato;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.zero.controlefinanceiro.model.Conta;
import br.zero.customdao.EntitySetup;

@Entity
@Table(name = "lancamento", schema = "controlefinanceiro_extrato")
@EntitySetup(findAllQuery = "select e from ExtratoLancamento e order by e.data, e.id", findByIdQuery = "select e from ExtratoLancamento e where e.id=:id", idFieldName = "id")
public class ExtratoLancamento {

	@Id
	@SequenceGenerator(name = "CONTROLEFINANCEIRO_EXTRATO.LANCAMENTO_ID_SEQ", sequenceName = "CONTROLEFINANCEIRO_EXTRATO.LANCAMENTO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CONTROLEFINANCEIRO_EXTRATO.LANCAMENTO_ID_SEQ")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "BancoID")
	private Conta banco;
		
	@ManyToOne
	@JoinColumn(name = "ArquivoID")
	private Arquivo arquivo;

	private boolean isTransfer;

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
	 * Indica se a linha é uma linha de transferência ou não.
	 * 
	 * @return
	 */
	public boolean isTransfer() {
		return isTransfer;
	}

	public void setTransfer(boolean isTransfer) {
		this.isTransfer = isTransfer;
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
