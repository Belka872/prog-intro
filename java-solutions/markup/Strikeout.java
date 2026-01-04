package markup;

import java.util.List;

public class Strikeout extends ContructText implements ParagraphText {


    public Strikeout(List<ParagraphText> elements) {
        super(List.copyOf(elements));
    }

    @Override
    protected String markdownTag() {
        return "~";
    }

    @Override
    protected String texStart() {
        return "\\textst{";
    }

    @Override
    protected String texEnd() {
        return "}";
    }
}
