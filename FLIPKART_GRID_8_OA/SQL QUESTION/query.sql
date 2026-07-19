WITH UserInternetUsage AS (
    SELECT 
        mc.user_ref,
        SUM(dsc.internet_mb) AS total_internet_usage
    FROM 
        mobile_connections mc
    JOIN 
        daily_service_consumption dsc ON mc.connection_ref = dsc.connection_ref
    GROUP BY 
        mc.user_ref
)
SELECT 
    user_ref,
    total_internet_usage
FROM 
    UserInternetUsage
WHERE 
    total_internet_usage = (SELECT MAX(total_internet_usage) FROM UserInternetUsage)
ORDER BY 
    user_ref ASC;
