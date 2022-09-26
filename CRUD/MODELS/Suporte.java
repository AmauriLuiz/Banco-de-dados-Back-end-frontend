package br.com.crud.models;

public class Suporte {
	
	private int IdSuporte;
	private String Mensagem;
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
	public int getIdSuporte() {
		return IdSuporte;
	}
	public void setIdSuporte(int idSuporte) {
		IdSuporte = idSuporte;
	}
	public String getMensagem() {
		return Mensagem;
	}
	public void setMensagem(String mensagem) {
		Mensagem = mensagem;
	}
	
	
	
	
	

}