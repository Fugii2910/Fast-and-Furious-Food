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
        return produtoRepository.save(produto);
    }
    
    public List<Produto> listar(){
        return produtoRepository.findAll();
    }
    
    public void excluir(Long produtoId) {
        produtoRepository.deleteById(produtoId);
    }
    
}
