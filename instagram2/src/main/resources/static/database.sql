create database ig2
use  ig2;
CREATE TABLE post_like (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           postId BIGINT NOT NULL,
                           userId BIGINT NOT NULL,
                           CONSTRAINT fk_post_like FOREIGN KEY (postId) REFERENCES post(id),
                           CONSTRAINT fk_user_like FOREIGN KEY (userId) REFERENCES user(id),
                           UNIQUE (postId, userId)
);
CREATE TABLE follow (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        followerId BIGINT NOT NULL,
                        followeeId BIGINT NOT NULL,
                        CONSTRAINT fk_follower FOREIGN KEY (followerId) REFERENCES user(id),
                        CONSTRAINT fk_followee FOREIGN KEY (followeeId) REFERENCES user(id),
                        UNIQUE (followerId, followeeId)
);
CREATE TABLE comment (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         postId BIGINT NOT NULL,
                         userId BIGINT NOT NULL,
                         content TEXT,
                         createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         modifiedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         CONSTRAINT fk_post_comment FOREIGN KEY (postId) REFERENCES post(id),
                         CONSTRAINT fk_user_comment FOREIGN KEY (userId) REFERENCES user(id)
);CREATE TABLE role (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        roleName VARCHAR(255) NOT NULL UNIQUE
  );
CREATE TABLE post (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      userId BIGINT NOT NULL,
                      shortDescription VARCHAR(255),
                      content TEXT,
                      createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      modifiedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      deletedDate TIMESTAMP,
                      state ENUM('active', 'deleted') DEFAULT 'active',
                      CONSTRAINT fk_user_post FOREIGN KEY (userId) REFERENCES user(id)
);CREATE TABLE user (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(255) NOT NULL UNIQUE,
                        dateOfBirth DATE,
                        password VARCHAR(255) NOT NULL,
                        createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        modifiedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        deletedDate TIMESTAMP,
                        state ENUM('active', 'deleted') DEFAULT 'active'
  );