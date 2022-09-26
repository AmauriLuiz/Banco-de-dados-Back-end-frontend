-- MySQL Workbench Synchronization
-- Generated: 2022-09-04 15:17
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Amauri  Luiz

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `db-AGENCIAS` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `db-AGENCIAS`.`destino` (
  `iddestino` INT(11) NOT NULL AUTO_INCREMENT,
  `cidadel` VARCHAR(45) NULL DEFAULT NULL,
  `uf` VARCHAR(45) NULL DEFAULT NULL,
  `local desembarque` VARCHAR(45) NULL DEFAULT NULL,
  `viagem_idviagem` INT(11) NOT NULL,
  `promoção_idpromoção` INT(11) NOT NULL,
  `promoção_adquire_idadquire` INT(11) NOT NULL,
  PRIMARY KEY (`iddestino`, `viagem_idviagem`, `promoção_idpromoção`, `promoção_adquire_idadquire`),
  INDEX `fk_destino_viagem1_idx` (`viagem_idviagem` ASC) VISIBLE,
  INDEX `fk_destino_promoção1_idx` (`promoção_idpromoção` ASC, `promoção_adquire_idadquire` ASC) VISIBLE,
  CONSTRAINT `fk_destino_viagem1`
    FOREIGN KEY (`viagem_idviagem`)
    REFERENCES `db-AGENCIAS`.`viagem` (`idviagem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_destino_promoção1`
    FOREIGN KEY (`promoção_idpromoção` , `promoção_adquire_idadquire`)
    REFERENCES `db-AGENCIAS`.`promoção` (`idpromoção` , `adquire_idadquire`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `db-AGENCIAS`.`promoção` (
  `idpromoção` INT(11) NOT NULL AUTO_INCREMENT,
  `destino` VARCHAR(45) NULL DEFAULT NULL,
  `desconto` VARCHAR(45) NULL DEFAULT NULL,
  `vencimento` VARCHAR(45) NULL DEFAULT NULL,
  `adquire_idadquire` INT(11) NOT NULL,
  PRIMARY KEY (`idpromoção`, `adquire_idadquire`),
  INDEX `fk_promoção_adquire1_idx` (`adquire_idadquire` ASC) VISIBLE,
  CONSTRAINT `fk_promoção_adquire1`
    FOREIGN KEY (`adquire_idadquire`)
    REFERENCES `db-AGENCIAS`.`adquire` (`idadquire`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `db-AGENCIAS`.`adquire` (
  `idadquire` INT(11) NOT NULL AUTO_INCREMENT,
  `cliente` VARCHAR(45) NULL DEFAULT NULL,
  `viagem` VARCHAR(45) NULL DEFAULT NULL,
  `promoção` VARCHAR(45) NULL DEFAULT NULL,
  `cliente_idcliente` INT(11) NOT NULL,
  `viagem_idviagem` INT(11) NOT NULL,
  PRIMARY KEY (`idadquire`, `viagem_idviagem`),
  INDEX `fk_adquire_cliente1_idx` (`cliente_idcliente` ASC) VISIBLE,
  INDEX `fk_adquire_viagem1_idx` (`viagem_idviagem` ASC) VISIBLE,
  CONSTRAINT `fk_adquire_cliente1`
    FOREIGN KEY (`cliente_idcliente`)
    REFERENCES `db-AGENCIAS`.`cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_adquire_viagem1`
    FOREIGN KEY (`viagem_idviagem`)
    REFERENCES `db-AGENCIAS`.`viagem` (`idviagem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `db-AGENCIAS`.`cliente` (
  `idcliente` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  `rg` VARCHAR(45) NULL DEFAULT NULL,
  `cpf` VARCHAR(45) NULL DEFAULT NULL,
  `data de nascimento` VARCHAR(45) NULL DEFAULT NULL,
  `e-mail` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idcliente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `db-AGENCIAS`.`viagem` (
  `idviagem` INT(11) NOT NULL AUTO_INCREMENT,
  `destino` VARCHAR(45) NULL DEFAULT NULL,
  `partida` VARCHAR(45) NULL DEFAULT NULL,
  `chegada` VARCHAR(45) NULL DEFAULT NULL,
  `valor padrão` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idviagem`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
