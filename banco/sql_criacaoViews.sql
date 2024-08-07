CREATE VIEW PedidosComProdutosEClientes AS
SELECT p.id AS pedidoId, p.data, p.valor_total, 
       c.id AS clienteId, c.nome AS clienteNome, c.email, 
       pr.id AS produtoId, pr.nome AS produtoNome, pr.marca, 
       pp.quantidadeProduto, pp.tamanhoProduto
FROM Pedido p
JOIN Cliente c ON p.clienteId = c.id
JOIN Pedido_has_Produto pp ON p.id = pp.idPedido
JOIN Produto pr ON pp.idProduto = pr.id;


CREATE VIEW ClienteComEndereco AS
SELECT c.id AS clienteId, c.nome, c.email, c.senha, 
       e.rua, e.bairro, e.cep, e.cidade, e.estado
FROM Cliente c
JOIN Cliente_has_Endereco che ON c.id = che.clienteId
JOIN EnderecoCliente e ON che.enderecoId = e.id;
