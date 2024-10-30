CREATE TABLE `tb_bolao` (
  `bolao_id` int NOT NULL AUTO_INCREMENT,
  `bolao_titulo` varchar(100) NOT NULL,
  `bolao_codigo` varchar(10) NOT NULL,
  `bolao_dt_criacao` date NOT NULL,
  `bolao_premio` float NOT NULL,
  PRIMARY KEY (`bolao_id`),
  UNIQUE KEY `bolao_codigo` (`bolao_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_usuario` (
  `usuario_id` int NOT NULL AUTO_INCREMENT,
  `usuario_nome` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `usuario_email` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `usuario_senha` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`usuario_id`),
  UNIQUE KEY `tb_usuario_unique` (`usuario_email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE DEFINER=`root`@`localhost` PROCEDURE `anhembi`.`sp_bolao_InserirAtualizar`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `anhembi`.`sp_bola_InserirAtualizar`(
    IN ID INT,
    IN TITULO VARCHAR(100),
    IN CODIGO INT,
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `anhembi`.`sp_usuario_FindById`(
	in ID int
)
begin 
	select * from tb_usuario
	where usuario_id = ID;
end;

CREATE DEFINER=`root`@`localhost` PROCEDURE `anhembi`.`sp_usuario_InserirAtualizar`(
  IN NOME VARCHAR(100),
  IN SENHA VARCHAR(11),
  IN EMAIL VARCHAR(150),
  IN ID INT
)
BEGIN
  -- Verificar se o e-mail já está cadastrado para outro usuário
  IF EXISTS(
      SELECT 1 FROM tb_usuario 
      WHERE usuario_email = EMAIL 
      AND (ID IS NULL OR usuario_id <> ID)
  ) THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Erro: E-mail já cadastrado para outro usuário.';
  ELSE
    -- Verificar se o usuário já existe pelo ID
    IF EXISTS(SELECT 1 FROM tb_usuario WHERE usuario_id = ID) THEN
      -- Atualizar o usuário existente
      UPDATE tb_usuario
      SET usuario_nome = NOME,
          usuario_email = EMAIL,
          usuario_senha = SENHA
      WHERE usuario_id = ID;
    ELSE
      -- Inserir novo usuário
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `anhembi`.`sp_usuario_Login`(
	in EMAIL VARCHAR(100),
	in SENHA VARCHAR(11)
)
begin 
	select * from tb_usuario
	where usuario_email = EMAIL and usuario_senha = SENHA;
end;