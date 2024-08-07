CREATE TABLE Cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    senha VARCHAR(45) NOT NULL
);

CREATE TABLE EnderecoCliente (
    id SERIAL PRIMARY KEY,
    rua VARCHAR(45) NOT NULL,
    bairro VARCHAR(45) NOT NULL,
    cep VARCHAR(45) NOT NULL,
    cidade VARCHAR(45) NOT NULL,
    estado VARCHAR(45) NOT NULL
);

CREATE TABLE Cliente_has_Endereco (
    cliente_id INT NOT NULL,
    endereco_id INT NOT NULL,
    PRIMARY KEY (cliente_id, endereco_id),
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id),
    FOREIGN KEY (endereco_id) REFERENCES EnderecoCliente(id)
);

CREATE TABLE Produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    marca VARCHAR(45) NOT NULL,
    categoria VARCHAR(45) NOT NULL,
    preco DECIMAL(11,2) NOT NULL,
    estoque INT NOT NULL
);

CREATE TABLE Pedido (
    id SERIAL PRIMARY KEY,
    data DATE NOT NULL,
    valor_total DECIMAL(11,2) NOT NULL DEFAULT 0.00,
    cliente_id INT,
    status VARCHAR(20) NOT NULL DEFAULT 'aberto',
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
);

CREATE TABLE Pedido_has_Produto (
    pedido_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade_produto INT NOT NULL,
    tamanho_produto VARCHAR(45) NOT NULL,
    PRIMARY KEY (pedido_id, produto_id, tamanho_produto),
    FOREIGN KEY (pedido_id) REFERENCES Pedido(id),
    FOREIGN KEY (produto_id) REFERENCES Produto(id)
);
