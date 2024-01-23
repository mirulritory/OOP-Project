-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 23, 2024 at 11:44 AM
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
  `drinkPrice` double(4,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `beveragesmenu`
--

INSERT INTO `beveragesmenu` (`drinkID`, `drinkName`, `drinkPrice`) VALUES
(1, 'Cappucino', 10.00),
(2, 'Kopi ais', 2.50),
(7, 'Mocha', 8.00);

-- --------------------------------------------------------

--
-- Table structure for table `orderitem`
--

CREATE TABLE `orderitem` (
  `OrderID` int(36) NOT NULL,
  `drinkName` varchar(10) NOT NULL,
  `quantity` int(10) NOT NULL,
  `drinkPrice` double NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orderitem`
--

INSERT INTO `orderitem` (`OrderID`, `drinkName`, `quantity`, `drinkPrice`, `name`) VALUES
(9, 'Cappucino', 1, 10, 'Sarah'),
(9, 'Kopi ais', 1, 3, 'Sarah'),
(9, 'Mocha', 1, 8, 'Sarah'),
(25, 'Kopi ais', 2, 5, 'aBu'),
(26, 'Mocha', 2, 16, 'Abu'),
(27, 'Kopi ais', 1, 3, 'Abu'),
(28, 'Kopi ais', 1, 3, 'Abu'),
(29, 'Kopi ais', 1, 3, 'Abu'),
(30, 'Cappucino', 1, 10, 'Abu'),
(31, 'Kopi ais', 2, 5, 'Abu'),
(32, 'Kopi ais', 1, 3, 'Abu'),
(33, 'Mocha', 1, 8, 'Abu'),
(34, 'Cappucino', 1, 10, 'Sarah'),
(35, 'Kopi ais', 1, 3, 'Sarah'),
(36, 'Mocha', 1, 8, 'Sarah'),
(49, 'Mocha', 1, 8, 'abu'),
(50, 'Kopi ais', 2, 5, 'abu'),
(51, 'Mocha', 2, 16, 'abu'),
(52, 'Kopi ais', 2, 5, 'abu'),
(53, 'Cappucino', 2, 20, 'abu'),
(54, 'Cappucino', 1, 10, 'abu'),
(55, 'Mocha', 1, 8, 'abu'),
(56, 'Cappucino', 1, 10, 'z'),
(57, 'Kopi ais', 1, 3, 'z'),
(58, 'Kopi ais', 1, 3, 'z'),
(59, 'Mocha', 1, 8, 'z'),
(60, 'Kopi ais', 1, 3, 'z'),
(61, 'Mocha', 1, 8, 'z'),
(62, 'Mocha', 4, 32, 'z'),
(63, 'Kopi ais', 5, 13, 'z'),
(64, 'Cappucino', 4, 40, 'z'),
(65, 'Kopi ais', 5, 13, 'z');

-- --------------------------------------------------------

--
-- Table structure for table `orderlist`
--

CREATE TABLE `orderlist` (
  `orderID` int(10) NOT NULL,
  `name` varchar(25) NOT NULL,
  `totalPayment` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `accountType` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `password`, `email`, `phonenum`, `accountType`) VALUES
(1, 'sanusi', 'abc1234', 'sanusi@utem.edu.my', '1', 'Staff'),
(2, 'ahmad', 'abc1234', 'ahmad@abc.com.my', '0146175647', 'Staff'),
(3, 'chong', 'abc1234', 'chong@utem.edu.my', '0126016488', 'Customer'),
(4, 'sritharan', 'abc1234', 'sri@utem.edu.my', '0136759587', 'Customer'),
(5, 'Abu', '1234', 'abu@email.com', '0196196980', 'Customer'),
(7, 'Amin', '1234', 'Amin@email.com', '0126016455', 'Customer'),
(8, 'Meena', 'abc123', 'meena@mail.com', '0136759576', 'Customer'),
(10, 'Nur', '123', 'nur@gmail.com', '0196996981', 'Customer'),
(11, 'Sarah', 'abc', 'sarah@abc.com', '607887232', 'Customer'),
(12, 'Raju', 'Raju123', 'Raju@gmail.com', '01234567902', 'Customer'),
(13, 'Limah', 'abc1234', '', '01234567890', 'Customer'),
(14, 'Milah', 'abc1234', 'Milah@yahoo.com', '01234567890', 'Customer'),
(15, 'test', '123', 'test', '123', 'Customer'),
(16, 'syafie', 'abc1234', 'syafie@gmail.com', '1234567890', 'Staff'),
(17, 'z', '1', 'aaaa', '1234', 'Customer');

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
  ADD KEY `drinkID` (`drinkName`),
  ADD KEY `id` (`OrderID`),
  ADD KEY `id_2` (`name`),
  ADD KEY `OrderID_2` (`OrderID`);

--
-- Indexes for table `orderlist`
--
ALTER TABLE `orderlist`
  ADD PRIMARY KEY (`orderID`);

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
  MODIFY `drinkID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `orderlist`
--
ALTER TABLE `orderlist`
  MODIFY `orderID` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
