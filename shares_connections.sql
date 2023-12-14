-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 14. Dez 2023 um 13:30
-- Server-Version: 10.4.28-MariaDB
-- PHP-Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `shares_connections`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `crypto_price`
--

CREATE TABLE `crypto_price` (
  `id` int(11) NOT NULL,
  `priceID` int(11) NOT NULL,
  `cryptoID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `etf_price`
--

CREATE TABLE `etf_price` (
  `id` int(11) NOT NULL,
  `priceID` int(11) NOT NULL,
  `etfID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `resource_price`
--

CREATE TABLE `resource_price` (
  `id` int(11) NOT NULL,
  `priceID` int(11) NOT NULL,
  `resourceID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `share_price`
--

CREATE TABLE `share_price` (
  `id` int(11) NOT NULL,
  `priceID` int(11) NOT NULL,
  `shareID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user_crypto`
--

CREATE TABLE `user_crypto` (
  `id` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `cryptoID` int(11) NOT NULL,
  `amountOfShares` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user_etfs`
--

CREATE TABLE `user_etfs` (
  `id` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `etfID` int(11) NOT NULL,
  `amountOfShares` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user_resources`
--

CREATE TABLE `user_resources` (
  `id` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `resourceID` int(11) NOT NULL,
  `amountOfShares` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user_shares`
--

CREATE TABLE `user_shares` (
  `id` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `shareID` int(11) NOT NULL,
  `amountOfShares` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user_transaction`
--

CREATE TABLE `user_transaction` (
  `id` int(11) NOT NULL,
  `transactionID` int(11) NOT NULL,
  `userID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `crypto_price`
--
ALTER TABLE `crypto_price`
  ADD PRIMARY KEY (`id`),
  ADD KEY `priceID` (`priceID`),
  ADD KEY `cryptoID` (`cryptoID`);

--
-- Indizes für die Tabelle `etf_price`
--
ALTER TABLE `etf_price`
  ADD PRIMARY KEY (`id`),
  ADD KEY `priceID` (`priceID`),
  ADD KEY `etfID` (`etfID`);

--
-- Indizes für die Tabelle `resource_price`
--
ALTER TABLE `resource_price`
  ADD PRIMARY KEY (`id`),
  ADD KEY `priceID` (`priceID`),
  ADD KEY `resourceID` (`resourceID`);

--
-- Indizes für die Tabelle `share_price`
--
ALTER TABLE `share_price`
  ADD PRIMARY KEY (`id`),
  ADD KEY `priceID` (`priceID`),
  ADD KEY `shareID` (`shareID`);

--
-- Indizes für die Tabelle `user_crypto`
--
ALTER TABLE `user_crypto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usertocrypto_ibfk_cryptoid` (`cryptoID`),
  ADD KEY `user_crypto_ibfk_iduser` (`userID`);

--
-- Indizes für die Tabelle `user_etfs`
--
ALTER TABLE `user_etfs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usertoetfs_ibfk_etfid` (`etfID`),
  ADD KEY `user_etfs_ibfk_iduser` (`userID`);

--
-- Indizes für die Tabelle `user_resources`
--
ALTER TABLE `user_resources`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usertoresources_ibfk_resourceid` (`resourceID`),
  ADD KEY `user_resources_ibfk_iduser` (`userID`);

--
-- Indizes für die Tabelle `user_shares`
--
ALTER TABLE `user_shares`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `shareID` (`shareID`),
  ADD UNIQUE KEY `userID` (`userID`);

--
-- Indizes für die Tabelle `user_transaction`
--
ALTER TABLE `user_transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_transaction_ibfk_idtransaction` (`transactionID`),
  ADD KEY `user_transaction_ibfk_iduser` (`userID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `crypto_price`
--
ALTER TABLE `crypto_price`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `etf_price`
--
ALTER TABLE `etf_price`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `resource_price`
--
ALTER TABLE `resource_price`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `share_price`
--
ALTER TABLE `share_price`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `user_crypto`
--
ALTER TABLE `user_crypto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `user_etfs`
--
ALTER TABLE `user_etfs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `user_resources`
--
ALTER TABLE `user_resources`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `user_shares`
--
ALTER TABLE `user_shares`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `user_transaction`
--
ALTER TABLE `user_transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `crypto_price`
--
ALTER TABLE `crypto_price`
  ADD CONSTRAINT `crypto_price_ibfk_1` FOREIGN KEY (`priceID`) REFERENCES `shares_investments`.`prices` (`id`),
  ADD CONSTRAINT `crypto_price_ibfk_2` FOREIGN KEY (`cryptoID`) REFERENCES `shares_investments`.`crypto` (`id`);

--
-- Constraints der Tabelle `etf_price`
--
ALTER TABLE `etf_price`
  ADD CONSTRAINT `etf_price_ibfk_1` FOREIGN KEY (`priceID`) REFERENCES `shares_investments`.`prices` (`id`),
  ADD CONSTRAINT `etf_price_ibfk_2` FOREIGN KEY (`etfID`) REFERENCES `shares_investments`.`etf` (`id`);

--
-- Constraints der Tabelle `resource_price`
--
ALTER TABLE `resource_price`
  ADD CONSTRAINT `resource_price_ibfk_1` FOREIGN KEY (`priceID`) REFERENCES `shares_investments`.`prices` (`id`),
  ADD CONSTRAINT `resource_price_ibfk_2` FOREIGN KEY (`resourceID`) REFERENCES `shares_investments`.`resource` (`id`);

--
-- Constraints der Tabelle `share_price`
--
ALTER TABLE `share_price`
  ADD CONSTRAINT `share_price_ibfk_1` FOREIGN KEY (`priceID`) REFERENCES `shares_investments`.`prices` (`id`),
  ADD CONSTRAINT `share_price_ibfk_2` FOREIGN KEY (`shareID`) REFERENCES `shares_investments`.`share` (`id`);

--
-- Constraints der Tabelle `user_crypto`
--
ALTER TABLE `user_crypto`
  ADD CONSTRAINT `user_crypto_ibfk_cryptoid` FOREIGN KEY (`cryptoID`) REFERENCES `shares_investments`.`crypto` (`id`),
  ADD CONSTRAINT `user_crypto_ibfk_iduser` FOREIGN KEY (`userID`) REFERENCES `shares_main`.`userdb` (`id`);

--
-- Constraints der Tabelle `user_etfs`
--
ALTER TABLE `user_etfs`
  ADD CONSTRAINT `user_etfs_ibfk_etfid` FOREIGN KEY (`etfID`) REFERENCES `shares_investments`.`etf` (`id`),
  ADD CONSTRAINT `user_etfs_ibfk_iduser` FOREIGN KEY (`userID`) REFERENCES `shares_main`.`userdb` (`id`);

--
-- Constraints der Tabelle `user_resources`
--
ALTER TABLE `user_resources`
  ADD CONSTRAINT `user_resources_ibfk_iduser` FOREIGN KEY (`userID`) REFERENCES `shares_main`.`userdb` (`id`),
  ADD CONSTRAINT `user_resources_ibfk_resourceid` FOREIGN KEY (`resourceID`) REFERENCES `shares_investments`.`resource` (`id`);

--
-- Constraints der Tabelle `user_shares`
--
ALTER TABLE `user_shares`
  ADD CONSTRAINT `user_shares_ibfk_iduser` FOREIGN KEY (`userID`) REFERENCES `shares_main`.`userdb` (`id`),
  ADD CONSTRAINT `user_shares_ibfk_sharesid` FOREIGN KEY (`shareID`) REFERENCES `shares_investments`.`share` (`id`);

--
-- Constraints der Tabelle `user_transaction`
--
ALTER TABLE `user_transaction`
  ADD CONSTRAINT `user_transaction_ibfk_idtransaction` FOREIGN KEY (`transactionID`) REFERENCES `shares_main`.`transaction` (`id`),
  ADD CONSTRAINT `user_transaction_ibfk_iduser` FOREIGN KEY (`userID`) REFERENCES `shares_main`.`userdb` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
