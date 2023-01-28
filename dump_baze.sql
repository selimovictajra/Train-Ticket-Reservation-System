-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: sql.freedb.tech    Database: freedb_MojaRPRbaza
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Reservations`
--

DROP TABLE IF EXISTS `Reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Reservations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `train_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `train_id_idx` (`train_id`),
  CONSTRAINT `train_id` FOREIGN KEY (`train_id`) REFERENCES `Trains` (`id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservations`
--

LOCK TABLES `Reservations` WRITE;
/*!40000 ALTER TABLE `Reservations` DISABLE KEYS */;
INSERT INTO `Reservations` VALUES (1,3,1),(4,5,3),(5,6,7),(6,6,6),(7,16,11),(8,16,5),(9,17,5),(10,7,1),(11,11,7),(12,9,14),(13,8,12),(14,8,16),(15,9,5),(16,6,16),(17,19,17),(18,19,6),(19,9,15),(20,4,6),(21,5,4),(22,24,1),(23,24,11),(24,20,12),(25,19,1),(26,19,1);
/*!40000 ALTER TABLE `Reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Trains`
--

DROP TABLE IF EXISTS `Trains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Trains` (
  `id` int NOT NULL AUTO_INCREMENT,
  `route` varchar(45) NOT NULL,
  `departure` datetime NOT NULL,
  `capacity` int NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trains`
--

LOCK TABLES `Trains` WRITE;
/*!40000 ALTER TABLE `Trains` DISABLE KEYS */;
INSERT INTO `Trains` VALUES (1,'Sarajevo - Mostar','2022-12-15 07:00:00',100,15),(2,'Sarajevo - Ploce','2023-01-20 07:00:00',100,15),(3,'Sarajevo - Konjic','2023-01-30 07:00:00',100,15),(4,'Berlin - Hamburg','2023-01-21 06:10:00',100,20),(5,'Milano - Venice','2023-01-27 08:30:00',100,25),(6,'Frankfurt - Koln','2023-01-30 07:40:00',100,20),(7,'Florence - Rome','2024-01-11 06:00:00',100,20),(11,'Paris - Lyon','2023-01-29 07:30:00',100,25),(12,'Barcelona - Sevilla','2023-02-25 05:10:00',100,30),(13,'Sarajevo - Visoko','2023-03-03 06:00:00',100,15),(14,'Amsterdam - Berlin','2023-01-19 06:30:00',100,25),(15,'Zagreb - Belgrade','2023-02-17 06:40:00',100,20),(16,'Milano - Venice','2023-02-17 09:10:00',100,20),(17,'Sarajevo - Ploce','2023-03-17 07:00:00',100,10);
/*!40000 ALTER TABLE `Trains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `role` tinyint NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'Tajra Selimovic',1,'tajra27','tajra27'),(2,'Nika',0,'nika1','nika1'),(3,'Ajla Aljovic',0,'ajla','ajla'),(4,'Belma Ceric',0,'bema','bema'),(5,'Uma Ceric',0,'umi','umi'),(6,'Taida Selimovic',0,'taida123','brazil123'),(7,'Sead Selimovic',0,'sead1','sead123'),(8,'Nejra Adilovic',0,'nejradilovic','nejaa1'),(9,'Dalila Krslak',0,'dalilakrslak','dalila6'),(10,'Hana Mahmutovic',0,'hanuta','hanam1'),(11,'Emina Efendic',0,'eminae3','emina3'),(12,'Edwin Graca',0,'egraca1','edwingraca'),(13,'Nejra Hidic',0,'nejrah','nejajah'),(14,'Eman Alibalic',0,'emanmane','emana'),(15,'Edna Basic',0,'ednab','ednab'),(16,'Nejra Alickovic',0,'nejra20','nejra20'),(17,'Emina Alickovic',0,'emiina','eminaa31'),(18,'Admir Mehmedagic',0,'amehmedagi1','123456'),(19,'Amina Abdagic',0,'aminaa','aminaa'),(20,'Edisa Velagic',0,'keka3','keka3'),(21,'Edin Ceric',0,'cera1','cera1'),(22,'Hana Ceric',0,'hanac','hanac'),(23,'Mehmed Ceric',0,'mesa','mesac'),(24,'Nead Velagic',0,'nead!','nead!'),(25,'Adis Velagic',0,'adov','adov'),(26,'Razija Ceric',0,'nanaraza','nanaraza'),(27,'Admira Selimovic',0,'admiras','admiras');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-28 12:11:49
