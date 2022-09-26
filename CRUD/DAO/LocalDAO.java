package br.com.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.crud.factory.ConnectionFactory;
import br.com.crud.models.Cliente;
import br.com.crud.models.Local;
import br.com.crud.models.Pedido;



public class LocalDAO {

	public void save(Local local) {

		String sql = "INSERT INTO Local (Descricao, Preco, fk_Pedido_IdPedido)" + "VALUES (?,?,?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			conn = ConnectionFactory.createConnectionSQLServer();

			pstm = conn.prepareStatement(sql);

			pstm.setString(1, local.getDescricao());
			pstm.setDouble(2, local.getPreco());
			pstm.setInt(3, local.getIdPedido());
			
			
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void removeByID(int id) {

		String sql = "DELETE FROM Local WHERE IdLocal = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionSQLServer();
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public void update (Local local) {
		
		String sql = "UPDATE Local SET Descricao = ?, Preco = ?, fk_Pedido_IdPedido = ?  WHERE IdLocal = ?";
		
		
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionSQLServer();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, local.getDescricao());
			pstm.setDouble(2, local.getPreco());
			pstm.setInt(3, local.getIdPedido());
			pstm.setInt(4, local.getIdLocal());
			
			pstm.execute();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if (conn != null) conn.close();
				if (pstm != null) pstm.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

	public List<Local> getLocais(){
		
		String sql = "select L.IdLocal, L.Descricao,L.Preco, P.IdPedido, P.DataPedido, "
				+ "C.IdCliente, C.Nome, C.Cpf, C.Telefone, C.Email from Local as L inner join "
				+ "Pedido as P on L.fk_Pedido_IdPedido = P.IdPedido inner join Cliente as C on "
				+ "L.fk_Pedido_IdPedido = C.IdCliente";
		
		List <Local> locais = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		
		try {
			conn = ConnectionFactory.createConnectionSQLServer();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()){
				
				Pedido pedido = new Pedido ();
				Cliente cliente = new Cliente();
				Local local = new Local();
				
				local.setIdLocal(rset.getInt("IdLocal"));
				local.setDescricao(rset.getString("Descricao"));
				local.setPreco(rset.getDouble("Preco"));
								
				pedido.setIdPedido(rset.getInt("IdPedido"));
				pedido.setDataPedido(rset.getDate("DataPedido"));
				
				cliente.setIdCliente(rset.getInt("IdCliente"));
				cliente.setNome(rset.getString("Nome"));
				cliente.setCpf(rset.getString("Cpf"));
				cliente.setTelefone(rset.getString("Telefone"));
				cliente.setEmail(rset.getString("Email"));
				
				pedido.setCliente(cliente);
				
				local.setPedido(pedido);
				
				locais.add(local);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset != null) rset.close();
				if(pstm != null) pstm.close();
				if(conn != null) conn.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return locais;
		
	}
	

}