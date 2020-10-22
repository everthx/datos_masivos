# Practice Git Flow


Link Github  “pratica_git_flow”
https://github.com/SalmaFabel/pratica_git_flow.git

#### Cuenta de github
Para la presente práctica se crea la cuenta de Github, pero ya se tenía creada.

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/cuentaGithub.PNG)

#### Llave SSH

Se configura la llave ssh en github para que no se tenga que poner siempre nuestro usuario y contraseña, para ello se realizaron los siguientes pasos:

Se descargan 3 herramientas de putty. Se ejecuta “puttygen” para que genere la clave privada, las herramientas se descargan de la siguiente página:
https://www.chiark.greenend.org.uk/~sgtatham/putty/latest.html

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/HerramientasPutty.PNG)

En donde dice “puttygen” se selecciona, para generar la clave privada y nos aparece lo siguiente :

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/Putty%20key%20generator.PNG)

Se genera la clave y se le escribe una contraseña, con la cual se podrá consultar después la clave . 
Se guarda la contraseña.

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/Putty%20Key.PNG)

Las herramientas se guardan en una carpeta dentro del inicio, se agrega en las variables del entorno del sistema con la referencia de la herramienta “plink.exe”.

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/VariablesEntorno.PNG)

En la cuenta de github, en configuraciones, nos dirigimos donde dice llave SSH se pega la clave pública.

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/GithubSSH.PNG)

Una vez que se guarda, se visualiza en nuestro github de la siguiente forma:

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/SSHvisualizacion.PNG)

Ya para terminar con la configuración de ssh entras a “git Bash” introduces tu usuario y contraseña. Después clones el repositorio y ya no te vuelve a pedir los datos. 

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

Como prueba de que se realizó de manera correcta, si nos vamos a github donde está la llave ssh podemos observar que aparece que la usamos.

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/UsoSSH.PNG)

####Creación del repositorio “pratica_git_flow”

En esta parte de la práctica se crea el repositorio.

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/RepositorioGitflow.PNG)

####Clonar repositorio en la PC

```
salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub
$ git clone git@github.com:SalmaFabel/pratica_git_flow.git
Cloning into 'practica_git_flow'...

```

Visualización del repositorio clonado.

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/ClonarRepositorio.PNG)

####Rama (branch) development 

Una vez que tenemos el repositorio clonado lo que sigue es crear las ramas con “git branch” podemos saber en qué rama nos encontramos, en este caso es la “master” que está por default.

```
salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub/pratica_git_flow (master)
$ git branch
*master

```

Se utiliza el siguiente comando para crear la rama “development”

```
salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub/pratica_git_flow (master)
$ git checkout -b development

```

Se modifica el README.md

####Commit en la rama development

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

Push en la rama development

```
salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub/pratica_git_flow (development)
$ git push --set-upstream origin development

```

Visualización de la modificación de la rama development

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/RamaDevelopment.PNG)

####Rama (branch) features

```
salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub/pratica_git_flow (development)
$ git checkout -b features
Switched to a new branch 'features'

```

Commit en la rama features

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

Push en la rama features

```
salmi@HENSF2 MINGW64 ~/OneDrive/Documentos/GitHub/pratica_git_flow (features)
$ git push --set-upstream origin features

```

Visualización de la modificación de la rama features.

![ScreenShot](https://github.com/SalmaFabel/IMG/blob/main/RamaFeatures.PNG)
