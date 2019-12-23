package view;

import java.io.IOException;

public interface Menu {
    void show() throws IOException;

    void exit();

    default void showItems(String[] items) {
        for (String item : items) {
            System.out.println("-------------");
            System.out.println(item);
        }
    }
}
