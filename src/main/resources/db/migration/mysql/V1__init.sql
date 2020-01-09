
create table users(
id integer ,
name varchar(200),
senha varchar(200),
email varchar(200),
primary key (id));

ALTER TABLE users
  MODIFY `id` integer NOT NULL AUTO_INCREMENT;

create table wallet(
id integer ,
name varchar(60),
value decimal(10,2),
primary key (id));

ALTER TABLE wallet
  MODIFY `id` integer  NOT NULL AUTO_INCREMENT;

create table users_wallet(
id integer ,
wallet integer ,
users integer ,
primary key(id));


ALTER TABLE users_wallet
  MODIFY `id` integer  NOT NULL AUTO_INCREMENT;

ALTER TABLE `users_wallet`
  ADD CONSTRAINT `FK46i4k5vl8wah7feutye9kbpi4` FOREIGN KEY (`users`) REFERENCES `users` (`id`);

ALTER TABLE `users_wallet`
  ADD CONSTRAINT `FK46i4k5vl8wah7feutye9kbpi` FOREIGN KEY (`wallet`) REFERENCES `wallet` (`id`);

create table wallet_items(
id integer ,
wallet integer ,
date date,
type varchar(2),
description varchar(500),
value decimal(10,2),
primary key(id)
);


ALTER TABLE wallet_items
  MODIFY `id` integer NOT NULL AUTO_INCREMENT;

ALTER TABLE `wallet_items`
  ADD CONSTRAINT `FK46i4k5vl8wah7feutye9kbp` FOREIGN KEY (`wallet`) REFERENCES `wallet` (`id`);