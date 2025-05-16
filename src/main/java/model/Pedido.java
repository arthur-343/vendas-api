package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente;

    @ElementCollection
    private Map<Class<? extends Produto>, List<Produto>> produtosPorTipo = new HashMap<>();

    public void adicionarProduto(Produto produto) {
        produtosPorTipo.computeIfAbsent(produto.getClass(), k -> new ArrayList<>()).add(produto);
    }

    public List<Produto> getProdutosPorTipo(Class<? extends Produto> tipo) {
        return produtosPorTipo.getOrDefault(tipo, Collections.emptyList());
    }
}
