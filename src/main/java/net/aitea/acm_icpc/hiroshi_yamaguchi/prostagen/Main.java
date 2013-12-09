package net.aitea.acm_icpc.hiroshi_yamaguchi.prostagen;

import net.aitea.acm_icpc.hiroshi_yamaguchi.prostagen.composers.HTMLComposer;
import net.aitea.acm_icpc.hiroshi_yamaguchi.prostagen.composers.TeXComposer;
import net.aitea.acm_icpc.hiroshi_yamaguchi.prostagen.io.Downloader;

public class Main {
    private static final String[] names = {
            "問題/A+B"
    };

    public static void main(String... args) throws Exception {
//        {
//            final byte[] bs = new byte[1024];
//            Runtime.getRuntime().exec("pwd").getInputStream().read(bs);
//            System.err.println(new String(bs));
//        }
        final Downloader d = new Downloader("http://example.com/pukiwiki/",
                "user", "pass", "utf-8");
        for (int i = 0; i < names.length; i++)
            try {
                System.err.println(i + ": " + names[i]);
                final char c = (char) ('A' + i);
                TeXComposer.compose(d, names[i], "" + c);
                HTMLComposer.compose(d, names[i], "" + c);
            } catch (Exception e) {
                e.printStackTrace();
                //throw null;
            }
    }

}