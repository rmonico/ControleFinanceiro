package br.zero.controlefinanceiro.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.zero.controlefinanceiro.model.extrato.ExtratoLancamento;
import br.zero.controlefinanceiro.model.modelo.LancamentoModelo;
import br.zero.customdao.EntitySetup;

@Entity
@Table(name = "lancamento", schema = "controlefinanceiro")
@EntitySetup(findAllQuery = "select l from Lancamento l order by l.data, l.n", findByIdQuery = "select l from Lancamento l where l.id=:id", idFieldName = "id")
public class Lancamento implements Comparable<Lancamento> {

	@Id
	@SequenceGenerator(name = "CONTROLEFINANCEIRO.LANCAMENTO_ID_SEQ", sequenceName = "CONTROLEFINANCEIRO.LANCAMENTO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CONTROLEFINANCEIRO.LANCAMENTO_ID_SEQ")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "LancamentoModeloID")
	private LancamentoModelo lancamentoModelo;

	@Temporal(TemporalType.DATE)
	private Calendar data;

	private Integer n;

	@ManyToOne
	@JoinColumn(name = "ExtratoID")
	private ExtratoLancamento extrato;

	@ManyToOne
	@JoinColumn(name = "ContaOrigemID")
	private Conta contaOrigem;

	@ManyToOne
	@JoinColumn(name = "ContaDestinoID")
	private Conta contaDestino;

	private Double valor;

	private String observacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LancamentoModelo getLancamentoModelo() {
		return lancamentoModelo;
	}

	public void setLancamentoModelo(LancamentoModelo lancamentoModelo) {
		this.lancamentoModelo = lancamentoModelo;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Integer getN() {
		return n;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	public ExtratoLancamento getExtrato() {
		return extrato;
	}

	public void setExtrato(ExtratoLancamento extrato) {
		this.extrato = extrato;
	}

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");

		String data = (this.data != null) ? (sdf.format(this.data.getTime())) : "[null]";
		String n = ((Integer) this.n).toString();
		String contaOrigemNome = (contaOrigem != null) ? contaOrigem.getNome() : "[null]";
		String contaDestinoNome = (contaDestino != null) ? contaDestino.getNome() : "[null]";
		String valor = (this.valor != null) ? this.valor.toString() : "[null]";
		String observacao = (this.observacao != null) ? this.observacao : "[null]";

		return data + ", " + n + ", " + contaOrigemNome + " -> " + contaDestinoNome + ", " + valor + ", " + observacao;
	}

	@Override
	public boolean equals(Object obj) {

		if (id == null) {
			return false;
		}

		return id.equals(obj);
	}

	@Override
	public int compareTo(Lancamento otherInstance) {
		if (getData() == null) {
			return +1;
		} else if (otherInstance.getData() == null) {
			return -1;
		}

		int dataComparision = getData().compareTo(otherInstance.getData());

		if (dataComparision != 0) {
			return dataComparision;
		}

		if ((getN() != null) && (otherInstance.getN() != null)) {
			int nComparision = getN().compareTo(otherInstance.getN());

			if (nComparision != 0) {
				return nComparision;
			}
		}

		if (getId() == null) {
			return +1;
		} else if (otherInstance.getId() == null) {
			return -1;
		}

		return getId().compareTo(otherInstance.getId());
	}
}
