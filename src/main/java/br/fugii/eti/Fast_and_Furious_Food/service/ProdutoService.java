package br.fugii.eti.Fast_and_Furious_Food.service;

import br.fugii.eti.Fast_and_Furious_Food.domain.model.Produto;
import br.fugii.eti.Fast_and_Furious_Food.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProdutoService {

     @Autowired
    private ProdutoRepository produtoRepository;
    
    public List<Produto> listar(){
        return produtoRepository.findAll();
    }
    
    public Produto buscarOuFalhar(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, 
                                "Produto não encontrado"));
    }
    
    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }
    
    public Produto atualizar(Long id, Produto produto) {
        Produto existente = buscarOuFalhar(id);

        existente.setNome(produto.getNome());
        existente.setPreco(produto.getPreco());
        existente.setIngredientes(produto.getIngredientes());
        existente.setCategoria(produto.getCategoria());

        return produtoRepository.save(existente);
    }
    
    public void excluir(Long id) {
        Produto produto = buscarOuFalhar(id);
        produtoRepository.delete(produto);
    }
    
    public List<Produto> buscarPorCategoria(String categoria) {
        return produtoRepository.findByCategoriaIgnoreCase(categoria);
    }
}
