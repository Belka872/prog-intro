package markup;

import java.util.List;

public class Strong extends ContructText implements ParagraphText {

    public Strong(List<ParagraphText> elements) {
        super(List.copyOf(elements));
    }

    @Override
    protected String markdownTag() {
        return "__";
    }

    @Override
    protected String texStart() {
        return "\\textbf{";
    }

    @Override
    protected String texEnd() {
        return "}";
    }
}
