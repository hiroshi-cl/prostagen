package net.aitea.acm_icpc.hiroshi_yamaguchi.prostagen.composers;

import net.aitea.acm_icpc.hiroshi_yamaguchi.prostagen.converters.Converter;
import net.aitea.acm_icpc.hiroshi_yamaguchi.prostagen.converters.Converter2HTML;
import net.aitea.acm_icpc.hiroshi_yamaguchi.prostagen.converters.Converter2HTMLReplaceVerb;
import net.aitea.acm_icpc.hiroshi_yamaguchi.prostagen.io.Downloader;
import net.aitea.acm_icpc.hiroshi_yamaguchi.prostagen.io.Image;
import net.aitea.acm_icpc.hiroshi_yamaguchi.prostagen.io.Writer;

import java.io.File;
import java.util.List;
import java.util.Map;

public class AtCoderComposer {
    public static void compose(final Downloader d, final String pageName, final String id)
            throws Converter.InconvertibleException {
        final String source = d.getPage(pageName);
        final Map<String, Image> map = d.getImages(pageName, source);
        final Sectionizer sc = new Sectionizer(source);

        final StringBuilder sb = new StringBuilder();

        sb.append("<script type=\"text/x-mathjax-config\">\n" +
                "\tMathJax.Hub.Config({ tex2jax: { inlineMath: [[\"$\",\"$\"], [\"\\\\(\",\"\\\\)\"]], processEscapes: true }});\n" +
                "</script>\n" +
                "<script type=\"text/javascript\"\n" +
                "\tsrc=\"http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS_HTML\">\n" +
                "</script>\n" +
                "<meta http-equiv=\"X-UA-Compatible\" CONTENT=\"IE=EmulateIE7\" />");
        sb.append("<style type=\"text/css\">");
        sb.append("blockquote {\n" +
                "  font-family: Menlo, Monaco, \"Courier New\", monospace;\n" +
                "  color: #333333;\n" +
                "  display: block;\n" +
                "  padding: 8.5px;\n" +
                "  margin: 0 0 9px;\n" +
                "  font-size: 12px;\n" +
                "  line-height: 18px;\n" +
                "  background-color: #f5f5f5;\n" +
                "  border: 1px solid #ccc;\n" +
                "  border: 1px solid rgba(0, 0, 0, 0.15);\n" +
                "  -webkit-border-radius: 4px;\n" +
                "  -moz-border-radius: 4px;\n" +
                "  border-radius: 4px;\n" +
                "  white-space: pre;\n" +
                "  white-space: pre-wrap;\n" +
                "  word-break: break-all;\n" +
                "  word-wrap: break-word;\n" +
                "}");
        sb.append("</style>");

//        sb.append("<h1>" + sc.getSection(SectionNames.title).trim() + "</h1>\n");
//		System.err.println(sc.getSection(SectionNames.statement));
        sb.append("<div class=\"part\"><h3>Problem Statement</h3>");
        sb.append(new Converter2HTML(sc.getSection(SectionNames.statement), map, null).get());

        sb.append("</div><hr /><div class=\"part\">");

        sb.append("<h3>Input</h3>\n");
        sb.append(new Converter2HTMLReplaceVerb(sc.getSection(SectionNames.input), map, "blockquote").get());

        if (sc.hasSection(SectionNames.constraints)) {
            sb.append("<h4>Constraints</h4>\n");
            sb.append(new Converter2HTMLReplaceVerb(sc.getSection(SectionNames.constraints), map, "blockquote").get());
        } else {
            System.err.println("No constraints section.");
        }

        sb.append("</div><div class=\"part\">");
        sb.append("<h3>Output</h3>\n");
        sb.append(new Converter2HTMLReplaceVerb(sc.getSection(SectionNames.output), map, "blockquote").get());


        final List<String> sampleInputs = sc.getSections(SectionNames.sampleInput, SectionNames.sampleInputF);
        final List<String> sampleOutputs = sc.getSections(SectionNames.sampleOutput, SectionNames.sampleOutputF);

        final int n = sampleInputs.size();
        if (sampleOutputs.size() != n)
            throw new RuntimeException(String.format("%d != %d", n, sampleOutputs.size()));

        for (int i = 0; i < n; i++) {
            sb.append("</div><hr /><div class=\"part\">");
            sb.append("<h3>Sample Input " + (i + 1) + "</h3>\n\n");
            sb.append(new Converter2HTML(sampleInputs.get(i), map, null).get());
            sb.append("</div><div class=\"part\">");
            sb.append("<h3>Output for the Sample Input " + (i + 1) + "</h3>\n\n");
            sb.append(new Converter2HTML(sampleOutputs.get(i), map, null).get());
        }

        if (sc.hasSection("Note")) {
            sb.append("</div><hr /><div class=\"part\">");
            sb.append("<h3>Note</h3>\n\n");
            sb.append(new Converter2HTML(sc.getSection("Note"), map, null).get());
        }

        sb.append("</div>");

        Writer.writeString(new File("html/", id + ".html"), sb.toString());
        Writer.writeImages(new File("html/", "fig"), map);
    }
}
