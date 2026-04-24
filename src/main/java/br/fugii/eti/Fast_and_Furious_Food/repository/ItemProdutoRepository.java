/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.fugii.eti.Fast_and_Furious_Food.repository;

import br.fugii.eti.Fast_and_Furious_Food.domain.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sesi3dia
 */
public interface ItemProdutoRepository {

    @Repository
    public interface ProdutoRepository extends JpaRepository<ItemPedido, Long> {
    }

}