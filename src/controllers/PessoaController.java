package controllers;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import entities.Pessoa;
import repositories.PessoaRepository;

public class PessoaController {

	public void cadastrarPessoa() {
		Scanner scanner = new Scanner(System.in);
		try {

			System.out.println("\nCADASTRO DE PESSOA");

			Pessoa pessoa = new Pessoa();
			pessoa.setId(UUID.randomUUID());

			System.out.print("Nome da Pessoa......:");
			pessoa.setNome(scanner.nextLine());

			System.out.print("Cpf da Pessoa.......:");
			pessoa.setCpf(scanner.nextLine());

			System.out.print("Telefone da Pessoa..:");
			pessoa.setTelefone(scanner.nextLine());

			PessoaRepository pessoaRepository = new PessoaRepository();
			pessoaRepository.inserir(pessoa);

			System.out.println("\n*****PESSOA CADASTRADA COM SUCESSO*****");

		} catch (Exception e) {
			System.out.println("\n*****FALHA AO CADASTRAR PESSOA*****S");
			System.out.println(e.getMessage());

		}
		scanner.close();
	}

	public void atualizarPessoa() {
		Scanner scanner = new Scanner(System.in);
		try {

			System.out.println("\nATUALIZAÇÃO DE PESSOA:\n");

			System.out.println("ENTRE COM O ID DA PESSOA...:");
			UUID id = UUID.fromString(scanner.nextLine());

			// consultando pessoa no banco de dados atraves do id
			PessoaRepository pessoaRepository = new PessoaRepository();
			Pessoa pessoa = pessoaRepository.obterPorId(id);

			if (pessoa != null) {

				System.out.print("Nome da Pessoa......:");
				pessoa.setNome(scanner.nextLine());

				System.out.print("Cpf da Pessoa.......:");
				pessoa.setCpf(scanner.nextLine());

				System.out.print("Telefone da Pessoa..:");
				pessoa.setTelefone(scanner.nextLine());

				pessoaRepository.editar(pessoa);

				System.out.println("\nPESSOA ATUALIZADA COM SUCESSO");

			} else {
				System.out.println("USUARIO NAO ENCONTRADO");

			}

		} catch (Exception e) {
			System.out.println("FALHA AO CADASTRAR PESSOA");
			System.out.println(e.getMessage());
		}
		scanner.close();
	}

	public void excluirPessoa() {
		Scanner scanner = new Scanner(System.in);
		try {

			System.out.println("\nEXCLUSAO DE PESSOA:\n");

			System.out.println("ENTRE COM O ID DA PESSOA...:");
			UUID id = UUID.fromString(scanner.nextLine());

			// consultando pessoa no banco de dados atraves do id
			PessoaRepository pessoaRepository = new PessoaRepository();
			Pessoa pessoa = pessoaRepository.obterPorId(id);

			if (pessoa != null) {

				System.out.print("Nome da Pessoa......:");
				pessoa.setNome(scanner.nextLine());

				System.out.print("Cpf da Pessoa.......:");
				pessoa.setCpf(scanner.nextLine());

				System.out.print("Telefone da Pessoa..:");
				pessoa.setTelefone(scanner.nextLine());

				pessoaRepository.excluir(pessoa);

				System.out.println("\nPESSOA EXCLUIDA COM SUCESSO");

			} else {
				System.out.println("USUARIO NAO ENCONTRADO");

			}

		} catch (Exception e) {
			System.out.println("FALHA AO EXCLUIR PESSOA");
			System.out.println(e.getMessage());
		}
		scanner.close();
	}

	public void consultarPessoas() {

		try {

			System.err.println("\nCONSULTA DE PESSOAS\n");

			// consultando todas as pessoas cadastradas
			PessoaRepository pessoaRepository = new PessoaRepository();
			List<Pessoa> lista = pessoaRepository.obterTodos();

			// percorrendo todos os objetos da lista
			for (Pessoa pessoa : lista) {

				System.out.println("\nID...........:" + pessoa.getId());
				System.out.println("\nNome.........:" + pessoa.getNome());
				System.out.println("\nCpf..........:" + pessoa.getCpf());
				System.out.println("\nTelefone.....:" + pessoa.getTelefone());
				System.out.println("\n");
			}

		}

		catch (Exception e) {
			System.out.println("FALHA AO ENCONTRAR PESSOA");
			System.out.println(e.getMessage());
		}

	}
}
