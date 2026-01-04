package markup;

import java.util.List;

public class UnorderedList extends ContructText implements ListText {
    public UnorderedList(List<ListItem> elements) {
        super(List.copyOf(elements));
    }

    @Override
    protected String markdownTag() {
        return "";
    }

    @Override
    protected String texStart() {
        return "\\begin{itemize}";
    }

    @Override
    protected String texEnd() {
        return "\\end{itemize}";
    }
}
