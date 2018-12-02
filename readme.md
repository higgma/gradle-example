外部ライブラリを使って開発する場合に、JavaScriptで言うところのタスクランナー、Rubyにおけるbundlerのようなものがあると便利である。Javaではビルドツールと呼ばれている。

Gradleを使ってみる。

https://gradle.org/

`choco install gradle` や `brew install gradle` でインストールできる。

```groovy
// gradle.build
apply plugin: 'java'
apply plugin: 'application'

mainClassName = 'Wikipedia'

repositories {
    mavenCentral()
}

dependencies {
    compile 'org.jsoup:jsoup:1.11.3'
}
```

ソースファイルは `src/main/java/` 以下に配置する。

```java
// src/main/java/Wikipedia.java
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * A simple example, used on the jsoup website.
 */
public class Wikipedia {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://hig.hatenablog.com/").get();
        System.out.println(doc.title());

        Elements newsHeadlines = doc.select(".entry-title a");
        for (Element headline : newsHeadlines) {
            String s = String.format("%s\n\t%s", headline.text(), headline.absUrl("href"));
            System.out.println(s);
        }
    }
}


```

実行する。

```
$ gradle run

> Task :run
Windows Millennium Edition
Java入門 #2
        https://hig.hatenablog.com/entry/2018/12/02/174551
Java入門 #1
        https://hig.hatenablog.com/entry/2018/12/02/160455

BUILD SUCCESSFUL in 1s
2 actionable tasks: 1 executed, 1 up-to-date

```

`gradle compileJava` とするとクラスファイルを生成する。
