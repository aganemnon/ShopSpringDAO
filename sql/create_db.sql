-- Create table
CREATE TABLE APP_USER
(
    USER_ID BIGINT NOT NULL,
    USER_NAME VARCHAR(36) NOT NULL,
    ENCRYPTED_PASSWORD VARCHAR(128) NOT NULL,
    ENABLED BIT NOT NULL
);
--
ALTER TABLE APP_USER
  ADD CONSTRAINT APP_USER_PK PRIMARY KEY (USER_ID);

ALTER TABLE APP_USER
  ADD CONSTRAINT APP_USER_UK UNIQUE (USER_NAME);

ALTER TABLE app_user MODIFY USER_ID bigint(20) NOT NULL auto_increment;

-- Create table
create table APP_ROLE
(
  ROLE_ID   BIGINT NOT NULL,
  ROLE_NAME VARCHAR(30) NOT NULL
) ;
--

ALTER TABLE APP_ROLE
  ADD CONSTRAINT APP_ROLE_PK PRIMARY KEY (ROLE_ID);

ALTER TABLE APP_ROLE
  ADD CONSTRAINT APP_ROLE_UK UNIQUE (ROLE_NAME);

ALTER TABLE APP_ROLE MODIFY ROLE_ID bigint(20) NOT NULL auto_increment;

-- Create table
CREATE TABLE USER_ROLE
(
  ID      BIGINT NOT NULL,
  USER_ID BIGINT NOT NULL,
  ROLE_ID BIGINT NOT NULL
);
--
ALTER TABLE USER_ROLE
  ADD CONSTRAINT USER_ROLE_PK PRIMARY KEY (ID);

ALTER TABLE USER_ROLE
  ADD CONSTRAINT USER_ROLE_UK UNIQUE (USER_ID, ROLE_ID);

ALTER TABLE USER_ROLE
  ADD CONSTRAINT USER_ROLE_FK1 FOREIGN KEY (USER_ID)
  REFERENCES APP_USER (USER_ID);

ALTER TABLE USER_ROLE
  ADD CONSTRAINT USER_ROLE_FK2 FOREIGN KEY (ROLE_ID)
  REFERENCES APP_ROLE (ROLE_ID);

ALTER TABLE user_role MODIFY ID bigint(20) NOT NULL auto_increment;

-- Used by Spring Remember Me API.
CREATE TABLE Persistent_Logins (

    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    PRIMARY KEY (series)

);

CREATE TABLE category (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE item (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `description` VARCHAR(255) NULL,
  `cost` BIGINT NULL,
  `image` VARCHAR(255) NULL,
  `category_id` BIGINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO app_role (ROLE_ID, ROLE_NAME)
VALUES (1, 'ROLE_ADMIN');

INSERT INTO  app_role (ROLE_ID, ROLE_NAME)
VALUES (2, 'ROLE_USER');