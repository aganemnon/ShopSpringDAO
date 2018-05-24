CREATE TABLE `catalog`.`item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `description` VARCHAR(255) NULL,
  `cost` BIGINT NULL,
  `image` VARCHAR(255) NULL,
  `category_id` BIGINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
