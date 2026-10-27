CREATE DATABASE IF NOT EXISTS sistema_estoque
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE sistema_estoque;

CREATE TABLE IF NOT EXISTS usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(120) NOT NULL UNIQUE,
  senha VARCHAR(255) NOT NULL,
  nivel_acesso VARCHAR(30) NOT NULL DEFAULT 'OPERADOR'
);

CREATE TABLE IF NOT EXISTS fornecedor (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(120) NOT NULL,
  contato VARCHAR(100),
  telefone VARCHAR(30),
  email VARCHAR(120),
  endereco VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS categoria (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL UNIQUE,
  descricao VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS produto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(120) NOT NULL,
  descricao VARCHAR(255),
  preco_custo DECIMAL(10,2) NOT NULL DEFAULT 0,
  preco_venda DECIMAL(10,2) NOT NULL DEFAULT 0,
  estoque_minimo INT NOT NULL DEFAULT 0,
  estoque_atual INT NOT NULL DEFAULT 0,
  categoria_id INT NOT NULL,
  fornecedor_id INT NOT NULL,
  CONSTRAINT fk_produto_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id),
  CONSTRAINT fk_produto_fornecedor FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(id)
);

CREATE TABLE IF NOT EXISTS movimentacao (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tipo ENUM('ENTRADA','SAIDA') NOT NULL,
  data_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  quantidade INT NOT NULL,
  observacao VARCHAR(255),
  usuario_id INT NOT NULL,
  produto_id INT NOT NULL,
  CONSTRAINT fk_mov_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
  CONSTRAINT fk_mov_produto FOREIGN KEY (produto_id) REFERENCES produto(id)
);

INSERT IGNORE INTO usuario(nome,email,senha,nivel_acesso)
VALUES ('Administrador','admin','admin123','ADMIN');
INSERT IGNORE INTO categoria(nome,descricao) VALUES
('Informática','Equipamentos e acessórios de informática'),
('Acessórios','Acessórios diversos');
INSERT INTO fornecedor(nome,contato,telefone,email,endereco)
SELECT 'Fornecedor Exemplo','Comercial','(47) 0000-0000','fornecedor@exemplo.com','São Francisco do Sul'
WHERE NOT EXISTS (SELECT 1 FROM fornecedor WHERE nome='Fornecedor Exemplo');
