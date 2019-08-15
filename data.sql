-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: easypick
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.21-MariaDB

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
-- Table structure for table `usrgrp`
--

DROP TABLE IF EXISTS `usrgrp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usrgrp` (
  `usrgrpId` int(11) NOT NULL AUTO_INCREMENT,
  `creatdat` datetime DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `upddat` datetime DEFAULT NULL,
  `usrgrpcod` varchar(120) DEFAULT NULL,
  `usrgrpnam` varchar(120) DEFAULT NULL,
  `validfrm` datetime DEFAULT NULL,
  `validto` datetime DEFAULT NULL,
  `updby` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`usrgrpId`),
  UNIQUE KEY `usrgrpcod` (`usrgrpcod`),
  KEY `FKCE30F2D17C5CE63D` (`updby`),
  KEY `FKCE30F2D16C4F650E` (`user_id`),
  CONSTRAINT `FKCE30F2D16C4F650E` FOREIGN KEY (`user_id`) REFERENCES `usrgrp` (`usrgrpId`),
  CONSTRAINT `FKCE30F2D17C5CE63D` FOREIGN KEY (`updby`) REFERENCES `usrsetup` (`usrid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usrgrp`
--

LOCK TABLES `usrgrp` WRITE;
/*!40000 ALTER TABLE `usrgrp` DISABLE KEYS */;
INSERT INTO `usrgrp` VALUES (1,NULL,'Y',NULL,'adm','admin',NULL,NULL,1,1),(2,NULL,'Y',NULL,'CW','content writer',NULL,NULL,1,1);
/*!40000 ALTER TABLE `usrgrp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usrpasswd`
--

DROP TABLE IF EXISTS `usrpasswd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usrpasswd` (
  `usrpasswdId` int(11) NOT NULL AUTO_INCREMENT,
  `creatdat` datetime DEFAULT NULL,
  `passwd` varchar(120) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `upddat` datetime DEFAULT NULL,
  `validfrm` datetime DEFAULT NULL,
  `validto` datetime DEFAULT NULL,
  `updby` int(11) NOT NULL,
  `usrid` int(11) NOT NULL,
  PRIMARY KEY (`usrpasswdId`),
  KEY `FKC09FF9327C5CE63D` (`updby`),
  KEY `FKC09FF9327C5E78AC` (`usrid`),
  CONSTRAINT `FKC09FF9327C5CE63D` FOREIGN KEY (`updby`) REFERENCES `usrsetup` (`usrid`),
  CONSTRAINT `FKC09FF9327C5E78AC` FOREIGN KEY (`usrid`) REFERENCES `usrsetup` (`usrid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usrpasswd`
--

LOCK TABLES `usrpasswd` WRITE;
/*!40000 ALTER TABLE `usrpasswd` DISABLE KEYS */;
/*!40000 ALTER TABLE `usrpasswd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usrsetup`
--

DROP TABLE IF EXISTS `usrsetup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usrsetup` (
  `usrid` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(120) DEFAULT NULL,
  `first_name` varchar(120) DEFAULT NULL,
  `creatdat` datetime DEFAULT NULL,
  `last_name` varchar(120) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `upddat` datetime DEFAULT NULL,
  `usrnam` varchar(120) DEFAULT NULL,
  `validfrm` datetime DEFAULT NULL,
  `validto` datetime DEFAULT NULL,
  PRIMARY KEY (`usrid`),
  UNIQUE KEY `usrnam` (`usrnam`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usrsetup`
--

LOCK TABLES `usrsetup` WRITE;
/*!40000 ALTER TABLE `usrsetup` DISABLE KEYS */;
INSERT INTO `usrsetup` VALUES (1,'admin',NULL,NULL,NULL,'admin','Y',NULL,'admin',NULL,NULL),(2,'vineeth1',NULL,NULL,NULL,'vineeth1','Y',NULL,'vineeth1',NULL,NULL),(3,'vineeth2',NULL,NULL,NULL,'vineeth2','Y',NULL,'vineeth2',NULL,NULL);
/*!40000 ALTER TABLE `usrsetup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'easypick'
--

--
-- Dumping routines for database 'easypick'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-13 15:08:37
