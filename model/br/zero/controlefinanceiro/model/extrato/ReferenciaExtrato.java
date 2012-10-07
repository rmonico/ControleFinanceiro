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
@Table(name = "extrato_referenciaextrato")
@EntitySetup(findAllQuery = "select re from ReferenciaExtrato re a order by re.id, re.banco.id, re.conta.id, re.referencia", findByIdQuery = "select re from ReferenciaExtrato re where re.id=:id", idFieldName = "id")
public class ReferenciaExtrato {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "BancoID")
	private Conta banco;

	@ManyToOne
	@JoinColumn(name = "ContaID")
	private Conta conta;

	private Integer n;

	private String referencia;

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

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	/**
	 * Indica a ordem em que esta referência deverá ser testada.
	 * 
	 * @return
	 */
	public Integer getN() {
		return n;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	@Override
	public String toString() {
		String nomeConta = conta == null ? "[null]" : (conta.getNome() == null ? "[null]" : conta.getNome());
		String nomeBanco = banco == null ? "[null]" : (banco.getNome() == null ? "[null]" : banco.getNome());
		String nStr = n == null ? "[null]" : n.toString();
		String referenciaStr = referencia == null ? "[null]" : referencia;
		
		return nomeConta + "(" + nomeBanco + "); " + nStr + "; " + referenciaStr;
	}

}
