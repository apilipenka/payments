-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.12 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for payments
CREATE DATABASE IF NOT EXISTS `payments` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `payments`;


-- Dumping structure for table payments.accounts
CREATE TABLE IF NOT EXISTS `accounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(45) NOT NULL,
  `amount` double NOT NULL,
  `currency_id` int(11) NOT NULL,
  `agreement_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number_UNIQUE` (`number`),
  KEY `fk_Accounts_Currencies1_idx` (`currency_id`),
  KEY `fk_Accounts_Agreements1_idx` (`agreement_id`),
  CONSTRAINT `fk_Accounts_Agreements1` FOREIGN KEY (`agreement_id`) REFERENCES `agreements` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Accounts_Currencies1` FOREIGN KEY (`currency_id`) REFERENCES `currencies` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table payments.agreements
CREATE TABLE IF NOT EXISTS `agreements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(45) DEFAULT NULL,
  `valid_from_date` date DEFAULT NULL,
  `valid_to_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `bank_id` int(11) NOT NULL,
  `user_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_agreements` (`bank_id`,`number`),
  KEY `fk_Agreements_Banks1_idx` (`bank_id`),
  KEY `fk_Agreements_Users1_idx` (`user_ID`),
  CONSTRAINT `fk_Agreements_Banks1` FOREIGN KEY (`bank_id`) REFERENCES `banks` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Agreements_Users1` FOREIGN KEY (`user_ID`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table payments.banks
CREATE TABLE IF NOT EXISTS `banks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `unn` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNN_UNIQUE` (`unn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table payments.cards
CREATE TABLE IF NOT EXISTS `cards` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `valid_to_date` date NOT NULL,
  `accounts_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number_UNIQUE` (`number`),
  KEY `fk_Cards_Accounts1_idx` (`accounts_id`),
  CONSTRAINT `fk_Cards_Accounts1` FOREIGN KEY (`accounts_id`) REFERENCES `accounts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table payments.commands
CREATE TABLE IF NOT EXISTS `commands` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `command` varchar(45) NOT NULL,
  `url` varchar(200) NOT NULL,
  `label` varchar(45) NOT NULL,
  `comment` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `COMMAND_UNIQUE` (`command`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table payments.currencies
CREATE TABLE IF NOT EXISTS `currencies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `mnemo_code` varchar(45) NOT NULL,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `currency_uk` (`mnemo_code`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table payments.exchange_rates
CREATE TABLE IF NOT EXISTS `exchange_rates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rate_date` date NOT NULL,
  `rate` float NOT NULL,
  `currency_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `exchange_rate_uk` (`currency_id`,`rate_date`),
  KEY `fk_exchange_rates_Curremcies1_idx` (`currency_id`),
  CONSTRAINT `fk_exchange_rates_Curremcies1` FOREIGN KEY (`currency_id`) REFERENCES `currencies` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table payments.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personal_number` varchar(45) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `birth_date` date NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `user_role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `personal_number_UNIQUE` (`personal_number`),
  KEY `fk_Users_User_Roles1_idx` (`user_role_id`),
  CONSTRAINT `fk_Users_User_Roles1` FOREIGN KEY (`user_role_id`) REFERENCES `user_roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table payments.user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table payments.user_role_commands
CREATE TABLE IF NOT EXISTS `user_role_commands` (
  `command_ID` int(11) NOT NULL,
  `user_role_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_role_commands_uk` (`command_ID`,`user_role_id`),
  KEY `fk_Commands_has_Ueser_Roles_Ueser_Roles1_idx` (`user_role_id`),
  KEY `fk_Commands_has_Ueser_Roles_Commands_idx` (`command_ID`),
  CONSTRAINT `fk_Commands_has_Ueser_Roles_Commands` FOREIGN KEY (`command_ID`) REFERENCES `commands` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Commands_has_Ueser_Roles_Ueser_Roles1` FOREIGN KEY (`user_role_id`) REFERENCES `user_roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
