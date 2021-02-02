-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-12-2020 a las 19:32:30
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `exercici1_m6`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnes`
--

CREATE TABLE `alumnes` (
  `nom` varchar(30) NOT NULL,
  `DNI` varchar(10) NOT NULL,
  `data_naixement` date NOT NULL,
  `adreça_postal` varchar(30) NOT NULL,
  `sexe` varchar(20) NOT NULL,
  `codi_postal` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `alumnes`
--

INSERT INTO `alumnes` (`nom`, `DNI`, `data_naixement`, `adreça_postal`, `sexe`, `codi_postal`) VALUES
('adria', '39933578-T', '1999-03-22', 'asdasdsad', 'home', 44002),
('david', '39933589-T', '1999-03-22', 'asdasd', 'home', 43740);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `poblacions`
--

CREATE TABLE `poblacions` (
  `poblacio` varchar(30) NOT NULL,
  `codi_postal` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `poblacions`
--

INSERT INTO `poblacions` (`poblacio`, `codi_postal`) VALUES
('mora', 43740),
('tarragona', 44002);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnes`
--
ALTER TABLE `alumnes`
  ADD PRIMARY KEY (`DNI`),
  ADD KEY `codi_postal` (`codi_postal`);

--
-- Indices de la tabla `poblacions`
--
ALTER TABLE `poblacions`
  ADD PRIMARY KEY (`codi_postal`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumnes`
--
ALTER TABLE `alumnes`
  ADD CONSTRAINT `alumnes_ibfk_1` FOREIGN KEY (`codi_postal`) REFERENCES `poblacions` (`codi_postal`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
