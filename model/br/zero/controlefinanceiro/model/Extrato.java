package br.zero.controlefinanceiro.model;

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

import br.zero.customdao.EntitySetup;

@Entity
@Table(name = "extrato", schema = "controlefinanceiro")
@EntitySetup(findAllQuery = "select e from Extrato e order by e.data, e.id", findByIdQuery = "select e from Extrato e where e.id=:id", idFieldName = "id")
public class Extrato {

	@Id
	@SequenceGenerator(name = "CONTROLEFINANCEIRO.EXTRATO_ID_SEQ", sequenceName = "CONTROLEFINANCEIRO.EXTRATO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CONTROLEFINANCEIRO.EXTRATO_ID_SEQ")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "BancoID")
	private Conta banco;

	@Temporal(TemporalType.DATE)
	private Calendar data;

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

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
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
