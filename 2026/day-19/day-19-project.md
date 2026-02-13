# Day 19 – Shell Scripting Project: Log Rotation, Backup & Crontab

# Log Rotation Script

![alt text](images/image-6.png)

![alt text](images/image-4.png)

# Server Backup Script

![alt text](images/image-3.png)

![alt text](images/image-1.png)

![alt text](images/image-2.png)

# Cronjobs 

- `corntab -e `: is used o edit cronjob
- `crontab -l `: is used to list cronjobs

![alt text](images/image-9.png)

- log_rotate.sh every day at 2 AM

    - ` 0 2 * * * bash home/ubuntu/devops/scripts/log_rotate.sh /home/ubuntu/logfiles /home/ubuntu/logbackups`

- backup.sh every Sunday at 3 AM

    - `0 3 * * 7 bash home/ubuntu/devops/scripts/backup.sh /home/ubuntu/data /home/ubuntu/backups`

- health check script every 5 minutes

    - `5 * * * * bash /home/ubuntu/devops/scripts/day18task/system_info.sh >> /home/ubuntu/system_info.log 2>&1`

![alt text](images/image-8.png)

# Combine — Scheduled Maintenance Script

![alt text](images/image-11.png)

![alt text](images/image-12.png)

- Daily at 1 AM

![alt text](images/image-13.png)
