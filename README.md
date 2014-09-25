prostagen
=========

Problem statement generator (from pukiwiki to html/tex)

old versions: https://github.com/hiroshi-cl/prostagen-old

Usage
-----

* Edit src/main/java/net/aitea/acm_icpc/hiroshi_yamaguchi/prostagen/Main.java
* Edit src/main/java/net/aitea/acm_icpc/hiroshi_yamaguchi/prostagen/composers/SectionNames.java
* Type `mvn package`
* Type `java -jar [generated jar file]`

Memo
----

設定によっては対象 pukiwiki の plugin/source.inc.php の 17 行目の check_readble の2番めの引数を `true` に修正しないと，ソースコードダウンロード時に認証フェーズに突入することなく弾かれるので注意．

既知のバグ
----------
* `~`, `^` 等一部の記号の tex 向けエスケープが正しくない

既知のバグでないもの
--------------------
* `<`, `>` 等のエスケープが AtCoder 上で編集したときにおかしくなるのは prostagen ではなく，AtCoder のバグです．(一部に有名)
* クォーテーションを tex 向けに置換する機能、逆に html 向けに戻す機能ありますが時々働いていないように見えると思います。正規表現による置換なのでバギーというのもありますが、複数単語にまたがるときはアポストロフィーとうまく区別できないので自動置換しないようになっています。
* 出力されたソースコードに ascii じゃない変なクォーテーション記号が混ざっているとき、それは prostagen のせいではなく、あなたの使っているエディタ、例えば mac 標準のアレとか、Google Docs とかがお節介なことをしているせいです。Prostagen はそれらに対して自動修正も含め何もしてません。

今後の予定
----------
* バックエンドで pandoc とかを使うようにして高次の文書構造だけ担当するようにした方がいいかな？
* TODO: pandoc の pukiwiki プラグインを作る？
