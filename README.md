# Stud.IP API - Uni Passau
<p align="center">
  <a href="https://lgtm.com/projects/g/ThexXTURBOXx/studip-uni-passau/alerts/"><img alt="Total alerts" src="https://img.shields.io/lgtm/alerts/g/ThexXTURBOXx/studip-uni-passau.svg?logo=lgtm&logoWidth=18"/></a>
  <a href="https://lgtm.com/projects/g/ThexXTURBOXx/studip-uni-passau/context:java"><img alt="Language grade: Java" src="https://img.shields.io/lgtm/grade/java/g/ThexXTURBOXx/studip-uni-passau.svg?logo=lgtm&logoWidth=18"/></a>
  <a href="https://travis-ci.com/ThexXTURBOXx/studip-uni-passau"><img src="https://travis-ci.com/ThexXTURBOXx/studip-uni-passau.svg?branch=master"></a>
  <a href="https://maven-badges.herokuapp.com/maven-central/de.femtopedia.studip/studip-uni-passau"><img src="https://maven-badges.herokuapp.com/maven-central/de.femtopedia.studip/studip-uni-passau/badge.svg"></a>
  <a href="https://github.com/ThexXTURBOXx/studip-uni-passau/releases"><img src="https://img.shields.io/github/release/thexxturboxx/studip-uni-passau.svg"></a>
</p>
<p>This is a simple library for Java handling the RestAPI of StudIP at the University Passau, using the <a target="_blank" href="https://github.com/ThexXTURBOXx/shib-uni-passau">Shibboleth Client Wrapper</a>.</p>

## Sample Project
There is a [Repository available](https://github.com/ThexXTURBOXx/studip-app-uni-passau), including source code.<br>
And of course its [release APK](http://femtopedia.de/studip/index.php).

## Including as dependency (Gradle)
Add the following snippet to your **build.gradle** and change the version number:
```Gradle
repositories {
    maven {
        jcenter()
    }
}
dependencies {
    implementation 'de.femtopedia.studip:studip-uni-passau:...'
}
```

## Including as dependency (Maven)
Add the following snippet to your **pom.xml** and change the version number:
```xml
<dependencies>
    <dependency>
        <groupId>de.femtopedia.studip</groupId>
        <artifactId>studip-uni-passau</artifactId>
        <version>...</version>
    </dependency>
</dependencies>
```
## Older builds
Older builds are available in my Maven repo here: [http://femtopedia.de/maven](http://femtopedia.de/maven)

## Basic Usage
```Java
//Instantiate the API and set OAuth Credentials
StudIPAPI api = new StudIPAPI("CONSUMER_KEY", "CONSUMER_SECRET");
try {
    //Print Authorization Url
    System.out.println(api.getAuthorizationUrl("callback_scheme://callback_url"));
    //Wait for user to input Verification Code
    api.verifyAccess(new Scanner(System.in).nextLine());
    //Get the user data of the current logged in user
    User user = api.getCurrentUserData();
    //Print the user's Family Name
    System.out.println(user.getName().getFamily());
    //Print the user's Email Adress
    System.out.println(user.getEmail());
} catch (IOException | IllegalAccessException | OAuthException e) {
    //Print errors
    e.printStackTrace();
}
```
