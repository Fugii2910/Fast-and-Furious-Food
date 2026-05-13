
package br.fugii.eti.Fast_and_Furious_Food.repository;

import br.fugii.eti.Fast_and_Furious_Food.domain.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ItemPedidoRepository {

    @Repository
    public interface ProdutoRepository extends JpaRepository<ItemPedido, Long> {
    }

}