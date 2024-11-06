BEGIN;

-- Função para verificar unicidade de user_id na tabela admin
CREATE OR REPLACE FUNCTION verificar_unico_usuario_admin()
RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (SELECT 1 FROM trainer WHERE user_id = NEW.user_id)
       OR EXISTS (SELECT 1 FROM client WHERE user_id = NEW.user_id) THEN
        RAISE EXCEPTION 'user_id já existe em trainer ou client';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger para a tabela admin
CREATE TRIGGER trigger_verificar_unico_usuario_admin
BEFORE INSERT ON admin
FOR EACH ROW
EXECUTE FUNCTION verificar_unico_usuario_admin();

-- Função para verificar unicidade de user_id na tabela trainer
CREATE OR REPLACE FUNCTION verificar_unico_usuario_trainer()
RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (SELECT 1 FROM admin WHERE user_id = NEW.user_id)
       OR EXISTS (SELECT 1 FROM client WHERE user_id = NEW.user_id) THEN
        RAISE EXCEPTION 'user_id já existe em admin ou client';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger para a tabela trainer
CREATE TRIGGER trigger_verificar_unico_usuario_trainer
BEFORE INSERT ON trainer
FOR EACH ROW
EXECUTE FUNCTION verificar_unico_usuario_trainer();

-- Função para verificar unicidade de user_id na tabela client
CREATE OR REPLACE FUNCTION verificar_unico_usuario_client()
RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (SELECT 1 FROM admin WHERE user_id = NEW.user_id)
       OR EXISTS (SELECT 1 FROM trainer WHERE user_id = NEW.user_id) THEN
        RAISE EXCEPTION 'user_id já existe em admin ou trainer';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger para a tabela client
CREATE TRIGGER trigger_verificar_unico_usuario_client
BEFORE INSERT ON client
FOR EACH ROW
EXECUTE FUNCTION verificar_unico_usuario_client();

END;
