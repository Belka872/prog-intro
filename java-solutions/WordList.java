import java.util.Arrays;
import java.util.Comparator;

public class WordList {
    private String[] list;
    private int sizeList;

    public WordList() {
        list = new String[8];
        sizeList = 0;
    }

    public void addWord(String word) {
        if (sizeList == list.length) {
            list = Arrays.copyOf(list, sizeList * 2);
        }
        list[sizeList] = word;
        sizeList++;
    }

    public String[] getList() {
        String[] cropList = Arrays.copyOf(list, sizeList);
        Arrays.sort(cropList, Comparator.comparingInt(String::length));
        return cropList;
    }
}