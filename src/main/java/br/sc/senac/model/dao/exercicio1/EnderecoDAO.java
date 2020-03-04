package br.sc.senac.model.dao.exercicio1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.sc.senac.model.dao.exercicio1.Banco;
import br.sc.senac.model.vo.exercicio1.Endereco;
import br.sc.senac.model.dao.exercicio1.BaseDAO;

public class EnderecoDAO implements BaseDAO<Endereco>{

	public Endereco salvar(Endereco novaEntidade) {
		// Conectar no banco
		Connection conexao = Banco.getConnection();
		
		String sql = "INSERT INTO ENDERECO (CEP, ESTADO, CIDADE, RUA, BAIRRO, NUMERO) "
				+ "VALUES (" + novaEntidade.getCep() + ", " + novaEntidade.getEstado() 
					+ ", " + novaEntidade.getCidade() + ", " + novaEntidade.getRua() 
					+ ", " + novaEntidade.getBairro() + "," + novaEntidade.getNumero() + ")";
		
		// Obter um statement
		PreparedStatement statement = Banco.getPreparedStatement(conexao, sql);
		
		try {
			// Fazer o INSERT
			statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			// Executar
			ResultSet resultado = statement.getGeneratedKeys();
			
			if(resultado.next()) {
				// Incluir a chave gerada na novaEntidade (coluna de posição 1)
				novaEntidade.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar novo endereço. Causa " + e.getMessage());
			e.printStackTrace();
		}
	
		// TODO Auto-generated method stub
		return null;
	}

	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean alterar(Endereco entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	public Endereco consultarPorId(int id) {
		Connection conexao = Banco.getConnection();		
		
		
		String sql = " SELECT id, cep, bairro, cidade, estado"
				+ " FROM ENDERECO WHERE id = ?";
		
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		Endereco enderecoConsultado = null;
		try {
			preparedStatement.setInt(1, id);
			ResultSet conjuntoResultante = preparedStatement.executeQuery();
			
			if(conjuntoResultante.next()) {
				enderecoConsultado = construirEnderecoDoResultSet(conjuntoResultante);
				
			}
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao consultar endereço. Id: " + id 
					+ ".Causa: "+ ex.getMessage());
			ex.printStackTrace();
		}
		
		
		
		
		return enderecoConsultado;
	}

	private Endereco construirEnderecoDoResultSet(ResultSet conjuntoResultante) {
		Endereco e = new Endereco();
		try {
			e = new Endereco();
			e.setId(conjuntoResultante.getInt("Id"));
			e.setCep(conjuntoResultante.getString("Cep"));
			e.setBairro(conjuntoResultante.getString("Bairro"));
			e.setCidade(conjuntoResultante.getString("Cidade"));
			e.setEstado(conjuntoResultante.getString("Estado"));
		}catch(SQLException ex) {
			System.out.println("Erro ao construir endereço: Causa: "+ ex.getMessage());
		}
		return null;
	}

	public ArrayList<Endereco> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}