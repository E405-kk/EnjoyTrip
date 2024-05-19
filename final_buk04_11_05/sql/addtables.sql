USE `ssafytrip`;
DROP TABLE IF EXISTS `ssafytrip`.`board` ;
DROP TABLE IF EXISTS `ssafytrip`.`notice` ;
DROP TABLE IF EXISTS `ssafytrip`.`tripplan`;
DROP TABLE IF EXISTS `ssafytrip`.`user` ;
DROP TABLE IF EXISTS `ssafytrip`.`file_info` ;
DROP TABLE IF EXISTS `ssafytrip`.`comment` ;
DROP TABLE IF EXISTS `ssafytrip`.`hotplace` ;

CREATE TABLE IF NOT EXISTS `ssafytrip`.`user` (
  `user_id` VARCHAR(16) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_password` VARCHAR(16) NOT NULL,
  `user_email` VARCHAR(50) NULL DEFAULT NULL,
  `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `is_admin` int DEFAULT '0',
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO user VALUES ("ssafy", "김싸피", "1234", "ssafy@ssafy.com", now(), 1);

CREATE TABLE IF NOT EXISTS `ssafytrip`.`board` (
  `article_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `hit` int DEFAULT '0',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  KEY `board_to_user_user_id_fk` (`user_id`),
  CONSTRAINT `board_to_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `ssafytrip`.`notice` (
  `article_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `hit` int DEFAULT '0',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  KEY `notice_to_user_user_id_fk` (`user_id`),
  CONSTRAINT `notice_to_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `ssafytrip`.`tripplan`(
	`num` int NOT NULL auto_increment PRIMARY KEY,
    `user_id` varchar(16) DEFAULT NULL,
    `plan_list` varchar(5000) NOT NULL,
	CONSTRAINT `tripplan_to_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

USE `ssafytrip`;
DROP TABLE IF EXISTS `ssafytrip`.`slang`;

CREATE TABLE IF NOT EXISTS `ssafytrip`.`slang`(
	`num` int NOT NULL auto_increment PRIMARY KEY,
    `word` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `hotplace` (
  `article_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `hit` int DEFAULT '0',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comment_cnt` int DEFAULT '0',
  `good` int DEFAULT '0',
  PRIMARY KEY (`article_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `file_info` (
	`idx` int NOT NULL AUTO_INCREMENT,
	`article_no` int NOT NULL,
	`save_folder` varchar(45) DEFAULT NULL,
	`original_file` varchar(50) DEFAULT NULL,
	`save_file` varchar(50) DEFAULT NULL,
	PRIMARY KEY (`idx`),
	KEY `file_to_hotplace_article_no_fk` (`article_no`),
	CONSTRAINT `file_to_hotplace_article_no_fk` FOREIGN KEY (`article_no`) REFERENCES `hotplace` (`article_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `comment` (
	`idx` int NOT NULL AUTO_INCREMENT,
	`article_no` int NOT NULL,
	`user_id` varchar(16) NOT NULL,
	`content` varchar(2000) DEFAULT NULL,
	`register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`idx`),
	KEY `comment_to_board_article_no_fk` (`article_no`),
	CONSTRAINT `comment_to_board_article_no_fk` FOREIGN KEY (`article_no`) REFERENCES `board` (`article_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO slang (word)
VALUES 
("ㅅㅂ"),
("시발"),
("병신"),
("ㅂㅅ"),
("미친새끼"),
("존나"),
("ㅈㄴ"),
("ㅈㄹ"),
("지랄"),
("씹"),
("니미"),
("니애미"),
("병신"),
("ㅂㅅ"),
("개새끼"),
("씨빨"),
("닥쳐"),
("또라이"),
("걸레"),
("좆밥");
