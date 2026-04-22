package br.fugii.eti.Fast_and_Furious_Food.service;

import br.fugii.eti.Fast_and_Furious_Food.domain.model.Produto;
import br.fugii.eti.Fast_and_Furious_Food.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto) {
        // 1. Buscamos produtos com o mesmo nome
        List<Produto> existentes = produtoRepository.findByNome(produto.getNome());

        // 2. Usamos um loop para verificar cada produto encontrado
        for (Produto existente : existentes) {
            // O erro deve acontecer apenas se:
        // 1. Encontrarmos um produto com o mesmo nome
        // 2. E esse produto tiver um ID diferente do que estamos tentando salvar
            if (!existente.getId().equals(produto.getId())) {
                throw new RuntimeException("O nome " + produto.getNome() + " já está em uso.");
            }
        }

        return produtoRepository.save(produto);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public void excluir(Long produtoId) {
        produtoRepository.deleteById(produtoId);
    }

}
