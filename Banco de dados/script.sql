DROP DATABASE IF EXISTS cwdb;
CREATE DATABASE cwdb;

USE cwdb;

CREATE TABLE endereco (
    id_endereco INT PRIMARY KEY AUTO_INCREMENT,
    logradouro VARCHAR(45),
    cep CHAR(8),
    numero VARCHAR(5),
    complemento VARCHAR(45),
    cidade VARCHAR(45) NOT NULL,
    uf CHAR(2)
);

CREATE TABLE empresa (
    id_empresa INT PRIMARY KEY AUTO_INCREMENT,
    nome_fantasia VARCHAR(45) NOT NULL,
    razao_social VARCHAR(45) NOT NULL,
    cnpj CHAR(14) NOT NULL,
    fk_filial INT,
    CONSTRAINT fk_filial_empresa FOREIGN KEY (fk_filial) REFERENCES empresa(id_empresa)
) AUTO_INCREMENT = 1000;

CREATE TABLE setor (
    id_setor INT PRIMARY KEY AUTO_INCREMENT,
    fk_empresa INT,
    setor VARCHAR(45),
    descricao VARCHAR(255),
    CONSTRAINT fk_empresa_setor FOREIGN KEY (fk_empresa) REFERENCES empresa(id_empresa)
);

CREATE TABLE funcionario (
    id_funcionario INT PRIMARY KEY AUTO_INCREMENT,
    primeiro_nome VARCHAR(45),
    sobrenome VARCHAR(45) NOT NULL,
    celular CHAR(11),
    telefone CHAR(11),
    email VARCHAR(60) NOT NULL,
    dt_nasc DATE,
    cpf CHAR(14) NOT NULL,
    cargo VARCHAR(45), -- Picklist
    senha VARCHAR(45) NOT NULL,
    fk_endereco INT,
    fk_setor INT,
    fk_empresa INT,
    CONSTRAINT fk_endereco_funcionario FOREIGN KEY (fk_endereco) REFERENCES endereco(id_endereco),
    CONSTRAINT fk_setor_funcionario FOREIGN KEY (fk_setor) REFERENCES setor(id_setor),
    CONSTRAINT fk_empresa_funcionario FOREIGN KEY (fk_empresa) REFERENCES empresa(id_empresa)
);

CREATE TABLE apontamento (
    id_apontamento INT PRIMARY KEY AUTO_INCREMENT,
    dt_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    chamadas_atendidas INT,
    chamadas_fcr INT,
    convertidos INT,
    detalhe VARCHAR(2000),
    CONSTRAINT fk_apontamento_funcionario FOREIGN KEY (fk_apontamento) REFERENCES apontamento(id_apontamento)
)

CREATE TABLE meta (
    id_meta INT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(255),
    dt_fim DATE,
    dt_inicio DATE,
    fk_supervisor INT NOT NULL,
    CONSTRAINT fk_supervisor_meta FOREIGN KEY (fk_supervisor) REFERENCES funcionario(id_funcionario)
);

CREATE TABLE meta_atribuida (
    id_meta_atribuida INT PRIMARY KEY AUTO_INCREMENT,
    fk_meta INT,
    fk_funcionario INT,
    concluida TINYINT DEFAULT 0,
    dt_concluida DEFAULT (CURRENT_DATE)
);

CREATE TABLE tempo_ociosidade (
    id_tempo_ociosidade INT PRIMARY KEY AUTO_INCREMENT,
    dt_hora_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
    tempo_registro_seg INT,
    fk_funcionario INT,
    CONSTRAINT fk_funcionario_tempo_ociosidade FOREIGN KEY (fk_funcionario) REFERENCES funcionario(id_funcionario)
);

CREATE TABLE artigo (
    id_artigo INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(45),
    descricao VARCHAR(2000),
    categoria VARCHAR(45),
    palavra_chave VARCHAR(45),
    fk_funcionario INT,
    CONSTRAINT fk_funcionario_artigo FOREIGN KEY (fk_funcionario) REFERENCES funcionario(id_funcionario)
);

CREATE TABLE ocorrencia (
    id_ocorrencia INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(45),
    descricao VARCHAR(255),
    data_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    prioridade VARCHAR(45), -- Picklist
    fk_funcionario INT,
    CONSTRAINT fk_funcionario_ocorrencia FOREIGN KEY (fk_funcionario) REFERENCES funcionario(id_funcionario),
    fk_atribuido INT,
    CONSTRAINT fk_suporte_ocorrencia FOREIGN KEY (fk_atribuido) REFERENCES funcionario(id_funcionario)
);

CREATE TABLE maquina (
    id_maquina INT PRIMARY KEY AUTO_INCREMENT,
    patrimonio VARCHAR(45),
    sistema_operacional VARCHAR(45),
    cpu VARCHAR(80),
    ram BIGINT, -- Bytes
    armazenamento BIGINT, -- Bytes
    detalhes VARCHAR(255),
    fk_empresa INT,
    CONSTRAINT fk_empresa_maquina FOREIGN KEY (fk_empresa) REFERENCES empresa(id_empresa)
);

CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    fk_maquina INT NOT NULL,
    fk_funcionario INT NOT NULL,
    usuario VARCHAR(80),
    senha VARCHAR(80),
    dt_criado DATETIME DEFAULT CURRENT_TIMESTAMP,
    ativo TINYINT DEFAULT 1,
    CONSTRAINT fk_maquina_usuario FOREIGN KEY (fk_maquina) REFERENCES maquina(id_maquina),
    CONSTRAINT fk_funcionario_usuario FOREIGN KEY (fk_funcionario) REFERENCES funcionario(id_funcionario)
);

CREATE TABLE registro (
    id_registro INT PRIMARY KEY AUTO_INCREMENT,
    data_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    uso_cpu DECIMAL(3,1), -- Porcentagem
    uso_ram BIGINT, -- Bytes
    disponivel_ram BIGINT, -- Bytes
    pacotes_recebidos BIGINT,
    fk_maquina CHAR(5),
    CONSTRAINT fk_maquina_registro FOREIGN KEY (fk_maquina) REFERENCES maquina(patrimonio)
);

CREATE TABLE volume (
    id_volume INT PRIMARY KEY AUTO_INCREMENT,
    fk_registro INT,
    nome VARCHAR(45),
    ponto_montagem VARCHAR(45),
    volume_total BIGINT, -- Bytes
    volume_disponivel BIGINT, -- Bytes
    CONSTRAINT fk_registro_volume FOREIGN KEY (fk_registro) REFERENCES registro(id_registro)
) AUTO_INCREMENT = 20000;

CREATE TABLE rede (
    id_rede INT PRIMARY KEY AUTO_INCREMENT,
    fk_registro INT,
    nome VARCHAR(45),
    ipv4 VARCHAR(15),
    bytes_recebidos BIGINT,
    bytes_enviados BIGINT,
    pacotes_recebidos BIGINT,
    pacotes_enviados BIGINT,
    CONSTRAINT fk_registro_rede FOREIGN KEY (fk_registro) REFERENCES registro(id_registro)
) AUTO_INCREMENT = 10000;