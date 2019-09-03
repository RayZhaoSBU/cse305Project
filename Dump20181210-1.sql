CREATE DATABASE  IF NOT EXISTS `CSE305_PROJ` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `CSE305_PROJ`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 24.47.137.77    Database: CSE305_PROJ
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.23-MariaDB-9+deb9u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Auction`
--

DROP TABLE IF EXISTS `Auction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Auction` (
  `AuctionID` int(11) NOT NULL,
  `BidIncrement` float DEFAULT NULL,
  `MinimumBid` float DEFAULT NULL,
  `Copies_Sold` int(11) DEFAULT NULL,
  `Monitor` int(9) NOT NULL,
  `ItemID` int(11) NOT NULL,
  PRIMARY KEY (`AuctionID`),
  UNIQUE KEY `AuctionID_UNIQUE` (`AuctionID`),
  KEY `ItemID` (`ItemID`),
  KEY `Auction_ibfk_1` (`Monitor`),
  CONSTRAINT `Auction_ibfk_1` FOREIGN KEY (`Monitor`) REFERENCES `Employee` (`EmployeeID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Auction_ibfk_2` FOREIGN KEY (`ItemID`) REFERENCES `Item` (`ItemID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Auction`
--

LOCK TABLES `Auction` WRITE;
/*!40000 ALTER TABLE `Auction` DISABLE KEYS */;
INSERT INTO `Auction` VALUES (1,10,100,1,2,1),(2,10,100,0,2,2),(3,10,100,0,3,3),(4,10,1500,1,4,4),(5,100,1200,1,1,5),(6,100,1000,1,2,6),(7,10,20,4,3,7),(8,10,20,1,4,8),(9,50,500,2,1,9),(10,50,500,1,2,10);
/*!40000 ALTER TABLE `Auction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bid`
--

DROP TABLE IF EXISTS `Bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bid` (
  `ItemID` int(11) NOT NULL,
  `CustomerID` char(9) NOT NULL,
  `AuctionID` int(11) NOT NULL,
  `BidTime` datetime NOT NULL,
  `BidPrice` double NOT NULL,
  `MaxBid` double NOT NULL,
  PRIMARY KEY (`BidTime`,`ItemID`,`CustomerID`,`AuctionID`),
  KEY `ItemID` (`ItemID`),
  KEY `Bid_ibfk_1_idx` (`CustomerID`),
  KEY `Bid_ibfk_3` (`AuctionID`),
  CONSTRAINT `Bid_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `Customer` (`CustomerID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Bid_ibfk_2` FOREIGN KEY (`ItemID`) REFERENCES `Item` (`ItemID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Bid_ibfk_3` FOREIGN KEY (`AuctionID`) REFERENCES `Auction` (`AuctionID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bid`
--

LOCK TABLES `Bid` WRITE;
/*!40000 ALTER TABLE `Bid` DISABLE KEYS */;
INSERT INTO `Bid` VALUES (4,'5',4,'2018-09-01 23:59:00',1700,1750),(10,'6',10,'2018-09-01 23:59:00',750,1000),(1,'5',1,'2018-09-02 15:59:00',100,500),(1,'6',1,'2018-09-02 20:59:00',150,500),(1,'5',1,'2018-09-02 21:59:00',155,500),(10,'5',10,'2018-09-02 23:59:00',760,1000),(1,'6',1,'2018-09-03 14:59:00',160,500),(1,'5',1,'2018-09-03 15:59:00',170,500),(2,'5',2,'2018-09-03 22:59:00',200,300),(1,'6',1,'2018-09-03 23:59:00',180,500),(10,'6',10,'2018-09-03 23:59:00',770,1000),(7,'5',7,'2018-09-04 23:59:00',30,100),(4,'6',4,'2018-09-05 23:59:00',1800,1800),(2,'6',2,'2018-09-06 23:59:00',210,300),(7,'6',7,'2018-09-07 23:59:00',40,100);
/*!40000 ALTER TABLE `Bid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `Bought`
--

DROP TABLE IF EXISTS `Bought`;
/*!50001 DROP VIEW IF EXISTS `Bought`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `Bought` AS SELECT 
 1 AS `CustomerID`,
 1 AS `ItemID`,
 1 AS `ItemType`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Customer` (
  `Rating` int(11) DEFAULT NULL,
  `CreditCard` char(16) DEFAULT NULL,
  `CustomerID` char(9) NOT NULL,
  PRIMARY KEY (`CustomerID`),
  CONSTRAINT `Customer_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `Person` (`SSN`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (80,'3740880000000001','10'),(70,'3733680000000001','11'),(95,'3405930000000001','12'),(50,'3775870000000001','13'),(60,'3771880000000001','14'),(70,'3754340000000001','15'),(80,'0000000000000000','5'),(50,'3451590000000001','6'),(95,'3712840000000001','8'),(50,'3402980000000001','9');
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `CustomerRepRevenue`
--

DROP TABLE IF EXISTS `CustomerRepRevenue`;
/*!50001 DROP VIEW IF EXISTS `CustomerRepRevenue`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `CustomerRepRevenue` AS SELECT 
 1 AS `Monitor`,
 1 AS `Revenue`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `CustomerRevenue`
--

DROP TABLE IF EXISTS `CustomerRevenue`;
/*!50001 DROP VIEW IF EXISTS `CustomerRevenue`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `CustomerRevenue` AS SELECT 
 1 AS `CustomerID`,
 1 AS `Revenue`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Employee` (
  `EmployeeID` int(9) NOT NULL,
  `EmployeeLvl` tinytext,
  `StartDate` date DEFAULT NULL,
  `HourlyRate` float DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`),
  UNIQUE KEY `Employee_ibfk_1` (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (1,'Manager','2017-01-01',130),(2,'Employee','2017-10-24',50),(3,'Employee','2017-12-31',55),(4,'Employee','2017-12-07',60);
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Item` (
  `ItemID` int(11) NOT NULL,
  `Description` char(255) DEFAULT NULL,
  `ItemName` char(255) DEFAULT NULL,
  `ItemType` char(255) DEFAULT NULL,
  `NumCopies` int(11) DEFAULT NULL,
  PRIMARY KEY (`ItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
INSERT INTO `Item` VALUES (1,'a','The High Sign','DVD',4),(2,'b','Every Girl Should Be Married','DVD',1),(3,'c','Final Cut  The','DVD',2),(4,'d','Esperante','CAR',1),(5,'e','Escalade EXT','CAR',1),(6,'f','Coachbuilder','CAR',2),(7,'g','Shadowy Dreams','BOOK',4),(8,'h','The Elemental Pleasure','BOOK',10),(9,'i','Microsoft Surface Book','LAPTOP',1),(10,'g','Lenovo Thinkpad','LAPTOP',2);
/*!40000 ALTER TABLE `Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Person`
--

DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Person` (
  `SSN` char(9) NOT NULL,
  `LastName` char(20) NOT NULL,
  `FirstName` char(20) NOT NULL,
  `Address` tinytext,
  `City` tinytext,
  `State` tinytext,
  `ZipCode` int(5) unsigned zerofill DEFAULT NULL,
  `Telephone` char(11) DEFAULT NULL,
  `Email` tinytext,
  `Password` varchar(45) NOT NULL DEFAULT '12345678',
  PRIMARY KEY (`SSN`),
  UNIQUE KEY `SSN_UNIQUE` (`SSN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Person`
--

LOCK TABLES `Person` WRITE;
/*!40000 ALTER TABLE `Person` DISABLE KEYS */;
INSERT INTO `Person` VALUES ('1','Marquardt','Amara','71747 Hilario Turnpike','Lake Dorian','Kansas',13110,'4039251501','Orpha@christine.co.uk','12345678'),('10','Treutel','Dashawn','891 Dana Summit','Greenfurt','New Jersey',86807,'5879761678','Jed_Schulist@bernadine.org','12345678'),('11','Gleason','Lisa','7804 Kirlin Alley','South Eliza','Maryland',66367,'8003066110','Joey_Hoeger@lon.net','12345678'),('12','Nitzsche','Sadie','7459 Miller Gateway','Port Rosamond','Oklahoma',83563,'4412557052','Giles.Schulist@nicolette.io','12345678'),('13','Howe','Josie','581 Oswaldo Drive','Buckridgeside','North Dakota',92011,'16650908827','Geovanni_Pfannerstill@bell.net','12345678'),('14','Hickle','Leta','756 Liza Streets','Ziemannview','Maine',24556,'9694629092','Brandon.Nikolaus@theron.me','12345678'),('15','Jaskolski','Khalid','571 Antwon Fort','Port Meaganbury','Delaware',98648,'2267689136','Eden_Roob@craig.tv','12345678'),('2','Herman','Dario','955 Senger Cove','South Evans','Hawaii',97933,'9809750117','Jordi@lia.net','12345678'),('3','West','Malika','09090 Eleanora Garden','Crystalstad','Alabama',11225,'3437582254','Palma_Stoltenberg@braulio.org','12345678'),('4','Littel','Linwood','05975 Stark Walk','Port Reaganmouth','Alabama',20106,'8987232136','Cristal@kitty.tv','12345678'),('5','Adams','Muriel','85797 Stehr Drive','Beattybury','Tennessee',40584,'9002944559','Vella@madalyn.tv','12345678'),('6','Sanford','Jacquelyn','457 Arnaldo Meadow','Rhodafort','Kentucky',48054,'6830052797','Jessy_Simonis@raheem.biz','12345678'),('8','Halvorson','Dorothea','0602 Arnaldo Port','Lake Guidoville','Idaho',13041,'6771691338','Brent@breanne.biz','12345678'),('9','Sporer','Paige','16312 Mraz Ridge','North Gregory','Montana',11192,'2449394794','Rubie@lamar.us','12345678');
/*!40000 ALTER TABLE `Person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Post`
--

DROP TABLE IF EXISTS `Post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Post` (
  `ExpireDate` datetime DEFAULT NULL,
  `PostDate` datetime DEFAULT NULL,
  `CustomerID` char(9) NOT NULL,
  `AuctionID` char(11) NOT NULL,
  PRIMARY KEY (`CustomerID`,`AuctionID`),
  UNIQUE KEY `AuctionID_UNIQUE` (`AuctionID`),
  CONSTRAINT `Post_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `Customer` (`CustomerID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Post`
--

LOCK TABLES `Post` WRITE;
/*!40000 ALTER TABLE `Post` DISABLE KEYS */;
INSERT INTO `Post` VALUES ('2018-09-03 23:59:00','2018-09-01 23:59:00','5','1'),('2018-09-06 23:59:00','2018-09-03 23:59:00','5','2'),('2018-09-05 23:59:00','2018-09-01 23:59:00','5','4'),('2018-10-26 23:59:00','2018-10-23 23:59:00','5','5'),('2018-09-05 23:59:00','2018-09-01 23:59:00','5','8'),('2018-09-03 23:59:00','2018-09-01 23:59:00','6','10'),('2018-10-26 23:59:00','2018-10-23 23:59:00','6','3'),('2018-10-28 23:59:00','2018-10-23 23:59:00','6','6'),('2018-09-07 23:59:00','2018-09-04 23:59:00','6','7'),('2018-12-26 23:59:00','2018-12-23 23:59:00','6','9');
/*!40000 ALTER TABLE `Post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `Sold`
--

DROP TABLE IF EXISTS `Sold`;
/*!50001 DROP VIEW IF EXISTS `Sold`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `Sold` AS SELECT 
 1 AS `CustomerID`,
 1 AS `AuctionID`,
 1 AS `SoldPrice`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'CSE305_PROJ'
--

--
-- Final view structure for view `Bought`
--

/*!50001 DROP VIEW IF EXISTS `Bought`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ray`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `Bought` AS select `B1`.`CustomerID` AS `CustomerID`,`I`.`ItemID` AS `ItemID`,`I`.`ItemType` AS `ItemType` from ((`Bid` `B1` join `Item` `I`) join `Auction` `A`) where ((`B1`.`AuctionID` = `A`.`AuctionID`) and (`A`.`ItemID` = `I`.`ItemID`) and (`B1`.`BidTime` > 'Today') and `B1`.`BidPrice` >= all (select `B2`.`BidPrice` from `Bid` `B2` where (`B1`.`AuctionID` = `B2`.`AuctionID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `CustomerRepRevenue`
--

/*!50001 DROP VIEW IF EXISTS `CustomerRepRevenue`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`ray`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `CustomerRepRevenue` AS select `A`.`Monitor` AS `Monitor`,sum(`S`.`SoldPrice`) AS `Revenue` from (`Sold` `S` join `Auction` `A`) where (`A`.`AuctionID` = `S`.`AuctionID`) group by `A`.`Monitor` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `CustomerRevenue`
--

/*!50001 DROP VIEW IF EXISTS `CustomerRevenue`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `CustomerRevenue` AS select `P`.`CustomerID` AS `CustomerID`,sum(`S`.`SoldPrice`) AS `Revenue` from ((`Sold` `S` join `Post` `P`) join `Auction` `A`) where ((`P`.`AuctionID` = `A`.`AuctionID`) and (`A`.`AuctionID` = `S`.`AuctionID`)) group by `P`.`CustomerID` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `Sold`
--

/*!50001 DROP VIEW IF EXISTS `Sold`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `Sold` AS select `B1`.`CustomerID` AS `CustomerID`,`B1`.`AuctionID` AS `AuctionID`,`B1`.`BidPrice` AS `SoldPrice` from `Bid` `B1` where ((`B1`.`BidTime` < curdate()) and `B1`.`BidPrice` >= all (select `B2`.`BidPrice` from `Bid` `B2` where (`B1`.`AuctionID` = `B2`.`AuctionID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-10 15:22:29
