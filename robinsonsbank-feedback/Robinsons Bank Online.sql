SELECT * FROM feedback ORDER BY created_date DESC;





SELECT DISTINCT rating as "Rating",  COUNT(  rating ) AS "Count"
FROM feedback
WHERE TRIM(rating) <> ''
AND TRIM(comment) <> 'UAT_TEST'
AND TRIM(comment) <> 'PROD_TEST'
AND created_date >= '2019-09-01' AND created_date < '2019-10-01'
GROUP BY rating;


SELECT 
rating AS "Rating",
comment AS "Comment" 
FROM feedback
WHERE TRIM(comment) <> ''
AND TRIM(comment) <> 'UAT_TEST'
AND TRIM(comment) <> 'PROD_TEST'
AND created_date >= '2019-09-01' AND created_date < '2019-10-01'
ORDER BY created_date ASC;