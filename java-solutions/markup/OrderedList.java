package markup;

import java.util.List;

public class OrderedList extends ContructText implements ListText {
    public OrderedList(List<ListItem> elements) {
        super(List.copyOf(elements));
    }

    @Override
    protected String markdownTag() {
        return "";
    }

    @Override
    protected String texStart() {
        return "\\begin{enumerate}";
    }

    @Override
    protected String texEnd() {
        return "\\end{enumerate}";
    }
}
