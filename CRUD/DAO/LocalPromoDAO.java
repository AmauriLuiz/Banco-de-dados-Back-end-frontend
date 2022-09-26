package br.com.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.crud.factory.ConnectionFactory;
import br.com.crud.models.Cliente;
import br.com.crud.models.LocalPromo;
import br.com.crud.models.PedidoPromo;



public class LocalPromoDAO {

	public void save(LocalPromo localPromo) {

		String sql = "INSERT INTO LocalPromo (DescricaoPromo, PrecoPromo, fk_PedidoPromo_IdPedidoPromo)" + "VALUES (?,?,?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			conn = ConnectionFactory.createConnectionSQLServer();

			pstm = conn.prepareStatement(sql);

			pstm.setString(1, localPromo.getDescricaoPromo());
			pstm.setDouble(2, localPromo.getPrecoPromo());
			pstm.setInt(3, localPromo.getIdPedidoPromo());
			
			
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

		String sql = "DELETE FROM LocalPromo WHERE IdLocalPromo = ?";

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
	
	public void update (LocalPromo localPromo) {
		
		String sql = "UPDATE LocalPromo SET DescricaoPromo = ?, PrecoPromo = ?, fk_PedidoPromo_IdPedidoPromo = ?  WHERE IdLocalPromo = ?";
		
		
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionSQLServer();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, localPromo.getDescricaoPromo());
			pstm.setDouble(2, localPromo.getPrecoPromo());
			pstm.setInt(3, localPromo.getIdPedidoPromo());
			pstm.setInt(4, localPromo.getIdLocalPromo());
			
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

	public List<LocalPromo> getLocaisPromo(){
		
		String sql = "select LP.IdLocalPromo, LP.DescricaoPromo,LP.PrecoPromo, PP.IdPedidoPromo, PP.DataPedidoPromo,"
				+ "C.IdCliente, C.Nome, C.Cpf, C.Telefone, C.Email from LocalPromo as LP inner join "
				+ "PedidoPromo as PP on LP.fk_PedidoPromo_IdPedidoPromo = PP.IdPedidoPromo inner join Cliente as C on "
				+ "PP.fk_Cliente_IdCliente = C.IdCliente;";
		
		List <LocalPromo> locaisPromo = new ArrayList<>();
		
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
				LocalPromo localPromo = new LocalPromo();
				
				localPromo.setIdLocalPromo(rset.getInt("IdLocalPromo"));
				localPromo.setDescricaoPromo(rset.getString("DescricaoPromo"));
				localPromo.setPrecoPromo(rset.getDouble("PrecoPromo"));
								
				pedidoPromo.setIdPedidoPromo(rset.getInt("IdPedidoPromo"));
				pedidoPromo.setDataPedidoPromo(rset.getDate("DataPedidoPromo"));
				
				cliente.setIdCliente(rset.getInt("IdCliente"));
				cliente.setNome(rset.getString("Nome"));
				cliente.setCpf(rset.getString("Cpf"));
				cliente.setTelefone(rset.getString("Telefone"));
				cliente.setEmail(rset.getString("Email"));
				
				pedidoPromo.setCliente(cliente);
				
				localPromo.setPedidoPromo(pedidoPromo);
				
				locaisPromo.add(localPromo);
				
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
		
		return locaisPromo;
		
	}
	

}