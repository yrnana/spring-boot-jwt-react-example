-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.4.8-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- spring 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `spring` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `spring`;

-- 테이블 spring.departments 구조 내보내기
CREATE TABLE IF NOT EXISTS `departments` (
  `department_id` int(11) unsigned NOT NULL,
  `department_name` varchar(30) NOT NULL,
  `manager_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`department_id`),
  KEY `manager_id` (`manager_id`),
  CONSTRAINT `departments_ibfk_1` FOREIGN KEY (`manager_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 spring.departments:~27 rows (대략적) 내보내기
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` (`department_id`, `department_name`, `manager_id`) VALUES
	(10, 'Administration', 200),
	(20, 'Marketing', 201),
	(30, 'Purchasing', 114),
	(40, 'Human Resources', 203),
	(50, 'Shipping', 121),
	(60, 'IT', 103),
	(70, 'Public Relations', 204),
	(80, 'Sales', 145),
	(90, 'Executive', 100),
	(100, 'Finance', 108),
	(110, 'Accounting', 205),
	(120, 'Treasury', NULL),
	(130, 'Corporate Tax', NULL),
	(140, 'Control And Credit', NULL),
	(150, 'Shareholder Services', NULL),
	(160, 'Benefits', NULL),
	(170, 'Manufacturing', NULL),
	(180, 'Construction', NULL),
	(190, 'Contracting', NULL),
	(200, 'Operations', NULL),
	(210, 'IT Support', NULL),
	(220, 'NOC', NULL),
	(230, 'IT Helpdesk', NULL),
	(240, 'Government Sales', NULL),
	(250, 'Retail Sales', NULL),
	(260, 'Recruiting', NULL),
	(270, 'Payroll', NULL);
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;

-- 테이블 spring.employees 구조 내보내기
CREATE TABLE IF NOT EXISTS `employees` (
  `employee_id` int(11) unsigned NOT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(25) NOT NULL,
  `hire_date` date NOT NULL,
  `salary` decimal(8,2) NOT NULL,
  `job_id` varchar(10) NOT NULL,
  `department_id` int(11) unsigned DEFAULT NULL,
  `manager_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `job_id` (`job_id`),
  KEY `department_id` (`department_id`),
  KEY `manager_id` (`manager_id`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`job_id`),
  CONSTRAINT `employees_ibfk_2` FOREIGN KEY (`department_id`) REFERENCES `departments` (`department_id`),
  CONSTRAINT `employees_ibfk_3` FOREIGN KEY (`manager_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 spring.employees:~107 rows (대략적) 내보내기
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` (`employee_id`, `first_name`, `last_name`, `hire_date`, `salary`, `job_id`, `department_id`, `manager_id`) VALUES
	(100, 'Steven', 'King', '1987-06-17', 24000.00, 'AD_PRES', 90, NULL),
	(101, 'Neena', 'Kochhar', '1989-09-21', 17000.00, 'AD_VP', 90, 100),
	(102, 'Lex', 'De Haan', '1993-01-13', 17000.00, 'AD_VP', 90, 100),
	(103, 'Alexander', 'Hunold', '1990-01-03', 9000.00, 'IT_PROG', 60, 102),
	(104, 'Bruce', 'Ernst', '1991-05-21', 6000.00, 'IT_PROG', 60, 103),
	(105, 'David', 'Austin', '1997-06-25', 4800.00, 'IT_PROG', 60, 103),
	(106, 'Valli', 'Pataballa', '1998-02-05', 4800.00, 'IT_PROG', 60, 103),
	(107, 'Diana', 'Lorentz', '1999-02-07', 4200.00, 'IT_PROG', 60, 103),
	(108, 'Nancy', 'Greenberg', '1994-08-17', 12000.00, 'FI_MGR', 100, 101),
	(109, 'Daniel', 'Faviet', '1994-08-16', 9000.00, 'FI_ACCOUNT', 100, 108),
	(110, 'John', 'Chen', '1997-09-28', 8200.00, 'FI_ACCOUNT', 100, 108),
	(111, 'Ismael', 'Sciarra', '1997-09-30', 7700.00, 'FI_ACCOUNT', 100, 108),
	(112, 'Jose Manuel', 'Urman', '1998-03-07', 7800.00, 'FI_ACCOUNT', 100, 108),
	(113, 'Luis', 'Popp', '1999-12-07', 6900.00, 'FI_ACCOUNT', 100, 108),
	(114, 'Den', 'Raphaely', '1994-12-07', 11000.00, 'PU_MAN', 30, 100),
	(115, 'Alexander', 'Khoo', '1995-05-18', 3100.00, 'PU_CLERK', 30, 114),
	(116, 'Shelli', 'Baida', '1997-12-24', 2900.00, 'PU_CLERK', 30, 114),
	(117, 'Sigal', 'Tobias', '1997-07-24', 2800.00, 'PU_CLERK', 30, 114),
	(118, 'Guy', 'Himuro', '1998-11-15', 2600.00, 'PU_CLERK', 30, 114),
	(119, 'Karen', 'Colmenares', '1999-08-10', 2500.00, 'PU_CLERK', 30, 114),
	(120, 'Matthew', 'Weiss', '1996-07-18', 8000.00, 'ST_MAN', 50, 100),
	(121, 'Adam', 'Fripp', '1997-04-10', 8200.00, 'ST_MAN', 50, 100),
	(122, 'Payam', 'Kaufling', '1995-05-01', 7900.00, 'ST_MAN', 50, 100),
	(123, 'Shanta', 'Vollman', '1997-10-10', 6500.00, 'ST_MAN', 50, 100),
	(124, 'Kevin', 'Mourgos', '1999-11-16', 5800.00, 'ST_MAN', 50, 100),
	(125, 'Julia', 'Nayer', '1997-07-16', 3200.00, 'ST_CLERK', 50, 120),
	(126, 'Irene', 'Mikkilineni', '1998-09-28', 2700.00, 'ST_CLERK', 50, 120),
	(127, 'James', 'Landry', '1999-01-14', 2400.00, 'ST_CLERK', 50, 120),
	(128, 'Steven', 'Markle', '2000-03-08', 2200.00, 'ST_CLERK', 50, 120),
	(129, 'Laura', 'Bissot', '1997-08-20', 3300.00, 'ST_CLERK', 50, 121),
	(130, 'Mozhe', 'Atkinson', '1997-10-30', 2800.00, 'ST_CLERK', 50, 121),
	(131, 'James', 'Marlow', '1997-02-16', 2500.00, 'ST_CLERK', 50, 121),
	(132, 'TJ', 'Olson', '1999-04-10', 2100.00, 'ST_CLERK', 50, 121),
	(133, 'Jason', 'Mallin', '1996-06-14', 3300.00, 'ST_CLERK', 50, 122),
	(134, 'Michael', 'Rogers', '1998-08-26', 2900.00, 'ST_CLERK', 50, 122),
	(135, 'Ki', 'Gee', '1999-12-12', 2400.00, 'ST_CLERK', 50, 122),
	(136, 'Hazel', 'Philtanker', '2000-02-06', 2200.00, 'ST_CLERK', 50, 122),
	(137, 'Renske', 'Ladwig', '1995-07-14', 3600.00, 'ST_CLERK', 50, 123),
	(138, 'Stephen', 'Stiles', '1997-10-26', 3200.00, 'ST_CLERK', 50, 123),
	(139, 'John', 'Seo', '1998-02-12', 2700.00, 'ST_CLERK', 50, 123),
	(140, 'Joshua', 'Patel', '1998-04-06', 2500.00, 'ST_CLERK', 50, 123),
	(141, 'Trenna', 'Rajs', '1995-10-17', 3500.00, 'ST_CLERK', 50, 124),
	(142, 'Curtis', 'Davies', '1997-01-29', 3100.00, 'ST_CLERK', 50, 124),
	(143, 'Randall', 'Matos', '1998-03-15', 2600.00, 'ST_CLERK', 50, 124),
	(144, 'Peter', 'Vargas', '1998-07-09', 2500.00, 'ST_CLERK', 50, 124),
	(145, 'John', 'Russell', '1996-10-01', 14000.00, 'SA_MAN', 80, 100),
	(146, 'Karen', 'Partners', '1997-01-05', 13500.00, 'SA_MAN', 80, 100),
	(147, 'Alberto', 'Errazuriz', '1997-03-10', 12000.00, 'SA_MAN', 80, 100),
	(148, 'Gerald', 'Cambrault', '1999-10-15', 11000.00, 'SA_MAN', 80, 100),
	(149, 'Eleni', 'Zlotkey', '2000-01-29', 10500.00, 'SA_MAN', 80, 100),
	(150, 'Peter', 'Tucker', '1997-01-30', 10000.00, 'SA_REP', 80, 145),
	(151, 'David', 'Bernstein', '1997-03-24', 9500.00, 'SA_REP', 80, 145),
	(152, 'Peter', 'Hall', '1997-08-20', 9000.00, 'SA_REP', 80, 145),
	(153, 'Christopher', 'Olsen', '1998-03-30', 8000.00, 'SA_REP', 80, 145),
	(154, 'Nanette', 'Cambrault', '1998-12-09', 7500.00, 'SA_REP', 80, 145),
	(155, 'Oliver', 'Tuvault', '1999-11-23', 7000.00, 'SA_REP', 80, 145),
	(156, 'Janette', 'King', '1996-01-30', 10000.00, 'SA_REP', 80, 146),
	(157, 'Patrick', 'Sully', '1996-03-04', 9500.00, 'SA_REP', 80, 146),
	(158, 'Allan', 'McEwen', '1996-08-01', 9000.00, 'SA_REP', 80, 146),
	(159, 'Lindsey', 'Smith', '1997-03-10', 8000.00, 'SA_REP', 80, 146),
	(160, 'Louise', 'Doran', '1997-12-15', 7500.00, 'SA_REP', 80, 146),
	(161, 'Sarath', 'Sewall', '1998-11-03', 7000.00, 'SA_REP', 80, 146),
	(162, 'Clara', 'Vishney', '1997-11-11', 10500.00, 'SA_REP', 80, 147),
	(163, 'Danielle', 'Greene', '1999-03-19', 9500.00, 'SA_REP', 80, 147),
	(164, 'Mattea', 'Marvins', '2000-01-24', 7200.00, 'SA_REP', 80, 147),
	(165, 'David', 'Lee', '2000-02-23', 6800.00, 'SA_REP', 80, 147),
	(166, 'Sundar', 'Ande', '2000-03-24', 6400.00, 'SA_REP', 80, 147),
	(167, 'Amit', 'Banda', '2000-04-21', 6200.00, 'SA_REP', 80, 147),
	(168, 'Lisa', 'Ozer', '1997-03-11', 11500.00, 'SA_REP', 80, 148),
	(169, 'Harrison', 'Bloom', '1998-03-23', 10000.00, 'SA_REP', 80, 148),
	(170, 'Tayler', 'Fox', '1998-01-24', 9600.00, 'SA_REP', 80, 148),
	(171, 'William', 'Smith', '1999-02-23', 7400.00, 'SA_REP', 80, 148),
	(172, 'Elizabeth', 'Bates', '1999-03-24', 7300.00, 'SA_REP', 80, 148),
	(173, 'Sundita', 'Kumar', '2000-04-21', 6100.00, 'SA_REP', 80, 148),
	(174, 'Ellen', 'Abel', '1996-05-11', 11000.00, 'SA_REP', 80, 149),
	(175, 'Alyssa', 'Hutton', '1997-03-19', 8800.00, 'SA_REP', 80, 149),
	(176, 'Jonathon', 'Taylor', '1998-03-24', 8600.00, 'SA_REP', 80, 149),
	(177, 'Jack', 'Livingston', '1998-04-23', 8400.00, 'SA_REP', 80, 149),
	(178, 'Kimberely', 'Grant', '1999-05-24', 7000.00, 'SA_REP', NULL, 149),
	(179, 'Charles', 'Johnson', '2000-01-04', 6200.00, 'SA_REP', 80, 149),
	(180, 'Winston', 'Taylor', '1998-01-24', 3200.00, 'SH_CLERK', 50, 120),
	(181, 'Jean', 'Fleaur', '1998-02-23', 3100.00, 'SH_CLERK', 50, 120),
	(182, 'Martha', 'Sullivan', '1999-06-21', 2500.00, 'SH_CLERK', 50, 120),
	(183, 'Girard', 'Geoni', '2000-02-03', 2800.00, 'SH_CLERK', 50, 120),
	(184, 'Nandita', 'Sarchand', '1996-01-27', 4200.00, 'SH_CLERK', 50, 121),
	(185, 'Alexis', 'Bull', '1997-02-20', 4100.00, 'SH_CLERK', 50, 121),
	(186, 'Julia', 'Dellinger', '1998-06-24', 3400.00, 'SH_CLERK', 50, 121),
	(187, 'Anthony', 'Cabrio', '1999-02-07', 3000.00, 'SH_CLERK', 50, 121),
	(188, 'Kelly', 'Chung', '1997-06-14', 3800.00, 'SH_CLERK', 50, 122),
	(189, 'Jennifer', 'Dilly', '1997-08-13', 3600.00, 'SH_CLERK', 50, 122),
	(190, 'Timothy', 'Gates', '1998-07-11', 2900.00, 'SH_CLERK', 50, 122),
	(191, 'Randall', 'Perkins', '1999-12-19', 2500.00, 'SH_CLERK', 50, 122),
	(192, 'Sarah', 'Bell', '1996-02-04', 4000.00, 'SH_CLERK', 50, 123),
	(193, 'Britney', 'Everett', '1997-03-03', 3900.00, 'SH_CLERK', 50, 123),
	(194, 'Samuel', 'McCain', '1998-07-01', 3200.00, 'SH_CLERK', 50, 123),
	(195, 'Vance', 'Jones', '1999-03-17', 2800.00, 'SH_CLERK', 50, 123),
	(196, 'Alana', 'Walsh', '1998-04-24', 3100.00, 'SH_CLERK', 50, 124),
	(197, 'Kevin', 'Feeney', '1998-05-23', 3000.00, 'SH_CLERK', 50, 124),
	(198, 'Donald', 'OConnell', '1999-06-21', 2600.00, 'SH_CLERK', 50, 124),
	(199, 'Douglas', 'Grant', '2000-01-13', 2600.00, 'SH_CLERK', 50, 124),
	(200, 'Jennifer', 'Whalen', '1987-09-17', 4400.00, 'AD_ASST', 10, 101),
	(201, 'Michael', 'Hartstein', '1996-02-17', 13000.00, 'MK_MAN', 20, 100),
	(202, 'Pat', 'Fay', '1997-08-17', 6000.00, 'MK_REP', 20, 201),
	(203, 'Susan', 'Mavris', '1994-06-07', 6500.00, 'HR_REP', 40, 101),
	(204, 'Hermann', 'Baer', '1994-06-07', 10000.00, 'PR_REP', 70, 101),
	(205, 'Shelley', 'Higgins', '1994-06-07', 12000.00, 'AC_MGR', 110, 101),
	(206, 'William', 'Gietz', '1994-06-07', 8300.00, 'AC_ACCOUNT', 110, 205);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;

-- 테이블 spring.jobs 구조 내보내기
CREATE TABLE IF NOT EXISTS `jobs` (
  `job_id` varchar(10) NOT NULL,
  `job_title` varchar(35) NOT NULL,
  `min_salary` decimal(8,0) unsigned DEFAULT NULL,
  `max_salary` decimal(8,0) unsigned DEFAULT NULL,
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 spring.jobs:~19 rows (대략적) 내보내기
/*!40000 ALTER TABLE `jobs` DISABLE KEYS */;
INSERT INTO `jobs` (`job_id`, `job_title`, `min_salary`, `max_salary`) VALUES
	('AC_ACCOUNT', 'Public Accountant', 4200, 9000),
	('AC_MGR', 'Accounting Manager', 8200, 16000),
	('AD_ASST', 'Administration Assistant', 3000, 6000),
	('AD_PRES', 'President', 20000, 40000),
	('AD_VP', 'Administration Vice President', 15000, 30000),
	('FI_ACCOUNT', 'Accountant', 4200, 9000),
	('FI_MGR', 'Finance Manager', 8200, 16000),
	('HR_REP', 'Human Resources Representative', 4000, 9000),
	('IT_PROG', 'Programmer', 4000, 10000),
	('MK_MAN', 'Marketing Manager', 9000, 15000),
	('MK_REP', 'Marketing Representative', 4000, 9000),
	('PR_REP', 'Public Relations Representative', 4500, 10500),
	('PU_CLERK', 'Purchasing Clerk', 2500, 5500),
	('PU_MAN', 'Purchasing Manager', 8000, 15000),
	('SA_MAN', 'Sales Manager', 10000, 20000),
	('SA_REP', 'Sales Representative', 6000, 12000),
	('SH_CLERK', 'Shipping Clerk', 2500, 5500),
	('ST_CLERK', 'Stock Clerk', 2000, 5000),
	('ST_MAN', 'Stock Manager', 5500, 8500);
/*!40000 ALTER TABLE `jobs` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
