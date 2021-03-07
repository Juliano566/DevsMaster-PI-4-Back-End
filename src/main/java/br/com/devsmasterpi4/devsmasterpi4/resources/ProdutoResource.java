/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devsmasterpi4.devsmasterpi4.resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import javax.persistence.criteria.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.devsmasterpi4.devsmasterpi4.dominio.Categoria;
import br.com.devsmasterpi4.devsmasterpi4.dominio.Produto;
import br.com.devsmasterpi4.devsmasterpi4.repositories.CategoriaRepository;
import br.com.devsmasterpi4.devsmasterpi4.repositories.ProdutoRepository;

/**
 *
 * @author nails
 */

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	private static String caminhoImagens = "\\DevsMaster-PI-4-Back-End\\src\\main\\resources\\static\\imagens";
    
 
	@Autowired
	private ProdutoRepository produtoRepository;
		
	//Cadastra produto
	@PostMapping
	public @ResponseBody Produto novoProduto(Produto produto/*, @RequestParam MultipartFile arquivo*/) {
		produtoRepository.save(produto);	
		
		/*try {
			if (!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = (Path) Paths.get(caminhoImagens + String.valueOf(produto.getId()) + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
				produto.setFoto1(String.valueOf(produto.getId()) + arquivo.getOriginalFilename());
				produtoRepository.save(produto);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/
			
		return produto;
	}
	
	
	//Listar produtos
	@GetMapping
	public Iterable<Produto> obtProduto() {
		return produtoRepository.findAll();
	}
	
	//LIstar produto por nome
	@GetMapping(path="/nome/{parteNome}")
	public Iterable<Produto>obtProdutoPorNome(@PathVariable String parteNome){
		return produtoRepository.findByNomeContaining(parteNome);
	}
	
	
	//Listar produtos por pagina
	@GetMapping(path="/pagina/{numeroPagina}/{qtdePagina}")
	public Iterable<Produto> obterProdutoPaginada(
			@PathVariable int numeroPagina, @PathVariable int qtdePagina) {
		if(qtdePagina >= 5) qtdePagina = 5;
		Pageable page = PageRequest.of(numeroPagina, qtdePagina);
		return produtoRepository.findAll(page);
	}
	
	//Buscar produto por ID
	@GetMapping(path="/{id}")
	public Optional<Produto> obterProdutoPorId(@PathVariable int id) {
		return produtoRepository.findById(id);
		
	}
	
	//Alterar produto
	@PutMapping
	public Produto alterarProduto(Produto produto) {
		produtoRepository.save(produto);
		return produto;
	}
	
	//deletar protudo
	@DeleteMapping(path = "/delete/{id}")
	public void excluirProduto(@PathVariable int id) {
		 produtoRepository.deleteById(id);
	}


}


	
	
	
	
	
	
	
	
	
	
	
	 
