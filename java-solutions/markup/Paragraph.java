package markup;

import java.util.List;

public class Paragraph extends ContructText implements ListText {
    public Paragraph(List<ParagraphText> elements) {
        super(List.copyOf(elements));
    }
    @Override
    protected String markdownTag() {
        return "";
    }
    @Override
    protected String texStart() {
        return "\\par{}";
    }
    @Override
    protected String texEnd() {
        return "";
    }
}
