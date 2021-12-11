# PiS-Hello
Business card holder app

Timeline:  
17.11 - 1 stage  
13.12 - 2 stage  
03.01 - 3 stage
24.01 - 4 stage

### Server

#### Run
To run the REST server, you either need to
```shell
cd server
gradle bootRun
```
or straight from the root
```shell
server/gradlew -b server/build.gradle.kts bootRun
```

### Mobile app
Prior to running the app, you need to install node.json your PC.
Then, you need to install expo with command
```shell
npm install --global expo-cli
```
Then, go to frontend directory and install all required packages
```shell
cd frontend
npm install
```

#### Run
To start the development server for the mobile app, you need to
```shell
npm start
```
After that, you can run the app on an android/iOS device emulator or directly on your mobile device using Expo Go app.

Links:
* [Jira Board](https://hello-pis.atlassian.net/jira/software/projects/HPIS/boards/1)
* [CI documentation](https://circleci.com/docs/2.0/configuration-reference)
* [Documentation](https://www.overleaf.com/read/gyhnrhzrhfxw)
