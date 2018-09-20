-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-09-2018 a las 00:12:49
-- Versión del servidor: 10.1.29-MariaDB
-- Versión de PHP: 7.2.0

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
-- Estructura de tabla para la tabla `area`
--

CREATE TABLE `area` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `area`
--

INSERT INTO `area` (`Id`, `Nombre`) VALUES
(1, 'Pulmones');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `barrios`
--

CREATE TABLE `barrios` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `barrios`
--

INSERT INTO `barrios` (`Id`, `Nombre`) VALUES
(1, 'Palermo'),
(2, 'MONTE CASTRO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfil`
--

CREATE TABLE `perfil` (
  `Id` int(11) NOT NULL,
  `Nombre` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Codigo` varchar(200) NOT NULL,
  `Precio` double NOT NULL,
  `EsVentaLibre` bit(1) NOT NULL,
  `rutaImg` varchar(250) DEFAULT NULL,
  `Area` int(11) NOT NULL COMMENT 'Cabeza,pecho,espalda,etc',
  `Proveedor` int(11) NOT NULL,
  `Via` int(11) NOT NULL COMMENT 'oral,ocular,nasal,etc',
  `Tipo` int(11) NOT NULL COMMENT 'cosmetios,medicamentos,etc'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`Id`, `Nombre`, `Codigo`, `Precio`, `EsVentaLibre`, `rutaImg`, `Area`, `Proveedor`, `Via`, `Tipo`) VALUES
(1, 'producto 1', 'codigo1', 50.75, b'1', NULL, 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productosucursales`
--

CREATE TABLE `productosucursales` (
  `Id` int(11) NOT NULL,
  `ProductoId` int(11) NOT NULL,
  `SucursalId` int(11) NOT NULL,
  `Stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productosucursales`
--

INSERT INTO `productosucursales` (`Id`, `ProductoId`, `SucursalId`, `Stock`) VALUES
(1, 1, 1, 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `Id` int(11) NOT NULL,
  `Cuit` varchar(20) NOT NULL,
  `Nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`Id`, `Cuit`, `Nombre`) VALUES
(1, '12-12345678-2', 'Roemmers');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `Id` int(11) NOT NULL,
  `ProductoId` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `FueCancelado` bit(1) NOT NULL,
  `CajeroId` int(11) NOT NULL,
  `ClienteId` int(11) NOT NULL,
  `FechaReserva` datetime NOT NULL,
  `CajeroCancelacionId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursales`
--

CREATE TABLE `sucursales` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Barrio` int(11) NOT NULL,
  `Latitud` varchar(200) NOT NULL,
  `Longitud` varchar(200) NOT NULL,
  `Telefono1` varchar(50) NOT NULL,
  `Telefono2` varchar(50) DEFAULT NULL,
  `Direccion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `sucursales`
--

INSERT INTO `sucursales` (`Id`, `Nombre`, `Barrio`, `Latitud`, `Longitud`, `Telefono1`, `Telefono2`, `Direccion`) VALUES
(1, 'sucursal 1', 1, '-34.613090', '-58.499097', '12345678', '1', 'calle 123'),
(2, 'sucursal 2', 2, '-34.615585', '-58.497145', '98765432', NULL, 'calle 789'),
(3, 'sucursal 3', 2, '-34.615315', '-58.497485', '1', '1', 'sucursal 4');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos`
--

CREATE TABLE `tipos` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipos`
--

INSERT INTO `tipos` (`Id`, `Nombre`) VALUES
(1, 'Medicamento');

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
  `PerfilId` int(11) NOT NULL,
  `us_sueldo` double DEFAULT NULL,
  `us_fechaalta` datetime NOT NULL,
  `us_fechabaja` datetime DEFAULT NULL,
  `us_estado` int(11) NOT NULL,
  `us_usuarioidalta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `Id` int(11) NOT NULL,
  `ClienteId` int(11) NOT NULL,
  `ProductoId` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `FechaVenta` datetime NOT NULL,
  `CajeroId` int(11) NOT NULL,
  `PrecioVenta` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vias`
--

CREATE TABLE `vias` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `vias`
--

INSERT INTO `vias` (`Id`, `Nombre`) VALUES
(1, 'Oral');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `area`
--
ALTER TABLE `area`
  ADD PRIMARY KEY (`Id`);

--
-- Indices de la tabla `barrios`
--
ALTER TABLE `barrios`
  ADD PRIMARY KEY (`Id`);

--
-- Indices de la tabla `perfil`
--
ALTER TABLE `perfil`
  ADD PRIMARY KEY (`Id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Proveedor` (`Proveedor`),
  ADD KEY `Area` (`Area`),
  ADD KEY `Tipo` (`Tipo`),
  ADD KEY `Via` (`Via`);

--
-- Indices de la tabla `productosucursales`
--
ALTER TABLE `productosucursales`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `ProductoId` (`ProductoId`),
  ADD KEY `SucursalId` (`SucursalId`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`Id`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `ProductoId` (`ProductoId`),
  ADD KEY `CajeroId` (`CajeroId`),
  ADD KEY `ClienteId` (`ClienteId`),
  ADD KEY `CajeroCancelacionId` (`CajeroCancelacionId`);

--
-- Indices de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Barrio` (`Barrio`);

--
-- Indices de la tabla `tipos`
--
ALTER TABLE `tipos`
  ADD PRIMARY KEY (`Id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`usuario_id`),
  ADD KEY `PerfilId` (`PerfilId`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `ClienteId` (`ClienteId`),
  ADD KEY `ProductoId` (`ProductoId`),
  ADD KEY `CajeroId` (`CajeroId`);

--
-- Indices de la tabla `vias`
--
ALTER TABLE `vias`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `area`
--
ALTER TABLE `area`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `barrios`
--
ALTER TABLE `barrios`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `perfil`
--
ALTER TABLE `perfil`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `productosucursales`
--
ALTER TABLE `productosucursales`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `reservas`
--
ALTER TABLE `reservas`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tipos`
--
ALTER TABLE `tipos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `usuario_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vias`
--
ALTER TABLE `vias`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`Proveedor`) REFERENCES `proveedores` (`Id`),
  ADD CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`Area`) REFERENCES `area` (`Id`),
  ADD CONSTRAINT `productos_ibfk_3` FOREIGN KEY (`Tipo`) REFERENCES `tipos` (`Id`),
  ADD CONSTRAINT `productos_ibfk_4` FOREIGN KEY (`Via`) REFERENCES `vias` (`Id`);

--
-- Filtros para la tabla `productosucursales`
--
ALTER TABLE `productosucursales`
  ADD CONSTRAINT `productosucursales_ibfk_1` FOREIGN KEY (`ProductoId`) REFERENCES `productos` (`Id`),
  ADD CONSTRAINT `productosucursales_ibfk_2` FOREIGN KEY (`SucursalId`) REFERENCES `sucursales` (`Id`);

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`ProductoId`) REFERENCES `productos` (`Id`),
  ADD CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`CajeroId`) REFERENCES `usuarios` (`usuario_id`),
  ADD CONSTRAINT `reservas_ibfk_3` FOREIGN KEY (`CajeroCancelacionId`) REFERENCES `usuarios` (`usuario_id`),
  ADD CONSTRAINT `reservas_ibfk_4` FOREIGN KEY (`ClienteId`) REFERENCES `usuarios` (`usuario_id`);

--
-- Filtros para la tabla `sucursales`
--
ALTER TABLE `sucursales`
  ADD CONSTRAINT `sucursales_ibfk_1` FOREIGN KEY (`Barrio`) REFERENCES `barrios` (`Id`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`PerfilId`) REFERENCES `perfil` (`Id`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`CajeroId`) REFERENCES `usuarios` (`usuario_id`),
  ADD CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`ClienteId`) REFERENCES `usuarios` (`usuario_id`),
  ADD CONSTRAINT `ventas_ibfk_3` FOREIGN KEY (`ProductoId`) REFERENCES `productos` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
