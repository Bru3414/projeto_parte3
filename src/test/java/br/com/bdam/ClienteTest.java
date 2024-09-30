package br.com.bdam;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.bdam.dao.ClienteDAO;
import br.com.bdam.dao.IClienteDAO;
import br.com.bdam.domain.Cliente;

public class ClienteTest {
	
	private IClienteDAO clienteDAO;

	@Test
	public void cadastrarTest() throws Exception {
		clienteDAO = new ClienteDAO();
		
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("1");
		cliente.setNome("Bruno");
		Integer countCad = clienteDAO.cadastrar(cliente);
		Assert.assertTrue(countCad == 1);
		
		Cliente clienteBD = clienteDAO.buscar(cliente.getCodigo());
		Assert.assertNotNull(clienteBD);
		Assert.assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		Assert.assertEquals(cliente.getNome(), clienteBD.getNome());
		
		Integer countDel = clienteDAO.excluir(clienteBD);
		Assert.assertTrue(countDel == 1);
	}
	
	@Test
	public void buscarTodosTest() throws Exception {
		clienteDAO = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("1");
		cliente.setNome("Bruno");
		Integer countCad = clienteDAO.cadastrar(cliente);
		Assert.assertTrue(countCad == 1);
		
		Cliente cliente2 = new Cliente();
		cliente2.setCodigo("2");
		cliente2.setNome("Ana");
		Integer countCad2 = clienteDAO.cadastrar(cliente2);
		Assert.assertTrue(countCad2 == 1);
		
		List<Cliente> list = clienteDAO.buscarTodos();
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
		
		int countDel = 0;
		for (Cliente cli : list) {
			clienteDAO.excluir(cli);
			countDel++;
		}
		
		Assert.assertEquals(list.size(), countDel);
		
		list = clienteDAO.buscarTodos();
		Assert.assertEquals(list.size(), 0);
		
	}
	
	@Test
	public void atualizarTest() throws Exception {
		clienteDAO = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("1");
		cliente.setNome("Bruno");
		Integer countCad = clienteDAO.cadastrar(cliente);
		Assert.assertTrue(countCad == 1);
		
		Cliente clienteBD = clienteDAO.buscar(cliente.getCodigo());
		Assert.assertNotNull(clienteBD);
		Assert.assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		Assert.assertEquals(cliente.getNome(), clienteBD.getNome());
		
		clienteBD.setCodigo("2");
		clienteBD.setNome("Bruno Dambroski");
		Integer countUpdate = clienteDAO.atualizar(clienteBD);
		Assert.assertTrue(countUpdate == 1);
		
		Cliente clienteBD2 = clienteDAO.buscar("2");
		Assert.assertNotNull(clienteBD2);
		Assert.assertEquals(clienteBD.getCodigo(), clienteBD2.getCodigo());
		Assert.assertEquals(clienteBD.getNome(), clienteBD2.getNome());
		
		Integer countDel = clienteDAO.excluir(clienteBD);
		Assert.assertTrue(countDel == 1);
	}
	
}
