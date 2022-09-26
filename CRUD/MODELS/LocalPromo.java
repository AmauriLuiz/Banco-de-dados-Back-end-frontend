package br.com.crud.models;

public class LocalPromo {

	private int IdLocalPromo;
	private String DescricaoPromo;
	private double PrecoPromo;
	private int IdPedidoPromo;
	private PedidoPromo pedidoPromo;
	
	
	public int getIdPedidoPromo() {
		return IdPedidoPromo;
	}
	public void setIdPedidoPromo(int idPedidoPromo) {
		IdPedidoPromo = idPedidoPromo;
	}
	public PedidoPromo getPedidoPromo() {
		return pedidoPromo;
	}
	public void setPedidoPromo(PedidoPromo pedidoPromo) {
		this.pedidoPromo = pedidoPromo;
	}
	public int getIdLocalPromo() {
		return IdLocalPromo;
	}
	public void setIdLocalPromo(int idLocalPromo) {
		IdLocalPromo = idLocalPromo;
	}
	public String getDescricaoPromo() {
		return DescricaoPromo;
	}
	public void setDescricaoPromo(String descricaoPromo) {
		DescricaoPromo = descricaoPromo;
	}
	public double getPrecoPromo() {
		return PrecoPromo;
	}
	public void setPrecoPromo(double precoPromo) {
		PrecoPromo = precoPromo;
	}
	
	
	
}