CREATE TABLE IF NOT EXISTS `categorias` (
  `id_categoria` INT(11) NOT NULL AUTO_INCREMENT,
  `categoria` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_categoria`));
  
CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 3 INCREMENT BY 1;
  
CREATE TABLE IF NOT EXISTS `clientes` (
  `id_cliente` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `senha` VARCHAR(8) NULL DEFAULT NULL,
  `rua` VARCHAR(100) NULL DEFAULT NULL,
  `cidade` VARCHAR(45) NULL DEFAULT NULL,
  `bairro` VARCHAR(45) NULL DEFAULT NULL,
  `cep` VARCHAR(45) NULL DEFAULT NULL,
  `estado` VARCHAR(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id_cliente`));
  
CREATE TABLE IF NOT EXISTS `pedidos` (
  `id_pedido` INT(11) NOT NULL AUTO_INCREMENT,
  `data` DATETIME NULL DEFAULT NULL,
  `id_cliente` INT(11) NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `sessao` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_pedido`),
  CONSTRAINT `id_cliente`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `clientes` (`id_cliente`));

CREATE TABLE IF NOT EXISTS `produtos` (
  `id_produto` INT(11) NOT NULL AUTO_INCREMENT,
  `id_categoria` INT(11) NULL DEFAULT NULL,
  `produto` VARCHAR(45) NULL DEFAULT NULL,
  `preco` DOUBLE NULL DEFAULT NULL,
  `quantidade` INT(11) NULL DEFAULT NULL,
  `descricao` VARCHAR(45) NULL DEFAULT NULL,
  `foto` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_produto`),
  CONSTRAINT `id_categoria`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `categorias` (`id_categoria`));
    
CREATE TABLE IF NOT EXISTS `pedido_itens` (
  `id_item` INT(11) NOT NULL AUTO_INCREMENT,
  `id_pedido` INT(11) NULL DEFAULT NULL,
  `id_produto` INT(11) NULL DEFAULT NULL,
  `quantidade` INT(11) NULL DEFAULT NULL,
  `valor` DOUBLE NULL DEFAULT NULL,
  `subtotal` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id_item`),
  CONSTRAINT `id_pedido`
    FOREIGN KEY (`id_pedido`)
    REFERENCES `pedidos` (`id_pedido`),
  CONSTRAINT `id_produto`
    FOREIGN KEY (`id_produto`)
    REFERENCES `produtos` (`id_produto`));
    
INSERT INTO `categorias` (`categoria`) VALUES ('previdencia');
INSERT INTO `categorias` (`categoria`) VALUES ('fundo de investimento');
INSERT INTO `categorias` (`categoria`) VALUES ('fundo imobiliario');
INSERT INTO `categorias` (`categoria`) VALUES ('acao');
INSERT INTO `categorias` (`categoria`) VALUES ('tesouro direto');
INSERT INTO `categorias` (`categoria`) VALUES ('poupanca');

INSERT INTO `produtos` (`id_categoria`, `produto`, `preco`, `quantidade`, `descricao`) VALUES ('4', 'BBSE3', '34', '100', 'Banco do Brasil Seguridade');
INSERT INTO `produtos` (`id_categoria`, `produto`, `preco`, `quantidade`, `descricao`) VALUES ('4', 'BBAS3', '49.42', '100', 'Banco do Brasil');