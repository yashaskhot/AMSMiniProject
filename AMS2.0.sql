-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: ams2
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `abs_reason`
--

DROP TABLE IF EXISTS `abs_reason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `abs_reason` (
  `abs_id` varchar(20) NOT NULL,
  `reason` varchar(100) NOT NULL,
  PRIMARY KEY (`abs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `absent_register`
--

DROP TABLE IF EXISTS `absent_register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `absent_register` (
  `attd_id` bigint NOT NULL,
  `lesson_id` varchar(45) NOT NULL,
  `stud_id` varchar(45) NOT NULL,
  `absent` tinyint NOT NULL,
  `abs_id` varchar(20) NOT NULL,
  PRIMARY KEY (`attd_id`,`abs_id`,`lesson_id`,`stud_id`),
  KEY `lesson_id_idx` (`lesson_id`),
  KEY `syud_id_idx` (`stud_id`),
  KEY `abs_id_idx` (`abs_id`),
  CONSTRAINT `abs_id` FOREIGN KEY (`abs_id`) REFERENCES `abs_reason` (`abs_id`),
  CONSTRAINT `lesson_id` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`lesson_id`),
  CONSTRAINT `sttud_id` FOREIGN KEY (`stud_id`) REFERENCES `student_info` (`stud_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `course_id` varchar(20) NOT NULL,
  `course` varchar(45) NOT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `credentials`
--

DROP TABLE IF EXISTS `credentials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credentials` (
  `teacher_id` varchar(20) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` blob NOT NULL,
  PRIMARY KEY (`teacher_id`),
  CONSTRAINT `teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher_details` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lesson` (
  `lesson_id` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `teach_id` varchar(20) NOT NULL,
  `course_id` varchar(45) NOT NULL,
  PRIMARY KEY (`lesson_id`,`teach_id`,`course_id`),
  KEY `teacher_id_idx` (`teach_id`),
  KEY `course_id_idx` (`course_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `teach_id` FOREIGN KEY (`teach_id`) REFERENCES `teacher_details` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `parent_info`
--

DROP TABLE IF EXISTS `parent_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parent_info` (
  `parent_id` int NOT NULL,
  `fisrtname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `phonenumber` varchar(45) NOT NULL,
  `emailaddress` varchar(45) NOT NULL,
  `parent_infocol` varchar(45) NOT NULL,
  `student_id` varchar(45) NOT NULL,
  PRIMARY KEY (`student_id`),
  KEY `stud_id_idx` (`student_id`),
  CONSTRAINT `student_id` FOREIGN KEY (`student_id`) REFERENCES `student_info` (`stud_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stud_course`
--

DROP TABLE IF EXISTS `stud_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stud_course` (
  `s_id` varchar(20) NOT NULL,
  `suub_id` varchar(45) NOT NULL,
  PRIMARY KEY (`s_id`,`suub_id`),
  KEY `suub_id_idx` (`suub_id`),
  CONSTRAINT `s_id` FOREIGN KEY (`s_id`) REFERENCES `student_info` (`stud_id`),
  CONSTRAINT `suub_id` FOREIGN KEY (`suub_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_info`
--

DROP TABLE IF EXISTS `student_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_info` (
  `stud_id` varchar(20) NOT NULL,
  `stud_name` varchar(50) NOT NULL,
  `stud_mob` bigint NOT NULL,
  `stud_email` varchar(25) NOT NULL,
  `stud_addr` varchar(100) NOT NULL,
  PRIMARY KEY (`stud_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_roll_batch`
--

DROP TABLE IF EXISTS `student_roll_batch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_roll_batch` (
  `stud_id` varchar(20) NOT NULL,
  `stud_roll` bigint NOT NULL,
  `stud_batch` varchar(10) NOT NULL,
  `stud_year` bigint NOT NULL,
  `stud_sem` bigint NOT NULL,
  `stud_dept` varchar(20) NOT NULL,
  `stud_syear` varchar(10) NOT NULL,
  PRIMARY KEY (`stud_id`),
  CONSTRAINT `stud_id` FOREIGN KEY (`stud_id`) REFERENCES `student_info` (`stud_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `teacher_course`
--

DROP TABLE IF EXISTS `teacher_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher_course` (
  `t_id` varchar(20) NOT NULL,
  `subject_id` varchar(45) NOT NULL,
  PRIMARY KEY (`t_id`,`subject_id`),
  KEY `sub_id_idx` (`subject_id`),
  CONSTRAINT `sub_id` FOREIGN KEY (`subject_id`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `t_id` FOREIGN KEY (`t_id`) REFERENCES `teacher_details` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `teacher_details`
--

DROP TABLE IF EXISTS `teacher_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher_details` (
  `teacher_id` varchar(20) NOT NULL,
  `teacher_phone` bigint NOT NULL,
  `teacher_email` varchar(30) NOT NULL,
  `teacher_name` varchar(45) NOT NULL,
  PRIMARY KEY (`teacher_id`)
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

-- Dump completed on 2022-10-01 13:22:21
