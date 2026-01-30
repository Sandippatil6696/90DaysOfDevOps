# Day 02 – Linux Architecture, Processes, and systemd

---

## Linux Architecture

- **Hardware**
    - It is base layer consist of an **CPU**,**Memory**,**Disk**,**Network** ,**Devices**

- **Kernel**
    - Is an heart of an linux system , kernel understands the hardware ,kernal interact with hardware to run a user task.

- **Shell** 
    - It is used by user to interact with kernel & to execute user commands .

- **Application/User**
    - Application are launched by shell .

--- 

## Process & Process Manegement 
- Everything in linux is processes ,even a cmd executed by user is process .
    - `a cmd enter by user on shell`
    - `shell  calls forks() & created a child process`
    - `child process calls exec()`
    - `kernel schedule it for execution`
- Some of process states as follow 
    - `Running : process is running on cpu`
    - `Sleeping : waiting for i/o`
    - `Zombie : process is finished but not cleaned`
- Some of important commands for process manegement are as follow 
    - `top: its shows all process used to monitor & troubleshoot`
    - `htop: you can horizontally & vertically scroll all process`
    - `ps -a : is used to display all process ` 
    - `kill -9 PID: forcefully terminate a process`

---

## Systemd/init Process

- It is a first process lanuched by kernal ,its pid is always 1 
- It is responsible for starting system service 
- Some of the imp commands of systemd as follow 
    - `systemctl status ssh: it will show ssh service status `
    - `systemctl start ssh: it will start ssh service`
    - `systemctl stop ssh: it will stop ssh service`
    - `systemctl enable ssh: it will start ssh service automatically after server boot`
    - `systemctl status ssh : it will restart ssh service`
    - `systemctl list-units : it will show all active units loaded in systemd`
    - `journalctl -u ssh : it will used to see a logs of systemd processes`




--- 

