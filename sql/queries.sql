INSERT INTO cd.facilities (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
VALUES (9, 'Spa', 20, 30, 100000, 800);

INTO cd.facilities (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
select (select max(facid) from cd.facilities)+1, 'Spa', 20, 30, 100000, 800;

UPDATE cd.facilities set initialoutlay = 10000 where facid = 1;

UPDATE cd.facilities set membercost = (select membercost * 1.1 from cd.facilities where facid = 0),
                         guestcost = (select guestcost * 1.1 from cd.facilities where facid = 0)
WHERE facid = 1;

TRUNCATE cd.bookings;

DELETE FROM cd.members WHERE memid = 37;

SELECT facid, name, membercost, monthlymaintenance FROM cd.facilities WHERE (membercost > 0 AND membercost < monthlymaintenance *0.02)

SELECT * FROM cd.facilities WHERE name LIKE '%Tennis%';

SELECT * FROM cd.facilities WHERE facid in (1,5);

SELECT memid, surname, firstname, joindate FROM cd.members WHERE joindate >= '2012-09-01';

SELECT surname FROM cd.members UNION SELECT name AS surname FROM cd.facilities;

SELECT starttime FROM cd.bookings JOIN cd.members
                                       ON bookings.memid = members.memid
WHERE firstname = 'David' AND surname = 'Farrell';

SELECT starttime AS start, name
FROM
    cd.bookings JOIN cd.facilities
                     ON bookings.facid = facilities.facid
WHERE facilities.facid in (1,0) AND
        starttime >= '2012-09-21' AND
        starttime < '2012-09-22'
ORDER BY starttime;

SELECT members.firstname AS memfname, members.surname memsname, rectable.firstname recfname, rectable.surname recsname
FROM cd.members LEFT OUTER JOIN cd.members AS rectable
                                ON members.recommendedby = rectable.memid
ORDER BY memsname, memfname;

SELECT DISTINCT rectable.firstname AS firstname, rectable.surname AS surname
FROM cd.members INNER JOIN cd.members AS rectable
                           ON members.recommendedby = rectable.memid
ORDER BY surname, firstname;

SELECT DISTINCT CONCAT(members.firstname , ' ' , members.surname) AS member,
                (SELECT CONCAT(recs.firstname , ' ' , recs.surname) as recommender
                 FROM cd.members recs
                 WHERE recs.memid = members.recommendedby)
FROM cd.members
ORDER BY member;

SELECT facid, sum(slots) AS "Total Slots"
FROM cd.bookings
GROUP BY facid
ORDER BY facid;

SELECT facid, sum(slots) AS "Total Slots"
FROM cd.bookings
WHERE
        starttime >= '2012-09-01' AND
        starttime < '2012-10-01'
GROUP BY facid
ORDER BY "Total Slots";

SELSECT facid, extract (month from starttime) AS month, sum(slots) AS "Total Slots"
FROM cd.bookings
WHERE extract (year from starttime) = 2012
GROUP BY facid, month
ORDER BY facid, month;

SELECT COUNT(DISTINCT memid)
FROM cd.bookings;

SELECT DISTINCT surname, firstname, members.memid, min(starttime) as starttime
FROM cd.bookings JOIN cd.members
                      ON bookings.memid = members.memid
WHERE bookings.starttime >= '2012-09-01'
GROUP BY members.surname, members.firstname, members.memid
ORDER BY members.memid;

SELECT COUNT(*) over(), firstname, surname
FROM cd.members
ORDER BY joindate;

SELECT ROW_NUMBER() over (order by joindate) AS row_number, firstname, surname
FROM cd.members
ORDER BY joindate;

SELECT facid, total
FROM ( select facid, sum (slots) AS total, rank() over (order by sum(slots) desc) rank
       FROM cd.bookings
       GROUP BY facid ) as rankings
WHERE rank = 1

SELECT surname || ', ' || firstname as name from cd.members;

select memid, telephone
FROM cd.members
WHERE telephone LIKE '%(%)%';

