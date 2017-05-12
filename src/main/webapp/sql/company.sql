-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2017-05-12 10:32:24
-- 服务器版本： 5.7.16-0ubuntu0.16.04.1
-- PHP Version: 5.6.30-7+deb.sury.org~xenial+1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- 表的结构 `company`
--

CREATE TABLE `company` (
  `id` int(200) NOT NULL,
  `company_name` char(100) NOT NULL,
  `company_key` char(30) NOT NULL,
  `company_assess` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `company`
--

INSERT INTO `company` (`id`, `company_name`, `company_key`, `company_assess`) VALUES
(1, '啊啊', '123', 0.13),
(2, 'change', '123', NULL),
(3, 'baidu', '123', NULL),
(4, 'Infini Studio', '5731669', NULL),
(5, '华为', '3131', NULL),
(6, '华为南京研究所', '185299', NULL),
(7, '华为北研所', '58618', NULL),
(8, '华为西安研究所', '57963', NULL),
(9, '华为赛门铁克', '30531', NULL),
(10, '网易', '3146', NULL),
(11, '网易游戏', '15379', NULL),
(12, '网易有道', '23095', NULL),
(13, '网易杭州研究院', '991121', NULL),
(14, '网易（杭州）网络有限公司', '2036037', NULL),
(15, '网易互动', '28845', NULL),
(16, '百度', '11514', 0.16000000000000003),
(17, '百度糯米', '597367', 0.19999999999999996),
(18, '百度深圳分公司', '2070269', NULL),
(19, '百度广州分公司', '1820460', NULL),
(20, '成都百都（百度）科技公司', '391959', NULL),
(21, '百度盘古', '2079286', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_company` (`company_name`,`company_key`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `company`
--
ALTER TABLE `company`
  MODIFY `id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
