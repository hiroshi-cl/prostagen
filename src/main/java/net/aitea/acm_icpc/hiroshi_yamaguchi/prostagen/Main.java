package net.aitea.acm_icpc.hiroshi_yamaguchi.prostagen;

import net.aitea.acm_icpc.hiroshi_yamaguchi.prostagen.composers.*;
import net.aitea.acm_icpc.hiroshi_yamaguchi.prostagen.io.*;

public class Main {
    private static final String[] names = {
            "fileId"
    };

    public static void main(String... args) throws Exception {
//        {
//            final byte[] bs = new byte[1024];
//            Runtime.getRuntime().exec("pwd").getInputStream().read(bs);
//            System.err.println(new String(bs));
//        }
        // final AbstractDownloader d = new PukiWikiDownloader("http://example.com/pukiwiki/", "user", "pass", "utf-8");
        final AbstractDownloader d = new GoogleDocsDownloader("apiKey", "utf-8");
        for (int i = 0; i < names.length; i++)
            try {
                System.err.println(i + ": " + names[i]);
                final char c = (char) ('A' + i);
                AtCoderMergedComposer.compose(d, names[i], "" + c);
                TeXMergedComposer.compose(d, names[i], "" + c);
                AtCoderComposer.compose(d, names[i], "" + c);
                TeXComposer.compose(d, names[i], "" + c);
            } catch (Exception e) {
                e.printStackTrace();
                //throw null;
            }
    }

}
