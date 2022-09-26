package br.com.crud.models;

import java.util.Date;

public class PedidoPromo {
	
	private int IdPedidoPromo;
	private Date DataPedidoPromo;
	private int IdCliente;
	private Cliente cliente;
	
	
	
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
	public int getIdPedidoPromo() {
		return IdPedidoPromo;
	}
	public void setIdPedidoPromo(int idPedidoPromo) {
		IdPedidoPromo = idPedidoPromo;
	}
	public Date getDataPedidoPromo() {
		return DataPedidoPromo;
	}
	public void setDataPedidoPromo(Date dataPedidoPromo) {
		DataPedidoPromo = dataPedidoPromo;
	}

	
	
}