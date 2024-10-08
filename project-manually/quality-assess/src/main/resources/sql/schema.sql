CREATE DATABASE IF NOT EXISTS `quality-assess` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `quality-assess`.`user` (
    ID INT AUTO_INCREMENT NOT NULL,
    USERNAME VARCHAR(255) NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    PASSWORD VARCHAR(255) DEFAULT NULL,
    EMAIL VARCHAR(255) NOT NULL,
    ROLE TINYINT DEFAULT 0,
    IS_DELETED TINYINT DEFAULT 0,
    CREATED_AT DATETIME DEFAULT NOW(),
    UPDATED_AT DATETIME DEFAULT NOW(),
    PRIMARY KEY (`ID`),
    UNIQUE `IDX_UK_USERNAME` (`USERNAME`),
    UNIQUE `IDX_UK_EMAIL` (`EMAIL`),
    INDEX `IDX_NO_NAME` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;