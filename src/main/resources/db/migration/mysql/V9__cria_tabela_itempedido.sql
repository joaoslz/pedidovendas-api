CREATE TABLE item_pedido (
  pedido_id INT NOT NULL,
  produto_id INT NOT NULL,
  valor DECIMAL(10,2) NULL,
  quantidade INT NULL,
  desconto INT NULL,
  PRIMARY KEY (pedido_id, produto_id),

  CONSTRAINT  fkItemPedido_pedidoId FOREIGN KEY (pedido_id)
    REFERENCES pedido(id),
  CONSTRAINT fkItemPedido_produtoId FOREIGN KEY (produto_id)
    REFERENCES produto(id)

)ENGINE=InnoDB;

INSERT INTO item_pedido (pedido_id, produto_id, valor, quantidade, desconto) VALUES (1, 1, 300.00, 3, 0.00);
INSERT INTO item_pedido (pedido_id, produto_id, valor, quantidade, desconto) VALUES (1, 2, 300.00, 1, 5.00);
INSERT INTO item_pedido (pedido_id, produto_id, valor, quantidade, desconto) VALUES (1, 3, 300.00, 2, 3.00);


INSERT INTO item_pedido (pedido_id, produto_id, valor, quantidade, desconto) VALUES (2, 1, 300.00, 2, 1.00);

