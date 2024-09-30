package br.com.bdam;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.bdam.dao.ProdutoDAO;
import br.com.bdam.dao.IProdutoDAO;
import br.com.bdam.domain.Produto;

public class ProdutoTest {
	private IProdutoDAO produtoDAO;

	@Test
	public void cadastrarTest() throws Exception {
		produtoDAO = new ProdutoDAO();
		
		
		Produto produto = new Produto();
		produto.setCodigo("1");
		produto.setNome("Televisão");
		produto.setPreco(900.00);
		Integer countCad = produtoDAO.cadastrar(produto);
		Assert.assertTrue(countCad == 1);
		
		Produto produtoBD = produtoDAO.buscar(produto.getCodigo());
		Assert.assertNotNull(produtoBD);
		Assert.assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		Assert.assertEquals(produto.getNome(), produtoBD.getNome());
		
		Integer countDel = produtoDAO.excluir(produtoBD);
		Assert.assertTrue(countDel == 1);
	}
	
	@Test
	public void buscarTodosTest() throws Exception {
		produtoDAO = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("1");
		produto.setNome("Televisão");
		produto.setPreco(900.00);
		Integer countCad = produtoDAO.cadastrar(produto);
		Assert.assertTrue(countCad == 1);
		
		Produto produto2 = new Produto();
		produto2.setCodigo("2");
		produto2.setNome("Geladeira");
		produto2.setPreco(1200.00);
		Integer countCad2 = produtoDAO.cadastrar(produto2);
		Assert.assertTrue(countCad2 == 1);
		
		List<Produto> list = produtoDAO.buscarTodos();
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
		
		int countDel = 0;
		for (Produto prod : list) {
			produtoDAO.excluir(prod);
			countDel++;
		}
		
		Assert.assertEquals(list.size(), countDel);
		
		list = produtoDAO.buscarTodos();
		Assert.assertEquals(list.size(), 0);
		
	}
	
	@Test
	public void atualizarTest() throws Exception {
		produtoDAO = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("1");
		produto.setNome("Televisão");
		produto.setPreco(900.00);
		Integer countCad = produtoDAO.cadastrar(produto);
		Assert.assertTrue(countCad == 1);
		
		Produto produtoBD = produtoDAO.buscar(produto.getCodigo());
		Assert.assertNotNull(produtoBD);
		Assert.assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		Assert.assertEquals(produto.getNome(), produtoBD.getNome());
		
		produtoBD.setCodigo("2");
		produtoBD.setNome("Geladeira");
		produtoBD.setPreco(1200.00);
		Integer countUpdate = produtoDAO.atualizar(produtoBD);
		Assert.assertTrue(countUpdate == 1);
		
		Produto produtoBD2 = produtoDAO.buscar("2");
		Assert.assertNotNull(produtoBD2);
		Assert.assertEquals(produtoBD.getCodigo(), produtoBD2.getCodigo());
		Assert.assertEquals(produtoBD.getNome(), produtoBD2.getNome());
		
		Integer countDel = produtoDAO.excluir(produtoBD);
		Assert.assertTrue(countDel == 1);
	}
}
