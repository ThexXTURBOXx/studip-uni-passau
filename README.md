# Stud.IP API - Uni Passau
<p align="center">
  <a href="https://lgtm.com/projects/g/ThexXTURBOXx/studip-uni-passau/alerts/"><img alt="Total alerts" src="https://img.shields.io/lgtm/alerts/g/ThexXTURBOXx/studip-uni-passau.svg?logo=lgtm&logoWidth=18"/></a>
  <a href="https://lgtm.com/projects/g/ThexXTURBOXx/studip-uni-passau/context:java"><img alt="Language grade: Java" src="https://img.shields.io/lgtm/grade/java/g/ThexXTURBOXx/studip-uni-passau.svg?logo=lgtm&logoWidth=18"/></a>
  <a href="https://travis-ci.com/ThexXTURBOXx/studip-uni-passau"><img src="https://travis-ci.com/ThexXTURBOXx/studip-uni-passau.svg?branch=master"></a>
  <a href="http://femtopedia.de/studip/index.php"><img src="https://img.shields.io/website-up-down-green-red/http/www.femtopedia.de/index.php.svg?label=Repository"></a>
  <a href="https://github.com/ThexXTURBOXx/studip-uni-passau/releases"><img src="https://img.shields.io/github/release/thexxturboxx/studip-uni-passau.svg"></a>
</p>
<p>This is a simple library for Java handling the RestAPI of StudIP at the University Passau, using the <a target="_blank" href="https://github.com/ThexXTURBOXx/shib-uni-passau">Shibboleth Client Wrapper</a>.</p>

## Sample Project
There is a [Repository available](https://github.com/ThexXTURBOXx/studip-app-uni-passau), including source code.<br>
And of course its [release APK](http://femtopedia.de/studip/index.php).

## Including as dependency (Gradle)
Add the following snippet to your **build.gradle**:
```Gradle
repositories {
    maven {
        url "http://femtopedia.de/maven"
    }
}
dependencies {
    implementation 'de.femtopedia.studip:studip-uni-passau:1.3'
}
```

## Including as dependency (Maven)
Add the following snippet to your **pom.xml**:
```xml
<repositories>
    <repository>
        <id>Femtopedia</id>
        <url>http://femtopedia.de/maven</url>
    </repository>
</repositories>
<dependencies>
    <dependency>
        <groupId>de.femtopedia.studip</groupId>
        <artifactId>studip-uni-passau</artifactId>
        <version>1.3</version>
    </dependency>
</dependencies>
```

## Basic Usage
```Java
//Instantiate the API
StudIPAPI api = new StudIPAPI();
try {
    //Authenticate using your Username and Password
    api.authenticate("USERNAME", "PASSWORD");
    //Get the user data of the current logged in user
    User user = api.getCurrentUserData();
    //Print the user's Family Name
    System.out.println(user.getName().getFamily());
    //Print the user's Email Adress
    System.out.println(user.getEmail());
} catch (IOException | IllegalAccessException e) {
    //Print errors
    e.printStackTrace();
} finally {
    //Shutdown the API
    api.shutdown();
}
```
