-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 23, 2024 at 06:38 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `myoop`
--

-- --------------------------------------------------------

--
-- Table structure for table `beveragesmenu`
--

CREATE TABLE `beveragesmenu` (
  `drinkID` int(10) NOT NULL,
  `drinkName` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `drinkPrice` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `beveragesmenu`
--

INSERT INTO `beveragesmenu` (`drinkID`, `drinkName`, `drinkPrice`) VALUES
(1, 'Cappucino', 8),
(2, 'Matcha', 8),
(3, 'Turkish Coffee', 7),
(4, 'Match Latte', 8),
(5, 'Caramel Macchiato', 10),
(6, 'Iced Americano', 5),
(7, 'Coffe Frappucino', 9),
(8, 'Vanilla Espresso', 8),
(9, 'Iced Brew Coffee', 6),
(10, 'Hazelnut Coffee', 9);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `beveragesmenu`
--
ALTER TABLE `beveragesmenu`
  ADD PRIMARY KEY (`drinkID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `beveragesmenu`
--
ALTER TABLE `beveragesmenu`
  MODIFY `drinkID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
