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
    //ISSO AQUI NÃO PODE SER UM JSON, NÃO POSSO ENVIAR ARQUIVO E JSON NO MESMO ENDPOINT, ao inves do objeto produto deve ser os seus atributos e depois tratar isso
    public ResponseEntity<String> NovoProduto(@RequestParam Produto produto, @RequestParam MultipartFile file) {
        //produtoRepository.save(produto);
        return fileService.saveFile(file);
    }

    @GetMapping("/file/download")
    public ResponseEntity<?> downloadImage(@RequestParam String fileName, @RequestParam int id) {
        return fileService.getFile(fileName, id);
    }

    //Listar produtos
    @GetMapping("/list-all")
    public Iterable<Produto> obtProduto() {
        return produtoRepository.findAll();
    }

    //LIstar produto por nome
    @GetMapping("/nome")
    public Iterable<Produto> obtProdutoPorNome(@RequestParam String nome) {
        return produtoRepository.findByNomeContaining(nome);
    }


    //Listar produtos por pagina
    @GetMapping("/pagina")
    public Iterable<Produto> obterProdutoPaginada(@RequestParam int numeroPagina,
                                                  @RequestParam int qtdePagina) {
        Pageable page = PageRequest.of(qtdePagina >= 5 ? 5 : qtdePagina, qtdePagina);
        return produtoRepository.findAll(page);
    }

    //Buscar produto por ID
    @GetMapping("/id")
    public Optional<Produto> obterProdutoPorId(@RequestParam int id) {
        return produtoRepository.findById(id);

    }

    //Alterar produto
    @PutMapping("/update")                      //TODO ASSIM COMO O METODO DE INSERIR NÃO PODEMOS DEIXAR UM OBJETO AQUI, NÃO POSSO ENVIAR JSON E IMAGEM
    public ResponseEntity<String> alterarProduto(Produto produto, @RequestParam String fileName,
                                                 @RequestParam MultipartFile file,
                                                 @RequestParam int id) {
        produtoRepository.save(produto);
        fileService.deleteDirectoryWithImages(id);
        fileService.saveFile(file);
        return ResponseEntity.ok().body("Update product ok");
    }

    //deletar protudo
    @DeleteMapping("/delete")
    public ResponseEntity<String> excluirProduto(@RequestParam int id) {
        produtoRepository.deleteById(id);
        return ResponseEntity.ok().body("successfully deleted");
    }


}


	
	
	
	
	
	
	
	
	
	
	
	 
