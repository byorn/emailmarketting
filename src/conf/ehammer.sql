-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 15, 2014 at 01:34 PM
-- Server version: 5.5.34
-- PHP Version: 5.4.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ehammer`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `version` int(10) NOT NULL,
  `status` varchar(3) NOT NULL,
  `email` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=64 ;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `name`, `version`, `status`, `email`) VALUES
(63, 'byorn', 0, 'ACT', 'byorn.desilva@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `email_status`
--

CREATE TABLE IF NOT EXISTS `email_status` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `flyer_id` int(10) NOT NULL,
  `customer_id` int(10) NOT NULL,
  `last_modified` datetime NOT NULL,
  `email_status` varchar(3) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `flyer_id` (`flyer_id`),
  KEY `customer_id` (`customer_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=41 ;

--
-- Dumping data for table `email_status`
--

INSERT INTO `email_status` (`id`, `flyer_id`, `customer_id`, `last_modified`, `email_status`, `description`) VALUES
(18, 51, 30, '2014-04-02 00:00:00', 'SUC', 'Successfully emailed tobyorn.desilva@gmail.com'),
(19, 52, 30, '2014-04-02 00:00:00', 'SUC', 'Successfully emailed tobyorn.desilva@gmail.com'),
(20, 52, 30, '2014-04-02 00:00:00', 'SUC', 'Successfully emailed tobyorn.desilva@gmail.com'),
(21, 52, 30, '2014-04-03 00:00:00', 'SUC', 'Successfully emailed tobyorn.desilva@gmail.com'),
(22, 52, 30, '2014-04-03 00:00:00', 'SUC', 'Successfully emailed tobyorn.desilva@gmail.com'),
(23, 52, 30, '2014-04-03 00:00:00', 'SUC', 'Successfully emailed tobyorn.desilva@gmail.com'),
(24, 52, 30, '2014-04-03 00:00:00', 'SUC', 'Successfully emailed tobyorn.desilva@gmail.com'),
(25, 52, 31, '2014-04-03 00:00:00', 'FAI', 'Error !Invalid Addresses'),
(26, 52, 32, '2014-04-03 00:00:00', 'FAI', 'Error !Invalid Addresses'),
(27, 52, 40, '2014-04-03 00:00:00', 'FAI', 'Error !Invalid Addresses'),
(28, 52, 58, '2014-04-03 00:00:00', 'FAI', 'Error !Invalid Addresses'),
(29, 52, 30, '2014-04-03 00:00:00', 'SUC', 'Successfully emailed tobyorn.desilva@gmail.com'),
(30, 52, 31, '2014-04-03 00:00:00', 'FAI', 'Error !Invalid Addresses'),
(31, 52, 32, '2014-04-03 00:00:00', 'FAI', 'Error !Invalid Addresses'),
(32, 52, 30, '2014-04-03 00:00:00', 'SUC', 'Successfully emailed tobyorn.desilva@gmail.com'),
(33, 52, 31, '2014-04-03 00:00:00', 'FAI', 'Error !Invalid Addresses'),
(34, 52, 30, '2014-04-03 00:00:00', 'SUC', 'Successfully emailed tobyorn.desilva@gmail.com'),
(35, 52, 31, '2014-04-03 00:00:00', 'FAI', 'Error !Invalid Addresses'),
(36, 52, 30, '2014-04-03 00:00:00', 'SUC', 'Successfully emailed tobyorn.desilva@gmail.com'),
(37, 61, 63, '2014-04-08 00:00:00', 'SUC', 'Successfully emailed tobyorn.desilva@gmail.com'),
(38, 61, 63, '2014-04-08 00:00:00', 'SUC', 'Successfully emailed tobyorn.desilva@gmail.com'),
(39, 61, 63, '2014-04-08 00:00:00', 'SUC', 'Successfully emailed tobyorn.desilva@gmail.com'),
(40, 61, 63, '2014-04-08 00:00:00', 'SUC', 'Successfully emailed tobyorn.desilva@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `flyer`
--

CREATE TABLE IF NOT EXISTS `flyer` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `status` varchar(3) NOT NULL,
  `version` int(10) NOT NULL,
  `html_content` mediumtext,
  `flyer_attachement` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=93 ;

--
-- Dumping data for table `flyer`
--

INSERT INTO `flyer` (`id`, `name`, `status`, `version`, `html_content`, `flyer_attachement`) VALUES
(87, 'test', 'ACT', 0, '<a href=""><img style="width="23" height="23"" src="http://localhost:8080/EHammer/ImageServlet?image_id=2" /></a>', NULL),
(88, '333', 'ACT', 0, '33333', NULL),
(89, 'eee', 'ACT', 0, 'null<a href=""><img 1212"="" "="" src="http://localhost:8080/EHammer/ImageServlet?image_id=3" height="447" width="261"></a>', NULL),
(90, 'test4', 'ACT', 0, '', NULL),
(91, 'test123', 'ACT', 0, '<a href=""><img style="width=" 233"="" height="233" "="" src="http://localhost:8080/EHammer/ImageServlet?image_id=3"></a>dddddd<a href=""><img style="width="4444" height="444"" src="http://localhost:8080/EHammer/ImageServlet?image_id=6" /></a>', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `image`
--

CREATE TABLE IF NOT EXISTS `image` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `image` mediumblob,
  `name` varchar(200) NOT NULL,
  `status` varchar(3) NOT NULL,
  `version` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

-- --------------------------------------------------------

--
-- Table structure for table `settings`
--

CREATE TABLE IF NOT EXISTS `settings` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `status` varchar(3) NOT NULL,
  `version` int(10) NOT NULL,
  `value` varchar(500) DEFAULT NULL,
  `code` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `settings`
--

INSERT INTO `settings` (`id`, `name`, `status`, `version`, `value`, `code`) VALUES
(1, 'EMAIL SERVER HOST', 'ACT', 0, 'smtp.gmail.com', 'EMAIL_HOST'),
(2, 'EMAIL SERVER PORT', 'ACT', 0, '465', 'EMAIL_PORT'),
(4, 'EMAIL SERVER USER NAME', 'ACT', 0, 'obama@gmail.com', 'EMAIL_USERNAME'),
(5, 'EMAIL SERVER PASSWORD', 'ACT', 0, 'obama', 'EMAIL_PASSWORD'),
(8, 'EMAIL SERVER FROM ADDRESS', 'ACT', 0, 'obama@gmail.com', 'EMAIL_FROM_ADDRESS'),
(10, 'Images Path', 'ACT', 0, '/home/byorn/appconfig/images/', 'IMG_PTH'),
(11, 'attachements path', 'ACT', 0, '/home/byorn/appconfig/attachements/', 'ATT_PATH');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;