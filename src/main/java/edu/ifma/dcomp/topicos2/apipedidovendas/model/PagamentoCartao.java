package edu.ifma.dcomp.topicos2.apipedidovendas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


@Entity
@Table(name = "pagamento_cartao")
public final class PagamentoCartao extends Pagamento {

    @Column(name = "numero_parcelas")
    @Positive
    private Short numeroDeParcelas;

    public Short getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Short numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
