package br.com.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.crud.factory.ConnectionFactory;
import br.com.crud.models.Cliente;
import br.com.crud.models.Suporte;



public class SuporteDAO {

	public void save(Suporte suporte) {

		String sql = "INSERT INTO Suporte (Mensagem, fk_Cliente_IdCliente)" + "VALUES (?,?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			conn = ConnectionFactory.createConnectionSQLServer();

			pstm = conn.prepareStatement(sql);

			pstm.setString(1, suporte.getMensagem());
			pstm.setInt(2, suporte.getIdCliente());
			
			
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

		String sql = "DELETE FROM Suporte WHERE IdSuporte = ?";

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
	
	public void update (Suporte suporte) {
		
		String sql = "UPDATE Suporte SET Mensagem = ?, fk_Cliente_IdCliente = ? WHERE IdSuporte = ?";
		
		
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionSQLServer();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, suporte.getMensagem());
			pstm.setInt(2, suporte.getIdCliente());
			pstm.setInt(3, suporte.getIdSuporte());
			
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

	public List<Suporte> getSuportes(){
		
		String sql = "select S.IdSuporte, C.IdCliente, C.Nome, C.Cpf, C.Telefone, C.Email, S.Mensagem from Suporte as S inner join Cliente as C on S.fk_Cliente_IdCliente = C.IdCliente";
		
		List <Suporte> suportes = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		
		try {
			conn = ConnectionFactory.createConnectionSQLServer();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()){
				
				Suporte suporte = new Suporte ();
				Cliente cliente = new Cliente();
				
				suporte.setIdSuporte(rset.getInt("IdSuporte"));
				suporte.setMensagem(rset.getString("Mensagem"));
				
				cliente.setIdCliente(rset.getInt("IdCliente"));
				cliente.setNome(rset.getString("Nome"));
				cliente.setCpf(rset.getString("Cpf"));
				cliente.setTelefone(rset.getString("Telefone"));
				cliente.setEmail(rset.getString("Email"));
				
				suporte.setCliente(cliente);
				
				suportes.add(suporte);
				
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
		
		return suportes;
		
	}
	

}