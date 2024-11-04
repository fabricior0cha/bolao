CREATE TABLE tb_bolao (
  bolao_id int NOT NULL AUTO_INCREMENT,
  bolao_titulo varchar(100) NOT NULL,
  bolao_codigo varchar(10) NOT NULL,
  bolao_dt_criacao date NOT NULL,
  bolao_premio float NOT NULL,
  PRIMARY KEY (bolao_id),
  UNIQUE KEY bolao_codigo (bolao_codigo)
);

CREATE TABLE tb_usuario (
  usuario_id int NOT NULL AUTO_INCREMENT,
  usuario_nome varchar(100) NOT NULL,
  usuario_email varchar(150) NOT NULL,
  usuario_senha varchar(11) NOT NULL,
  PRIMARY KEY (usuario_id),
  UNIQUE KEY tb_usuario_unique_email (usuario_email)
)

CREATE PROCEDURE sp_bolao_InserirAtualizar(
    IN ID INT,
    IN TITULO VARCHAR(100),
    IN CODIGO VARCHAR(10),
    IN DT_CRIACAO DATE,
    IN PREMIO FLOAT
)
BEGIN
     IF EXISTS(SELECT 1 FROM tb_bolao WHERE bolao_id = ID) THEN 
        UPDATE tb_bolao
        SET bolao_titulo = TITULO, bolao_codigo = CODIGO, bolao_dt_criacao = DT_CRIACAO, bolao_premio = PREMIO
        WHERE bolao_id = ID;
    ELSE
        INSERT INTO tb_bolao (bolao_titulo, bolao_codigo, bolao_dt_criacao, bolao_premio) 
        VALUES (TITULO, CODIGO, DT_CRIACAO, PREMIO);
    END IF;
END;

CREATE PROCEDURE sp_usuario_FindById(
    IN ID INT
)
BEGIN 
    SELECT * FROM tb_usuario
    WHERE usuario_id = ID;
END;

CREATE PROCEDURE sp_usuario_InserirAtualizar(
    IN NOME VARCHAR(100),
    IN SENHA VARCHAR(11),
    IN EMAIL VARCHAR(150),
    IN ID INT
)
BEGIN
    IF EXISTS(
        SELECT 1 FROM tb_usuario 
        WHERE usuario_email = EMAIL 
        AND (ID IS NULL OR usuario_id <> ID)
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Erro: E-mail já cadastrado para outro usuário.';
    ELSE
        IF EXISTS(SELECT 1 FROM tb_usuario WHERE usuario_id = ID) THEN
            UPDATE tb_usuario
            SET usuario_nome = NOME,
                usuario_email = EMAIL,
                usuario_senha = SENHA
            WHERE usuario_id = ID;
        ELSE
            INSERT INTO tb_usuario (
                usuario_nome,
                usuario_email,
                usuario_senha
            ) VALUES (
                NOME,
                EMAIL,
                SENHA
            );
        END IF;
    END IF;
END;

CREATE PROCEDURE sp_usuario_Login(
    IN EMAIL VARCHAR(100),
    IN SENHA VARCHAR(11)
)
BEGIN 
    SELECT * FROM tb_usuario
    WHERE usuario_email = EMAIL AND usuario_senha = SENHA;
END;
