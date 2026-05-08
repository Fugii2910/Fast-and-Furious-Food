package br.fugii.eti.Fast_and_Furious_Food.controller;

import br.fugii.eti.Fast_and_Furious_Food.domain.model.Pedido;
import br.fugii.eti.Fast_and_Furious_Food.domain.model.Produto;
import br.fugii.eti.Fast_and_Furious_Food.domain.model.StatusPedido;
import br.fugii.eti.Fast_and_Furious_Food.repository.PedidoRepository;
import br.fugii.eti.Fast_and_Furious_Food.service.PedidoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    // Lista os pedidos
    @GetMapping("/pedidos")
    public List<Pedido> lista() {
        return pedidoService.listar();
    }

    //adicionar os pedidos
    @PostMapping("/pedidos")
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido adicionar(@RequestBody Pedido pedido) {
        return pedidoService.salvar(pedido);
    }

    @PutMapping("/pedidos/{id}")
    public ResponseEntity<Pedido> atualizar(@Valid @PathVariable Long id, @RequestBody StatusPedido statusPedido) {

        Optional<Pedido> pedidoAtualOpt = pedidoService.atualizarStatus(id, statusPedido);

        if (pedidoAtualOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoAtualOpt.get());
    }

    @DeleteMapping("/pedidos/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        if (!pedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        pedidoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
