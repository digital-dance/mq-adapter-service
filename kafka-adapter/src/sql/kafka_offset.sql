CREATE TABLE `kafka_offset` (
  `topic` varchar(200) COLLATE utf8_bin NOT NULL,
  `partiton` int(11) NOT NULL,
  `offset` bigint(64) DEFAULT NULL,
  PRIMARY KEY (`topic`,`partiton`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
