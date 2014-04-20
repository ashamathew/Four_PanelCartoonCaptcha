/*
SQLyog Ultimate v9.02 
MySQL - 5.0.41-community-nt : Database - captcha
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`captcha` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `captcha`;

/*Table structure for table `pass` */

DROP TABLE IF EXISTS `pass`;

CREATE TABLE `pass` (
  `pass` varchar(100) default NULL,
  `username` varchar(100) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pass` */

/*Table structure for table `position` */

DROP TABLE IF EXISTS `position`;

CREATE TABLE `position` (
  `positionX` varchar(50) default NULL,
  `positionY` varchar(50) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `position` */

insert  into `position`(`positionX`,`positionY`) values ('[54, 87, 44, 216, 35, 105]','[73, 38, 54, 120, 145, 51]');

/*Table structure for table `profile` */

DROP TABLE IF EXISTS `profile`;

CREATE TABLE `profile` (
  `username` varchar(100) default NULL,
  `password` varchar(100) default NULL,
  `firstname` varchar(100) default NULL,
  `lastname` varchar(100) default NULL,
  `address1` varchar(100) default NULL,
  `address2` varchar(100) default NULL,
  `city` varchar(100) default NULL,
  `state` varchar(100) default NULL,
  `zipcode` varchar(100) default NULL,
  `telephone` varchar(100) default NULL,
  `email` varchar(100) default NULL,
  `check1` varchar(10) default NULL,
  `check2` varchar(10) default NULL,
  `check3` varchar(10) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `profile` */

insert  into `profile`(`username`,`password`,`firstname`,`lastname`,`address1`,`address2`,`city`,`state`,`zipcode`,`telephone`,`email`,`check1`,`check2`,`check3`) values ('a','rt9cp/YULRZZVRv0zi/SFQ==','a','a','a','a','a','a','a','a','thamaskumar.s@pantechmail.com','13.jpg','13.jpg','13.jpg');

/*Table structure for table `words` */

DROP TABLE IF EXISTS `words`;

CREATE TABLE `words` (
  `word` varchar(10) default NULL,
  `desc` varchar(1000) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `words` */

insert  into `words`(`word`,`desc`) values ('ABACUS','An ABACUS is a manual aid to calculating that consists of beads or disks that can be moved up and down on a series of sticks or strings within a usually wooden frame. The abacus itself doesn\'t calculate; it\'s simply a device for helping a human being to calculate by remembering what has been counted. The modern Chinese abacus, which is still widely used in China and other countries, dates from about 1200 A.D.'),('ANDROID','ANDROID is a Linux-based operating system designed primarily for touchscreen mobile devices such as smartphones and tablet computers. Initially developed by Android, Inc., which Google backed financially and later purchased in 2005,[9] Android was unveiled in 2007 along with the founding of the Open Handset Alliance: a consortium of hardware, software, and telecommunication companies devoted to advancing open standards for mobile devices.[10] The first Android-powered phone was sold in October 2008.'),('NETBOOK','NETBOOKs are small portable computing device, similar to a notebook, and are great for surfing the Web and checking e-mail. What differentiates a netbook from a notebook is its physical size and computing power.'),('UBUNTU','UBUNTU is a community-developed Debian-based Linux operating system that can be used on desktops, laptops or servers. The operating system includes a variety of applications including those for word processing, e-mail applications, Web server software and also programming tools'),('ROBOT','Traditionally, ROBOT is a device that can move and react to sensory input. Robots are widely used in factories to perform high-precision jobs, such as welding and riveting, and are also used in special situations that would otherwise be dangerous for humans (for example, cleaning toxic waste or defusing bombs). Robotics refers to the field of computer science and engineering concerned with creating robots; it is a branch of artificial intelligence.'),('BACKUP','BACKUP is the activity of copying files or databases so that they will be preserved in case of equipment failure or other catastrophe. Backup is usually a routine part of the operation of large businesses with mainframes as well as the administrators of smaller business computers. For personal computer users, backup is also necessary but often neglected. The retrieval of files you backed up is called restoring them.'),('APPLET','An APPLET is a small program or application, usually written in Java, that runs on a Web browser and powers many of the fancier features (such as animation or computation). It downloads quickly and can be used by any computer equipped with a Java- or ActiveX-enabled browser. Applets are found both online and offline (for example, the calculator on Windows 95 is an applet).'),('PARSER','In computer technology, a PARSER is a program, usually part of a compiler, that receives input in the form of sequential source program instructions, interactive online commands, markup tags, or some other defined interface and breaks them up into parts (for example, the nouns (objects), verbs (methods), and their attributes or options) that can then be managed by other programming (for example, other components in a compiler). A parser may also check to see that all input has been provided that is necessary.'),('PAYPAL','PAYPAL is a Web-based application for the secure transfer of funds between member accounts. It doesn\'t cost the user anything to join PayPal or to send money through the service, but there is a fee structure in place for those members who wish to receive money. PayPal relies on the existing infrastructure used by financial institutions and credit card companies and uses advanced fraud prevention technologies to enhance the security of transactions.'),('PERL','PERL is a script programming language that is similar in syntax to the C language and that includes a number of popular UNIX facilities such as sed, awk, and tr. Perl is an interpreted language that can optionally be compiled just before execution into either C code or cross-platform bytecode. When compiled, a Perl program is almost (but not quite) as fast as a fully precompiled C language program. Perl is regarded as a good choice for developing common gateway interface (CGI) programs because it has good text manipulation facilities (although it also handles binary files). It was invented by Larry Wall.'),('PIRACY','Software PIRACY is the illegal copying, distribution, or use of software. It is such a profitable \"business\" that it has caught the attention of organized crime groups in a number of countries. According to the Business Software Alliance (BSA), about 36% of all software in current use is stolen. Software piracy causes significant lost revenue for publishers, which in turn results in higher prices for the consumer.'),('PIXEL','The PIXEL (a word invented from \"picture element\") is the basic unit of programmable color on a computer display or in a computer image. Think of it as a logical - rather than a physical - unit. The physical size of a pixel depends on how you\'ve set the resolution for the display screen. If you\'ve set the display to its maximum resolution, the physical size of a pixel will equal the physical size of the dot pitch (let\'s just call it the dot size) of the display. '),('TASK','In computer programming, a TASK is a basic unit of programming that an operating system controls. Depending on how the operating system defines a task in its design, this unit of programming may be an entire program or each successive invocation of a program. Since one program may make requests of other utility programs, the utility programs may also be considered tasks (or subtasks). All of today\'s widely-used operating systems support multitasking , which allows multiple tasks to run concurrently, taking turns using the resources of the computer.'),('KERNEL','The KERNEL is the essential center of a computer operating system, the core that provides basic services for all other parts of the operating system. A synonym is nucleus. A kernel can be contrasted with a shell, the outermost part of an operating system that interacts with user commands. Kernel and shell are terms used more frequently in Unix operating systems than in IBM mainframe or Microsoft Windows systems.'),('RADAR','RADAR is an acronym for \"radio detection and ranging.\" A radar system usually operates in the ultra-high-frequency (UHF) or microwave part of the radio-frequency (RF) spectrum, and is used to detect the position and/or movement of objects. Radar can track storm systems, because precipitation reflects electromagnetic fields at certain frequencies. Radar can also render precise maps. Radar systems are widely used in air-traffic control, aircraft navigation, and marine navigation.');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
