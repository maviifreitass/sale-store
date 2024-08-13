ALTER TABLE pedido
ADD COLUMN forma_pagamento VARCHAR(50) NULL;

ALTER TABLE pedido
ADD COLUMN endereco_id INTEGER;

ALTER TABLE pedido
ADD CONSTRAINT fk_endereco_cliente
FOREIGN KEY (endereco_id) REFERENCES enderecoCliente(id);