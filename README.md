### Stagnation

[![AGPL-3.0 License][license-shield]][license-url]
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fszumaster%2Fstagnation.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fszumaster%2Fstagnation?ref=badge_shield)

**Source**: Project is based on the code of 2009scape project which you can find [here](https://gitlab.com/2009scape/2009scape).<br>
**Devs**: [Authors of source code](https://github.com/szumaster/stagnation/wiki/Authors#source).

___

This is an unofficial modified version.

***

Relative to the original project I removed: 
- Exp stages
- Ironman mode
- Custom shops
- Custom PVP
- Custom items
- Spam messages
- Custom commands for `regular players`

The full list of changes here you can find the [here](https://github.com/szumaster/2009scape/wiki).

<details>

<summary>Setting up the project</summary>

Requirements:
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=windows)
- [Java 11](https://adoptium.net/temurin/releases/?version=11).

Before start familiarize yourself with the basic guides:
- [Fork a repo](https://docs.github.com/en/get-started/quickstart/fork-a-repo)
- [Pull requests](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-pull-requests)

1. **Install JDK version 11**

- go to the [website](https://adoptium.net/temurin/releases/?version=11), and choose the version that compatible with your system<br>
  `If you are running a 64bit verison of Windows (standard), select Windows x64`
- Download and install like any normal application.

2. **Install Intellij IDEA**
- Visit [official IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=windows) website,
- scroll down and you will see a download under the "Community Edition".
- Download and install like any normal application.

3. **Setup the project**
- Fork this repository.
- Open IntelliJ
- Click `File` -> `New` -> Project from `Version Control`
- Paste the URL of your fork repository in the URL field, click `Done`
- Wait for the repository to clone.
- Now Navigate to the `Server` folder inside the file browser from left side, and locate file `pom.xml`
- Right click this, and click `Add as Maven Project` wait a while for everything to load.

4. **Running the project**
- if you have gone through all the previous steps, in the intelij on the right side you will find a `maven` tab.
- Click it, expand the list named `lifecycle`, then Click `compile`, wait until the process is complete.
- If all the steps have been done correctly, you should receive information that the project has been built correctly.

  ![build_success](https://i.imgur.com/BDhhyOZ.png)

- If so, expand plugins list, then the `exec` list and select from the list `exec`:`java` to start the server.

  ![Running_the_project](https://i.imgur.com/fuDMnXb.png)

5. Running the client. 

- To play you will need a **client**, unfortunately you will not find it here.
- I can only recommend you to download it from official repository [![download_button](https://i.imgur.com/rJsueNx.png)](https://gitlab.com/2009scape/rt4-client/-/artifacts)

### Aditional information

- https://docs.github.com/en

</details>

<details>

<summary>Launching the game as singleplayer mode) (<b>setting up the project is not required</b>) </summary>

### Installation process

***

- Make sure you have `java 11` installed before running the process. If you don't have, you can download it from [this page](https://adoptium.net/temurin/releases/?version=11).
- If you already have java installed or had it, you will have to download this archive. <br>At the top right of the page you will find a green button `code` click it, and then press the `Download ZIP` button.

![install_process_0](https://i.imgur.com/3RsP9EC.png)

- After download, go to the location where you saved the archive and then extract it, (_Where you want_).

***

- Ok, now we need to build the **server.jar**, in the first folder, find the `run.bat` and **click it**. <br>
- If you have properly installed java, you should see a cmd console, informing you that the process has been started.

![install_process_1](https://i.imgur.com/zxhUewI.png)

- This process takes _about 15 minutes_. After the whole procedure is completed,
- console should close automatically, if this does not happen, close it manually.

- Now we make sure that the process has finished correctly. 
- If so, in the server folder should be `server.jar` (If not, run `run.bat` again).

![install_process_2](https://i.imgur.com/wgxyxpz.png)

- To play you will need a **client**, unfortunately you will not find it here.
- I can only recommend you to download it from official repository [![download_button](https://i.imgur.com/rJsueNx.png)](https://gitlab.com/2009scape/rt4-client/-/artifacts)
- After you got it, move on. (_Btw: Client can be anywhere on the disk_).

### Launch process

___

- In **server** folder, find the `launch.bat`, run it, select by typing `1` and confirm with `eneter` Done. (_the first load takes some time_).

![run_process_2](https://i.imgur.com/OuB8R67.png)

- When the server is ready you will see such information:

![run_process_2](https://i.imgur.com/gGVZT3W.png)

- Now, run the **client**, and enjoy the game.

### Additional information

- If you want to change the default settings, edit the **default.conf** file. Can be found in the archive in the **worldprops** folder.
- If you want to give yourself the **administrator rights** (_or not_), edit config and change the `noauth_default_admin` from `false` to `true`, and save the changes.

![4](https://i.imgur.com/o0QNW56.png)

- If you made config changes while you had the server running, you will have to **re-run it**.

[![AGPL-3.0 License][license-shield]][license-url]

</details>

___

Contact: [szumaster0@gmail.com](szumaster0@gmail.com)

___

### License

Repository is under AGPL 3.0 license, you can read more [here](https://www.gnu.org/licenses/agpl-3.0.en.html).

[license-shield]: https://img.shields.io/badge/license-AGPL--3.0-informational
[license-url]: https://www.gnu.org/licenses/agpl-3.0.en.html

[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fszumaster%2Fstagnation.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fszumaster%2Fstagnation?ref=badge_large)
