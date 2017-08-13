package net.aitea.acm_icpc.hiroshi_yamaguchi.prostagen.io;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleDocsDownloader extends AbstractDownloader {
    private final String apiKey;
    private final String encode;
    private static final Pattern p1 = Pattern.compile("ref\\((?:&quot;)?([^,]+?)(?:,.+)?(?:&quot;)?\\)");

    public GoogleDocsDownloader(final String apiKey, final String encode) {
        this.apiKey = apiKey;
        this.encode = encode;
    }

    private String buildURL(final String fileId) {
        return "https://www.googleapis.com/drive/v3/files/" + fileId + "/export?mimeType=text/plain&key=" + apiKey;
    }

    @Override
    public String getPage(String p) {
        try {
            final URL url = new URL(buildURL(p));
            final String html = fetchText(url, encode);
            // ignore BOM
            if (html.startsWith("\uFEFF")) {
                return html.substring(1);
            }
            return html;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Check the URL in the source code!", e);
        }
    }

    @Override
    public Map<String, Image> getImages(String p, String source) {
        final Map<String, Image> map = new HashMap<>();
        final Matcher m = p1.matcher(source);
        while (m.find()) {
            final String imgName = m.group(1);
            if (!map.containsKey(imgName)) {
                try {
                    final URL url = new URL(imgName);
                    final Image image = fetchImage(url);
                    map.put(imgName, image);
                } catch (Image.NotAImageException e) {
                    System.err.println(imgName + " is not a image file.");
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Check the URL in the source code!", e);
                }
            }
        }
        return map;
    }
}
