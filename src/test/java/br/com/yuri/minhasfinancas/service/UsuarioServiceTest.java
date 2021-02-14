package br.com.yuri.minhasfinancas.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.yuri.minhasfinancas.exception.RegraDeNegocioException;
import br.com.yuri.minhasfinancas.model.entity.Usuario;
import br.com.yuri.minhasfinancas.model.repository.UsuarioRepository;
import br.com.yuri.minhasfinancas.service.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@TestInstance(value = Lifecycle.PER_CLASS)
public class UsuarioServiceTest {
	
	private UsuarioService service;
	
	@MockBean
	private UsuarioRepository repository;
	
	@BeforeAll
	public void setUp() {
		
		service = new UsuarioServiceImpl(repository);
	}
	
	@Test
	public void deveValidarEmail() {
		
		// cenário
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
		
		// ação
		Assertions.assertDoesNotThrow(new Executable() {
			
			@Override
			public void execute() throws Throwable {
				
				service.validarEmail("email@email.com");
			}
		});
	
	}
	
	@Test
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
		
		Assertions.assertThrows(RegraDeNegocioException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				
				//cenário
				Usuario usuario = Usuario.builder().nome("Yuri").senha("123").email("email@email.com").build();
				
				repository.save(usuario);
				
				//ação
				service.validarEmail("email@email.com");
			}
		});
	}
	
	@Test
	public void deveAutenticarUmUsuarioComSucesso() {
		
		//cenário
		String email = "email@gmail.com";
		String senha = "123";
		
		Usuario usuario  = Usuario.builder().email(email).senha(senha).id(1L).build();
		Mockito.when( repository.findByEmail(email)).thenReturn(Optional.of(usuario));
		
		//acão
		Usuario result = service.autenticar(email, senha);
		
		//verificação
		Assertions.assertDoesNotThrow(new Executable() {
			
			@Override
			public void execute() throws Throwable {

				Assertions.assertNotNull(result);
				
			}
		});
	}

}
