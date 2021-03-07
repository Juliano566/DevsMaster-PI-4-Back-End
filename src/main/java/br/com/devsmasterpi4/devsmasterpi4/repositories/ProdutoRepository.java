package br.com.devsmasterpi4.devsmasterpi4.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.devsmasterpi4.devsmasterpi4.dominio.Produto;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Integer> {

	public Iterable<Produto> findByNomeContaining(String parteNome);
	
}
