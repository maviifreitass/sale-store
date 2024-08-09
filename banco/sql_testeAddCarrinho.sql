INSERT INTO cliente (nome, email, senha) VALUES ('joao', 'joazinho@hotmail.com', '1234')

INSERT INTO produto(nome, marca, categoria, preco, estoque) VALUES ('camiseta gola V', 'nike', 'camisetas', 55.90, 100)

INSERT INTO produto(nome, marca, categoria, preco, estoque) VALUES ('calça slim', 'adidas', 'calças', 140.00, 100)

SELECT adicionar_produto_pedido(1,1,'lx',2);

SELECT adicionar_produto_pedido(1,2,'m',1);

SELECT adicionar_produto_pedido(1,1,'p',1);

SELECT adicionar_produto_pedido(1,2,'p',3);

DELETE FROM pedido_has_produto 
WHERE pedido_id = 1 AND produto_id = 2 AND tamanho_produto = 'p';