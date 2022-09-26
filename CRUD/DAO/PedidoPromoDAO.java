package br.com.crud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.crud.factory.ConnectionFactory;
import br.com.crud.models.Cliente;
import br.com.crud.models.PedidoPromo;


public class PedidoPromoDAO {

	public void save(PedidoPromo pedidoPromo) {

		String sql = "INSERT INTO PedidoPromo (DataPedidoPromo, fk_Cliente_IdCliente)" + "VALUES (?,?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			conn = ConnectionFactory.createConnectionSQLServer();

			pstm = conn.prepareStatement(sql);

			pstm.setDate(1, new Date(pedidoPromo.getDataPedidoPromo().getTime()));
			pstm.setInt(2, pedidoPromo.getIdCliente());
			
			
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

		String sql = "DELETE FROM PedidoPromo WHERE IdPedidoPromo = ?";

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
	
	public void update (PedidoPromo pedidoPromo) {
		
		String sql = "UPDATE PedidoPromo SET DataPedidoPromo = ?, fk_Cliente_IdCliente = ? WHERE IdPedidoPromo = ?";
		
		
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionSQLServer();
			pstm = conn.prepareStatement(sql);
			
			pstm.setDate(1, new Date(pedidoPromo.getDataPedidoPromo().getTime()));
			pstm.setInt(2, pedidoPromo.getIdCliente());
			pstm.setInt(3, pedidoPromo.getIdPedidoPromo());
			
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

	public List<PedidoPromo> getPedidosPromo(){
		
		String sql = "select PP.IdPedidoPromo, PP.DataPedidoPromo, C.IdCliente, C.Nome, C.Cpf, C.Telefone, C.Email from PedidoPromo as PP inner join Cliente as C on PP.fk_Cliente_IdCliente = C.IdCliente";
		
		List <PedidoPromo> pedidosPromo = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		
		try {
			conn = ConnectionFactory.createConnectionSQLServer();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()){
				
				PedidoPromo pedidoPromo = new PedidoPromo ();
				Cliente cliente = new Cliente();
				
				pedidoPromo.setIdPedidoPromo(rset.getInt("IdPedidoPromo"));
				pedidoPromo.setDataPedidoPromo(rset.getDate("DataPedidoPromo"));
				
				cliente.setIdCliente(rset.getInt("IdCliente"));
				cliente.setNome(rset.getString("Nome"));
				cliente.setCpf(rset.getString("Cpf"));
				cliente.setTelefone(rset.getString("Telefone"));
				cliente.setEmail(rset.getString("Email"));
				
				pedidoPromo.setCliente(cliente);
				
				pedidosPromo.add(pedidoPromo);
				
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
		
		return pedidosPromo;
		
	}
	

}