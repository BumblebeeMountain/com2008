Run the following command to get access to the database on the command line:

```
mysql -h stusql.dcs.shef.ac.uk -u team019 -p team019
```

Followed by this password:

```
21b87683
```

Personal notes:
```
insert into User values ("dbarter1@sheffield.ac.uk","MR", "Dom", "Barter", "bad-password", "STUDENT");
insert into Student (email, personalTutor) values ("dbarter1@sheffield.ac.uk","Dawn Walker");
insert into Degree values ("COMU01", "Computer Science", False, 3);
insert into Degree values ("COMU02", "Computer Science w/ Year In Industry", True, 3);
```