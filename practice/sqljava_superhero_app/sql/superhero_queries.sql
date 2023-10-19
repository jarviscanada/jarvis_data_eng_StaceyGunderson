SELECT gender_id, COUNT(*) FROM superhero WHERE alignment_id = 1 AND (eye_colour_id = 14 OR hair_colour_id in (23-27,32,35) OR (eye_colour_id in (8,11,20,27,31-32) AND NOT hair_colour_id = 4)) GROUP BY gender_id ORDER BY gender_id;

SELECT publisher_id, COUNT(*) AS hero_count  FROM superhero WHERE publisher_id IS NOT NULL GROUP BY publisher_id;

SELECT publisher_id, SUM(publisher_id), hero_count*100/SUM(hero_count)
FROM (SELECT publisher_id, SUM(publisher_id) AS hero_count
      FROM superhero WHERE publisher_id IS NOT NULL GROUP BY publisher_id)
group by publisher_id;


SELECT * FROM superhero WHERE weight_kg > 80 AND race_id IS NOT NULL and height_cm IS NOT NULL;
