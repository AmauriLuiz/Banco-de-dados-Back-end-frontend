package br.com.crud.models;

import java.util.Date;

public class Pedido {

	private int IdPedido;
	private Date DataPedido;
	private int IdCliente;
	
	public int getIdCliente() {
		return IdCliente;
	}
	public void setIdCliente(int idCliente) {
		IdCliente = idCliente;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	private Cliente cliente;
	
	public int getIdPedido() {
		return IdPedido;
	}
	public void setIdPedido(int idPedido) {
		IdPedido = idPedido;
	}
	public Date getDataPedido() {
		return DataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		DataPedido = dataPedido;
	}
	
	
	
	
}