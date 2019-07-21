CREATE TABLE `kafka_message` (
  `topic` varchar(200) COLLATE utf8_bin NOT NULL,
  `partition` int(11) NOT NULL,
  `offset` bigint(64) DEFAULT NULL,
  `record_key` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `record_value` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`topic`,`partition`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
