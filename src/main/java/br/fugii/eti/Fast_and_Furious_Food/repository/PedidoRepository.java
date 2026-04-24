package br.fugii.eti.Fast_and_Furious_Food.repository;

import br.fugii.eti.Fast_and_Furious_Food.domain.model.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
    List<Pedido> findAll();
    List<Pedido> findByCliente(String cliente);
    
}
