package br.fugii.eti.Fast_and_Furious_Food.service;

import br.fugii.eti.Fast_and_Furious_Food.domain.model.Pedido;
import br.fugii.eti.Fast_and_Furious_Food.domain.model.Produto;
import br.fugii.eti.Fast_and_Furious_Food.domain.model.StatusPedido;
import br.fugii.eti.Fast_and_Furious_Food.repository.PedidoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    // Busca os produtos por id e nome
    @GetMapping("/pedidos/{pedidosID}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long pedidosID) {

        Optional<Pedido> pedido = pedidoRepository.findById(pedidosID);

        if (pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Optional<Pedido> atualizarStatus(Long id, StatusPedido novoStatus) {

        Optional<Pedido> optPedido = pedidoRepository.findById(id);

        if (optPedido.isEmpty()) {
            return optPedido;
        }

        Pedido pedidoAntigo = optPedido.get();

        if (novoStatus == StatusPedido.PRONTO && pedidoAntigo.getStatusPedido() == StatusPedido.ABERTO) {
            pedidoAntigo.setStatusPedido(StatusPedido.PRONTO);
            pedidoAntigo.setDtPronto(LocalDateTime.now());
        } else if (novoStatus == StatusPedido.ENTREGUE && pedidoAntigo.getStatusPedido() == StatusPedido.PRONTO) {
            pedidoAntigo.setStatusPedido(StatusPedido.ENTREGUE);
            pedidoAntigo.setDtEntregue(LocalDateTime.now());
        } else {
            throw new RuntimeException("Status " + novoStatus + "não pode ser aplicado em " + pedidoAntigo);
        }

        optPedido = Optional.of(pedidoRepository.save(pedidoAntigo));

        return optPedido;

    }

    public void excluir(Long pedidoId) {
        pedidoRepository.deleteById(pedidoId);
    }
    
    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
}
