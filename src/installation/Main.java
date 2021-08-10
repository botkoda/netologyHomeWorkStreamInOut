package installation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File dir1 = new File("D://Games");
        String strToWriteFile = "";
        StringBuilder stringBuilder = new StringBuilder(strToWriteFile);
        //Список папок
        List<File> folderList = Arrays.asList(
                //1.В папке Games создайте несколько директорий: src, res, savegames, temp.
                new File(dir1 + "//src"),
                new File(dir1 + "//res"),
                new File(dir1 + "//savegames"),
                new File(dir1 + "//temp"),

                //2.В каталоге src создайте две директории: main, test.
                new File(dir1 + "//src//main"),
                new File(dir1 + "//src//test"),

                //4.В каталог res создайте три директории: drawables, vectors, icons.
                new File(dir1 + "//res//drawables"),
                new File(dir1 + "//res//vectors"),
                new File(dir1 + "//res////icons")
        );
        //Список файлов
        List<File> fileList = Arrays.asList(
                //3.В подкаталоге main создайте два файла: Main.java, Utils.java.
                new File(dir1 + "//src//main//Main.java"),
                new File(dir1 + "//src//main//Utils.java"),

                //5.В директории temp создайте файл temp.txt.
                new File(dir1 + "//temp//temp.txt")
        );

        //создание папок
        folderList.stream().forEach(folder -> {
            if (folder.mkdir()) stringBuilder.append("Папка " + folder + " создана;" + '\n');
            else stringBuilder.append("Папка " + folder + " не создана (либо нет прав, либо уже создана);" + '\n');
        });

        //создание файлов
        fileList.stream().forEach(file -> {
            try {
                if (file.createNewFile()) stringBuilder.append("Файл " + file + " создан;" + '\n');
                else stringBuilder.append("Файл " + file + " не создан ;" + '\n');
            } catch (IOException ex) {
                stringBuilder.append(ex.getMessage() + '\n');
            }
        });

        //запись в файл temp.txt
        try (FileWriter writer = new FileWriter(dir1 + "//temp//temp.txt", false)) {
            writer.write(stringBuilder.toString());
            writer.flush();
        } catch (IOException ex) {
            stringBuilder.append(ex.getMessage() + '\n');
        }
        System.out.println(stringBuilder);

    }
}
