xEOF
# Introduction

# SQL Quries

###### Table Setup (DDL)

###### Question 1: 
The club is adding a new facility - a spa. We need to add it into the facilities table. Use the following values:

    facid: 9, Name: 'Spa', membercost: 20, guestcost: 30, initialoutlay: 100000, monthlymaintenance: 800.

```sql
INSERT INTO cd.facilities (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
VALUES (9, 'Spa', 20, 30, 100000, 800);
```

###### Questions 2: 

```sql
INTO cd.facilities (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
select (select max(facid) from cd.facilities)+1, 'Spa', 20, 30, 100000, 800;
```

###### Questions 3:

```sql
UPDATE cd.facilities set initialoutlay = 10000 where facid = 1;
```
###### Questions 4:

```sql
UPDATE cd.facilities set membercost = (select membercost * 1.1 from cd.facilities where facid = 0),
                         guestcost = (select guestcost * 1.1 from cd.facilities where facid = 0)
WHERE facid = 1;
```
###### Questions 5:

```sql
TRUNCATE cd.bookings;
```
###### Questions 6:

```sql
DELETE FROM cd.members WHERE memid = 37;
```
###### Questions 7:How can you produce a list of facilities that charge a fee to members, and that fee is less than 1/50th of the monthly maintenance cost? Return the facid, facility name, member cost, and monthly maintenance of the facilities in question.

```sql
SELECT facid, name, membercost, monthlymaintenance FROM cd.facilities WHERE (membercost > 0 AND membercost < monthlymaintenance *0.02);
```
###### Questions 8: How can you produce a list of all facilities with the word 'Tennis' in their name?

```sql
SELECT * FROM cd.facilities WHERE name LIKE '%Tennis%';
```
###### Questions 9: How can you retrieve the details of facilities with ID 1 and 5? Try to do it without using the OR operator.

```sql
SELECT * FROM cd.facilities WHERE facid in (1,5);
```
###### Questions 10: How can you produce a list of members who joined after the start of September 2012? Return the memid, surname, firstname, and joindate of the members in question.

```sql
SELECT memid, surname, firstname, joindate FROM cd.members WHERE joindate >= '2012-09-01';
```
###### Questions 11: You, for some reason, want a combined list of all surnames and all facility names. Yes, this is a contrived example :-). Produce that list!

```sql
SELECT surname FROM cd.members UNION SELECT name AS surname FROM cd.facilities;
```
###### Questions 12: How can you produce a list of the start times for bookings by members named 'David Farrell'?

```sql
SELECT starttime FROM cd.bookings JOIN cd.members
                                       ON bookings.memid = members.memid
WHERE firstname = 'David' AND surname = 'Farrell';
```

###### Questions 13: How can you produce a list of the start times for bookings for tennis courts, for the date '2012-09-21'? Return a list of start time and facility name pairings, ordered by the time.

```sql
SELECT starttime AS start, name
FROM
    cd.bookings JOIN cd.facilities
                     ON bookings.facid = facilities.facid
WHERE facilities.facid in (1,0) AND
    starttime >= '2012-09-21' AND
    starttime < '2012-09-22'
ORDER BY starttime;
```

###### QuSssions 14: How can you output a list of all members, including the individual who recommended them (if any)? Ensure that results are ordered by (surname, firstname).

```sql
SELECT members.firstname AS memfname, members.surname memsname, rectable.firstname recfname, rectable.surname recsname
FROM cd.members LEFT OUTER JOIN cd.members AS rectable
                                ON members.recommendedby = rectable.memid
ORDER BY memsname, memfname;

```
###### Questions 15: How can you output a list of all members who have recommended another member? Ensure that there are no duplicates in the list, and that results are ordered by (surname, firstname).


```sql
SELECT DISTINCT rectable.firstname AS firstname, rectable.surname AS surname
FROM cd.members INNER JOIN cd.members AS rectable
                           ON members.recommendedby = rectable.memid
ORDER BY surname, firstname;
```
###### Questions 16: How can you output a list of all members, including the individual who recommended them (if any), without using any joins? Ensure that there are no duplicates in the list, and that each firstname + surname pairing is formatted as a column and ordered.

```sql
SELECT DISTINCT CONCAT(members.firstname , ' ' , members.surname) AS member,
                (SELECT CONCAT(recs.firstname , ' ' , recs.surname) as recommender
                 FROM cd.members recs
                 WHERE recs.memid = members.recommendedby)
FROM cd.members
ORDER BY member;
```
###### Questions 17: Produce a count of the number of recommendations each member has made. Order by member ID.


```sql
SELECT recommendedby, COUNT(*) AS count
FROM cd.members
WHERE recommendedby IS NOT NULL
GROUP BY recommendedby
ORDER BY recommendedby;
```
###### Questions 18: Produce a list of the total number of slots booked per facility. For now, just produce an output table consisting of facility id and slots, sorted by facility id.

```sql
SELECT facid, sum(slots) AS "Total Slots"
FROM cd.bookings
GROUP BY facid
ORDER BY facid;
```
###### Questions 19: Produce a list of the total number of slots booked per facility in the month of September 2012. Produce an output table consisting of facility id and slots, sorted by the number of slots.

```sql
SELECT facid, sum(slots) AS "Total Slots"
FROM cd.bookings
WHERE
    starttime >= '2012-09-01' AND
    starttime < '2012-10-01'
GROUP BY facid
ORDER BY "Total Slots";
```
###### Questions 20: Produce a list of the total number of slots booked per facility per month in the year of 2012. Produce an output table consisting of facility id and slots, sorted by the id and month.

```sql
SELECT facid, extract (month from starttime) AS month, sum(slots) AS "Total Slots"
FROM cd.bookings
WHERE extract (year from starttime) = 2012
GROUP BY facid, month
ORDER BY facid, month;
```
###### Questions 21: Find the total number of members (including guests) who have made at least one booking.

```sql
SELECT COUNT(DISTINCT memid)
FROM cd.bookings;
```
###### Questions 22: Produce a list of each member name, id, and their first booking after September 1st 2012. Order by member ID.

```sql
SELECT DISTINCT surname, firstname, members.memid, min(starttime) as starttime
FROM cd.bookings JOIN cd.members
                      ON bookings.memid = members.memid
WHERE bookings.starttime >= '2012-09-01'
GROUP BY members.surname, members.firstname, members.memid
ORDER BY members.memid;
```
###### Questions 23: Produce a list of member names, with each row containing the total member count. Order by join date, and include guest members.

```sql
SELECT COUNT(*) over(), firstname, surname
FROM cd.members
ORDER BY joindate;
```
###### Questions 24: Produce a monotonically increasing numbered list of members (including guests), ordered by their date of joining. Remember that member IDs are not guaranteed to be sequential.


```sql
SELECT ROW_NUMBER() over (order by joindate) AS row_number, firstname, surname
FROM cd.members
ORDER BY joindate;
```
###### Questions 25: 

```sql
SELECT facid, total
FROM ( select facid, sum (slots) AS total, rank() over (order by sum(slots) desc) rank
       FROM cd.bookings
       GROUP BY facid ) as rankings
WHERE rank = 1
```
###### Questions 26: Output the names of all members, formatted as 'Surname, Firstname'

```sql
SELECT surname || ', ' || firstname as name from cd.members;
```
###### Questions 27: You've noticed that the club's member table has telephone numbers with very inconsistent formatting. You'd like to find all the telephone numbers that contain parentheses, returning the member ID and telephone number sorted by member ID.

```sql
select memid, telephone
FROM cd.members
WHERE telephone LIKE '%(%)%';
```
###### Questions 28: You'd like to produce a count of how many members you have whose surname starts with each letter of the alphabet. Sort by the letter, and don't worry about printing out a letter if the count is 0.

```sql
SELECT substr(members.surname,1,1) as letter, count(*) as total
FROM cd.members
GROUP BY letter
ORDER BY letter;
```

EOF
