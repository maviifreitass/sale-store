CREATE OR REPLACE FUNCTION adicionar_produto_pedido(
    p_id_cliente INT,
    p_id_produto INT,
    p_tamanho VARCHAR,
    p_quantidade INT
)
RETURNS VOID AS $$
DECLARE
    v_id_pedido INT;
    v_quantidade_atual INT;
	p_preco DECIMAL;
BEGIN
	--Guarda o preco do produto
	SELECT preco INTO p_preco
	FROM produto
	WHERE id = p_id_produto
	LIMIT 1;
	
    -- Verificar se já existe um pedido em aberto para o cliente
    SELECT id INTO v_id_pedido
    FROM pedido
    WHERE cliente_id = p_id_cliente AND status = 'aberto'
    LIMIT 1;

    -- Se existir um pedido em aberto
    IF v_id_pedido IS NOT NULL THEN
        -- Verificar se existe o produto do mesmo tamanho nesse pedido
        SELECT quantidade_produto INTO v_quantidade_atual
        FROM pedido_has_produto
        WHERE pedido_id = v_id_pedido AND produto_id = p_id_produto AND tamanho_produto = p_tamanho
        LIMIT 1;

        -- Se o produto do mesmo tamanho já existe no pedido
        IF v_quantidade_atual IS NOT NULL THEN
            -- Fazer um update na linha existente com a quantidade antiga somada com a nova
            UPDATE pedido_has_produto
            SET quantidade_produto = v_quantidade_atual + p_quantidade
            WHERE pedido_id = v_id_pedido AND produto_id = p_id_produto AND tamanho_produto = p_tamanho;
        ELSE
            -- Fazer um insert em pedido_has_produto com a quantidade e tamanho novos
            INSERT INTO pedido_has_produto (pedido_id, produto_id, tamanho_produto, quantidade_produto)
            VALUES (v_id_pedido, p_id_produto, p_tamanho, p_quantidade);
        END IF;
    ELSE
        -- Se não existir um pedido em aberto, abrir um novo pedido
        INSERT INTO pedido (cliente_id, data)
        VALUES (p_id_cliente, NOW())
        RETURNING id INTO v_id_pedido;

        -- Adicionar o produto ao novo pedido
        INSERT INTO pedido_has_produto (pedido_id, produto_id, tamanho_produto, quantidade_produto)
        VALUES (v_id_pedido, p_id_produto, p_tamanho, p_quantidade);
    END IF;
END;
$$ LANGUAGE plpgsql;
