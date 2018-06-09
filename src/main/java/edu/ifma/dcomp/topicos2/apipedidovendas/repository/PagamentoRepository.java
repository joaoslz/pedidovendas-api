package edu.ifma.dcomp.topicos2.apipedidovendas.repository;

import edu.ifma.dcomp.topicos2.apipedidovendas.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {


}
