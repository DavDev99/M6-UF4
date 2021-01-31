-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-01-2021 a las 19:53:08
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dames`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `moviments`
--

CREATE TABLE `moviments` (
  `id` int(11) NOT NULL,
  `id_partida` int(11) NOT NULL,
  `fila` int(11) NOT NULL,
  `columna` int(11) NOT NULL,
  `fila_origen` int(11) NOT NULL,
  `columna_origen` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partides`
--

CREATE TABLE `partides` (
  `id` int(11) NOT NULL,
  `data` varchar(40) NOT NULL,
  `guanyador` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `partides`
--

INSERT INTO `partides` (`id`, `data`, `guanyador`) VALUES
(1, '2021/01/31 19:44:55', NULL),
(2, '2021/01/31 19:50:52', NULL),
(3, '2021/01/31 19:52:40', NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `moviments`
--
ALTER TABLE `moviments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_partida` (`id_partida`);

--
-- Indices de la tabla `partides`
--
ALTER TABLE `partides`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `moviments`
--
ALTER TABLE `moviments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `partides`
--
ALTER TABLE `partides`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `moviments`
--
ALTER TABLE `moviments`
  ADD CONSTRAINT `moviments_ibfk_1` FOREIGN KEY (`id_partida`) REFERENCES `partides` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
