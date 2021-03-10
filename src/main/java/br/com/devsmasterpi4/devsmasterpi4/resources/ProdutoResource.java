/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devsmasterpi4.devsmasterpi4.resources;

import br.com.devsmasterpi4.devsmasterpi4.dominio.Produto;
import br.com.devsmasterpi4.devsmasterpi4.repositories.ProdutoRepository;
import br.com.devsmasterpi4.devsmasterpi4.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * @author nails
 */

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private FileService fileService;

    //Cadastra produto
    @PostMapping("/create")
    public ResponseEntity<String> NovoProduto(@RequestBody Produto produto) {
        produtoRepository.save(produto);
        return ResponseEntity.ok().body("Produto salvo");
    }

    //Envia imagem
    @PostMapping("/file/upload")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) {
        return fileService.saveFile(file);
    }

    //Baixa a imagem
    @GetMapping("/file/download")
    public ResponseEntity<?> downloadImage(@RequestBody String fileName, @RequestBody int id) {
        return fileService.getFile(fileName, id);
    }

    //Listar produtos
    @GetMapping("/list-all")
    public Iterable<Produto> obtProduto() {
        return produtoRepository.findAll();
    }

    //LIstar produto por nome
    @GetMapping("/nome")
    public Iterable<Produto> obtProdutoPorNome(@RequestBody String nome) {
        return produtoRepository.findByNomeContaining(nome);
    }

    //Listar produtos por pagina
    @GetMapping("/pagina")
    public Iterable<Produto> obterProdutoPaginada(@RequestBody int numeroPagina,
                                                  @RequestBody int qtdePagina) {
        Pageable page = PageRequest.of(qtdePagina >= 5 ? 5 : qtdePagina, qtdePagina);
        return produtoRepository.findAll(page);
    }

    //Buscar produto por ID
    @GetMapping("/id")
    public Optional<Produto> obterProdutoPorId(@RequestBody int id) {
        return produtoRepository.findById(id);

    }

    //Alterar produto
    @PutMapping("/update")
    public ResponseEntity<String> alterarProduto(@RequestBody Produto produto) {
        produtoRepository.save(produto);
        return ResponseEntity.ok().body("Update product ok");
    }

    //Atualiza a imagem
    @PutMapping("/update/file")
    public ResponseEntity<String> updateFileProduct(@RequestBody int id) {
        fileService.deleteDirectoryWithImages(id);
        return ResponseEntity.ok().body("Produto alterado");
    }

    //deletar protudo
    @DeleteMapping("/delete")
    public ResponseEntity<String> excluirProduto(@RequestBody int id) {
        produtoRepository.deleteById(id);
        return ResponseEntity.ok().body("successfully deleted");
    }


}


	
	
	
	
	
	
	
	
	
	
	
	 
