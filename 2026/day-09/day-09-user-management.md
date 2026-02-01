# Day 09 – Linux User & Group Management Challenge

---

# Task 1: Create Users

- `Create user`

![alt text](/2026/day-09/Images/image.png)

![alt text](/2026/day-09/Images/image-1.png)

- `check users`

![alt text](/2026/day-09/Images/image-5.png)

- `Set password for user`

![alt text](/2026/day-09/Images/image-2.png)


# ask 2: Create Groups

- `create group`

![alt text](/2026/day-09/Images/image-3.png)

- `check group`

![alt text](/2026/day-09/Images/image-4.png)

# Task 3: Assign to Groups

`Assign users`

- `tokyo → developers`

![alt text](/2026/day-09/Images/image-6.png)

- `berlin → developers + admins (both groups)`

![alt text](/2026/day-09/Images/image-8.png)

- `Remove user from group`

![alt text](/2026/day-09/Images/image-7.png)

- `professor → admins`

![alt text](/2026/day-09/Images/image-9.png)

# Task 4: Shared Directory

1. Create directory: `/opt/dev-project`
2. Set group owner to `developers`
3. Set permissions to `775` (rwxrwxr-x)
4. Test by creating files as `tokyo` and `berlin`

![alt text](/2026/day-09/Images/image-10.png)

# Team Workspace

1. Create user `nairobi` with home directory
2. Create group `project-team`
3. Add `nairobi` and `tokyo` to `project-team`
4. Create `/opt/team-workspace` directory
5. Set group to `project-team`, permissions to `775`
6. Test by creating file as `nairobi`

![alt text](/2026/day-09/Images/image-11.png)


