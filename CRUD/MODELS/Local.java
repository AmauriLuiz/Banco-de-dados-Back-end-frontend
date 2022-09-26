package br.com.crud.models;

public class Local {

	private int IdLocal;
	private String Descricao;
	private double Preco;
	private int IdPedido;
	private Pedido pedido;
	
	public int getIdPedido() {
		return IdPedido;
	}
	public void setIdPedido(int idPedido) {
		IdPedido = idPedido;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public int getIdLocal() {
		return IdLocal;
	}
	public void setIdLocal(int idLocal) {
		IdLocal = idLocal;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	public double getPreco() {
		return Preco;
	}
	public void setPreco(double preco) {
		Preco = preco;
	}
	
	
}