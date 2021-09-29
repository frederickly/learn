package regular;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://www.lintcode.com/problem/523/
 * Url Parser
 * Description
 *
 * Parse a html page, extract the Urls in it.
 *
 * Use regex to parse html.
 * Example
 *
 * Example 1:
 *
 * Input:
 *
 *   <html>
 *
 *     <body>
 *
 *       <div>
 *
 *         <a href="http://www.google.com" class="text-lg">Google</a>
 *
 *         <a href="http://www.facebook.com" style="display:none">Facebook</a>
 *
 *       </div>
 *
 *       <div>
 *
 *         <a href="https://www.linkedin.com">Linkedin</a>
 *
 *         <a href = "http://github.io">LintCode</a>
 *
 *       </div>
 *
 *     </body>
 *
 *   </html>
 *
 * Output:
 *
 *   [
 *
 *     "http://www.google.com",
 *
 *     "http://www.facebook.com",
 *
 *     "https://www.linkedin.com",
 *
 *     "http://github.io"
 *
 *   ]
 *
 * Example 2:
 *
 * Input:
 *
 *   <html>
 *
 *     <body>
 *
 *       <div>
 *
 *         <a  HREF =    "http://www.google.com" class="text-lg">Google</a>
 *
 *         <a  HREF = "http://www.facebook.com" style="display:none">Facebook</a>
 *
 *       </div>
 *
 *       <div>
 *
 *         <a href="https://www.linkedin.com">Linkedin</a>
 *
 *         <a href = "http://github.io">LintCode</a>
 *
 *       </div>
 *
 *     </body>
 *
 *   </html>
 *
 * Output:
 *
 *   [
 *
 *     "http://github.io",
 *
 *     "http://www.facebook.com",
 *
 *     "http://www.google.com",
 *
 *     "https://www.linkedin.com"
 *
 *   ]
 */
public class HtmlParser {
    private static final String HTML_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*[\"|']([^\"'\\s]+)";

    public List parseUrls(String content) {

        List links = new ArrayList();
        Pattern pattern = Pattern.compile(HTML_HREF_TAG_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        String url = null;
        while (matcher.find()) {
            url = matcher.group(1);
            if (url.length() == 0 || url.startsWith("#"))
                continue;
            links.add(url);
        }
        return links;
    }
}
