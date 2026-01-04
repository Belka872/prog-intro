package markup;

import java.util.List;

public abstract class ContructText implements InterfaceText {
    protected List<InterfaceText> elements;

    public ContructText(List<InterfaceText> elements) {
        this.elements = elements;
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(markdownTag());
        for (InterfaceText element : elements) {
            element.toMarkdown(sb);
        }
        sb.append(markdownTag());
    }

    protected abstract String markdownTag();

    protected abstract String texStart();

    protected abstract String texEnd();

    public void toTex(StringBuilder sb) {
        sb.append(texStart());
        for (InterfaceText element : elements) {
            element.toTex(sb);
        }
        sb.append(texEnd());
    }
}
