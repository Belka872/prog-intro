package markup;

import java.util.List;

public class Text implements ParagraphText {
    final String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(text);
    }

    @Override
    public void toTex(StringBuilder sb) {
        sb.append(text);
    }
}
