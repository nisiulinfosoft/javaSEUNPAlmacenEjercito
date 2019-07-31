/*
MySQL Backup
Source Server Version: 5.0.41
Source Database: bdalmacen_ejercito
Date: 15/07/2013 16:34:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `tbarticulo`
-- ----------------------------
DROP TABLE IF EXISTS `tbarticulo`;
CREATE TABLE `tbarticulo` (
  `id_art` varchar(30) collate latin1_general_ci NOT NULL,
  `nombre_art` varchar(255) collate latin1_general_ci default NULL,
  `fecingreso` date default NULL,
  `cantidad_ingreso_art` int(11) default NULL,
  `cantidad_internada_art` int(11) default NULL,
  `cantidad_existente_art` int(11) default NULL,
  `descripcion_art` varchar(255) collate latin1_general_ci default NULL,
  PRIMARY KEY  (`id_art`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- ----------------------------
--  Table structure for `tbcliente`
-- ----------------------------
DROP TABLE IF EXISTS `tbcliente`;
CREATE TABLE `tbcliente` (
  `id_cli` varchar(30) collate latin1_general_ci NOT NULL,
  `id_uni` varchar(30) collate latin1_general_ci NOT NULL,
  `nombre_cli` varchar(50) collate latin1_general_ci default NULL,
  `apepat_cli` varchar(50) collate latin1_general_ci default NULL,
  `apemat_cli` varchar(50) collate latin1_general_ci default NULL,
  `sexo_cli` varchar(15) collate latin1_general_ci default NULL,
  `fecnacimiento_cli` date default NULL,
  `fecingreso_cli` date default NULL,
  `telefono_cli` varchar(20) collate latin1_general_ci default NULL,
  `celular_cli` varchar(20) collate latin1_general_ci default NULL,
  `email_cli` varchar(150) collate latin1_general_ci default NULL,
  `calle_cli` varchar(255) collate latin1_general_ci default NULL,
  `numerocasa_cli` int(11) default NULL,
  `barrio_cli` varchar(255) collate latin1_general_ci default NULL,
  `observacion_cli` varchar(255) collate latin1_general_ci default NULL,
  PRIMARY KEY  (`id_cli`),
  KEY `Rel_01` (`id_uni`),
  CONSTRAINT `tbcliente_ibfk_1` FOREIGN KEY (`id_uni`) REFERENCES `tbunidad` (`id_uni`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- ----------------------------
--  Table structure for `tbdetalledevolucion`
-- ----------------------------
DROP TABLE IF EXISTS `tbdetalledevolucion`;
CREATE TABLE `tbdetalledevolucion` (
  `id_prest` varchar(30) collate latin1_general_ci NOT NULL,
  `id_art` varchar(30) collate latin1_general_ci NOT NULL,
  `id_devol` varchar(30) collate latin1_general_ci NOT NULL,
  `cantidad_devol` int(11) default NULL,
  PRIMARY KEY  (`id_devol`,`id_art`,`id_prest`),
  KEY `Rel_09` (`id_art`),
  KEY `Rel_10` (`id_prest`),
  CONSTRAINT `tbdetalledevolucion_ibfk_1` FOREIGN KEY (`id_devol`) REFERENCES `tbpedidodevolucion` (`id_devol`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tbdetalledevolucion_ibfk_2` FOREIGN KEY (`id_art`) REFERENCES `tbarticulo` (`id_art`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tbdetalledevolucion_ibfk_3` FOREIGN KEY (`id_prest`) REFERENCES `tbpedidoprestamo` (`id_prest`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- ----------------------------
--  Table structure for `tbdetalleprestamo`
-- ----------------------------
DROP TABLE IF EXISTS `tbdetalleprestamo`;
CREATE TABLE `tbdetalleprestamo` (
  `id_art` varchar(30) collate latin1_general_ci NOT NULL,
  `id_prest` varchar(30) collate latin1_general_ci NOT NULL,
  `cantidad_prest` int(11) default NULL,
  `cantidad_devuelta_prest` int(11) default NULL,
  PRIMARY KEY  (`id_prest`,`id_art`),
  KEY `Rel_05` (`id_art`),
  CONSTRAINT `tbdetalleprestamo_ibfk_1` FOREIGN KEY (`id_prest`) REFERENCES `tbpedidoprestamo` (`id_prest`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tbdetalleprestamo_ibfk_2` FOREIGN KEY (`id_art`) REFERENCES `tbarticulo` (`id_art`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- ----------------------------
--  Table structure for `tbpedidodevolucion`
-- ----------------------------
DROP TABLE IF EXISTS `tbpedidodevolucion`;
CREATE TABLE `tbpedidodevolucion` (
  `id_devol` varchar(30) collate latin1_general_ci NOT NULL,
  `id_cli` varchar(30) collate latin1_general_ci NOT NULL,
  `id_usu` varchar(30) collate latin1_general_ci NOT NULL,
  `fecha_devol` date default NULL,
  PRIMARY KEY  (`id_devol`),
  KEY `Rel_06` (`id_usu`),
  KEY `Rel_07` (`id_cli`),
  CONSTRAINT `tbpedidodevolucion_ibfk_1` FOREIGN KEY (`id_usu`) REFERENCES `tbusuario` (`id_usu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tbpedidodevolucion_ibfk_2` FOREIGN KEY (`id_cli`) REFERENCES `tbcliente` (`id_cli`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- ----------------------------
--  Table structure for `tbpedidoprestamo`
-- ----------------------------
DROP TABLE IF EXISTS `tbpedidoprestamo`;
CREATE TABLE `tbpedidoprestamo` (
  `id_prest` varchar(30) collate latin1_general_ci NOT NULL,
  `id_cli` varchar(30) collate latin1_general_ci NOT NULL,
  `id_usu` varchar(30) collate latin1_general_ci NOT NULL,
  `fecha_prest` date default NULL,
  PRIMARY KEY  (`id_prest`),
  KEY `Rel_02` (`id_usu`),
  KEY `Rel_03` (`id_cli`),
  CONSTRAINT `tbpedidoprestamo_ibfk_1` FOREIGN KEY (`id_usu`) REFERENCES `tbusuario` (`id_usu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tbpedidoprestamo_ibfk_2` FOREIGN KEY (`id_cli`) REFERENCES `tbcliente` (`id_cli`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- ----------------------------
--  Table structure for `tbunidad`
-- ----------------------------
DROP TABLE IF EXISTS `tbunidad`;
CREATE TABLE `tbunidad` (
  `id_uni` varchar(30) collate latin1_general_ci NOT NULL,
  `nombre_uni` varchar(50) collate latin1_general_ci default NULL,
  `descripcion_uni` varchar(255) collate latin1_general_ci default NULL,
  PRIMARY KEY  (`id_uni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- ----------------------------
--  Table structure for `tbusuario`
-- ----------------------------
DROP TABLE IF EXISTS `tbusuario`;
CREATE TABLE `tbusuario` (
  `id_usu` varchar(30) collate latin1_general_ci NOT NULL,
  `nombre_usu` varchar(50) collate latin1_general_ci default NULL,
  `apepat_usu` varchar(50) collate latin1_general_ci default NULL,
  `apemat_usu` varchar(50) collate latin1_general_ci default NULL,
  `contrasena_usu` varchar(50) collate latin1_general_ci default NULL,
  PRIMARY KEY  (`id_usu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- ----------------------------
--  Procedure definition for `sp_actualizar_articulo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_actualizar_articulo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar_articulo`(id varchar(30),nombre varchar(255),fecingre date,cantingreso int,cantinternada int,cantexistente int,descrip varchar(255))
BEGIN
	UPDATE tbArticulo SET nombre_art=nombre,fecingreso=fecingre,cantidad_ingreso_art=cantingreso,cantidad_internada_art=cantinternada,cantidad_existente_art=cantexistente,descripcion_art=descrip WHERE id_art=id;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_actualizar_cantDevueltaDetPresDevolucion`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_actualizar_cantDevueltaDetPresDevolucion`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar_cantDevueltaDetPresDevolucion`(idart varchar(30),idprest varchar(30),cantidad int)
BEGIN
	UPDATE tbDetallePrestamo SET cantidad_devuelta_prest=cantidad_devuelta_prest+cantidad WHERE id_art=idart AND id_prest=idprest;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_actualizar_cantExistenteArtDevolucion`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_actualizar_cantExistenteArtDevolucion`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar_cantExistenteArtDevolucion`(codigo varchar(30),cantidad int)
BEGIN
	UPDATE tbArticulo SET cantidad_existente_art=cantidad_existente_art+cantidad WHERE id_art=codigo;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_actualizar_cantExistenteArtPrestamo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_actualizar_cantExistenteArtPrestamo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar_cantExistenteArtPrestamo`(codigo varchar(30),cantidad int)
BEGIN
	UPDATE tbArticulo SET cantidad_existente_art=cantidad_existente_art-cantidad WHERE id_art=codigo;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_actualizar_cliente`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_actualizar_cliente`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar_cliente`(idclie varchar(30),iduni varchar(30),nombre varchar(50),apepat varchar(50),apemat varchar(50),sexo varchar(15),fecnacimiento date,fecingreso date,telefono varchar(20),celular varchar(20),email varchar(150),calle varchar(255),numerocasa int,barrio varchar(255),observacion varchar(255))
BEGIN
	UPDATE tbCliente SET id_uni=iduni,nombre_cli=nombre,apepat_cli=apepat,apemat_cli=apemat,sexo_cli=sexo,fecnacimiento_cli=fecnacimiento,fecingreso_cli=fecingreso,telefono_cli=telefono,celular_cli=celular,email_cli=email,calle_cli=calle,numerocasa_cli=numerocasa,barrio_cli=barrio,observacion_cli=observacion WHERE id_cli=idclie;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_actualizar_detallepedidoDevolucion`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_actualizar_detallepedidoDevolucion`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar_detallepedidoDevolucion`(idprest varchar(30),idart varchar(30),iddevol varchar(30),cantdevol int)
BEGIN
	UPDATE tbDetalleDevolucion SET cantidad_devol=cantdevol WHERE id_prest=idprest AND id_art=idart AND id_devol=iddevol;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_actualizar_detallepedidoPrestamo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_actualizar_detallepedidoPrestamo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar_detallepedidoPrestamo`(idart varchar(30),idprest varchar(30),cantprest int,cantdevuelprest int)
BEGIN
	UPDATE tbDetallePrestamo SET cantidad_prest=cantprest,cantidad_devuelta_prest=cantdevuelprest WHERE id_art=idart AND id_prest=idprest;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_actualizar_pedidoDevolucion`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_actualizar_pedidoDevolucion`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar_pedidoDevolucion`(iddevol varchar(30),idcli varchar(30),idusu varchar(30),fechadevol date)
BEGIN
	UPDATE tbPedidoDevolucion SET id_cli=idcli,id_usu=idusu,fecha_devol=fechadevol WHERE id_devol=iddevol;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_actualizar_pedidoPrestamo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_actualizar_pedidoPrestamo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar_pedidoPrestamo`(idpres varchar(30),idcli varchar(30),idusu varchar(30),fechapres date)
BEGIN
	UPDATE tbPedidoPrestamo SET id_cli=idcli,id_usu=idusu,fecha_prest=fechapres WHERE id_prest=idpres;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_actualizar_unidad`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_actualizar_unidad`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar_unidad`(codigo varchar(30), nombre varchar(50),descrip varchar(255))
BEGIN
	UPDATE tbUnidad SET nombre_uni=nombre,descripcion_uni=descrip WHERE id_uni=codigo;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_actualizar_usuario`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_actualizar_usuario`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar_usuario`(codigo varchar(30),nombre varchar(50),apepaterno varchar(50),apematerno varchar(50),contras varchar(50))
BEGIN
	UPDATE tbUsuario SET nombre_usu=nombre,apepat_usu=apepaterno,apemat_usu=apematerno,contrasena_usu=contras WHERE id_usu=codigo;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_agregar_articulo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_agregar_articulo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_agregar_articulo`(id varchar(30),nombre varchar(255),fecingre date,cantingreso int,cantinternada int,cantexistente int,descrip varchar(255))
BEGIN
	INSERT INTO tbArticulo (id_art,nombre_art,fecingreso,cantidad_ingreso_art,cantidad_internada_art,cantidad_existente_art,descripcion_art) VALUES(id,nombre,fecingre,cantingreso,cantinternada,cantexistente,descrip);
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_agregar_cliente`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_agregar_cliente`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_agregar_cliente`(idclie varchar(30),iduni varchar(30),nombre varchar(50),apepat varchar(50),apemat varchar(50),sexo varchar(15),fecnacimiento date,fecingreso date,telefono varchar(20),celular varchar(20),email varchar(150),calle varchar(255),numerocasa int,barrio varchar(255),observacion varchar(255))
BEGIN
	INSERT INTO tbCliente(id_cli,id_uni,nombre_cli,apepat_cli,apemat_cli,sexo_cli,fecnacimiento_cli,fecingreso_cli,telefono_cli,celular_cli,email_cli,calle_cli,numerocasa_cli,barrio_cli,observacion_cli) VALUES(idclie,iduni,nombre,apepat,apemat,sexo,fecnacimiento,fecingreso,telefono,celular,email,calle,numerocasa,barrio,observacion);
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_agregar_detallepedidoDevolucion`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_agregar_detallepedidoDevolucion`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_agregar_detallepedidoDevolucion`(idprest varchar(30),idart varchar(30),iddevol varchar(30),cantdevol int)
BEGIN
	INSERT INTO tbDetalleDevolucion(id_prest,id_art,id_devol,cantidad_devol) VALUES(idprest,idart,iddevol,cantdevol);
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_agregar_detallepedidoPrestamo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_agregar_detallepedidoPrestamo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_agregar_detallepedidoPrestamo`(idart varchar(30),idprest varchar(30),cantprest int,cantdevuelprest int)
BEGIN
	INSERT INTO tbDetallePrestamo(id_art,id_prest,cantidad_prest,cantidad_devuelta_prest) VALUES(idart,idprest,cantprest,cantdevuelprest);
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_agregar_pedidoDevolucion`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_agregar_pedidoDevolucion`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_agregar_pedidoDevolucion`(iddevol varchar(30),idcli varchar(30),idusu varchar(30),fechadevol date)
BEGIN
	INSERT INTO tbPedidoDevolucion(id_devol,id_cli,id_usu,fecha_devol) VALUES(iddevol,idcli,idusu,fechadevol);
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_agregar_pedidoPrestamo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_agregar_pedidoPrestamo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_agregar_pedidoPrestamo`(idpres varchar(30),idcli varchar(30),idusu varchar(30),fechapres date)
BEGIN
	INSERT INTO tbPedidoPrestamo(id_prest,id_cli,id_usu,fecha_prest) VALUES(idpres,idcli,idusu,fechapres);
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_agregar_unidad`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_agregar_unidad`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_agregar_unidad`(codigo varchar(30),nombre varchar(50),descripcion varchar(255))
BEGIN
	INSERT INTO tbUnidad (id_uni,nombre_uni,descripcion_uni) VALUES(codigo,nombre,descripcion);
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_agregar_usuario`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_agregar_usuario`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_agregar_usuario`(codigo varchar(30),nombre varchar(50),apepaterno varchar(50),apematerno varchar(50),contras varchar(50))
BEGIN
	INSERT INTO tbUsuario (id_usu,nombre_usu,apepat_usu,apemat_usu,contrasena_usu) VALUES(codigo,nombre,apepaterno,apematerno,contras);
    
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_buscar_articulo_codigo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_buscar_articulo_codigo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_articulo_codigo`(id varchar(30))
BEGIN
	SELECT id_art,nombre_art,fecingreso,cantidad_ingreso_art,cantidad_internada_art,cantidad_existente_art,descripcion_art FROM tbArticulo WHERE  id_art=id;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_buscar_articulo_nombre`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_buscar_articulo_nombre`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_articulo_nombre`(nombre varchar(255))
BEGIN
	SELECT * FROM tbArticulo WHERE nombre_art=nombre;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_buscar_cliente_codigo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_buscar_cliente_codigo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_cliente_codigo`(idclie varchar(30))
BEGIN
	SELECT id_cli,id_uni,nombre_cli,apepat_cli,apemat_cli,sexo_cli,fecnacimiento_cli,fecingreso_cli,telefono_cli,celular_cli,email_cli,calle_cli,numerocasa_cli,barrio_cli,observacion_cli FROM tbCliente WHERE id_cli=idclie;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_buscar_cliente_nombre`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_buscar_cliente_nombre`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_cliente_nombre`(nombreCompleto varchar(150))
BEGIN
	SELECT id_cli,id_uni,nombre_cli,apepat_cli,apemat_cli,sexo_cli,fecnacimiento_cli,fecingreso_cli,telefono_cli,celular_cli,email_cli,calle_cli,numerocasa_cli,barrio_cli,observacion_cli FROM tbCliente WHERE  CONCAT(nombre_cli,' ',apepat_cli,' ',apemat_cli)=nombreCompleto;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_buscar_detallepedidoDevolucion_codigo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_buscar_detallepedidoDevolucion_codigo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_detallepedidoDevolucion_codigo`(iddevol varchar(30))
BEGIN
	SELECT id_prest,id_art,id_devol,cantidad_devol FROM tbDetalleDevolucion WHERE id_devol=iddevol;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_buscar_detallepedidoPrestamo_codigo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_buscar_detallepedidoPrestamo_codigo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_detallepedidoPrestamo_codigo`(idprest varchar(30))
BEGIN
	SELECT id_art,id_prest,cantidad_prest,cantidad_devuelta_prest FROM tbDetallePrestamo WHERE id_prest=idprest;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_buscar_pedidoDevolucion_codigo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_buscar_pedidoDevolucion_codigo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_pedidoDevolucion_codigo`(iddevol varchar(30))
BEGIN
	SELECT id_devol,id_cli,id_usu,fecha_devol FROM tbPedidoDevolucion WHERE  id_devol=iddevol;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_buscar_pedidoPrestamo_codigo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_buscar_pedidoPrestamo_codigo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_pedidoPrestamo_codigo`(idpres varchar(30))
BEGIN
	SELECT id_prest,id_cli,id_usu,fecha_prest FROM tbPedidoPrestamo WHERE  id_prest=idpres;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_buscar_unidad_codigo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_buscar_unidad_codigo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_unidad_codigo`(codigo varchar(30))
BEGIN
	SELECT id_uni,nombre_uni,descripcion_uni FROM tbUnidad WHERE  id_uni=codigo ORDER BY id_uni;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_buscar_unidad_nombre`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_buscar_unidad_nombre`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_unidad_nombre`(nombre varchar(50))
BEGIN
	select * from tbUnidad where nombre_uni=nombre;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_buscar_usuario_codigo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_buscar_usuario_codigo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_usuario_codigo`(codigo varchar(30))
BEGIN
	SELECT id_usu,nombre_usu,apepat_usu,apemat_usu,contrasena_usu FROM tbUsuario WHERE  id_usu=codigo ORDER BY id_usu;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_buscar_usuario_nombre`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_buscar_usuario_nombre`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_usuario_nombre`(nombreCompleto varchar(150))
BEGIN
	SELECT * FROM tbUsuario WHERE CONCAT(nombre_usu,' ',apepat_usu,' ',apemat_usu)=nombreCompleto;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_eliminar_articulo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_eliminar_articulo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_articulo`(id varchar(30))
BEGIN
	DELETE FROM tbArticulo WHERE id_art=id;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_eliminar_cliente`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_eliminar_cliente`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_cliente`(idclie varchar(30))
BEGIN
	DELETE FROM tbCliente WHERE id_cli=idclie;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_eliminar_detallepedidoDevolucion`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_eliminar_detallepedidoDevolucion`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_detallepedidoDevolucion`(iddevol varchar(30))
BEGIN
	DELETE FROM tbDetalleDevolucion WHERE id_devol=iddevol;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_eliminar_detallepedidoPrestamo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_eliminar_detallepedidoPrestamo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_detallepedidoPrestamo`(idprest varchar(30))
BEGIN
	DELETE FROM tbDetallePrestamo WHERE id_prest=idprest;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_eliminar_pedidoDevolucion`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_eliminar_pedidoDevolucion`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_pedidoDevolucion`(iddevol varchar(30))
BEGIN
	DELETE FROM tbPedidoDevolucion WHERE id_devol=iddevol;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_eliminar_pedidoPrestamo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_eliminar_pedidoPrestamo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_pedidoPrestamo`(idpres varchar(30))
BEGIN
	DELETE FROM tbPedidoPrestamo WHERE id_prest=idpres;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_eliminar_unidad`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_eliminar_unidad`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_unidad`(codigo varchar(30))
BEGIN
	DELETE FROM tbUnidad WHERE id_uni=codigo;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_eliminar_usuario`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_eliminar_usuario`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_usuario`(codigo varchar(30))
BEGIN
	DELETE FROM tbUsuario WHERE id_usu=codigo;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_listar_articulos_codigo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_listar_articulos_codigo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_articulos_codigo`(id varchar(30))
BEGIN
	SELECT id_art,nombre_art,fecingreso,cantidad_ingreso_art,cantidad_internada_art,cantidad_existente_art,descripcion_art FROM tbArticulo WHERE  id_art LIKE CONCAT('%',id,'%');
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_listar_articulos_nombrecompleto`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_listar_articulos_nombrecompleto`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_articulos_nombrecompleto`(nombre varchar(255))
BEGIN
	SELECT id_art,nombre_art,fecingreso,cantidad_ingreso_art,cantidad_internada_art,cantidad_existente_art,descripcion_art FROM tbArticulo WHERE  nombre_art LIKE CONCAT('%',nombre,'%') ORDER BY nombre_art;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_listar_Articulos_queDebeCliente`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_listar_Articulos_queDebeCliente`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_Articulos_queDebeCliente`(codigoCliente varchar(30))
BEGIN
	SELECT PP.id_prest,PP.fecha_prest,PD.id_art,A.nombre_art,PD.cantidad_prest,(PD.cantidad_prest-PD.cantidad_devuelta_prest) AS resultado FROM tbpedidoprestamo AS PP
	INNER JOIN tbdetalleprestamo AS PD ON PP.id_prest=PD.id_prest
	INNER JOIN tbarticulo AS A ON PD.id_art=A.id_art
	WHERE PP.id_cli=codigoCliente
	HAVING resultado>0;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_listar_Clientes_queDebenArticulos_codigo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_listar_Clientes_queDebenArticulos_codigo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_Clientes_queDebenArticulos_codigo`(codigoCliente varchar(30))
BEGIN
	SELECT PP.id_cli,C.nombre_cli,C.apepat_cli,C.apemat_cli,SUM((PD.cantidad_prest-PD.cantidad_devuelta_prest)) AS resultado FROM tbpedidoprestamo AS PP
	INNER JOIN tbcliente AS C ON PP.id_cli=C.id_cli
	INNER JOIN tbdetalleprestamo AS PD ON PP.id_prest=PD.id_prest
	WHERE PP.id_cli LIKE CONCAT('%',codigoCliente,'%')
	GROUP BY PP.id_cli
	HAVING resultado>0;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_listar_Clientes_queDebenArticulos_nombre`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_listar_Clientes_queDebenArticulos_nombre`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_Clientes_queDebenArticulos_nombre`(nombreCompleto varchar(150))
BEGIN
	SELECT PP.id_cli,C.nombre_cli,C.apepat_cli,C.apemat_cli,SUM((PD.cantidad_prest-PD.cantidad_devuelta_prest)) AS resultado FROM tbpedidoprestamo AS PP
	INNER JOIN tbcliente AS C ON PP.id_cli=C.id_cli
	INNER JOIN tbdetalleprestamo AS PD ON PP.id_prest=PD.id_prest
	WHERE CONCAT(C.nombre_cli,' ',C.apepat_cli,' ',C.apemat_cli) LIKE CONCAT('%',nombreCompleto,'%')
	GROUP BY PP.id_cli
	HAVING resultado>0;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_listar_cliente_codigo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_listar_cliente_codigo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_cliente_codigo`(idclie varchar(30))
BEGIN
	SELECT id_cli,id_uni,nombre_cli,apepat_cli,apemat_cli,sexo_cli,fecnacimiento_cli,fecingreso_cli,telefono_cli,celular_cli,email_cli,calle_cli,numerocasa_cli,barrio_cli,observacion_cli FROM tbCliente WHERE  id_cli LIKE CONCAT('%',idclie,'%');
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_listar_cliente_nombrecompleto`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_listar_cliente_nombrecompleto`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_cliente_nombrecompleto`(nombre varchar(150))
BEGIN
	SELECT id_cli,id_uni,nombre_cli,apepat_cli,apemat_cli,sexo_cli,fecnacimiento_cli,fecingreso_cli,telefono_cli,celular_cli,email_cli,calle_cli,numerocasa_cli,barrio_cli,observacion_cli FROM tbCliente WHERE  CONCAT(nombre_cli,' ',apepat_cli,' ',apemat_cli) LIKE CONCAT('%',nombre,'%');
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_listar_detallepedidoDevolucion_codigo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_listar_detallepedidoDevolucion_codigo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_detallepedidoDevolucion_codigo`(iddevol varchar(30))
BEGIN
	SELECT id_prest,id_art,id_devol,cantidad_devol FROM tbDetallePrestamo WHERE id_devol=iddevol;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_listar_detallepedidoPrestamo_codigo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_listar_detallepedidoPrestamo_codigo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_detallepedidoPrestamo_codigo`(idprest varchar(30))
BEGIN
	SELECT id_art,id_prest,cantidad_prest,cantidad_devuelta_prest FROM tbDetallePrestamo WHERE id_prest=idprest;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_listar_pedidoDevolucion_codigo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_listar_pedidoDevolucion_codigo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_pedidoDevolucion_codigo`(iddevol varchar(30))
BEGIN
	SELECT id_devol,id_cli,id_usu,fecha_devol FROM tbPedidoDevolucion WHERE  id_devol LIKE CONCAT('%',iddevol,'%');
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_listar_pedidoPrestamo_codigo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_listar_pedidoPrestamo_codigo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_pedidoPrestamo_codigo`(idpres varchar(30))
BEGIN
	SELECT id_prest,id_cli,id_usu,fecha_prest FROM tbPedidoPrestamo WHERE  id_prest LIKE CONCAT('%',idpres,'%');
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_listar_unidades_nombrecompleto`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_listar_unidades_nombrecompleto`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_unidades_nombrecompleto`(nombre varchar(50))
BEGIN
	SELECT id_uni,nombre_uni,descripcion_uni FROM tbUnidad WHERE  nombre_uni LIKE CONCAT('%',nombre,'%');
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_listar_unidad_codigo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_listar_unidad_codigo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_unidad_codigo`(codigo varchar(30))
BEGIN
	SELECT id_uni,nombre_uni,descripcion_uni FROM tbUnidad WHERE  id_uni LIKE CONCAT('%',codigo,'%') ORDER BY id_uni;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_listar_usuarios_codigo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_listar_usuarios_codigo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_usuarios_codigo`(codigo varchar(30))
BEGIN
	SELECT id_usu,nombre_usu,apepat_usu,apemat_usu,contrasena_usu FROM tbUsuario WHERE  id_usu LIKE CONCAT('%',codigo,'%') ORDER BY id_usu;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_listar_usuarios_nombrecompleto`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_listar_usuarios_nombrecompleto`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_usuarios_nombrecompleto`(nombre varchar(200))
BEGIN
	SELECT id_usu,nombre_usu,apepat_usu,apemat_usu,contrasena_usu FROM tbUsuario WHERE  CONCAT(nombre_usu,' ',apepat_usu,' ',apemat_usu) LIKE CONCAT('%',nombre,'%') ORDER BY apepat_usu;
    END
;;
DELIMITER ;

-- ----------------------------
--  Procedure definition for `sp_validar_usuario`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_validar_usuario`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_validar_usuario`(codigo varchar(30),contras varchar(50))
BEGIN
	SELECT * FROM tbUsuario WHERE  id_usu=codigo AND contrasena_usu=contras;
    END
;;
DELIMITER ;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `tbarticulo` VALUES ('A0000001','ROPA','2012-10-16','50','10','38','ROPA BLANCA MUY SUCIA'), ('A0000002','ZAPATOS','2012-10-18','30','20','10',''), ('A0000003','SACO  BB DSLD','2012-10-02','20','5','15','JHJ'), ('A0000004','SSSSSSSFF','2012-10-02','400','90','310','HHHHHHHHRR.'), ('A0000005','QQQ','2012-10-09','20','2','18','FD'), ('A0000006','CAMA','2012-10-03','100','20','77','');
INSERT INTO `tbcliente` VALUES ('1','U0000001','LUIS','MAZA','ALCALDE','F',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL), ('2','U0000002','LUCHO','PEREZ','CONDOR','M','2012-10-16','2012-10-01','','','','FDGFDG','0','',''), ('3','U0000004','JUANAA','PEDEE','ALCALSS','','2012-10-08','2012-10-16','555','66','FGDF','MANCORA','2','FDG','FGDG'), ('C0000004','U0000002','GEAN','NAVARRO','GARRIDO','M','2012-10-08','2012-10-16','123456','123456789','LUIS_12_835@HOTMAIL.COM','AMOTAPE','0','SANTA TERESITA','AAAAAAAAAAAAAAAA'), ('C0000005','U0000003','FABIAN','ALVA','ALCAL','M','2012-10-16','2012-10-04','','','','','0','',''), ('C0000006','U0000004','FATII','ALVAA','FDSFF','M','2012-10-06','2012-10-05','','','','','0','',''), ('C0000007','U0000002','FFFFFFF','DDDDDDD','GHG','F','2012-10-08','2012-10-09','','','','','0','',''), ('C0000008','U0000003','GGGGG','AAA','VVVVV','M','2012-10-09','2012-10-10','','','','','0','',''), ('C0000009','U0000007','AAAA','BBB','CCCC','M','2012-10-16','2012-10-18','','','','','0','',''), ('C0000010','U0000003','HH','JJ','KK','F','2012-10-15','2012-10-17','','','','','0','',''), ('C0000011','U0000004','JULIO','IGLESIAS','MASIAS','M','2012-10-09','2012-10-12','','','','','0','',''), ('C0000012','U0000001','DIANA','SAMAME','GOMES','','2012-10-02','2012-10-10','','','','','0','',''), ('C0000013','U0000001','MAIZ','AA','BB','M','2012-10-01','2012-10-20','','','','','0','',''), ('C0000014','U0000002','RRR','HH','WW','M','2012-10-01','2012-10-03','','','','','0','',''), ('C0000015','U0000004','WW','QQ','HH','M','2012-10-02','2012-10-03','','','','','0','',''), ('C0000016','U0000003','MAZ','GG','KK','F','2012-10-16','2012-10-17','','','','','0','','');
INSERT INTO `tbdetalledevolucion` VALUES ('P0000001','A0000001','D0000001','2'), ('P0000001','A0000002','D0000001','2'), ('P0000001','A0000003','D0000001','5'), ('P0000003','A0000004','D0000001','6'), ('P0000003','A0000006','D0000001','1'), ('P0000001','A0000001','D0000002','2'), ('P0000001','A0000005','D0000002','3'), ('P0000003','A0000004','D0000003','4'), ('P0000003','A0000006','D0000003','4'), ('P0000002','A0000006','D0000004','2');
INSERT INTO `tbdetalleprestamo` VALUES ('A0000001','P0000001','4','4'), ('A0000002','P0000001','2','2'), ('A0000003','P0000001','5','5'), ('A0000005','P0000001','3','3'), ('A0000001','P0000002','2','0'), ('A0000006','P0000002','5','2'), ('A0000004','P0000003','10','10'), ('A0000006','P0000003','5','5');
INSERT INTO `tbpedidodevolucion` VALUES ('D0000001','C0000004','1','2012-10-25'), ('D0000002','C0000004','U0000002','2012-10-25'), ('D0000003','C0000004','U0000002','2012-10-26'), ('D0000004','C0000005','U0000003','2012-10-26');
INSERT INTO `tbpedidoprestamo` VALUES ('P0000001','C0000004','U0000002','2012-10-23'), ('P0000002','C0000005','U0000003','2012-10-23'), ('P0000003','C0000004','U0000003','2012-10-24');
INSERT INTO `tbunidad` VALUES ('U0000001','BATALLA','JLKFJD'), ('U0000002','INGENIERIA','AAAAAAAAAA'), ('U0000003','CABALLERIA','GGGGGGGGGG'), ('U0000004','COMANDO','JJJJJJJJJ'), ('U0000005','SOLDADURA','LO MAXIMO'), ('U0000006','           SAVAJDFK','LO MEJOR'), ('U0000007','LUIS        DFSDFD','DDDDD'), ('U0000008','LUIS ENRIQUE','LUIS'), ('U0000009','LUISD             FDFD','FFFFFFFFFFFFFFFFF'), ('U0000010','HHHHH    FF','DDDDDDDD'), ('U0000011','DDDDDD DD       DDD       .','GGGGGGGGGGGG');
INSERT INTO `tbusuario` VALUES ('1','LUISIN','MAZA','ALCALDE','NISIULINFO12'), ('U0000002','LEONIDASS','SOTOO','MORANM','1234567'), ('U0000003','PERICO','PANCRACIO','PAA','123'), ('U0000004','FAB','DDDDDDDDD','FEE           JJJJJJJ      .','RRRRRRRRR');
