CREATE TABLE IF NOT EXISTS produto(
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  preco DECIMAL(10,2) NULL,
  PRIMARY KEY (id)
) ENGINE = InnoDB;

INSERT INTO produto (nome, preco) VALUES ('Computador All-in-one Positivo', '2199.90');
INSERT INTO produto (nome, preco) VALUES ('Notebook Samsung Core i7', '2099.50');
INSERT INTO produto (nome, preco) VALUES ('HD SSD 500gb', '849.00');
