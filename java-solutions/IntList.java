import java.util.Arrays;

public class IntList {
    private int[] list;
    private int sizeList;

    public IntList() {
        list = new int[8];
        sizeList = 0;
    }

    public IntList(int num) {
        list = new int[8];
        list[0] = num;
        sizeList = 1;
    }

    public void addInt(int num) {
        if (sizeList == list.length) {
            list = Arrays.copyOf(list, sizeList * 2);
        }
        list[sizeList] = num;
        sizeList++;
    }

    public int[] getList() {
        return Arrays.copyOf(list, sizeList);
    }

    public void replaceLast(int a) {
        list[sizeList - 1] = a;
    }
}