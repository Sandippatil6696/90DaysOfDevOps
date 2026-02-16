# Day 21 – Shell Scripting Cheat Sheet

# Basics

- **Shebang (#!/bin/bash)**
    - it tells which interpreter is used for cuurent script

- **Running a script — chmod +x, ./script.sh, bash script.sh**

![alt text](images/image.png)


- **Comments — single line (#) and inline**

    - `#this is single line comment` 

    -   <<comment
        this is 
        multi line comment
        comment

    - `echo "my name is sandip" #this is inline comment `


![alt text](images/image-1.png)

- **Variables — declaring, using, and quoting ($VAR, "$VAR", '$VAR')**

- $VAR - will print value of variabele
- "$VAR" - Will also print value of variable
- '$VAR' - will print 'value of variable inside single quote'

![alt text](images/image-2.png)

![alt text](images/image-3.png)

- **Reading user input — read**

- `read -p "Enter your name" NAME`

![alt text](images/image-4.png)

![alt text](images/image-5.png)

- **Command-line arguments — $0, $1, $#, $@, $?**

    - $0 : print 0th number argument i.e script name 
    - $1 : print 1st number argument 
    - $# : count number of argument ignoring 0th argument
    - $@ : print all argument
    - $? " check o/p of last cmd 0- success 1 - failed 

    ![alt text](images/image-6.png)

    ![alt text](images/image-7.png)

# Operators and Conditionals

- **String comparisons : =, !=, -z, -n**

    - `=` : it return true if  string value matches 

    ![alt text](images/image-8.png)

    ![alt text](images/image-9.png)

    - `!=` : it return true if string value does not match

    ![alt text](images/image-10.png)

    ![alt text](images/image-11.png)

    - `-z` : it returns true if string is empty 

    ![alt text](images/image-12.png)

    ![alt text](images/image-13.png)
    
    - `-n` : it returns true if string is not empty

    ![alt text](images/image-14.png)

    ![alt text](images/image-15.png)

- **Integer comparisons : -eq, -ne, -lt, -gt, -le, -ge**

![alt text](images/image-16.png)

![alt text](images/image-17.png)

- **File test operators — -f, -d, -e, -r, -w, -x, -s**

    `-f` : it return true if file exits 

    ![alt text](images/image-18.png)

    ![alt text](images/image-19.png)

    `-d` : it return true if directory exits

    ![alt text](images/image-20.png)

    ![alt text](images/image-21.png)

    -`-e` : it returns true if file or directory exits 

    ![alt text](images/image-22.png)

    ![alt text](images/image-23.png)

    - `-r`: it returns true if file has read permission

    - `-w` : it reurns true if file has write permission

    - `-x` : it returns true if file has execute permission

    - `-s` : it returns true if file is not empty 

    ![alt text](images/image-26.png)

    ![alt text](images/image-24.png)

    ![alt text](images/image-25.png)

- **if, elif, else syntax**

![alt text](images/image-27.png)

![alt text](images/image-28.png)

- **Logical operators — &&, ||, !**


![alt text](images/image-30.png)

![alt text](images/image-29.png)

- **Case statements — case ... esac**

![alt text](images/image-32.png)

![alt text](images/image-31.png)


# Loops

- **for loop — list-based and C-style**

![alt text](images/image-33.png)

![alt text](images/image-34.png)

- **while loop**

![alt text](images/image-35.png)

![alt text](images/image-36.png)

- **untill loop**

![alt text](images/image-38.png)

![alt text](images/image-37.png)

- **Loop control — break, continue**

    - `continue : it will skip the loop at that particular condition`

    ![alt text](images/image-40.png)

    ![alt text](images/image-39.png)

    -`break : it will break the when condition meets`

    ![alt text](images/image-41.png)

    ![alt text](images/image-42.png)

- **Looping over files — for file in *.log**

![alt text](images/image-43.png)

![alt text](images/image-44.png)

- **Looping over command output — while read line**

![alt text](images/image-45.png)

![alt text](images/image-46.png)

# Functions

- **Defining a function — function_name() { ... }**

![alt text](images/image-47.png)

![alt text](images/image-48.png)

# Text Processing Commands

- `grep — search patterns, -i, -r, -c, -n, -v, -E`

    - `-i: case insensitive`
        - `grep -i 'info' zookeeper.log |tail -5`

    ![alt text](images/image-49.png)

    - `-r: this will search word inside directory/sub direcory`
        - ` grep -r 'hellow' /home/ubuntu/`

    ![alt text](images/image-50.png)

    - `-c : this will count word `
        - `grep  -c 'info' zookeeper.log`

    ![alt text](images/image-51.png)

    - `-n : this will print number of line`
        - `grep  -ni 'info' zookeeper.log |tail -2`
    ![alt text](images/image-52.png)

    - `-v : this will print invert `
        - `grep  -vi 'info' zookeeper.log |tail -2`

    ![alt text](images/image-53.png)

    - `E : regular exression`
        - `grep  -E 'INFO|ERROR' zookeeper.log |tail -5`

    ![alt text](images/image-54.png)

- **awk — print columns, field separator, patterns, BEGIN/END**

    - `awk '{print}' zookeeper.log |tail -2`
    - `awk '{print $1}' zookeeper.log |tail -2`
    - `awk '{print $1,$2,$NF}' zookeeper.log |tail -2`
    - `awk '$1 == "2015-08-10"{print $1,$2,$NF}' zookeeper.log |tail -10`

![alt text](images/image-55.png)

- **sed — substitution, delete lines, in-place edit**

    - `substitution & in-place edit`

    ![alt text](images/image-56.png)

    ![alt text](images/image-57.png)

    - `delete lines`

    ![alt text](images/image-58.png)

- **cut — extract columns by delimiter**

    - `cut -d ' ' -f1 zookeeper.log |tail -3 `

    ![alt text](images/image-59.png)

- **sort uniq ,reverse  — alphabetical, numerical, reverse, unique**

    - ` awk '{print $4}' zookeeper.log |sort |uniq -c |sort -nrk1`

    ![alt text](images/image-60.png)

- **tr — translate/delete characters**

    - `Transalate`

    ![alt text](images/image-61.png)

    - `delete`

    ![alt text](images/image-62.png)

- **wc - line/word/char count**

    - `line count: wc -l zookeeper.log`
    - `word count : wc -w zookeeper.log`
    - `Character count: wc -c zookeeper.log`

    ![alt text](images/image-64.png)


**head / tail — first/last N lines, follow mode**

- `head -2 zookeeper.log`
- `tail -2 zookeeper.log`
- `tail -f zookeeper.log`

![alt text](images/image-65.png)

# Useful Patterns and One-Liners

- **Find and delete files older than N days**

    - `find /home/ubuntu/devops/scripts -type f -mtime +8 -delete`

    ![alt text](images/image-67.png)



- **Count lines in all .log files**

    - `wc -l *.log`

    ![alt text](images/image-66.png)

- **Replace a string across multiple files**

    - `sudo find /home/ -type f -exec 's/12457/zewyfsde12hfgt458/g' {} +'`

    ![alt text](images/image-68.png)

- **Check if a service is running**

    - `sudo systemctl status ssh`

- **Monitor disk usage with alerts**

    - `df -h `

- **Parse CSV or JSON from command line**
    - `'awk -F '/' '{print $2}' config.csv`

- **tail a log and filter for errors in real time**

    - `tail -f app.log |grep 'ERROR'`

# Error Handling and Debugging

- Exit codes — $?, exit 0, exit 1
    - `$? : it give the status code of last command`
    - `exit 1 :  exits the script `
    - `exit 0 : scripts execute successful`

- **set -e — exit on error**

- **set -u — treat unset variables as error**

- **set -o pipefail — catch errors in pipes**

- **set -x used to dubug**

- **Trap — trap 'cleanup' EXIT**

    - `it will execute a cleanup function even script fail or success`





