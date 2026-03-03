# Day 30 – Docker Images & Container Lifecycle

**Task 1: Docker Images**

1. Pull the nginx, ubuntu, and alpine images from Docker Hub

    - `docker pull nginx`
    - `docker pull ubuntu`
    - `docker pull alpine`

2. List all images on your machine — note the sizes

    - `docker images`

    ![alt text](images/image.png)

3. Compare ubuntu vs alpine — why is one much smaller?

    - alpine image is much smaller than ubuntu

4. Inspect an image — what information can you see?

    - `docker inspect ubuntu`

    - it shows detailed information about your container like cmds , layers , image used

5. Remove an image you no longer need

    - `docker rmi <images>`



**Task 2: Image Layers**

1. Run docker image history nginx — what do you see?

    - `docker image history nginx`

    ![alt text](images/image-1.png)

2. Each line is a layer. Note how some layers show sizes and some show 0B

    - Layers show 0B when 
        
        - you run CMD 
        - while changing env 
        - while showing metadata
        - setting configuration 

    - Some layers shows size 

        - while installing packages 
        - while copying workdir 
        - while modifying filesystem
    
3. Write in your notes: What are layers and why does Docker use them?

    - Each line in dockerfile is layer 
    - docker used them for faster build , smaller storage ,fater pull 

**Task 3: Container Lifecycle**

1. Create a container (without starting it)

    - `docker create <image>`

    - it shows created only not run container

    ![alt text](images/image-2.png)


2. Start the container

    - `docker start <containerid>`

    - its shows container is running 

    ![alt text](images/image-3.png)


3. Pause it and check status

    - `docker pause <containerid>`

    - it will pause the container

    ![alt text](images/image-4.png)

4. Unpause it

    - `docker unpause <containerid>`

    - it will unpause conatiner & con. running 

    ![alt text](images/image-5.png)

5. Stop it

    - `docker stop <containerid>`

    - it will stop the conatiner

    ![alt text](images/image-6.png)

6. Restart it

    - `docker restart <containerid>`

    - it will restart the conatiner 

    ![alt text](images/image-7.png)

7. Kill it

    - `docker kill <containerid>`

    - it will kill the container

    ![alt text](images/image-8.png)

8. Remove it

    - `docker rm <containerid>`

    - it will remove container 

    ![alt text](images/image-9.png)

**Task 4: Working with Running Containers**

1. Run an Nginx container in detached mode

    - `docker run -itd nginx`

    ![alt text](images/image-10.png)

2. View its logs

    - `docker logs <containerid>`

    ![alt text](images/image-11.png)

3. View real-time logs (follow mode)

    - `docker logs -f <containerid>`

    ![alt text](images/image-12.png)

4. Exec into the container and look around the filesystem

    - `docker exec -it <containerid> bash`

    ![alt text](images/image-13.png)

5. Run a single command inside the container without entering it

    - `docker exec <containerid> nginx -v`

    ![alt text](images/image-14.png)

6. inspect the container — find its IP address, port mappings, and mounts

    - `docker inspect <containerid>`

    ![alt text](images/image-15.png)

    ![alt text](images/image-16.png)

    ![alt text](images/image-17.png)


**Task 5: Cleanup**

1. Stop all running containers in one command

    - `docker stop $(docker ps -q)`

    ![alt text](images/image-18.png)

2. Remove all stopped containers in one command

    - `docker container prune`

    ![alt text](images/image-19.png)

    - `docker system prune`

    ![alt text](images/image-20.png)

3. Remove unused images

    - `docker image prune`

4. Check how much disk space Docker is using

    - `docker system df `

    ![alt text](images/image-21.png)


