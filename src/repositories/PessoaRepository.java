package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.Pessoa;
import factories.ConnectionFactory;

public class PessoaRepository {

	public void inserir(Pessoa pessoa) throws Exception {

		// abrir conexão com banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// escrever o script sql para inserir o registo na tabela pessoa
		PreparedStatement statement = connection
				.prepareStatement("insert into pessoa(id, nome, cpf, telefone) values(?,?,?,?)");

		// preenchendo os parametros do comando insert
		statement.setObject(1, pessoa.getId());
		statement.setString(2, pessoa.getNome());
		statement.setString(3, pessoa.getCpf());
		statement.setString(4, pessoa.getTelefone());
		statement.execute();

		// fechar conexão
		connection.close();
	}

	public void editar(Pessoa pessoa) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("update pessoa set nome=?, cpf=?, telefone=? where id=?");

		statement.setString(1, pessoa.getNome());
		statement.setString(2, pessoa.getCpf());
		statement.setString(3, pessoa.getTelefone());
		statement.setObject(4, pessoa.getId());
		statement.execute();

		connection.close();
	}

	public void excluir(Pessoa pessoa) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("delete from pessoa where id=?");
		statement.setObject(1, pessoa.getId());
		statement.execute();

		connection.close();

	}

	public List<Pessoa> obterTodos() throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from pessoa order by nome");

		// let todos os registros da consulta do banco de dados
		ResultSet resultSet = statement.executeQuery();

		// criar lista de objetos da classe pessoa
		List<Pessoa> lista = new ArrayList<Pessoa>();

		// percorrer cada registro ontido em resultSet
		while (resultSet.next()) {

			Pessoa pessoa = new Pessoa();
			pessoa.setId(UUID.fromString(resultSet.getString("id")));
			pessoa.setNome(resultSet.getString("nome"));
			pessoa.setCpf(resultSet.getString("cpf"));
			pessoa.setTelefone(resultSet.getString("telefone"));

			lista.add(pessoa); // adicionando o objeto 'pessoa' na lista
		}

		connection.close();
		// retornando lista
		return lista;
	}

	public Pessoa obterPorId(UUID id) throws Exception {

		// criando conexão com o banco
		Connection connection = ConnectionFactory.getConnection();

		// escrevendo script sql para consultar registros na base de dados
		PreparedStatement statement = connection.prepareStatement("select * from pessoa where id=?");

		statement.setObject(1, id);
		ResultSet resultSet = statement.executeQuery();

		Pessoa pessoa = null; // vazio - sem espaço em memoria

		if (resultSet.next()) {

			pessoa = new Pessoa();
			pessoa.setId(UUID.fromString(resultSet.getString("id")));
			pessoa.setNome(resultSet.getString("nome"));
			pessoa.setCpf(resultSet.getString("cpf"));
			pessoa.setTelefone(resultSet.getString("telefone"));

		}

		connection.close();
		// retornando lista
		return pessoa;
	}

}
