package br.com.alurafood.pedidos.dto.pagamentos;

import java.math.BigDecimal;
import br.com.alurafood.pedidos.enums.pagamentos.StatusPagamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoDTO {
    private Long id;
    private BigDecimal valor;
    private String nome;
    private String numero;
    private String expiracao;
    private String codigo;
    private StatusPagamento status;
    private Long formaDePagamentoId;
    private Long pedidoId;
}
