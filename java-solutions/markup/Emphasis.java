package markup;

import java.util.List;

public class Emphasis extends ContructText implements ParagraphText {

    public Emphasis(List<ParagraphText> elements) {
        super(List.copyOf(elements));
    }

    @Override
    protected String markdownTag() {
        return "*";
    }

    @Override
    protected String texStart() {
        return "\\emph{";
    }

    @Override
    protected String texEnd() {
        return "}";
    }
}
