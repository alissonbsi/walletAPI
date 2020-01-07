
create table users(
id bigint,
name varchar(200),
password varchar(8),
email varchar(200),
primary key (id));

create table wallet(
id bigint,
name varchar(60),
value numeric(10,2),
primary key (id));

create table users_wallet(
id bigint,
wallet bigint,
users bigint,
primary key(id));

ALTER TABLE `users_wallet`
  ADD CONSTRAINT `FK46i4k5vl8wah7feutye9kbpi4` FOREIGN KEY (`users`) REFERENCES `users` (`id`);

ALTER TABLE `users_wallet`
  ADD CONSTRAINT `FK46i4k5vl8wah7feutye9kbpi` FOREIGN KEY (`wallet`) REFERENCES `wallet` (`id`);

create table wallet_items(
id bigint,
wallet bigint,
date date,
type varchar(2),
description varchar(500),
value decimal(10,2),
primary key(id)
);

ALTER TABLE `wallet_items`
  ADD CONSTRAINT `FK46i4k5vl8wah7feutye9kbp` FOREIGN KEY (`wallet`) REFERENCES `wallet` (`id`);