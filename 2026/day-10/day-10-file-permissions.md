# Day 10 – File Permissions & File Operations Challenge

# File creation 

- Create file using touch , vi , echo 

![alt text](images/image-1.png)

# Read files 

- read file using cat & vim editor

![alt text](images/image-2.png)

![alt text](images/image-3.png)

# Understand Permissions
- notes.txt has permission as below 
    user- read ,write
    group - read ,write
    other - read 

![alt text](images/image-4.png)

# Modify Permissions

-1 Make script.sh executable

![alt text](images/image-5.png)

- 2 Set devops.txt to read-only for all 

![alt text](images/image-6.png)

- 3 Set notes.txt to 640

![alt text](images/image-7.png)

-4 Create directory project/ with permissions 755

![alt text](images/image-8.png)

# Try writing to a read-only file
- I am still able to edit a read only file because i am created a file using ubuntu user so file owner has still permission to edit if it read only

# Try executing a file without execute permission
- i am not able to execute a file without +x permission even execute with sudo 


# trying to enter home directory of other user 
- if ubuntu user has permission rwx-r-x-r-- & i am trying to using other user then i am not able to cd ,ls to ubuntu user home     directory still other user has read permission 
- you have need to given r-w permission to other to enter ubuntu user home directory