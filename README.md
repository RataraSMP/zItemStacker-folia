# zItemStacker
folia-project
Allows you to stack your items

Download here: https://modrinth.com/plugin/zitemstacker


## Maven
````xml
<repository>
  <id>groupez-releases</id>
  <name>GroupeZ Repository</name>
  <url>https://repo.groupez.dev/releases</url>
</repository>
````
````xml
<dependency>
  <groupId>fr.maxlego08.itemstacker</groupId>
  <artifactId>zitemstacker-api</artifactId>
  <version>{version}</version>
</dependency>
````

## Gradle
````gradle
maven {
    name = "groupezReleases"
    url = uri("https://repo.groupez.dev/releases")
}
````
````gradle
compileOnly("fr.maxlego08.itemstacker:zitemstacker-api:{version}")
````

---
Note: Optimized for Folia performance and thread safety.
