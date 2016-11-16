package br.com.fabricaprogramador.persistencia.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

public class UsuarioDAO {
	public Connection conex =  ConexaoFactory.getConnectionn();

	
	public void cadastrar(Usuario usu) {
				
		
		String sql = " insert into usuarios (nome, login, senha) values (?,?,?)";
		try {
			PreparedStatement preparador = conex.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			
			preparador.executeQuery();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
	
	public void alterar(Usuario usu){
		String sql = " update  usuarios set nome =?, login =?, senha=? where id =?";
		try {
			PreparedStatement preparador = conex.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			
			preparador.executeQuery();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void excluir(Usuario usu){
		String sql = " delete from usuarios where id=?";
		
			try {
				
				PreparedStatement preparador = conex.prepareStatement(sql);
				preparador.setInt(1, usu.getId());
				preparador.executeQuery();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	public void salvar(Usuario usuario){
		if (usuario.getId() != null) {
			alterar(usuario);
		} else {
			salvar(usuario);
		}
	}
	
	public Usuario buscaPorId(Integer id){
		String sql = " select * from usuarios where id=?";
		try {
			PreparedStatement preparador = conex.prepareStatement(sql);
			preparador.setInt(1,id);
			
			ResultSet rs = preparador.executeQuery();
			if(rs.next()){
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				return usuario;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Usuario> buscaTodos(){
		String sql = " select * from usuarios";
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			PreparedStatement preparador = conex.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()){
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				lista.add(usuario);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return lista;
	}
	
	public Usuario altenticar(Usuario usuConsulta){
		String sql = "select * from usuarios where login=? and senha=?";
		try(PreparedStatement preparador = conex.prepareStatement(sql)){
			preparador.setString(1, usuConsulta.getLogin());
			preparador.setString(2, usuConsulta.getSenha());
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()){
			Usuario usuario = new Usuario();
			usuario.setId(resultado.getInt("id"));
			usuario.setNome(resultado.getString("nome"));
			usuario.setLogin(resultado.getString("login"));
			usuario.setSenha(resultado.getString("senha"));
			
			return usuario;
			}else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
