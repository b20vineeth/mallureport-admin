CREATE DATABASE  IF NOT EXISTS `mod_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mod_db`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mod_db
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
-- Table structure for table `assign_privilege`
--

DROP TABLE IF EXISTS `assign_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assign_privilege` (
  `assign_privilege_id` int(11) NOT NULL AUTO_INCREMENT,
  `module` int(11) NOT NULL,
  `user_group_Id` int(11) NOT NULL,
  `Privilege_Group_Id` int(11) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `validity_From` datetime DEFAULT CURRENT_TIMESTAMP,
  `validity_to` datetime DEFAULT '2028-05-20 00:00:00',
  `user_id` int(11) NOT NULL,
  `updated_by` int(11) NOT NULL,
  `status` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`module`,`user_group_Id`),
  UNIQUE KEY `assign_privilege_id_UNIQUE` (`assign_privilege_id`),
  KEY `FK7C2F5361B4A771EF` (`user_id`),
  KEY `FK7C2F536123C8152A` (`module`),
  KEY `FK7C2F5361ABD0163B` (`updated_by`),
  KEY `FK7C2F536180BF9549` (`Privilege_Group_Id`),
  KEY `FK7C2F5361DDFB10B1` (`user_group_Id`),
  CONSTRAINT `FK7C2F536123C8152A` FOREIGN KEY (`module`) REFERENCES `module` (`module_id`),
  CONSTRAINT `FK7C2F536180BF9549` FOREIGN KEY (`Privilege_Group_Id`) REFERENCES `privilege_group` (`PrivilegeGroup_id`),
  CONSTRAINT `FK7C2F5361ABD0163B` FOREIGN KEY (`updated_by`) REFERENCES `usersetup` (`user_id`),
  CONSTRAINT `FK7C2F5361B4A771EF` FOREIGN KEY (`user_id`) REFERENCES `usersetup` (`user_id`),
  CONSTRAINT `FK7C2F5361DDFB10B1` FOREIGN KEY (`user_group_Id`) REFERENCES `usergroup` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assign_privilege`
--

LOCK TABLES `assign_privilege` WRITE;
/*!40000 ALTER TABLE `assign_privilege` DISABLE KEYS */;
INSERT INTO `assign_privilege` VALUES (2,1,1,1,'122','2018-08-05 16:58:24','2018-08-05 16:58:24','2018-08-05 16:58:24','2028-05-20 00:00:00',1,1,'Y');
/*!40000 ALTER TABLE `assign_privilege` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `mod_db`.`assign_privilege_BEFORE_UPDATE` BEFORE UPDATE ON `assign_privilege` FOR EACH ROW
BEGIN
INSERT INTO assign_privilege_his 
                     SELECT  h.* , NULL,NOW(),NEW.updated_by FROM assign_privilege h
                     WHERE assign_privilege_id = OLD.assign_privilege_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `assign_privilege_his`
--

DROP TABLE IF EXISTS `assign_privilege_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assign_privilege_his` (
  `assign_privilege_id` int(11) DEFAULT NULL,
  `module` int(11) DEFAULT NULL,
  `user_group_Id` int(11) DEFAULT NULL,
  `Privilege_Group_Id` int(11) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `validity_From` datetime DEFAULT NULL,
  `validity_to` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `version` int(11) NOT NULL AUTO_INCREMENT,
  `back_up_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `back_up_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`version`),
  KEY `FK7C2F5361B4A771EF` (`user_id`),
  KEY `FK7C2F536123C8152A` (`module`),
  KEY `FK7C2F5361ABD0163B` (`updated_by`),
  KEY `FK7C2F536180BF9549` (`Privilege_Group_Id`),
  KEY `FK7C2F5361DDFB10B1` (`user_group_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assign_privilege_his`
--

LOCK TABLES `assign_privilege_his` WRITE;
/*!40000 ALTER TABLE `assign_privilege_his` DISABLE KEYS */;
INSERT INTO `assign_privilege_his` VALUES (2,1,1,1,NULL,'2018-08-05 16:58:24','2018-08-05 16:58:24','2018-08-05 16:58:24','2028-05-20 00:00:00',1,1,'Y',1,'2018-08-05 16:59:08',1);
/*!40000 ALTER TABLE `assign_privilege_his` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `module` (
  `module_id` int(11) NOT NULL AUTO_INCREMENT,
  `module_code` varchar(25) NOT NULL,
  `module_name` varchar(120) DEFAULT NULL,
  `user_id` int(11) NOT NULL DEFAULT '1',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT NULL,
  `validity_From` datetime DEFAULT CURRENT_TIMESTAMP,
  `validity_to` datetime DEFAULT NULL,
  `updated_by` int(11) NOT NULL,
  `status` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`module_code`),
  UNIQUE KEY `module_id_UNIQUE` (`module_id`),
  KEY `module_userid_idx` (`user_id`),
  KEY `updated_byFK_idx` (`updated_by`),
  CONSTRAINT `module_userid` FOREIGN KEY (`user_id`) REFERENCES `usersetup` (`user_id`),
  CONSTRAINT `updated_byFK` FOREIGN KEY (`updated_by`) REFERENCES `usersetup` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES (1,'ALL','ALL',1,'2018-08-05 12:22:07','2017-09-02 00:00:00','2018-08-05 12:22:07','2020-09-02 00:00:00',1,'Y');
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `mod_db`.`module_BEFORE_UPDATE` BEFORE UPDATE ON `module` FOR EACH ROW
BEGIN
INSERT INTO module_his 
                     SELECT NULL, h.* ,NOW(),NEW.updated_by FROM module h WHERE module_id = OLD.module_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `module_his`
--

DROP TABLE IF EXISTS `module_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `module_his` (
  `version` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) NOT NULL,
  `module_code` varchar(25) NOT NULL,
  `module_name` varchar(120) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `validity_From` datetime DEFAULT NULL,
  `validity_to` datetime DEFAULT NULL,
  `updated_by` int(11) NOT NULL,
  `status` varchar(1) DEFAULT NULL,
  `backup_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `backup_user` int(11) NOT NULL,
  PRIMARY KEY (`version`),
  KEY `module_userid_idx` (`user_id`),
  KEY `updated_byFK_idx` (`updated_by`),
  KEY `module_back_up_8520_idx` (`backup_user`),
  CONSTRAINT `module_back_up_8520` FOREIGN KEY (`backup_user`) REFERENCES `usersetup` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module_his`
--

LOCK TABLES `module_his` WRITE;
/*!40000 ALTER TABLE `module_his` DISABLE KEYS */;
INSERT INTO `module_his` VALUES (1,1,'ALL','ALL',1,'0000-00-00 00:00:00','2017-09-02 00:00:00','2018-08-05 12:22:07','2020-09-02 00:00:00',1,'Y','2018-08-05 13:01:11',1);
/*!40000 ALTER TABLE `module_his` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilege`
--

DROP TABLE IF EXISTS `privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilege` (
  `privilege_id` int(11) NOT NULL AUTO_INCREMENT,
  `module` int(11) NOT NULL,
  `privile_type` int(11) NOT NULL,
  `privilege_Code` varchar(250) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `validity_From` datetime DEFAULT CURRENT_TIMESTAMP,
  `validity_to` datetime DEFAULT '2028-05-10 00:00:00',
  `updated_by` int(11) NOT NULL,
  `status` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`module`,`privilege_Code`),
  UNIQUE KEY `privilege_id_UNIQUE` (`privilege_id`),
  KEY `FKA1FAF6B1B4A771EF` (`user_id`),
  KEY `FKA1FAF6B123C8152A` (`module`),
  KEY `FKA1FAF6B114F67A5F` (`privile_type`),
  KEY `FKA1FAF6B1ABD0163B` (`updated_by`),
  CONSTRAINT `FKA1FAF6B114F67A5F` FOREIGN KEY (`privile_type`) REFERENCES `privilege_type` (`Privilege_type_id`),
  CONSTRAINT `FKA1FAF6B123C8152A` FOREIGN KEY (`module`) REFERENCES `module` (`module_id`),
  CONSTRAINT `FKA1FAF6B1ABD0163B` FOREIGN KEY (`updated_by`) REFERENCES `usersetup` (`user_id`),
  CONSTRAINT `FKA1FAF6B1B4A771EF` FOREIGN KEY (`user_id`) REFERENCES `usersetup` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege`
--

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
INSERT INTO `privilege` VALUES (1,1,1,'test','test',1,'2018-08-05 13:34:59','2018-08-05 13:34:59','2018-08-05 13:34:59','2028-04-10 00:00:00',1,'Y');
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `mod_db`.`privilege_BEFORE_UPDATE` BEFORE UPDATE ON `privilege` FOR EACH ROW
BEGIN
INSERT INTO privilege_his 
                     SELECT  h.* , NULL,NOW(),NEW.updated_by 
                     FROM privilege h 
                     WHERE privilege_id = OLD.privilege_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `privilege_group`
--

DROP TABLE IF EXISTS `privilege_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilege_group` (
  `PrivilegeGroup_id` int(11) NOT NULL AUTO_INCREMENT,
  `module` int(11) NOT NULL,
  `Privilege_Group_Code` varchar(25) NOT NULL,
  `Privilege_Group_name` varchar(25) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `validity_From` datetime DEFAULT CURRENT_TIMESTAMP,
  `validity_to` datetime DEFAULT '2028-08-05 00:00:00',
  `updated_by` int(11) NOT NULL,
  `status` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`module`,`Privilege_Group_Code`),
  UNIQUE KEY `PrivilegeGroup_id_UNIQUE` (`PrivilegeGroup_id`),
  KEY `FK34D3D751B4A771EF` (`user_id`),
  KEY `FK34D3D75123C8152A` (`module`),
  KEY `FK34D3D751ABD0163B` (`updated_by`),
  CONSTRAINT `FK34D3D75123C8152A` FOREIGN KEY (`module`) REFERENCES `module` (`module_id`),
  CONSTRAINT `FK34D3D751ABD0163B` FOREIGN KEY (`updated_by`) REFERENCES `usersetup` (`user_id`),
  CONSTRAINT `FK34D3D751B4A771EF` FOREIGN KEY (`user_id`) REFERENCES `usersetup` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege_group`
--

LOCK TABLES `privilege_group` WRITE;
/*!40000 ALTER TABLE `privilege_group` DISABLE KEYS */;
INSERT INTO `privilege_group` VALUES (1,1,'admin','admin','admin',1,'2018-08-05 16:51:37','2018-08-05 16:51:37','2018-08-05 16:51:37','2028-08-05 00:01:00',1,'Y');
/*!40000 ALTER TABLE `privilege_group` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `mod_db`.`privilege_group_BEFORE_UPDATE` BEFORE UPDATE ON `privilege_group` FOR EACH ROW
BEGIN
INSERT INTO privilege_group_his 
                     SELECT  h.* , NULL,NOW(),NEW.updated_by FROM privilege_group h
                     WHERE PrivilegeGroup_id = OLD.PrivilegeGroup_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `privilege_group_his`
--

DROP TABLE IF EXISTS `privilege_group_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilege_group_his` (
  `PrivilegeGroup_id` int(11) DEFAULT NULL,
  `module` int(11) DEFAULT NULL,
  `Privilege_Group_Code` varchar(25) DEFAULT NULL,
  `Privilege_Group_name` varchar(25) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `validity_From` datetime DEFAULT NULL,
  `validity_to` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `version` int(11) NOT NULL AUTO_INCREMENT,
  `back_up_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `back_up_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`version`),
  KEY `FK34D3D751B4A771EF` (`user_id`),
  KEY `FK34D3D75123C8152A` (`module`),
  KEY `FK34D3D751ABD0163B` (`updated_by`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege_group_his`
--

LOCK TABLES `privilege_group_his` WRITE;
/*!40000 ALTER TABLE `privilege_group_his` DISABLE KEYS */;
INSERT INTO `privilege_group_his` VALUES (3,1,'admin','admin','admin1',1,'2018-08-05 16:51:37','2018-08-05 16:51:37','2018-08-05 16:51:37','2028-08-05 00:01:00',1,'Y',1,'2018-08-05 16:57:26',1),(3,1,'admin','admin','admin',1,'2018-08-05 16:51:37','2018-08-05 16:51:37','2018-08-05 16:51:37','2028-08-05 00:01:00',1,'Y',2,'2018-08-05 16:58:15',1);
/*!40000 ALTER TABLE `privilege_group_his` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilege_his`
--

DROP TABLE IF EXISTS `privilege_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilege_his` (
  `privilege_id` int(11) DEFAULT NULL,
  `module` int(11) DEFAULT NULL,
  `privile_type` int(11) DEFAULT NULL,
  `privilege_Code` varchar(250) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `validity_From` datetime DEFAULT NULL,
  `validity_to` datetime DEFAULT NULL,
  `updated_by` int(11) NOT NULL,
  `status` varchar(1) DEFAULT NULL,
  `version` int(11) NOT NULL AUTO_INCREMENT,
  `backup_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `back_up_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`version`),
  KEY `FKA1FAF6B1B4A771EF` (`user_id`),
  KEY `FKA1FAF6B123C8152A` (`module`),
  KEY `FKA1FAF6B114F67A5F` (`privile_type`),
  KEY `FKA1FAF6B1ABD0163B` (`updated_by`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege_his`
--

LOCK TABLES `privilege_his` WRITE;
/*!40000 ALTER TABLE `privilege_his` DISABLE KEYS */;
INSERT INTO `privilege_his` VALUES (1,1,1,'test','test',1,'2018-08-05 13:34:59','2018-08-05 13:34:59','2018-08-05 13:34:59','2028-05-10 00:00:00',1,'Y',1,'2018-08-05 14:36:08',1);
/*!40000 ALTER TABLE `privilege_his` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilege_type`
--

DROP TABLE IF EXISTS `privilege_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilege_type` (
  `Privilege_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `module` int(11) NOT NULL,
  `Privilege_type_code` varchar(25) NOT NULL,
  `Privilege_type_name` varchar(120) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(11) NOT NULL,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `validity_From` datetime DEFAULT CURRENT_TIMESTAMP,
  `validity_to` datetime DEFAULT '2018-05-10 00:00:00',
  `updated_by` int(11) NOT NULL,
  `status` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`module`,`Privilege_type_code`),
  UNIQUE KEY `Privilege_type_id_UNIQUE` (`Privilege_type_id`),
  KEY `FK4A30A428B4A771EF` (`user_id`),
  KEY `FK4A30A42823C8152A` (`module`),
  KEY `FK4A30A428ABD0163B` (`updated_by`),
  CONSTRAINT `FK4A30A42823C8152A` FOREIGN KEY (`module`) REFERENCES `module` (`module_id`),
  CONSTRAINT `FK4A30A428ABD0163B` FOREIGN KEY (`updated_by`) REFERENCES `usersetup` (`user_id`),
  CONSTRAINT `FK4A30A428B4A771EF` FOREIGN KEY (`user_id`) REFERENCES `usersetup` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege_type`
--

LOCK TABLES `privilege_type` WRITE;
/*!40000 ALTER TABLE `privilege_type` DISABLE KEYS */;
INSERT INTO `privilege_type` VALUES (1,1,'page','page','ddd',NULL,1,NULL,NULL,NULL,1,'Y');
/*!40000 ALTER TABLE `privilege_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `mod_db`.`privilege_type_BEFORE_UPDATE` BEFORE UPDATE ON `privilege_type` FOR EACH ROW
BEGIN
INSERT INTO privilege_type_his 
                     SELECT  h.* , NULL,NOW(),NEW.updated_by FROM privilege_type h
                     WHERE Privilege_type_id = OLD.Privilege_type_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `privilege_type_his`
--

DROP TABLE IF EXISTS `privilege_type_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilege_type_his` (
  `Privilege_type_id` int(11) DEFAULT NULL,
  `module` int(11) DEFAULT NULL,
  `Privilege_type_code` varchar(25) DEFAULT NULL,
  `Privilege_type_name` varchar(120) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `validity_From` datetime DEFAULT NULL,
  `validity_to` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `version` int(11) NOT NULL AUTO_INCREMENT,
  `back_up_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `back_up_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`version`),
  KEY `FK4A30A428B4A771EF` (`user_id`),
  KEY `FK4A30A42823C8152A` (`module`),
  KEY `FK4A30A428ABD0163B` (`updated_by`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege_type_his`
--

LOCK TABLES `privilege_type_his` WRITE;
/*!40000 ALTER TABLE `privilege_type_his` DISABLE KEYS */;
INSERT INTO `privilege_type_his` VALUES (1,1,'page','page',NULL,NULL,1,NULL,NULL,NULL,1,'Y',1,'2018-08-05 16:32:38',1);
/*!40000 ALTER TABLE `privilege_type_his` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usergroup`
--

DROP TABLE IF EXISTS `usergroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usergroup` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `module` int(11) NOT NULL,
  `group_code` varchar(25) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `validity_From` datetime DEFAULT CURRENT_TIMESTAMP,
  `validity_to` datetime DEFAULT NULL,
  `updated_by` int(11) NOT NULL,
  `status` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`module`,`group_code`),
  UNIQUE KEY `group_id_UNIQUE` (`group_id`),
  KEY `FK12E9C174B4A771EF` (`user_id`),
  KEY `FK12E9C17423C8152A` (`module`),
  KEY `FK12E9C174ABD0163B` (`updated_by`),
  CONSTRAINT `FK12E9C17423C8152A` FOREIGN KEY (`module`) REFERENCES `module` (`module_id`),
  CONSTRAINT `FK12E9C174ABD0163B` FOREIGN KEY (`updated_by`) REFERENCES `usersetup` (`user_id`),
  CONSTRAINT `FK12E9C174B4A771EF` FOREIGN KEY (`user_id`) REFERENCES `usersetup` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usergroup`
--

LOCK TABLES `usergroup` WRITE;
/*!40000 ALTER TABLE `usergroup` DISABLE KEYS */;
INSERT INTO `usergroup` VALUES (1,1,'ADMIN','admin',1,'2018-08-05 13:25:16','2018-08-05 13:25:16','2018-08-05 13:25:16',1,'Y');
/*!40000 ALTER TABLE `usergroup` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `mod_db`.`usergroup_BEFORE_UPDATE` BEFORE UPDATE ON `usergroup` FOR EACH ROW
BEGIN
INSERT INTO usergroup_his 
                     SELECT  h.* ,NULL,NOW(),NEW.updated_by FROM usergroup h 
                     WHERE group_id = OLD.group_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `usergroup_his`
--

DROP TABLE IF EXISTS `usergroup_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usergroup_his` (
  `group_id` int(11) DEFAULT NULL,
  `module` int(11) DEFAULT NULL,
  `group_code` varchar(25) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `validity_From` datetime DEFAULT NULL,
  `validity_to` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `version` int(11) NOT NULL AUTO_INCREMENT,
  `back_up_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `back_up_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`version`),
  KEY `FK12E9C174B4A771EF` (`user_id`),
  KEY `FK12E9C17423C8152A` (`module`),
  KEY `FK12E9C174ABD0163B` (`updated_by`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usergroup_his`
--

LOCK TABLES `usergroup_his` WRITE;
/*!40000 ALTER TABLE `usergroup_his` DISABLE KEYS */;
INSERT INTO `usergroup_his` VALUES (1,1,'ADMIN','admin',1,'2018-08-05 13:25:16','2018-08-05 13:25:16',NULL,1,'Y',1,'2018-08-05 13:25:25',1);
/*!40000 ALTER TABLE `usergroup_his` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usersetup`
--

DROP TABLE IF EXISTS `usersetup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usersetup` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `module` int(11) NOT NULL,
  `user_name` varchar(125) NOT NULL,
  `email` varchar(100) NOT NULL,
  `first_name` varchar(125) DEFAULT NULL,
  `last_name` varchar(125) DEFAULT NULL,
  `mobile_number` varchar(12) NOT NULL,
  `password` varchar(250) DEFAULT NULL,
  `prefix_mob` varchar(5) DEFAULT '91',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `validity_From` datetime DEFAULT CURRENT_TIMESTAMP,
  `validity_to` datetime DEFAULT '2028-08-05 00:00:00',
  `status` varchar(1) DEFAULT 'Y',
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`module`,`user_name`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  KEY `FK154FF59223C8152A` (`module`),
  CONSTRAINT `FK154FF59223C8152A` FOREIGN KEY (`module`) REFERENCES `module` (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usersetup`
--

LOCK TABLES `usersetup` WRITE;
/*!40000 ALTER TABLE `usersetup` DISABLE KEYS */;
INSERT INTO `usersetup` VALUES (1,1,'Admin','admin','Vineeth','B','8089248382','123456','91','2018-08-05 12:23:15','2018-08-05 12:23:15','2018-08-05 12:23:15','2028-08-05 12:23:15','Y',1);
/*!40000 ALTER TABLE `usersetup` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `mod_db`.`usersetup_BEFORE_UPDATE` BEFORE UPDATE ON `usersetup` FOR EACH ROW
BEGIN
INSERT INTO usersetup_his 
                     SELECT  h.* ,NULL,NEW.updated_by,NOW() FROM usersetup h 
                     WHERE user_id = OLD.user_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `usersetup_his`
--

DROP TABLE IF EXISTS `usersetup_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usersetup_his` (
  `user_id` int(11) DEFAULT NULL,
  `module` int(11) NOT NULL,
  `user_name` varchar(125) NOT NULL,
  `email` varchar(100) NOT NULL,
  `first_name` varchar(125) DEFAULT NULL,
  `last_name` varchar(125) DEFAULT NULL,
  `mobile_number` varchar(12) NOT NULL,
  `password` varchar(250) DEFAULT NULL,
  `prefix_mob` varchar(5) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `validity_From` datetime DEFAULT NULL,
  `validity_to` datetime DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `version` int(11) NOT NULL AUTO_INCREMENT,
  `back_up_by` int(11) DEFAULT NULL,
  `back_up_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`version`),
  KEY `FK154FF59223C8152A` (`module`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usersetup_his`
--

LOCK TABLES `usersetup_his` WRITE;
/*!40000 ALTER TABLE `usersetup_his` DISABLE KEYS */;
INSERT INTO `usersetup_his` VALUES (1,1,'Admin','admin','Vineeth','B','8089248382','123456','91','2018-08-05 12:23:15','2018-08-05 12:23:15','2018-08-05 12:23:15','2028-08-05 12:23:15','Y',NULL,1,2147483647,'0000-00-00 00:00:00'),(1,1,'Admin','admin','Vineeth','B','8089248382','123456','91','2018-08-05 12:23:15','2018-08-05 12:23:15','2018-08-05 12:23:15','2028-08-05 12:23:15','N',1,2,1,'2018-08-05 13:19:18');
/*!40000 ALTER TABLE `usersetup_his` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mod_db'
--

--
-- Dumping routines for database 'mod_db'
--
/*!50003 DROP PROCEDURE IF EXISTS `log_update` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `log_update`(
       
      id INT(11)
      
           
)
BEGIN
   
   -- IF pAction = "B" THEN
        INSERT INTO `mod_db`.`test` (`id`) VALUES ('1');

    -- END IF;
 
     
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-05 17:02:18
