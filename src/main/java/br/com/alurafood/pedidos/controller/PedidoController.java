package br.com.alurafood.pedidos.controller;

import br.com.alurafood.pedidos.dto.PedidoDto;
import br.com.alurafood.pedidos.dto.StatusDto;
import br.com.alurafood.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Endpoints para gerenciamento de pedidos")
public class PedidoController {

        @Autowired
        private PedidoService service;

        @Operation(summary = "Listar todos os pedidos", description = "Retorna uma lista de todos os pedidos")
        @GetMapping()
        public List<PedidoDto> listarTodos() {
            return service.obterTodos();
        }

        @GetMapping("/{id}")
        @Operation(summary = "Buscar pedido por ID", description = "Retorna um pedido específico pelo seu ID")
        public ResponseEntity<PedidoDto> listarPorId(@PathVariable @NotNull Long id) {
            PedidoDto dto = service.obterPorId(id);

            return  ResponseEntity.ok(dto);
        }

        @PostMapping()
        @Operation(summary = "Criar pedido", description = "Cria um novo pedido")
        public ResponseEntity<PedidoDto> realizaPedido(@RequestBody @Valid PedidoDto dto, UriComponentsBuilder uriBuilder) {
            PedidoDto pedidoRealizado = service.criarPedido(dto);

            URI endereco = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedidoRealizado.getId()).toUri();

            return ResponseEntity.created(endereco).body(pedidoRealizado);

        }

        @PutMapping("/{id}/status")
        @Operation(summary = "Atualizar status do pedido", description = "Atualiza o status de um pedido existente")
        public ResponseEntity<PedidoDto> atualizaStatus(@PathVariable Long id, @RequestBody StatusDto status){
           PedidoDto dto = service.atualizaStatus(id, status);

            return ResponseEntity.ok(dto);
        }

        @Operation(summary = "Aprovar pagamento", description = "Aprova o pagamento de um pedido")
        @PutMapping("/{id}/pago")
        public ResponseEntity<Void> aprovaPagamento(@PathVariable @NotNull Long id) {
            service.aprovaPagamentoPedido(id);
            return ResponseEntity.ok().build();
        }

        @Operation(summary = "Obter porta", description = "Retorna a porta em que o serviço está rodando")
        @GetMapping("/porta")
        public String obterPorta(@Value("${local.server.port}") String porta) {
            return String.format("porta: %s", porta);
        }
}
