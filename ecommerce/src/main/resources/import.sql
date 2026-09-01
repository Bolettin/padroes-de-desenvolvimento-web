-- CATEGORIA

INSERT INTO categoria (nome, descricao)
VALUES ('Eletrônicos', 'Produtos eletrônicos e acessórios');

INSERT INTO categoria (nome, descricao)
VALUES ('Informática', 'Computadores, periféricos e acessórios');

INSERT INTO categoria (nome, descricao)
VALUES ('Celulares', 'Smartphones e acessórios para celulares');

INSERT INTO categoria (nome, descricao)
VALUES ('Casa e Cozinha', 'Produtos para casa e cozinha');

INSERT INTO categoria (nome, descricao)
VALUES ('Esportes', 'Artigos esportivos e acessórios');


-- CLIENTE

INSERT INTO cliente (nome, email, telefone)
VALUES ('João Silva', 'joao.silva@email.com', '(11) 99999-1111');

INSERT INTO cliente (nome, email, telefone)
VALUES ('Maria Oliveira', 'maria.oliveira@email.com', '(11) 99999-2222');

INSERT INTO cliente (nome, email, telefone)
VALUES ('Carlos Santos', 'carlos.santos@email.com', '(11) 99999-3333');

INSERT INTO cliente (nome, email, telefone)
VALUES ('Ana Souza', 'ana.souza@email.com', '(11) 99999-4444');

INSERT INTO cliente (nome, email, telefone)
VALUES ('Pedro Costa', 'pedro.costa@email.com', '(11) 99999-5555');


-- PRODUTO

INSERT INTO produto (nome, descricao, estoque, preco, categoria_id)
VALUES ('Notebook Dell Inspiron', 'Notebook com processador Intel Core i5', 10, 3599.90, 2);

INSERT INTO produto (nome, descricao, estoque, preco, categoria_id)
VALUES ('Smartphone Samsung Galaxy', 'Smartphone com 128GB de armazenamento', 20, 1899.90, 3);

INSERT INTO produto (nome, descricao, estoque, preco, categoria_id)
VALUES ('Fone Bluetooth JBL', 'Fone de ouvido sem fio com cancelamento de ruído', 30, 299.90, 1);

INSERT INTO produto (nome, descricao, estoque, preco, categoria_id)
VALUES ('Air Fryer Mondial', 'Fritadeira elétrica sem óleo de 4 litros', 15, 399.90, 4);

INSERT INTO produto (nome, descricao, estoque, preco, categoria_id)
VALUES ('Bola de Futebol Nike', 'Bola oficial para futebol de campo', 25, 159.90, 5);


-- PEDIDO

INSERT INTO pedido (data, status, valor_total, cliente_id)
VALUES ('2026-08-25 10:30:00', 'PAGO', 3899.80, 1);

INSERT INTO pedido (data, status, valor_total, cliente_id)
VALUES ('2026-08-26 14:15:00', 'PAGO', 1899.90, 2);

INSERT INTO pedido (data, status, valor_total, cliente_id)
VALUES ('2026-08-27 09:45:00', 'PROCESSANDO', 599.80, 3);

INSERT INTO pedido (data, status, valor_total, cliente_id)
VALUES ('2026-08-28 16:20:00', 'ENVIADO', 399.90, 4);

INSERT INTO pedido (data, status, valor_total, cliente_id)
VALUES ('2026-08-29 11:10:00', 'PAGO', 159.90, 5);


-- ITEM_PEDIDO

INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id)
VALUES (1, 3599.90, 1, 1);

INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id)
VALUES (1, 299.90, 1, 3);

INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id)
VALUES (1, 1899.90, 2, 2);

INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id)
VALUES (2, 299.90, 3, 3);

INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id)
VALUES (1, 399.90, 4, 4);

INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id)
VALUES (1, 159.90, 5, 5);


-- PAGAMENTO

INSERT INTO pagamento (valor, data, status, tipo, pedido_id)
VALUES (3899.80, '2026-08-25 10:35:00', 'APROVADO', 'CARTAO_CREDITO', 1);

INSERT INTO pagamento (valor, data, status, tipo, pedido_id)
VALUES (1899.90, '2026-08-26 14:20:00', 'APROVADO', 'PIX', 2);

INSERT INTO pagamento (valor, data, status, tipo, pedido_id)
VALUES (599.80, '2026-08-27 09:50:00', 'APROVADO', 'CARTAO_DEBITO', 3);

INSERT INTO pagamento (valor, data, status, tipo, pedido_id)
VALUES (399.90, '2026-08-28 16:25:00', 'APROVADO', 'PIX', 4);

INSERT INTO pagamento (valor, data, status, tipo, pedido_id)
VALUES (159.90, '2026-08-29 11:15:00', 'APROVADO', 'CARTAO_CREDITO', 5);
