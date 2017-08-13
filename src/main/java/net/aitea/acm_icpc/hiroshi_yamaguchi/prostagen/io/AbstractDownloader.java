package net.aitea.acm_icpc.hiroshi_yamaguchi.prostagen.io;

import java.io.*;
import java.net.URL;
import java.util.Map;

public abstract class AbstractDownloader {
    public abstract String getPage(final String p);

    public abstract Map<String, Image> getImages(final String p, final String source);

    protected String fetchText(final URL url, String encode) {
        final StringBuilder html = new StringBuilder();
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), encode))) {
            for (int c = br.read(); c >= 0; c = br.read())
                html.append((char) c);
        } catch (IOException e) {
            throw new RuntimeException("Wait a minute and try again!", e);
        }
        return html.toString();
    }

    protected Image fetchImage(final URL url) throws Image.NotAImageException {
        try (final BufferedInputStream bis = new BufferedInputStream(url.openStream());
             final ByteArrayOutputStream bao = new ByteArrayOutputStream()) {
            for (int b = bis.read(); b >= 0; b = bis.read())
                bao.write((byte) b);
            bao.flush();
            return new Image(bao.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Wait a minute and try again!", e);
        }
    }
}
