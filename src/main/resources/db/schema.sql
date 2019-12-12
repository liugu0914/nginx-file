CREATE TABLE t_file_result (
  id varchar(32) NOT NULL COMMENT 'id',
  url varchar(500) NOT NULL COMMENT '文件地址',
  size LONGTEXT DEFAULT NULL COMMENT '文件大小',
  filename varchar(1000) DEFAULT NULL COMMENT '文件名称',
  md5 varchar(100) COMMENT 'MD5',
  type varchar(100) COMMENT '文件类型',
  createdt datetime DEFAULT NULL COMMENT '创建时间',
  updatedt datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件存储';
