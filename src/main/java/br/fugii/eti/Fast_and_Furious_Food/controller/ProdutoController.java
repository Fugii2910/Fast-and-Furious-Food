package br.fugii.eti.Fast_and_Furious_Food.controller;

import br.fugii.eti.Fast_and_Furious_Food.domain.model.Produto;
import br.fugii.eti.Fast_and_Furious_Food.repository.ProdutoRepository;
import br.fugii.eti.Fast_and_Furious_Food.service.ProdutoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
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
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    // Lista os produtos
    @GetMapping("/produtos")
    public List<Produto> lista() {
        return produtoService.listar();
    }

    // Busca os produtos por id e nome
    @GetMapping("/produtos/{produtosID}")
    public ResponseEntity<Produto> buscar(@PathVariable Long produtoID) {

        Optional<Produto> produto = produtoRepository.findById(produtoID);

        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //adicionar os produtos
    @PostMapping("/produtos")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto adicionar(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    @PutMapping("/produtos/{produtosID}")
    public ResponseEntity<Produto> atualizar(@Valid @PathVariable Long produtoID, @RequestBody Produto produto) {

        if (!produtoRepository.existsById(produtoID)) {
            return ResponseEntity.notFound().build();
        }
        produto.setId(produtoID);
        produto = produtoService.salvar(produto);
        return ResponseEntity.ok(produto);
    }
    
     @DeleteMapping("/produtos/{produtosID}")
    public ResponseEntity<Void> excluir(@PathVariable Long produtoID) {

        if (!produtoRepository.existsById(produtoID)) {
            return ResponseEntity.notFound().build();
        }

        produtoService.excluir(produtoID);
        return ResponseEntity.noContent().build();
    }
    
}
