ALTER TABLE item_pedido
CHANGE COLUMN valor preco DECIMAL(10,2) NOT NULL,
CHANGE COLUMN quantidade quantidade INT(11) NOT NULL;