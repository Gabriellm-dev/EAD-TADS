-- Criar banco de dados
CREATE DATABASE db_brasileirao;

-- Usar o banco de dados
USE db_brasileirao;

-- Comandos para criarem as tabelas do banco de dados e suas respectivas chaves
CREATE TABLE `partidas`(
    `partida_id` BIGINT NOT NULL PRIMARY KEY,
    `rodada` BIGINT NOT NULL,
    `data` DATE NOT NULL,
    `hora` DATETIME NOT NULL,
    `mandante` TEXT NOT NULL,
    `visitante` TEXT NOT NULL,
    `tecnico_mandante` TEXT,
    `tecnico_visitante` TEXT,
    `vencedor` TEXT,
    `arena` TEXT NOT NULL,
    `mandante_placar` BIGINT NOT NULL,
    `visitante_placar` BIGINT NOT NULL,
    `mandante_estado` TEXT NOT NULL,
    `visitante_estado` TEXT NOT NULL
);
CREATE TABLE `gols`(
    `gol_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `partida_id` BIGINT NOT NULL,
    `clube` TEXT NOT NULL,
    `atleta` TEXT NOT NULL,
    `minuto` BIGINT NOT NULL,
    `tipo_de_gol` TEXT
);
CREATE TABLE `estatisticas`(
    `estatistica_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `partida_id` BIGINT NOT NULL,
    `clube` TEXT NOT NULL,
    `chutes` BIGINT,
    `posse_de_bola` DOUBLE,
    `passes` BIGINT,
    `precisao_passes` DOUBLE,
    `faltas` BIGINT,
    `impedimentos` BIGINT,
    `escanteios` BIGINT
);
CREATE TABLE `cartoes`(
    `cartao_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `partida_id` BIGINT NOT NULL,
    `clube` TEXT NOT NULL,
    `cartao` TEXT NOT NULL,
    `atleta` TEXT NOT NULL,
    `posicao` TEXT,
    `minuto` BIGINT NOT NULL
);
ALTER TABLE
    `cartoes` ADD CONSTRAINT `cartoes_partida_id_foreign` FOREIGN KEY(`partida_id`) REFERENCES `partidas`(`partida_id`);
ALTER TABLE
    `estatisticas` ADD CONSTRAINT `estatisticas_partida_id_foreign` FOREIGN KEY(`partida_id`) REFERENCES `partidas`(`partida_id`);
ALTER TABLE
    `gols` ADD CONSTRAINT `gols_partida_id_foreign` FOREIGN KEY(`partida_id`) REFERENCES `partidas`(`partida_id`);
    


ALTER TABLE `partidas` MODIFY `hora` TIME;
ALTER TABLE `cartoes` MODIFY `minuto` TEXT;
ALTER TABLE `gols` MODIFY `minuto` TEXT;
ALTER TABLE `estatisticas` MODIFY `precisao_passes` TEXT;
ALTER TABLE `estatisticas` MODIFY `posse_de_bola` TEXT;

 -- Procedimento para atualizações fazer o update
 
SET SQL_SAFE_UPDATES = 0;
-- Execute sua atualização aqui

-- Defina o valor padrão (0) para 'minuto' onde for nulo ou em branco
UPDATE cartoes
SET minuto = 0
WHERE minuto IS NULL OR minuto = '';

-- Defina valores padrão para colunas que estão ausentes (nulas ou em branco) em estatísticas
UPDATE estatisticas
SET chutes = IFNULL(chutes, 0),
    posse_de_bola = IFNULL(posse_de_bola, 0),
    passes = IFNULL(passes, 0),
    precisao_passes = IFNULL(precisao_passes, 0),
    faltas = IFNULL(faltas, 0),
    impedimentos = IFNULL(impedimentos, 0),
    escanteios = IFNULL(escanteios, 0);

-- Defina um valor padrão ("Desconhecido") para 'tipo_de_gol' onde estiver nulo ou em branco
UPDATE gols
SET tipo_de_gol = IFNULL(tipo_de_gol, 'Desconhecido')
WHERE tipo_de_gol IS NULL OR tipo_de_gol = '';

UPDATE estatisticas
SET precisao_passes = IFNULL(precisao_passes, 0);

SET SQL_SAFE_UPDATES = 1; -- Restaure o modo de atualização segura

 
-- mostrar o caminho para colocar os arquivos csv 
SHOW VARIABLES LIKE "Secure_file_priv";

-- após os arquivos csv estarem na pasta de UPLOAD esse comando irar importar os dados para as tabelas
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\partidas.csv' 
INTO TABLE partidas 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\cartoes.csv' 
INTO TABLE cartoes 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(partida_id, clube, cartao, atleta, posicao, minuto);

LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\gols.csv' 
INTO TABLE gols 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(partida_id,clube,atleta,minuto,tipo_de_gol);

LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\estatisticas.csv'
INTO TABLE estatisticas
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(partida_id, clube, chutes, posse_de_bola, passes, precisao_passes, faltas, impedimentos, escanteios);

-- Quantas partidas cada clube venceu
SELECT vencedor, COUNT(*) AS vitorias
FROM partidas
WHERE vencedor IS NOT NULL
GROUP BY vencedor;

-- Quais times foram os mais vencedores
SELECT vencedor, COUNT(*) AS vitorias
FROM partidas
WHERE vencedor IS NOT NULL
GROUP BY vencedor
ORDER BY vitorias DESC;

-- Quais times tiveram menos vitorias
SELECT vencedor, COUNT(*) AS vitorias
FROM partidas
WHERE vencedor IS NOT NULL
GROUP BY vencedor
ORDER BY vitorias ASC;

-- Quantidade de Cartoes Vermelhos e Amarelos
SELECT
    cartao,
    COUNT(*) AS quantidade
FROM cartoes
WHERE cartao IN ('Amarelo', 'Vermelho')
GROUP BY cartao;

-- Quantidade de numeros de cartoes por clube
SELECT clube, COUNT(*) AS quantidade_de_cartoes
FROM cartoes
GROUP BY clube
ORDER BY quantidade_de_cartoes DESC;

-- Quais jogadores receberam o maior numero de cartoes
SELECT atleta, COUNT(*) AS quantidade_de_cartoes
FROM cartoes
GROUP BY atleta
ORDER BY quantidade_de_cartoes DESC;

-- Quantidades de Gols
SELECT clube, COUNT(*) AS quantidade_de_gols
FROM gols
GROUP BY clube
ORDER BY quantidade_de_gols DESC;

-- Maiores artilheiros
SELECT atleta, COUNT(*) AS quantidade_de_gols
FROM gols
GROUP BY atleta
ORDER BY quantidade_de_gols DESC;














