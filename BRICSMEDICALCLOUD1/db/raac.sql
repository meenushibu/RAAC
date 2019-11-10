-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 24, 2017 at 08:01 AM
-- Server version: 5.5.24-log
-- PHP Version: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `raac`
--

-- --------------------------------------------------------

--
-- Table structure for table `brics`
--

CREATE TABLE IF NOT EXISTS `brics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `brics`
--

INSERT INTO `brics` (`id`, `country`) VALUES
(1, 'India'),
(2, 'Brazil'),
(3, 'Russia'),
(4, 'China'),
(5, 'South Africa');

-- --------------------------------------------------------

--
-- Table structure for table `resource`
--

CREATE TABLE IF NOT EXISTS `resource` (
  `resourceID` int(11) NOT NULL AUTO_INCREMENT,
  `resourceName` varchar(1000) NOT NULL,
  `resourceContent` blob NOT NULL,
  `resourceOwner` varchar(100) NOT NULL,
  PRIMARY KEY (`resourceID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Table structure for table `res_country`
--

CREATE TABLE IF NOT EXISTS `res_country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resid` int(11) NOT NULL,
  `rescountry` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Table structure for table `res_special`
--

CREATE TABLE IF NOT EXISTS `res_special` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resid` int(11) NOT NULL,
  `resspecial` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Table structure for table `specialization`
--

CREATE TABLE IF NOT EXISTS `specialization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `specialization` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `specialization`
--

INSERT INTO `specialization` (`id`, `specialization`) VALUES
(1, 'General Physician'),
(2, 'Gynecologist'),
(3, 'Pediatrician'),
(4, 'Ophthalmologist'),
(5, 'Dermatologist'),
(6, 'ENT Doctor'),
(7, 'Orthopedic'),
(8, 'Cardiologist'),
(9, 'Neurologist'),
(10, 'Dentist '),
(11, 'Urologist '),
(12, 'Psychiatrist'),
(13, 'Pathologist'),
(14, 'Radiologists'),
(15, 'Anesthesiologist'),
(16, 'General Surgeon'),
(17, 'Oncologist'),
(18, 'Nephrologist '),
(19, 'Endocrinologist '),
(20, 'Gastrologist'),
(21, 'Veterinary');

-- --------------------------------------------------------

--
-- Table structure for table `userlist`
--

CREATE TABLE IF NOT EXISTS `userlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `userpass` varchar(100) NOT NULL,
  `role` int(11) NOT NULL DEFAULT '0',
  `status` int(11) NOT NULL DEFAULT '0',
  `regno` int(11) NOT NULL,
  `regyear` int(11) NOT NULL,
  `medicalcouncil` varchar(100) NOT NULL,
  `bricscountry` varchar(100) NOT NULL,
  `specialization` varchar(100) NOT NULL,
  `secretkey` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `userlist`
--

INSERT INTO `userlist` (`id`, `fullname`, `username`, `userpass`, `role`, `status`, `regno`, `regyear`, `medicalcouncil`, `bricscountry`, `specialization`, `secretkey`) VALUES
(1, 'Administrator', 'admin', '1234', 1, 1, 0, 0, '', '', '', ''),
(7, 'Bobby Thomas', 'bobby', '1234', 0, 1, 11111, 2006, 'Medical Council of India', 'India', 'Pediatrician', ''),
(14, 'Country Admin', 'cadmin', '1234', 2, 1, 0, 0, '', '', '', ''),
(15, 'Specialization Admin', 'sadmin', '1234', 3, 1, 0, 0, '', '', '', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
