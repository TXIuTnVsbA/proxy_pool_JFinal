# proxy_pool_JFinal

利用JAVA的JFinal模块建立的爬虫代理IP池(proxy_pool)

## 数据库

使用: mysql

修改: src/*/resources/config.txt

建立库: ip_pool

建立表: ip

或者使用下面的代码

```
CREATE DATABASE `ip_pool`;
USE `ip_pool`;
DROP TABLE IF EXISTS `ip`;
CREATE TABLE `ip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` char(32) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0' COMMENT '0,未检测;1,http;2,https;3,http+s;4,del',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_ip` (`ip`)
) ENGINE=InnoDB AUTO_INCREMENT=3302 DEFAULT CHARSET=utf8;
```

## 目前

＝ ＝新手写东西，里面还说有坑的。

## 如何获取数据？


http://localhost/get/{Ip数} or http://localhost/getall/{Ip数}

http://localhost/gethttp/{Ip数}

http://localhost/gethttps/{Ip数}


