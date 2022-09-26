package br.com.crud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.crud.factory.ConnectionFactory;
import br.com.crud.models.Cliente;
import br.com.crud.models.Pedido;



public class PedidoDAO {

	public void save(Pedido pedido) {

		String sql = "INSERT INTO Pedido (DataPedido, fk_Cliente_IdCliente)" + "VALUES (?,?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			conn = ConnectionFactory.createConnectionSQLServer();

			pstm = conn.prepareStatement(sql);

			pstm.setDate(1, new Date(pedido.getDataPedido().getTime()));
			pstm.setInt(2, pedido.getIdCliente());
			
			
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

		String sql = "DELETE FROM Pedido WHERE IdPedido = ?";

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
	
	public void update (Pedido pedido) {
		
		String sql = "UPDATE Pedido SET DataPedido = ?, fk_Cliente_IdCliente = ? WHERE IdPedido = ?";
		
		
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionSQLServer();
			pstm = conn.prepareStatement(sql);
			
			pstm.setDate(1, new Date(pedido.getDataPedido().getTime()));
			pstm.setInt(2, pedido.getIdCliente());
			pstm.setInt(3, pedido.getIdPedido());
			
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

	public List<Pedido> getPedidos(){
		
		String sql = "select P.IdPedido, P.DataPedido, C.IdCliente, C.Nome, C.Cpf, C.Telefone, C.Email from Pedido as P inner join Cliente as C on P.fk_Cliente_IdCliente = C.IdCliente";
		
		List <Pedido> pedidos = new ArrayList<>();
		
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
				
				pedido.setIdPedido(rset.getInt("IdPedido"));
				pedido.setDataPedido(rset.getDate("DataPedido"));
				
				cliente.setIdCliente(rset.getInt("IdCliente"));
				cliente.setNome(rset.getString("Nome"));
				cliente.setCpf(rset.getString("Cpf"));
				cliente.setTelefone(rset.getString("Telefone"));
				cliente.setEmail(rset.getString("Email"));
				
				pedido.setCliente(cliente);
				
				pedidos.add(pedido);
				
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
		
		return pedidos;
		
	}
	

}