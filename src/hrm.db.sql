BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "employees" (
	"ic_passport"	TEXT,
	"first_name"	TEXT NOT NULL,
	"last_name"	TEXT NOT NULL,
	"role"	TEXT NOT NULL,
	"department"	TEXT NOT NULL,
	PRIMARY KEY("ic_passport")
);
CREATE TABLE IF NOT EXISTS "leave_applications" (
	"leave_id"	TEXT,
	"ic_passport"	TEXT NOT NULL,
	"start_date"	TEXT NOT NULL,
	"end_date"	TEXT NOT NULL,
	"status"	TEXT NOT NULL,
	PRIMARY KEY("leave_id"),
	FOREIGN KEY("ic_passport") REFERENCES "employees"("ic_passport")
);
INSERT INTO "employees" VALUES ('tewe','wstwt','twtwwttw','twwttwtw','twwtwttw');
INSERT INTO "employees" VALUES ('sfsf','sfsf','sfsf','YESSIRRRR','sfsf');
COMMIT;
