-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 23, 2024 at 08:21 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `orderitem`
--

CREATE TABLE `orderitem` (
  `OrderID` int(36) NOT NULL,
  `drinkName` varchar(50) NOT NULL,
  `quantity` int(10) NOT NULL,
  `drinkPrice` double(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orderitem`
--

INSERT INTO `orderitem` (`OrderID`, `drinkName`, `quantity`, `drinkPrice`) VALUES
(8, 'Iced Americano', 1, 5.00),
(9, 'Hazelnut Coffee', 1, 9.00),
(9, 'Iced Americano', 2, 10.00),
(9, 'Turkish Coffee', 1, 7.00),
(9, 'Vanilla Espresso', 1, 8.00),
(10, 'Coffe Frappucino', 4, 36.00),
(10, 'Iced Brew Coffee', 1, 6.00);

-- --------------------------------------------------------

--
-- Table structure for table `orderlist`
--

CREATE TABLE `orderlist` (
  `orderID` int(10) NOT NULL,
  `name` varchar(25) NOT NULL,
  `totalPayment` double NOT NULL,
  `points` int(11) NOT NULL,
  `DateOrder` date DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orderlist`
--

INSERT INTO `orderlist` (`orderID`, `name`, `totalPayment`, `points`, `DateOrder`) VALUES
(2, 'abu', 103, 103, NULL),
(3, 'abu', 60, 60, NULL),
(4, 'abu', 32, 32, NULL),
(5, 'Nur', 62, 62, NULL),
(6, 'nur', 20, 20, NULL),
(7, 'Abu', 5, 5, NULL),
(8, 'Abu', 5, 5, NULL),
(9, 'Amin', 34, 34, NULL),
(10, 'Raju', 42, 42, '2024-01-24');

-- --------------------------------------------------------

--
-- Table structure for table `rewards`
--

CREATE TABLE `rewards` (
  `rewardID` int(15) NOT NULL,
  `rewardName` varchar(200) NOT NULL,
  `pointN` int(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rewards`
--

INSERT INTO `rewards` (`rewardID`, `rewardName`, `pointN`) VALUES
(1, '2 x Caramel Machiato', 50),
(2, '5 x Caramel Machiato', 100),
(3, '2 x Iced Americano', 30),
(4, 'Buy 1 Free 1 Drink', 30),
(5, 'Daily Cappucino for A Week', 200);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(5) NOT NULL,
  `name` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `email` varchar(32) DEFAULT NULL,
  `phonenum` varchar(11) DEFAULT NULL,
  `accountType` varchar(8) NOT NULL,
  `points` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `password`, `email`, `phonenum`, `accountType`, `points`) VALUES
(1, 'sanusi', 'abc1234', 'sanusi@utem.edu.my', '1', 'Staff', 0),
(2, 'ahmad', 'abc1234', 'ahmad@abc.com.my', '0146175647', 'Staff', 0),
(3, 'chong', 'abc1234', 'chong@utem.edu.my', '0126016488', 'Customer', 0),
(4, 'sritharan', 'abc1234', 'sri@utem.edu.my', '0136759587', 'Customer', 0),
(5, 'Abu', '1234', 'abu@email.com', '0196196980', 'Customer', 5555455),
(7, 'Amin', '1234', 'Amin@email.com', '0126016455', 'Customer', 34),
(8, 'Meena', 'abc123', 'meena@mail.com', '0136759576', 'Customer', 0),
(10, 'Nur', '123', 'nur@gmail.com', '0196996981', 'Customer', 0),
(11, 'Sarah', 'abc', 'sarah@abc.com', '607887232', 'Customer', 0),
(12, 'Raju', 'Raju123', 'Raju@gmail.com', '01234567902', 'Customer', 42),
(13, 'Limah', 'abc1234', '', '01234567890', 'Customer', 0),
(14, 'Milah', 'abc1234', 'Milah@yahoo.com', '01234567890', 'Customer', 0),
(15, 'test', '123', 'test', '123', 'Customer', 0),
(16, 'syafie', 'abc1234', 'syafie@gmail.com', '1234567890', 'Staff', 0),
(17, 'z', '1', 'aaaa', '1234', 'Customer', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `beveragesmenu`
--
ALTER TABLE `beveragesmenu`
  ADD PRIMARY KEY (`drinkID`);

--
-- Indexes for table `orderitem`
--
ALTER TABLE `orderitem`
  ADD PRIMARY KEY (`OrderID`,`drinkName`),
  ADD KEY `drinkID` (`drinkName`);

--
-- Indexes for table `orderlist`
--
ALTER TABLE `orderlist`
  ADD PRIMARY KEY (`orderID`);

--
-- Indexes for table `rewards`
--
ALTER TABLE `rewards`
  ADD PRIMARY KEY (`rewardID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `beveragesmenu`
--
ALTER TABLE `beveragesmenu`
  MODIFY `drinkID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `orderlist`
--
ALTER TABLE `orderlist`
  MODIFY `orderID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `rewards`
--
ALTER TABLE `rewards`
  MODIFY `rewardID` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
