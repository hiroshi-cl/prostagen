prostagen
=========

Problem statement generator (from pukiwiki to html/tex)

Usage
-----

* Edit src/main/java/net/aitea/acm_icpc/hiroshi_yamaguchi/prostagen/Main.java
* Edit src/main/java/net/aitea/acm_icpc/hiroshi_yamaguchi/prostagen/composers/SectionNames.java
* Type `mvn package`
* Type `java -jar [generated jar file]`

Memo
----

設定によっては対象 pukiwiki の plugin/source.inc.php の 17 行目の check_readble の2番めの引数を `true` に修正しないと，ソースコードダウンロード時に認証フェーズに突入することなく弾かれるので注意．
