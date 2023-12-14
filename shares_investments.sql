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
-- Datenbank: `shares_investments`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `crypto`
--

CREATE TABLE `crypto` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `shortl` varchar(11) NOT NULL,
  `pricepershare` double NOT NULL,
  `stockreturn_in_percent` double NOT NULL,
  `existingShareAmounts` int(255) NOT NULL,
  `dateOfEntry` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `crypto`
--

INSERT INTO `crypto` (`id`, `name`, `shortl`, `pricepershare`, `stockreturn_in_percent`, `existingShareAmounts`, `dateOfEntry`) VALUES
(1, 'Bitcoin', 'BTC', 20000, 23.8, 5000000, '2023-11-30'),
(2, 'Etherium', 'ETH', 5000, 28.42, 75000000, '2023-11-30'),
(3, 'Dodgecoin', 'DGC', 564, 56.13, 10000000, '2023-11-30'),
(4, 'Ergon Coin', 'ERC', 220, 87.32, 4200000, '2023-11-30'),
(5, 'Binance Coin', 'BNC', 420, 32.87, 240000000, '2023-11-30'),
(6, 'Solana', 'SOL', 758, 19.8, 9600000, '2023-11-30'),
(7, 'Tron', 'TRO', 990, 44.65, 1200000, '2023-11-30');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `etf`
--

CREATE TABLE `etf` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `stockreturn` double DEFAULT NULL,
  `pricepershare` double DEFAULT NULL,
  `dateOfEntry` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `etf`
--

INSERT INTO `etf` (`id`, `name`, `stockreturn`, `pricepershare`, `dateOfEntry`) VALUES
(1, 'MSCI World', 12.8, 230, '2023-12-07'),
(2, 'XTrackers MSCO World', 6.7, 140, '2023-12-07'),
(3, 'HSBC MSCI World UCITS', 8.9, 176, '2023-12-07');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `prices`
--

CREATE TABLE `prices` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `prices`
--

INSERT INTO `prices` (`id`, `date`, `price`) VALUES
(1, '2023-08-15', 1000);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `resource`
--

CREATE TABLE `resource` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `shortl` text NOT NULL,
  `stockreturn` double NOT NULL,
  `existingsharesamount` int(11) NOT NULL,
  `dateofentry` date NOT NULL,
  `pricepershare` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `resource`
--

INSERT INTO `resource` (`id`, `name`, `shortl`, `stockreturn`, `existingsharesamount`, `dateofentry`, `pricepershare`) VALUES
(1, 'ÖL', 'OIL', 73.5, 1044163, '2023-11-30', 454),
(2, 'Gold', 'GLD', 85.3, 327948, '2023-11-30', 1654),
(3, 'Eisen', 'IRON', 27.85, 152405, '2023-11-30', 874),
(4, 'Papier', 'PAPR', 75, 440503, '2023-11-30', 120),
(5, 'Wasser', 'WATR', 100, 294196, '2023-11-30', 130),
(6, 'Kupfer', 'Copper', 120, 1052586, '2023-11-30', 252);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `share`
--

CREATE TABLE `share` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `shortl` text NOT NULL,
  `company` text NOT NULL,
  `stockreturn` double NOT NULL,
  `exisitingshareamounts` int(11) NOT NULL,
  `dateofentry` date NOT NULL,
  `pricepershare` int(11) NOT NULL,
  `companyvalue` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `share`
--

INSERT INTO `share` (`id`, `name`, `shortl`, `company`, `stockreturn`, `exisitingshareamounts`, `dateofentry`, `pricepershare`, `companyvalue`) VALUES
(1, 'Ergon', 'ERG', 'Ergon Informatik AG', 12.8, 133455, '2023-11-30', 450, 202252),
(2, 'Ti&M', 'Ti&M', 'Ti&M', 63.225, 848013, '2023-11-30', 350, 501298),
(3, 'Adnovum', 'ADNV', 'Adnovum', 23.63, 244770, '2023-11-30', 200, 556338),
(4, 'Apple', 'APL', 'Apple', 36.34, 513363, '2023-11-30', 600, 919093),
(5, 'Tesla', 'TSLA', 'Tesla', 76.8, 916381, '2023-11-30', 560, 745896),
(6, 'SpaceX', 'SPX', 'SpaceX', 34.97, 113903, '2023-11-30', 900, 147360),
(7, 'Nestle', 'NSTL', 'Nestle', 19.37, 375965, '2023-11-30', 370, 390212),
(8, 'Stark', 'STARK IND.', 'Stark Industries', 86.3, 526045, '2023-11-30', 1200, 306687),
(9, 'ZKB', 'ZKB', 'Zürcher Kantonalbank', 23.63, 906306, '2023-11-30', 590, 190393),
(10, 'Raiffeisen', 'RAFFI', 'Raiffeisen', 62.375, 646864, '2023-11-30', 480, 820002);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `crypto`
--
ALTER TABLE `crypto`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `etf`
--
ALTER TABLE `etf`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `prices`
--
ALTER TABLE `prices`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `resource`
--
ALTER TABLE `resource`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `share`
--
ALTER TABLE `share`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`) USING HASH;

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `crypto`
--
ALTER TABLE `crypto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT für Tabelle `etf`
--
ALTER TABLE `etf`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `prices`
--
ALTER TABLE `prices`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT für Tabelle `resource`
--
ALTER TABLE `resource`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT für Tabelle `share`
--
ALTER TABLE `share`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
