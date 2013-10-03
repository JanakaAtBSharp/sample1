CREATE DATABASE [DemoPatient] ;
USE [DemoPatient];
CREATE TABLE [dbo].[Patient](
	[HealthRecordNumber] [int] NOT NULL,
	[FirstName] [varchar](max) NULL,
	[LastName] [varchar](max) NULL,
	[Gender] [varchar](50) NULL,
	[Age] [int] NULL
	CONSTRAINT PK_Patient_HealthRecordNumber PRIMARY KEY CLUSTERED (HealthRecordNumber)
	)ON [PRIMARY];
	