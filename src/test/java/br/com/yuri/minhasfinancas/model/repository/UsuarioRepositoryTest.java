package br.com.yuri.minhasfinancas.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.yuri.minhasfinancas.model.entity.Usuario;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private TestEntityManager entityMenager;
	
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		
		// cenário
		Usuario usuario = criarUsuario();
		
		entityMenager.persist(usuario);
			
		// execução /ação
		boolean result = this.repository.existsByEmail("usuario@gmail.com");
		
		// verificação
		Assertions.assertThat(result).isTrue();
		
	}
	
	@Test
	public void deveRetornarFalsoQuandoHouverUmUsuarioCadastradoComEmail() {
		
		// cenário
		
		// execução /ação
		boolean result = this.repository.existsByEmail("usuario@gmail.com");
		
		// verificação
		Assertions.assertThat(result).isFalse();
	}
	
	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {
		
		// cenário
		Usuario usuario = criarUsuario();
		
		 //execução
		Usuario usuarioSalvo = this.repository.save(usuario);
		
		//verificação
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
	}
	
	@Test
	public void deveBuscarUmUsuarioPorEmail() {
		
		//cenário
		Usuario usuario = criarUsuario();
		this.entityMenager.persist(usuario);
		
		//execução
		Optional<Usuario> result = this.repository.findByEmail("usuario@gmail.com");
		
		//verificação
		Assertions.assertThat(result.isPresent()).isTrue();
	}
	
	@Test
	public void deveRetornarVazioAoBuscarEmailQuandoNaoExisteNaBase() {
			
		//execução
		Optional<Usuario> result = this.repository.findByEmail("usuario@gmail.com");
				
		//verificação
		Assertions.assertThat(result.isPresent()).isFalse();
		
	}
	
	public static Usuario criarUsuario() {
		Usuario usuario = Usuario.builder()
				.nome("Yuri")
				.email("usuario@gmail.com")
				.senha("senha")
				.build();
		return usuario;
	}
	
}
	