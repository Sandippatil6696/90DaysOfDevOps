# Git cheatsheet

# Setup & Config

- To check git version

`git --version`

- To set global author on your system

`git config --global user.name "sandip"`

- To set global author email on your system

`git config --global user.email "xyz@gmail.com"`

- To set a global default branchname on your system 

`git config --global init.defaultBranch <name>`

- To see a config details on your system 

`git config user.name`

`git config user.email`
---

# Basic Workflow 

- To Initialize empty git repo

`git init`

- Default branch created on local is **master** you can rename it using 

`git branch -m newbranchname`

- To see status of your repo 

`git status`

- To add untracked files to stagging

`git add <file>`

- To add all file untracked files to stagging 

`git add .`

- To unstage

`git rm --cached <file>`

- To add file to Tracked 

`git commit -m "message" `

- To see a commit logs

`git log`

- To see commit history in oneline 

`git log --oneline`

- To remove from tracked 

`git rm --cached <file>`

- To restore file 

`git restore <file>`
---

# Branch

- To see a branch

`git branch`

- To switch/checkout to branch

`git switch <branchname>`

`git checkout <branchname>`

- To create a branch & checkout to that branch

`git branch -b <branchname>`

- To rename branch 

`git branch -m main`
---

# Remote Repo 

- To see origin points to which remote repository

`git remote -v`

- To add remote repository to your origin 

`git remote add origin git@github.com:Sandippatil6696/git-demo-repo.git`
---

# Push /pull from local/remote repo

- **There are three ways to pull push from local to remote**

     1. **Using ssh keys**
        
        - generate ssh keys first in ~/.ssh directory
        
        `ssh-keygen`

        - add public key to your github account 

        `settings -> SSH and GPG keys -> New SSH key`

        - Check your origin & Set your origin to ssh url 

        `git remote -v`

        `git set-url origin git@github.com:Sandippatil6696/git-demo-repo.git`

        - Then you can push or pull from/to remote 

            - if your local & remote branch have commit history then you have to merge or rebase or fast-forward

        `git pull --rebase origin main`

        `git push origin main`
    
    2. **Using personal access token**

        - Check your origin where points to ssh url or https url

        `git remote -v`

        - If your url points to ssh then set your url to https 

        `git remote set-url origin https://github.com/Sandippatil6696/git-demo-repo.git` 

        - Create a personal access token in your github account

        `settings -> developer setting -> personal access token (classic) -> generate new token classic` 

        - Then set origin url with token you can skip setting of url without token directly set url with token 

        - Replace token with your origial token 

        `git remote set-url origin https://<token>@github.com/Sandippatil6696/git-demo-repo.git`


    3. **By using gitub username & password** 

        - if your origin  url is set on https & you want to push to remote repo git asking for username & password 

        - by passing username & password you will be able to push /pull 

     



