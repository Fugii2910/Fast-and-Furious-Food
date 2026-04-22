package br.fugii.eti.Fast_and_Furious_Food.controller;

import br.fugii.eti.Fast_and_Furious_Food.domain.model.Produto;
import br.fugii.eti.Fast_and_Furious_Food.repository.ProdutoRepository;
import br.fugii.eti.Fast_and_Furious_Food.service.ProdutoService;
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
    public ResponseEntity<Produto> buscar(@PathVariable Long produtosID) {

        Optional<Produto> produto = produtoRepository.findById(produtosID);

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

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        // 1. Buscamos o produto que já existe no banco
        Optional<Produto> produtoAtualOpt = produtoRepository.findById(id);

        if (produtoAtualOpt.isPresent()) {
            Produto produtoAtual = produtoAtualOpt.get();

            // 2. Copiamos as propriedades que vieram no JSON para o objeto do banco
            // O terceiro parâmetro "id" serve para NÃO sobrescrever o ID original
            BeanUtils.copyProperties(produto, produtoAtual, "id");

            // 3. Salvamos através do service para rodar sua validação de nome
            Produto salvo = produtoService.salvar(produtoAtual);
            return ResponseEntity.ok(salvo);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
