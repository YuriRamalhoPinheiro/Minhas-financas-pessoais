package br.com.yuri.minhasfinancas.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.yuri.minhasfinancas.exception.ErroDeAutenticacao;
import br.com.yuri.minhasfinancas.exception.RegraDeNegocioException;
import br.com.yuri.minhasfinancas.model.entity.Usuario;
import br.com.yuri.minhasfinancas.model.repository.UsuarioRepository;
import br.com.yuri.minhasfinancas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	private UsuarioRepository repository;  
	
	@Autowired
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		
		Optional<Usuario> usuario = this.repository.findByEmail(email);
		
		if(!usuario.isPresent()) {
			throw new ErroDeAutenticacao("Usuário não encontrado para o e-mail informado.");
		}
		
		if(!usuario.get().getSenha().equals(senha)) {
			throw new ErroDeAutenticacao("Senha inválida.");
		}
		
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		
		validarEmail(usuario.getEmail());
				
		return usuario.equals(null) ? usuario : this.repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		
		boolean exists = this.repository.existsByEmail(email);
		
		if(exists) {
			throw new RegraDeNegocioException("Já existe um usuário cadastrado com esse e-mail");
		}
		
	}

}
