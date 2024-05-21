-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: cobrokea
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `private_conversation`
--

DROP TABLE IF EXISTS `private_conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `private_conversation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `private_conversation_participants`
--

DROP TABLE IF EXISTS `private_conversation_participants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `private_conversation_participants` (
  `conversations_id` bigint NOT NULL,
  `participants_id` bigint NOT NULL,
  KEY `FKoqqwrxwvwrv4mdcdf5l12oeyf` (`participants_id`),
  KEY `FK664fwtvj3g6b7jlstb0kjycop` (`conversations_id`),
  CONSTRAINT `FK664fwtvj3g6b7jlstb0kjycop` FOREIGN KEY (`conversations_id`) REFERENCES `private_conversation` (`id`),
  CONSTRAINT `FKoqqwrxwvwrv4mdcdf5l12oeyf` FOREIGN KEY (`participants_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `private_message`
--

DROP TABLE IF EXISTS `private_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `private_message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` tinyint NOT NULL,
  `text` varchar(255) NOT NULL,
  `timestamp` datetime(6) NOT NULL,
  `private_conversation_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKms54gs8u5k0sg9qshxs2qirol` (`private_conversation_id`),
  KEY `FKqnnmwm2kqm0lt10bfhittjd84` (`user_id`),
  CONSTRAINT `FKms54gs8u5k0sg9qshxs2qirol` FOREIGN KEY (`private_conversation_id`) REFERENCES `private_conversation` (`id`),
  CONSTRAINT `FKqnnmwm2kqm0lt10bfhittjd84` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `private_message_chk_1` CHECK ((`status` between 0 and 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bathroom_qty` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `internal_size` double NOT NULL,
  `location` varchar(255) NOT NULL,
  `room_qty` int DEFAULT NULL,
  `size` double NOT NULL,
  `size_unit` varchar(255) NOT NULL,
  `amenities_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9dinxegjdnibe7e395976nwaq` (`amenities_id`),
  CONSTRAINT `FKgjysbgd2doyipw360d8ybr5ht` FOREIGN KEY (`amenities_id`) REFERENCES `property_amenities` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property_amenities`
--

DROP TABLE IF EXISTS `property_amenities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property_amenities` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `air_conditioning` bit(1) NOT NULL,
  `balcony` bit(1) NOT NULL,
  `bed_linens` bit(1) NOT NULL,
  `cable` bit(1) NOT NULL,
  `event_space` bit(1) NOT NULL,
  `expenses` bit(1) NOT NULL,
  `garage` bit(1) NOT NULL,
  `gas` bit(1) NOT NULL,
  `gym` bit(1) NOT NULL,
  `internet` bit(1) NOT NULL,
  `laundry` bit(1) NOT NULL,
  `open_view` bit(1) NOT NULL,
  `pool` bit(1) NOT NULL,
  `property_tax` bit(1) NOT NULL,
  `security` bit(1) NOT NULL,
  `water` bit(1) NOT NULL,
  `property_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_f4l2em2bl6ucokt1s3w4nky9q` (`property_id`),
  CONSTRAINT `FK6jdjp7ndbpbwpgyhivp2jarn1` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property_photo`
--

DROP TABLE IF EXISTS `property_photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property_photo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_principal` bit(1) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `url` bit(1) NOT NULL,
  `property_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh4s5im1ldcccf9pmh9v0r9nma` (`property_id`),
  CONSTRAINT `FKh4s5im1ldcccf9pmh9v0r9nma` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property_photos`
--

DROP TABLE IF EXISTS `property_photos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property_photos` (
  `property_id` bigint NOT NULL,
  `photos_id` bigint NOT NULL,
  UNIQUE KEY `UK_b37j2jcqtgj94rjght3movfgc` (`photos_id`),
  KEY `FKc3elvu5gliw5r1pq1c2nuxe6c` (`property_id`),
  CONSTRAINT `FK5ine8sitsp4s4ayup4s83n5sc` FOREIGN KEY (`photos_id`) REFERENCES `property_photo` (`id`),
  CONSTRAINT `FKc3elvu5gliw5r1pq1c2nuxe6c` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `publication_property_offer`
--

DROP TABLE IF EXISTS `publication_property_offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publication_property_offer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `currency` tinyint NOT NULL,
  `map_latitude` float DEFAULT NULL,
  `map_longitude` float DEFAULT NULL,
  `map_radius` int DEFAULT NULL,
  `max_occupants` int DEFAULT NULL,
  `price` double NOT NULL,
  `property_offer_type` tinyint NOT NULL,
  `status` tinyint NOT NULL,
  `title` varchar(255) NOT NULL,
  `property_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6s4gumdurp3ct77ojf3gk7qln` (`property_id`),
  KEY `FKcadw84svelnftlphu704tkcpr` (`user_id`),
  CONSTRAINT `FK6s4gumdurp3ct77ojf3gk7qln` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`),
  CONSTRAINT `FKcadw84svelnftlphu704tkcpr` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `publication_property_offer_chk_1` CHECK ((`currency` between 0 and 2)),
  CONSTRAINT `publication_property_offer_chk_2` CHECK ((`property_offer_type` between 0 and 1)),
  CONSTRAINT `publication_property_offer_chk_3` CHECK ((`status` between 0 and 3))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `publication_user_request`
--

DROP TABLE IF EXISTS `publication_user_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publication_user_request` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` tinyint NOT NULL,
  `text` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4rlbe4a4nyxoq09u2120xp1se` (`user_id`),
  CONSTRAINT `FK4rlbe4a4nyxoq09u2120xp1se` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `publication_user_request_chk_1` CHECK ((`status` between 0 and 3))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rating` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `opinion` varchar(255) DEFAULT NULL,
  `publication_id` bigint NOT NULL,
  `publication_type` tinyint NOT NULL,
  `rating` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpn05vbx6usw0c65tcyuce4dw5` (`user_id`),
  CONSTRAINT `FKpn05vbx6usw0c65tcyuce4dw5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `rating_chk_1` CHECK ((`publication_type` between 0 and 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `error_reason` varchar(255) DEFAULT NULL,
  `payment_type` tinyint NOT NULL,
  `price` double NOT NULL,
  `publication_id` bigint NOT NULL,
  `publication_type` tinyint NOT NULL,
  `status` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `transaction_chk_1` CHECK ((`payment_type` between 0 and 3)),
  CONSTRAINT `transaction_chk_2` CHECK ((`publication_type` between 0 and 1)),
  CONSTRAINT `transaction_chk_3` CHECK ((`status` between 0 and 3))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `authorities` varbinary(255) DEFAULT NULL,
  `document` varchar(255) NOT NULL,
  `document_type` tinyint NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `is_account_non_expired` bit(1) NOT NULL,
  `is_account_non_locked` bit(1) NOT NULL,
  `is_credentials_non_expired` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `nationality` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `type` tinyint NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_chk_1` CHECK ((`document_type` between 0 and 1)),
  CONSTRAINT `user_chk_2` CHECK ((`type` between 0 and 3))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-21  2:04:03