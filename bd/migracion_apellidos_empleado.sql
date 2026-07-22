-- Migracion de empleado.apellido a apellidos separados.
-- Ejecutar una sola vez sobre una base existente antes de desplegar la nueva version.
USE `erp_mamatomato`;

ALTER TABLE `empleado`
  CHANGE COLUMN `apellido` `apellido_paterno` varchar(20) NOT NULL,
  ADD COLUMN `apellido_materno` varchar(20) NOT NULL DEFAULT '' AFTER `apellido_paterno`;
