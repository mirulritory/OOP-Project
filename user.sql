-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 22, 2024 at 02:21 PM
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
(16, 'syafie', 'abc1234', 'syafie@gmail.com', '1234567890', 'Staff');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
