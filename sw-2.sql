-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2016 at 08:33 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sw-2`
--

-- --------------------------------------------------------

--
-- Table structure for table `check-in`
--

CREATE TABLE IF NOT EXISTS `check-in` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `placeName` varchar(22) NOT NULL,
  `numOfLikes` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `check-in`
--

INSERT INTO `check-in` (`id`, `userId`, `placeName`, `numOfLikes`) VALUES
(3, 8, 'mac', 0),
(4, 1, ',mohandseen', 0),
(5, 9, 'mohandseen', 0),
(6, 10, 'october', 0),
(7, 5, 'october', 0),
(8, 4, 'cairo', 0),
(9, 8, 'mac', 0),
(10, 3, 'cairo', 0),
(11, 3, 'new', 0),
(12, 2, 'cairo', 0);

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `checkinId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `comment` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`commentId`, `checkinId`, `userId`, `comment`) VALUES
(3, 5, 4, 'nice'),
(4, 5, 4, 'nice'),
(5, 2, 2, 'niceds'),
(6, 2, 2, 'niceds'),
(7, 2, 2, 'niceds'),
(8, 3, 2, 'niceds'),
(9, 12, 6, 'fantastic');

-- --------------------------------------------------------

--
-- Table structure for table `followers`
--

CREATE TABLE IF NOT EXISTS `followers` (
  `followerID` int(11) NOT NULL,
  `followingID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `followers`
--

INSERT INTO `followers` (`followerID`, `followingID`) VALUES
(4, 5),
(7, 3),
(2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `historyofactions`
--

CREATE TABLE IF NOT EXISTS `historyofactions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `typeId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `historyofactions`
--

INSERT INTO `historyofactions` (`id`, `type`, `typeId`) VALUES
(1, 1, 1),
(2, 2, 8),
(3, 0, 3);

-- --------------------------------------------------------

--
-- Table structure for table `homepage`
--

CREATE TABLE IF NOT EXISTS `homepage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `typeId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `homepage`
--

INSERT INTO `homepage` (`id`, `userId`, `type`, `typeId`) VALUES
(1, 8, 0, 3),
(2, 4, 0, 8),
(3, 4, 1, 1),
(4, 8, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `like`
--

CREATE TABLE IF NOT EXISTS `like` (
  `likeId` int(11) NOT NULL AUTO_INCREMENT,
  `checkinId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`likeId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `like`
--

INSERT INTO `like` (`likeId`, `checkinId`, `userId`) VALUES
(1, 4, 3),
(2, 4, 3),
(3, 4, 3),
(4, 4, 1),
(5, 12, 6);

-- --------------------------------------------------------

--
-- Table structure for table `notificationlist`
--

CREATE TABLE IF NOT EXISTS `notificationlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user1Id` int(11) NOT NULL,
  `user2Id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `typeId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `notificationlist`
--

INSERT INTO `notificationlist` (`id`, `user1Id`, `user2Id`, `type`, `typeId`) VALUES
(1, 2, 8, 0, 8),
(2, 3, 1, 1, 0),
(3, 3, 1, 1, 3),
(4, 1, 1, 1, 4),
(5, 6, 2, 0, 9),
(6, 6, 2, 1, 5);

-- --------------------------------------------------------

--
-- Table structure for table `places`
--

CREATE TABLE IF NOT EXISTS `places` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(300) NOT NULL,
  `description` text NOT NULL,
  `lat` double NOT NULL,
  `long` double NOT NULL,
  `numOfReq` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `places`
--

INSERT INTO `places` (`id`, `name`, `description`, `lat`, `long`, `numOfReq`) VALUES
(1, 'cairo', 'country', 15.098, 8765.1, 5),
(2, 'new', 'NewPlace', 1202, 23, 0),
(3, 'mac', 'resturant', 55.1, 16.9, 3);

-- --------------------------------------------------------

--
-- Table structure for table `savedplaces`
--

CREATE TABLE IF NOT EXISTS `savedplaces` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `placeId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `savedplaces`
--

INSERT INTO `savedplaces` (`id`, `userId`, `placeId`) VALUES
(1, 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(300) NOT NULL,
  `email` varchar(500) NOT NULL,
  `password` varchar(300) NOT NULL,
  `lat` double DEFAULT NULL,
  `long` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `last_place_id` (`lat`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `lat`, `long`) VALUES
(1, 'mohamed', 'mhmdsamir@gmail.com', '123', 30.0310036, 31.2127736),
(2, 'mohamed', 'mhmdsamir1@gmail.com', '123', 0, NULL),
(3, 'mohamed', 'mhmdsamir91@gmail.com', '123456789', NULL, NULL),
(4, 'mohamed', 'mhmdsamir92@gmail.com', '123456789', NULL, NULL),
(5, 'mohamed', 'm.samir', '123456789', 30, 31),
(6, 'Omar', 'omar', '123', NULL, NULL),
(7, 'farida', 'farida.h@ho.com', '518', NULL, NULL),
(8, 'farida', 'fareda.com', '246', NULL, NULL),
(9, 'laila', 'laila.com', '246', NULL, NULL),
(10, 'amira', 'amira.com', '2466', NULL, NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
