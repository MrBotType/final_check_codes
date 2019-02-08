CREATE DATABASE  IF NOT EXISTS `news` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `news`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: news
-- ------------------------------------------------------
-- Server version	5.1.45-community

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
-- Table structure for table `user_favourite_article`
--

DROP TABLE IF EXISTS `user_favourite_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_favourite_article` (
  `fa_id` int(11) NOT NULL AUTO_INCREMENT,
  `fa_us_id` int(11) NOT NULL,
  `fa_ar_id` int(11) NOT NULL,
  PRIMARY KEY (`fa_id`),
  KEY `fa_us_id` (`fa_us_id`),
  KEY `fa_ar_id` (`fa_ar_id`),
  CONSTRAINT `fa_us_id` FOREIGN KEY (`fa_us_id`) REFERENCES `user` (`us_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fa_ar_id` FOREIGN KEY (`fa_ar_id`) REFERENCES `favourite_article` (`ar_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=297 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_favourite_article`
--

LOCK TABLES `user_favourite_article` WRITE;
/*!40000 ALTER TABLE `user_favourite_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_favourite_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favourite_article`
--

DROP TABLE IF EXISTS `favourite_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favourite_article` (
  `ar_id` int(11) NOT NULL AUTO_INCREMENT,
  `ar_title` varchar(500) NOT NULL,
  `ar_description` varchar(500) NOT NULL,
  `ar_la_id` int(11) NOT NULL,
  PRIMARY KEY (`ar_id`),
  KEY `ar_la_id` (`ar_la_id`),
  CONSTRAINT `ar_la_id` FOREIGN KEY (`ar_la_id`) REFERENCES `language` (`la_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favourite_article`
--

LOCK TABLES `favourite_article` WRITE;
/*!40000 ALTER TABLE `favourite_article` DISABLE KEYS */;
INSERT INTO `favourite_article` VALUES (7,'Fuorionda Conte-Merkel: \"Salvini è contro tutto\". Il vicepremier replica: \"Non è vero\"','',7),(8,'Borsa, Shanghai a +0,50% - Ultima Ora','Le Borse cinesi viaggiano positive dopo il nuovo round negoziale di Washington sul commercio tra Usa\r\ne Cina che, pur se chiuso senza un accordo generale, ha segnato passi in avanti nelle trattative e\r\nl\'impegno a continuare il confronto con una delegazione .…',7),(9,'???? 4 ?????.. ????? ???? \"?????\" ????? ???? ???? ???? ????? ????','???? 4 ????? ????? ???? ????? ????? ???? ???? ???? ????? ???...??????',1),(10,'Romain Ntamack : « La déception prédomine » après la défaite des Bleus contre le Pays de Galles','Romain Ntamack a estimé que sa première sélection chez les Bleus, contre le Pays de Galles ce vendredi, restera un moment fort de sa carrière malgré la défaite.',5),(11,'Camille Lopez : «On leur donne le match»','Comme la plupart de ses coéquipiers, Camille Lopez regrette cette deuxième période manquée contre le pays de Galles (19-24) et ces points offerts sur des erreurs individuelles.',5),(12,'Tottenham prête le jeune Shayon Harrison à Melbourne City','Shayon Harrison, attaquant prometteur de Tottenham, finira la saison en prêt à Melbourne City, en Australie.',5),(13,'Arthur Iturria (France) : «Pas le droit de perdre comme ça» contre les Gallois','Arthur Iturria, troisième ligne de l\'équipe de France, reconnait que les Bleus ont donné le match au Pays de Galles (19-24) ce vendredi.',5),(14,'Enquête ouverte après la fuite d\'une vidéo embarrassante pour la police de Toulouse','À Toulouse, en pleine manifestation de gilets jaunes, des agents réagissent depuis le Centre de commandement et d\'information de la police.',5),(15,'Championnats du monde : six Bleu(e)s en finale du skicross','Quatre Français et deux Françaises ont obtenu leur place en finale du skicross des Mondiaux (à suivre sur la chaîne L\'Equipe), vendredi.',5),(16,'Waste Management 2019 : Électrique Phoenix Open','Le Phoenix Open est l\'attraction de ce début d\'année sur le PGA Tour et en particulier sa tribune du 16.',5),(17,'À quelle heure et sur quelle chaîne suivre OL-PSG ?','Actuel 3e de la Ligue 1, l\'Olympique Lyonnais reçoit le PSG, ce dimanche soir à 21 heures.',5),(18,'Les Etats-Unis quittent un traité au risque de relancer la Guerre froide','Les Etats-Unis ont mis vendredi à exécution leur menace de se retirer d’un traité de désarmement nucléaire crucial avec la Russie, accusée de violer ce texte signé lors de la Guerre froide, au risque d’une nouvelle course aux armements.',5);
/*!40000 ALTER TABLE `favourite_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `ro_id` int(11) NOT NULL,
  `ro_name` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`ro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin'),(2,NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `us_id` int(11) NOT NULL AUTO_INCREMENT,
  `us_name` varchar(30) NOT NULL,
  `us_password` varchar(30) NOT NULL,
  `us_ro_id` int(11) NOT NULL,
  `us_la_id` int(11) NOT NULL,
  `us_blacklisted` tinyint(1) DEFAULT NULL,
  `us_email` varchar(50) NOT NULL,
  PRIMARY KEY (`us_id`),
  KEY `us_ro_id` (`us_ro_id`),
  KEY `us_la_id` (`us_la_id`),
  CONSTRAINT `us_la_id` FOREIGN KEY (`us_la_id`) REFERENCES `language` (`la_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `us_ro_id` FOREIGN KEY (`us_ro_id`) REFERENCES `role` (`ro_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'soumya','1234',1,1,0,'soumya@gmail.com'),(2,'sarbajat','1234',2,1,1,'sarbo@gmail.com'),(39,'soumya_soumya','1234',2,1,0,'soumya_soumya@gmail.com'),(40,'soumya_soumya1','1234',2,1,0,'soumya_soumya1@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `la_id` int(11) NOT NULL,
  `la_name` varchar(20) NOT NULL,
  `la_code` varchar(2) NOT NULL,
  PRIMARY KEY (`la_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,'Arabic','ar'),(2,'German','de'),(3,'English','en'),(4,'Spanish','es'),(5,'French','fr'),(6,'Hebrew','he'),(7,'Italian','it'),(8,'Dutch','nl'),(9,'Norwegian','no'),(10,'Portuguese','pt'),(11,'Russian','ru'),(12,'Northern Sami','se'),(13,'Urdu','ud'),(14,'Chinese','zh');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-04 10:17:44
