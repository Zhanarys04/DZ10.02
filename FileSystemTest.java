import java.util.ArrayList;
import java.util.List;

interface FileSystemComponent {
    void display();
    int getSize();
}

class File implements FileSystemComponent {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void display() {
        System.out.println("Файл: " + name + ", размер: " + size + " байт");
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Файл: " + name;
    }
}

class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components;

    public Directory(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }

    public void addComponent(FileSystemComponent component) {
        if (!components.contains(component)) {
            components.add(component);
            System.out.println("Добавлен: " + component.toString());
        } else {
            System.out.println("Компонент уже существует: " + component.toString());
        }
    }

    public void removeComponent(FileSystemComponent component) {
        if (components.remove(component)) {
            System.out.println("Удален: " + component.toString());
        } else {
            System.out.println("Компонент не найден: " + component.toString());
        }
    }

    @Override
    public void display() {
        System.out.println("Папка: " + name);
        for (FileSystemComponent component : components) {
            component.display();
        }
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : components) {
            totalSize += component.getSize();
        }
        return totalSize;
    }

    @Override
    public String toString() {
        return "Папка: " + name;
    }
}


public class FileSystemTest {

    public static void main(String[] args) {
        Directory rootDirectory = new Directory("Корневая папка");
        Directory subDirectory1 = new Directory("Подпапка 1");
        Directory subDirectory2 = new Directory("Подпапка 2");

        File file1 = new File("файл1.txt", 150);
        File file2 = new File("файл2.txt", 300);
        File file3 = new File("файл3.txt", 450);

        subDirectory1.addComponent(file1);
        subDirectory2.addComponent(file2);
        subDirectory2.addComponent(file3);

        rootDirectory.addComponent(subDirectory1);
        rootDirectory.addComponent(subDirectory2);

        rootDirectory.display();
        System.out.println("Общий размер корневой папки: " + rootDirectory.getSize() + " байт");
    }
}
