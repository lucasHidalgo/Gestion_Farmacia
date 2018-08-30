-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-08-2018 a las 05:33:59
-- Versión del servidor: 10.1.35-MariaDB
-- Versión de PHP: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestionfarmacia_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `usuario_id` int(11) NOT NULL,
  `us_nombre` varchar(35) NOT NULL,
  `us_apellido` varchar(35) NOT NULL,
  `us_email` varchar(35) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `us_tipodoc` int(11) NOT NULL,
  `us_numdoc` varchar(35) NOT NULL,
  `us_sueldo` double DEFAULT NULL,
  `us_fechaalta` datetime NOT NULL,
  `us_fechabaja` datetime DEFAULT NULL,
  `us_estado` int(11) NOT NULL,
  `us_usuarioidalta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`usuario_id`, `us_nombre`, `us_apellido`, `us_email`, `clave`, `us_tipodoc`, `us_numdoc`, `us_sueldo`, `us_fechaalta`, `us_fechabaja`, `us_estado`, `us_usuarioidalta`) VALUES
(1, 'lucas', 'hidalgo', 'lucas@lucas.com', '123456', 1, '39243287', 0, '2018-08-29 00:00:00', '0000-00-00 00:00:00', 1, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`usuario_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `usuario_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
