package br.com.zero.controlefinanceiro.actions;

import java.util.List;

/**
 * Armazena as propriedades do view de uma lista de entidades de um determinado
 * tipo.
 * 
 * @author Rafael Monico
 * 
 * @param <Entity>
 */
public class ListProperties<Entity> {
	private int currentPage = 1;
	private int itensPerPage = 25;
	private List<Entity> list;

	public ListProperties(List<Entity> list) {
		this.list = list;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getLastPage() {
		/*
		 * java.util.Math.floor: arredonda um número para baixo.
		 */
		return (int) Math.floor(list.size() / itensPerPage);
	}

	public void setItensPerPage(int itensPerPage) {
		this.itensPerPage = itensPerPage;
	}

	public int getItensPerPage() {
		return itensPerPage;
	}

}
