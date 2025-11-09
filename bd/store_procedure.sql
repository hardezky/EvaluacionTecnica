DELIMITER $$
DROP PROCEDURE IF EXISTS sp_calcular_decision$$
CREATE PROCEDURE sp_calcular_decision(
    IN p_monto DECIMAL(15,2),
    IN p_plazo INT,
    IN p_ingresos DECIMAL(15,2),
    OUT p_decision VARCHAR(20)
)
BEGIN
    DECLARE local_score INT DEFAULT 0;

    -- regla 1: relación monto/ingresos
    IF p_ingresos IS NULL OR p_ingresos = 0 THEN
        SET local_score = local_score - 50;
    ELSE
        IF p_monto / p_ingresos <= 3 THEN
            SET local_score = local_score + 40;
        ELSEIF p_monto / p_ingresos <= 6 THEN
            SET local_score = local_score + 10;
        ELSE
            SET local_score = local_score - 20;
        END IF;
    END IF;

    -- regla 2: plazo
    IF p_plazo <= 12 THEN
        SET local_score = local_score + 20;
    ELSEIF p_plazo <= 36 THEN
        SET local_score = local_score + 5;
    ELSE
        SET local_score = local_score - 10;
    END IF;

    -- regla 3: monto
    IF p_monto <= 50000 THEN
        SET local_score = local_score + 10;
    ELSEIF p_monto <= 200000 THEN
        SET local_score = local_score + 0;
    ELSE
        SET local_score = local_score - 15;
    END IF;

    -- decisión final
    IF local_score >= 20 THEN
        SET p_decision = 'APROBADO';
    ELSE
        SET p_decision = 'RECHAZADO';
    END IF;
