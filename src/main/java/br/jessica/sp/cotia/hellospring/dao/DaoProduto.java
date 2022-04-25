package br.jessica.sp.cotia.hellospring.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.jessica.sp.cotia.hellospring.model.Produto;
import br.jessica.sp.cotia.hellospring.model.TipoProduto;

public class DaoProduto {
	private Connection conexao;
	
	public DaoProduto() {
		conexao = ConnectionFactory.conectar();
	}
	
	public void inserir(Produto produto) {
		String sql = "insert into tb_produto" +"(nome, data_validade, preco, estoque, tipo_produto)" +"values (?,?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setDate(2, new Date(produto.getDataValidade().getTimeInMillis()));
			stmt.setDouble(3, produto.getPreco());
			stmt.setInt(4, produto.getEstoque());
			stmt.setInt(5, produto.getTipoProduto().ordinal());
			stmt.execute();
			conexao.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void atualizar(Produto produto) {
		String sql = "update tb_produto set nome = ?, data_validade = ?, preco = ?, estoque = ?, tipo_produto = ? where id = ?";
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setDate(2, new Date(produto.getDataValidade().getTimeInMillis()));
			stmt.setDouble(3, produto.getPreco());
			stmt.setInt(4, produto.getEstoque());
			stmt.setInt(5, produto.getTipoProduto().ordinal());
			stmt.setLong(6, produto.getId());
			stmt.execute();
			conexao.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Produto> listar() {
		String sql = "select * from tb_produto order by nome asc";
		List<Produto> lista = new ArrayList<Produto>();
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Produto p = new Produto();
				p.setId(rs.getLong("id"));
				p.setNome(rs.getString("nome"));
				p.setPreco(rs.getDouble("preco"));
				p.setEstoque(rs.getInt("estoque"));
				// cria um Calendar
				Calendar validade = Calendar.getInstance();
				// extrair o Date do resultset
				Date dataBd = rs.getDate("data_validade");
				// "setar" a data do calendar pela data do Date
				validade.setTimeInMillis(dataBd.getTime());
				// "setar" a validade no produto
				p.setDataValidade(validade);
				// estrair a posicao da enumeracao do resultset
				int posEnum = rs.getInt("tipo_produto");
				TipoProduto tipo = TipoProduto.values()[posEnum];
				// "setar" o tipo no produto
				p.setTipoProduto(tipo);
				lista.add(p);
			}
			rs.close();
			stmt.close();
			conexao.close();
			return lista;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Produto buscar(Long idProduto) {
		String sql = "select * from tb_produto where id = ?";
		Produto p = null;
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, idProduto);
			ResultSet rs = stmt.executeQuery();
			 if(rs.next()) {
				p = new Produto();
				p.setId(rs.getLong("id"));
				p.setNome(rs.getString("nome"));
				p.setPreco(rs.getDouble("preco"));
				p.setEstoque(rs.getInt("estoque"));
				// cria um Calendar
				Calendar validade = Calendar.getInstance();
				// extrair o Date do resultset
				Date dataBd = rs.getDate("data_validade");
				// "setar" a data do calendar pela data do Date
				validade.setTimeInMillis(dataBd.getTime());
				// "setar" a validade no produto
				p.setDataValidade(validade);
				// estrair a posicao da enumeracao do resultset
				int posEnum = rs.getInt("tipo_produto");
				TipoProduto tipo = TipoProduto.values()[posEnum];
				// "setar" o tipo no produto
				p.setTipoProduto(tipo);
			}
			rs.close();
			stmt.close();
			conexao.close();
			return p;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void excluir(long id) {
		String sql = "delete from tb_produto where id = ?";
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
