CREATE TABLE item_do_pedido (
  id bigint NOT NULL AUTO_INCREMENT,
  descricao varchar(255) DEFAULT NULL,
  quantidade int NOT NULL,
  pedido_id bigint NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (pedido_id) REFERENCES pedidos(id)
);