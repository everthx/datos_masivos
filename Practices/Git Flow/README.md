# Practice Git Flow


Link Github  “pratica_git_flow”
https://github.com/SalmaFabel/pratica_git_flow.git

#### Cuenta de github
>For the current practice, we need to create Github account, but we already had one.

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/cuentaGithub.PNG)

#### Llave SSH

>An SSH Key is configured in github so we don't have to put our Username and Password each time we do a pull/push, to do that, the following steps where taken:

>We donwloaded 3 putty tools. We execute “puttygen” to generate a private key, the tools can be downloaded from the following page:
https://www.chiark.greenend.org.uk/~sgtatham/putty/latest.html

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/HerramientasPutty.PNG)

>We select “puttygen”, this is so it can generate the private key as shown next:

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/Putty%20key%20generator.PNG)

>The key is generated and a password is given, this will allow us to revisit the key. 
The password is saved.

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/Putty%20Key.PNG)

>The tools are saved in a folder within the start menu, they're added to the system's Environment Variables with a reference to the tool named “plink.exe”.

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/VariablesEntorno.PNG)

>In the github account, under Settings, we go to SSH Key and paste the public key.

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/GithubSSH.PNG)

>Once saved, it will show on our github as shown next:

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/SSHvisualizacion.PNG)

>To finish with the SSH Key configuration, we enter:
```
git Bash
```
We introduce our Username and Password. After that, we can clone the repository and it won't ask us for our credentials again.

```
salmi@HENSF2 MINGW64 /c/Program Files/PuTTY
$ git config --global user.name "Salma Fabel"

salmi@HENSF2 MINGW64 /c/Program Files/PuTTY
$ git config --global user.email salma.hernandez16@tectijuana.edu.mx

salmi@HENSF2 MINGW64 /c/Program Files/PuTTY
$ plink.exe -v git@github.com
Looking up host "github.com" for SSH connection
Connecting to 140.82.113.4 port 22
We claim version: SSH-2.0-PuTTY_Release_0.74
Remote version: SSH-2.0-babeld-c2abfebf
Using SSH protocol version 2

```

>As a test that the setup was successful, if we go to github where the SSH is shown, there we will see the one, we used.

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/UsoSSH.PNG)

#### Repository “pratica_git_flow” creation

>In this part of the practice we create a repository.

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/RepositorioGitflow.PNG)

#### Cloning the repository to the PC

```
salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub
$ git clone git@github.com:SalmaFabel/pratica_git_flow.git
Cloning into 'practica_git_flow'...

```

>Visualizing the cloned repository

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/ClonarRepositorio.PNG)

#### development Branch 

>Once we have the repository clones, the next step is to create new branches, with “git branch” we can find out on which branch are we, in this, case this would be the “master” branch by default.

```
salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub/pratica_git_flow (master)
$ git branch
*master

```

>We use the following command to create the “development” branch.

```
salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub/pratica_git_flow (master)
$ git checkout -b development

```

>The README.md file is modified

#### Commit on the development branch

```
salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub/pratica_git_flow (development)
$ ls
README.md

salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub/pratica_git_flow (development)
$ git add -A

salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub/pratica_git_flow (development)
$ git status
On branch development
Changes to be committed:
  (use "git  restore --staged <file>..." to unstage)
            modified:    README.md


salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub/pratica_git_flow (development)
$ git commit -m "rama development"

```

>Push on development branch

```
salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub/pratica_git_flow (development)
$ git push --set-upstream origin development

```

>Visualizing changes done to the development branch.

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/RamaDevelopment.PNG)

#### features branch.

```
salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub/pratica_git_flow (development)
$ git checkout -b features
Switched to a new branch 'features'

```

>Commit on features branch

```
salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub/pratica_git_flow (features)
$ git status
On branch features
Changes to be committed:
  (use "git  restore --staged <file>..." to unstage)
            modified:    README.md
salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub/pratica_git_flow (features)
$ git commit -m "rama features"

```

>Push on features branch

```
salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub/pratica_git_flow (features)
$ git push --set-upstream origin features

```

>Visualizing changes done on the features branch

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/RamaFeatures.PNG)
