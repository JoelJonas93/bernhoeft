-- Inserir categorias
INSERT INTO categoria (nome, situacao) VALUES ('Eletrônicos', 'Ativo');
INSERT INTO categoria (nome, situacao) VALUES ('Roupas', 'Ativo');

-- Inserir produtos para a categoria 'Eletrônicos'
INSERT INTO produto (nome, descricao, preco, situacao, categoria_id)
VALUES
    ('Smartphone', 'Um telefone inteligente', 999.99, 'Inativo', 1),
    ('Tablet', 'Um dispositivo tablet', 399.99, 'Ativo', 1),
    ('Notebook', 'Um computador portátil', 899.99, 'Ativo', 1),
    ('Fone de Ouvido', 'Fones de ouvido sem fio', 149.99, 'Inativo', 1),
    ('TV 4K', 'Uma televisão de alta definição', 799.99, 'Ativo', 1),
    ('Câmera DSLR', 'Câmera digital de lentes intercambiáveis', 599.99, 'Ativo', 1),
    ('Console de Jogos', 'Um console de jogos de última geração', 349.99, 'Inativo', 1),
    ('Impressora Multifuncional', 'Impressora que também faz cópias e digitalizações', 199.99, 'Ativo', 1),
    ('Smartwatch', 'Relógio inteligente com recursos avançados', 249.99, 'Ativo', 1),
    ('Caixa de Som Bluetooth', 'Caixa de som portátil com conectividade Bluetooth', 79.99, 'Ativo', 1);

-- Inserir produtos para a categoria 'Roupas'
INSERT INTO produto (nome, descricao, preco, situacao, categoria_id)
VALUES
    ('Camiseta', 'Camiseta de algodão', 29.99, 'Ativo', 2),
    ('Calça Jeans', 'Calça jeans de corte reto', 49.99, 'Inativo', 2),
    ('Vestido', 'Vestido elegante', 79.99, 'Ativo', 2),
    ('Sapatos', 'Sapatos de couro', 69.99, 'Inativo', 2),
    ('Jaqueta de Couro', 'Jaqueta de couro genuíno', 129.99, 'Ativo', 2),
    ('Bermuda', 'Bermuda casual', 39.99, 'Ativo', 2),
    ('Blusa de Tricô', 'Blusa de tricô quente', 59.99, 'Inativo', 2),
    ('Tênis Esportivo', 'Tênis confortável para esportes', 89.99, 'Ativo', 2),
    ('Saia', 'Saia curta', 34.99, 'Ativo', 2),
    ('Moletom', 'Moletom com capuz', 44.99, 'Ativo', 2);
