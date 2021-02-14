package br.com.yuri.minhasfinancas.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import br.com.yuri.minhasfinancas.model.enums.StatusLancamento;
import br.com.yuri.minhasfinancas.model.enums.TipoLancamento;
import lombok.Builder;
import lombok.Data;

@Entity
@Table( name = "lancamento", schema = "financas")
@Data
@Builder
public class Lancamento {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer mes;
	
	private Integer ano;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	private BigDecimal valor;
	
	@Column(name = "data_cadastro")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataDeCadastro;
	
	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoLancamento tipoLancamento;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusLancamento statusLancamento;
	
}
