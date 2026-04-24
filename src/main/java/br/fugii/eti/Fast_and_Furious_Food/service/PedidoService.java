package br.fugii.eti.Fast_and_Furious_Food.service;

import br.fugii.eti.Fast_and_Furious_Food.domain.model.Pedido;
import br.fugii.eti.Fast_and_Furious_Food.domain.model.Produto;
import br.fugii.eti.Fast_and_Furious_Food.repository.PedidoRepository;
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
     
     public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
    
     public void excluir(Long pedidoId) {
        pedidoRepository.deleteById(pedidoId);
    }
     
}
