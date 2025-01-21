package br.com.alurafood.pedidos.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.alurafood.pedidos.dto.pagamentos.PagamentoDTO;

@Component
public class PagamentoListener {

    @RabbitListener(queues = "pagamentos.detalhes-pedido")
    public void recebeMensagem(PagamentoDTO pagamentoDTO) {
        String message =  """
                Dados do pagamento: %s
                Número do pedido: %s
                Valor: %s
                Status: %s
                """.formatted(pagamentoDTO.getPedidoId(), pagamentoDTO.getPedidoId() ,pagamentoDTO.getValor(), pagamentoDTO.getStatus());

                System.out.println(message);
    }
}
