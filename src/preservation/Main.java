package preservation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        String saveWay = "D://Games//savegames";
        File dir1 = new File(saveWay);
        if (dir1.mkdir()) System.out.println("D://Games//savegames создан");
        GameProgress save1 = new GameProgress(10, 20, 1, 2);
        GameProgress save2 = new GameProgress(100, 70, 20, 10);
        GameProgress save3 = new GameProgress(1000, 300, 100, 200);

        //сохранение
        saveGame(saveWay + "//save1.dat", save1);
        saveGame(saveWay + "//save2.dat", save2);
        saveGame(saveWay + "//save3.dat", save3);

        //Список файлов dat
        if (dir1.isDirectory()) {
            for (File item : dir1.listFiles()) {
                if (!item.isDirectory() && item.getName().endsWith("dat"))
                    stringList.add(item.getAbsoluteFile().getAbsolutePath());
            }
        }

        //архивация
        zipFiles(saveWay + "//zip.zip", stringList);

        //удаление
        delFiles(stringList);
    }


    public static void saveGame(String saveWay, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(saveWay);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipWay, List<String> stringList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipWay));
             FileInputStream fis = new FileInputStream(zipWay)) {
            for (String fileName : stringList) {
                ZipEntry entry = new ZipEntry(fileName);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void delFiles(List<String> stringList) {
        stringList.stream().forEach(x -> {
            if (new File(x).delete()) System.out.println("Файл удален");
        });
    }
}


