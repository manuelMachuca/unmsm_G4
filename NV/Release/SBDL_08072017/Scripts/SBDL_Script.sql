-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: bdchapatuprofe
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumno` (
  `IDALUMNO` char(5) NOT NULL,
  `IDPERSONA` char(5) DEFAULT NULL,
  PRIMARY KEY (`IDALUMNO`),
  KEY `ALUMNO_PERSONA_FK` (`IDPERSONA`),
  CONSTRAINT `ALUMNO_PERSONA_FK` FOREIGN KEY (`IDPERSONA`) REFERENCES `persona` (`IDPERSONA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `IDCATEGORIA` char(5) NOT NULL,
  `NOMBRECATEGORIA` char(5) DEFAULT NULL,
  PRIMARY KEY (`IDCATEGORIA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comentario`
--

DROP TABLE IF EXISTS `comentario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comentario` (
  `IDCOMENTARIO` char(5) NOT NULL,
  `IDCURSO` char(5) NOT NULL,
  `IDDOCENTE` char(5) NOT NULL,
  PRIMARY KEY (`IDCOMENTARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso` (
  `IDCURSO` char(5) NOT NULL,
  `NOMBRE` char(5) DEFAULT NULL,
  `IDCATEGORIA` char(5) DEFAULT NULL,
  `IDNIVEL` char(5) DEFAULT NULL,
  PRIMARY KEY (`IDCURSO`),
  KEY `CURSO_CATEGORIA_FK` (`IDCATEGORIA`),
  KEY `CURSO_NIVEL_FK` (`IDNIVEL`),
  CONSTRAINT `CURSO_CATEGORIA_FK` FOREIGN KEY (`IDCATEGORIA`) REFERENCES `categoria` (`IDCATEGORIA`),
  CONSTRAINT `CURSO_NIVEL_FK` FOREIGN KEY (`IDNIVEL`) REFERENCES `nivel` (`IDNIVEL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `curso_favorito`
--

DROP TABLE IF EXISTS `curso_favorito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso_favorito` (
  `IDALUMNO` char(5) NOT NULL,
  `IDCURSO` char(5) NOT NULL,
  `IDDOCENTE` char(5) NOT NULL,
  PRIMARY KEY (`IDALUMNO`,`IDCURSO`,`IDDOCENTE`),
  KEY `CURSO__CURSO_x_FK` (`IDCURSO`,`IDDOCENTE`),
  CONSTRAINT `CURSO_X_ALUMNO_ALUMNO_FK` FOREIGN KEY (`IDALUMNO`) REFERENCES `alumno` (`IDALUMNO`),
  CONSTRAINT `CURSO__CURSO_x_FK` FOREIGN KEY (`IDCURSO`, `IDDOCENTE`) REFERENCES `curso_x_docente` (`IDCURSO`, `IDDOCENTE`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `curso_x_docente`
--

DROP TABLE IF EXISTS `curso_x_docente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso_x_docente` (
  `IDCURSO` char(5) NOT NULL,
  `IDDOCENTE` char(5) NOT NULL,
  `ID_CUR_DOC_COM` char(5) NOT NULL,
  PRIMARY KEY (`IDCURSO`,`IDDOCENTE`,`ID_CUR_DOC_COM`),
  UNIQUE KEY `ID_CUR_DOC_COM_UNIQUE` (`ID_CUR_DOC_COM`),
  KEY `CURSO_X_DOCENTE_DOCENTE_FK` (`IDDOCENTE`),
  CONSTRAINT `CURSO_X_DOCENTE_CURSO_FK` FOREIGN KEY (`IDCURSO`) REFERENCES `curso` (`IDCURSO`),
  CONSTRAINT `CURSO_X_DOCENTE_DOCENTE_FK` FOREIGN KEY (`IDDOCENTE`) REFERENCES `docente` (`IDDOCENTE`),
  CONSTRAINT `ID_COMENTARIO_FK` FOREIGN KEY (`ID_CUR_DOC_COM`) REFERENCES `comentario` (`IDCOMENTARIO`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cursoalumno`
--

DROP TABLE IF EXISTS `cursoalumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursoalumno` (
  `IDCURSOALUMNO` char(5) NOT NULL,
  PRIMARY KEY (`IDCURSOALUMNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cursodocente`
--

DROP TABLE IF EXISTS `cursodocente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursodocente` (
  `IDCURSODOCENTE` char(5) NOT NULL,
  PRIMARY KEY (`IDCURSODOCENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `docente`
--

DROP TABLE IF EXISTS `docente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docente` (
  `IDDOCENTE` char(5) NOT NULL,
  `IDPERSONA` char(5) DEFAULT NULL,
  `IDNIVELESTUDIO` char(5) DEFAULT NULL,
  PRIMARY KEY (`IDDOCENTE`),
  KEY `DOCENTE_PERSONA_FK` (`IDPERSONA`),
  KEY `DOCE_NIVEL_ESTU_FK` (`IDNIVELESTUDIO`),
  CONSTRAINT `DOCENTE_PERSONA_FK` FOREIGN KEY (`IDPERSONA`) REFERENCES `persona` (`IDPERSONA`),
  CONSTRAINT `DOCE_NIVEL_ESTU_FK` FOREIGN KEY (`IDNIVELESTUDIO`) REFERENCES `nivel_estudio` (`IDNIVELESTUDIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `informaciondetalle`
--

DROP TABLE IF EXISTS `informaciondetalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `informaciondetalle` (
  `IDCURSO` char(5) NOT NULL,
  `IDALUMNO` char(5) NOT NULL,
  `INFO_DETALLE` char(5) DEFAULT NULL,
  `FECHA` char(5) DEFAULT NULL,
  PRIMARY KEY (`IDCURSO`,`IDALUMNO`),
  KEY `INFORMACIONDETALLE_ALUMNO_FK` (`IDALUMNO`),
  CONSTRAINT `INFORMACIONDETALLE_ALUMNO_FK` FOREIGN KEY (`IDALUMNO`) REFERENCES `alumno` (`IDALUMNO`),
  CONSTRAINT `INFORMACIONDETALLE_CURSO_FK` FOREIGN KEY (`IDCURSO`) REFERENCES `curso` (`IDCURSO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nivel`
--

DROP TABLE IF EXISTS `nivel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nivel` (
  `IDNIVEL` char(5) NOT NULL,
  `NOMBRENIVEL` char(5) DEFAULT NULL,
  PRIMARY KEY (`IDNIVEL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nivel_estudio`
--

DROP TABLE IF EXISTS `nivel_estudio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nivel_estudio` (
  `IDNIVELESTUDIO` char(5) NOT NULL,
  `NOMBRE` char(5) DEFAULT NULL,
  PRIMARY KEY (`IDNIVELESTUDIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfil` (
  `IDPERFIL` char(5) NOT NULL,
  `NOMBREPERFIL` char(5) DEFAULT NULL,
  PRIMARY KEY (`IDPERFIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `IDPERSONA` char(5) NOT NULL,
  `IDUSUARIO` char(5) DEFAULT NULL,
  `IDUBIGEO` char(5) DEFAULT NULL,
  `DIRECCION` char(5) DEFAULT NULL,
  `FECHA_NAC` char(5) DEFAULT NULL,
  `NOMBRES` char(5) DEFAULT NULL,
  `APELLIDOS` char(5) DEFAULT NULL,
  `TELEFONO` char(5) DEFAULT NULL,
  `CORREOELECTRONICO` char(5) DEFAULT NULL,
  PRIMARY KEY (`IDPERSONA`),
  KEY `PERSONA_USUARIO_FK` (`IDUSUARIO`),
  KEY `PERSONA_UBIGEO_FK` (`IDUBIGEO`),
  CONSTRAINT `PERSONA_UBIGEO_FK` FOREIGN KEY (`IDUBIGEO`) REFERENCES `ubigeo` (`IDUBIGEO`),
  CONSTRAINT `PERSONA_USUARIO_FK` FOREIGN KEY (`IDUSUARIO`) REFERENCES `usuario` (`IDUSUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ubigeo`
--

DROP TABLE IF EXISTS `ubigeo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ubigeo` (
  `IDUBIGEO` char(5) NOT NULL,
  `DISTRITO` char(5) DEFAULT NULL,
  `PROVINCIA` char(5) DEFAULT NULL,
  `REGION` char(5) DEFAULT NULL,
  PRIMARY KEY (`IDUBIGEO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `IDUSUARIO` char(5) NOT NULL,
  `USUARIO` char(5) DEFAULT NULL,
  `PASSWORD` char(5) DEFAULT NULL,
  `IDPERFIL` char(5) DEFAULT NULL,
  PRIMARY KEY (`IDUSUARIO`),
  KEY `USUARIO_Perfil_FK` (`IDPERFIL`),
  CONSTRAINT `USUARIO_Perfil_FK` FOREIGN KEY (`IDPERFIL`) REFERENCES `perfil` (`IDPERFIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-26 17:31:43
