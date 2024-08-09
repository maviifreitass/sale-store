--COMPONENTE COMPRA DE PRODUTO

-- Função para atualizar o valor total após o INSERT do produto no pedido
CREATE OR REPLACE FUNCTION trigger_inserir_produto_no_pedido() RETURNS TRIGGER AS $$
BEGIN
    -- Atualiza o valor total do pedido
    UPDATE pedido
    SET valor_total = valor_total + (NEW.quantidade_produto * (SELECT preco FROM produto WHERE id = NEW.produto_id))
    WHERE id = NEW.pedido_id;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger que dispara após o INSERT
CREATE TRIGGER apos_inserir_produto_no_pedido
AFTER INSERT ON pedido_has_produto
FOR EACH ROW
EXECUTE FUNCTION trigger_inserir_produto_no_pedido();


-- Função para atualizar o valor total após o DELETE do produto do pedido
CREATE OR REPLACE FUNCTION trigger_excluir_produto_no_pedido() RETURNS TRIGGER AS $$
BEGIN
    UPDATE pedido
    SET valor_total = valor_total - (OLD.quantidade_produto * (SELECT preco FROM produto WHERE id = OLD.produto_id))
    WHERE id = OLD.pedido_id;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

-- Trigger que dispara após o DELETE
CREATE TRIGGER apos_excluir_produto_do_pedido
AFTER DELETE ON pedido_has_produto
FOR EACH ROW
EXECUTE FUNCTION trigger_excluir_produto_no_pedido();


--Função para atualizar o VALOR TOTAL após o UPDATE de quantidade de produto no pedido
CREATE OR REPLACE FUNCTION trigger_atualizar_quantidade_produto()
RETURNS TRIGGER AS $$
BEGIN
    -- Atualiza o valor total do pedido após atualização de quantidade
    UPDATE pedido
    SET valor_total = valor_total + ((NEW.quantidade_produto - OLD.quantidade_produto) * (SELECT preco FROM produto WHERE id = OLD.produto_id))
    WHERE id = OLD.pedido_id;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;



-- Trigger que dispara após o UPDATE
CREATE TRIGGER apos_atualizar_quantidade_produto
AFTER UPDATE ON pedido_has_produto
FOR EACH ROW
EXECUTE FUNCTION trigger_atualizar_quantidade_produto();