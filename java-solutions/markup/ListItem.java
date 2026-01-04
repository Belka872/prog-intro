package markup;

import java.util.List;

public class ListItem extends ContructText implements InterfaceText {

    public ListItem(List<ListText> elements) {
        super(List.copyOf(elements));
    }

    @Override
    protected String markdownTag() {
        return "";
    }

    @Override
    protected String texEnd() {
        return "";
    }

    @Override
    protected String texStart() {
        return "\\item ";
    }
}
