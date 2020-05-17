package br.com.yuri.minhasfinancas.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

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

@Entity
@Table( name = "lancamento", schema = "financas")
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
	
	public Lancamento() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(LocalDate dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public StatusLancamento getStatusLancamento() {
		return statusLancamento;
	}

	public void setStatusLancamento(StatusLancamento statusLancamento) {
		this.statusLancamento = statusLancamento;
	}

	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", mes=" + mes + ", ano=" + ano + ", usuario=" + usuario + ", valor=" + valor
				+ ", dataDeCadastro=" + dataDeCadastro + ", tipoLancamento=" + tipoLancamento + ", statusLancamento="
				+ statusLancamento + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ano, dataDeCadastro, id, mes, statusLancamento, tipoLancamento, usuario, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Lancamento))
			return false;
		Lancamento other = (Lancamento) obj;
		return Objects.equals(ano, other.ano) && Objects.equals(dataDeCadastro, other.dataDeCadastro)
				&& Objects.equals(id, other.id) && Objects.equals(mes, other.mes)
				&& statusLancamento == other.statusLancamento && tipoLancamento == other.tipoLancamento
				&& Objects.equals(usuario, other.usuario) && Objects.equals(valor, other.valor);
	}
	
}
