package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static javax.media.opengl.GL.GL_TRIANGLE_FAN;

/**
 * Класс задачи
 */
public class Problem {
    /**
     * текст задачи
     */
    public static final String PROBLEM_TEXT = "На плоскости задано множество \"широких лучей\". Найти такие два \"широких луча\",\n" +
            "что фигура, находящаяся внутри этих двух \"широких лучей\" замкнута и имеет\n" +
            "наибольшую площадь.\n" +
            "В качестве ответа:\n" +
            "выделить найденные два \"широких луча\",\n" +
            "выделить контур фигуры, которая ограничивает точки внутри обоих \"широких\n" +
            "лучей\",\n" +
            "желательно выделить внутреннее пространство фигуры (залить цветом).";

    /**
     * заголовок окна
     */
    public static final String PROBLEM_CAPTION = "Итоговый проект ученика 10-7 Василия Лебедева";

    /**
     * путь к файлу
     */
    private static final String FILE_NAME = "beams.txt";

    /**
     * список широких лучей
     */
    private ArrayList<WideBeams> beams; //Список широких лучей

    private Polygon polygon; //Область максимальной площади пересечения

    /**
     * Решить задачу
     */

    public void solve() {
        for (WideBeams w : beams){
            w.IsSolution = false; //Перекрашиваем все лучи в стандартный цвет, если были добавлены новые, а старые не были убраны
        }
        double max = 0; //Площадь, которую мы будем фиксировать как максимальную
        Polygon p = null; //Многоугольник, который мы будем фиксировать как максимальный
        // Перебираем пары широких лучей из списка
        for (WideBeams w1 : beams) {
            for (WideBeams w2 : beams) {
                if (w1 != w2) { // Если пара будет состоять их одного и того же широкого луча, то площадь будет считаться всей площадью луча, нам этого не надо
                    /**
                     * Метод w1.intersection(w2) ищет множество точек пересечения сторон двух "широких лучей" и точек одного луча, лежащих внутри другого
                     * По сути, ищет точки(на самом деле вектора), образующие(концы которых образуют) область пересечения двух лучей
                     * Метод sort(Vector[] v) сортирует найденные точки(концы векторов) так, чтобы они были пронумерованы по порядку вдоль периметра области пересечения
                     * Второй метод сделан для удобства нахождения площади и отрисовки этой области
                     */
                    polygon = new Polygon(sort(w1.intersection(w2))); //Область пересечения тех лучей, которые мы перебираем
                    if (polygon.Square() > max){ //Если площадь найденной области больше максимальной уже найденной площади
                        max = polygon.Square(); //Обновляем значение максимальной площади
                        p = polygon; //Обновляем многоугольник, имеющий максимальную площадь
                        //Square() - метод нахождения площади многоугольника с отсортированными вершинами по формуле Гаусса
                    }
                }
            }
        }
        polygon = p; //Присваимаем отрисовываемому многоугольнику наибольшую область пересечения
        for (WideBeams w1 : beams) { //Найдём решающие "широкие лучи", чтобы поменять их цвет
            for (WideBeams w2 : beams) {
                if (w1 != w2) {
                    //Если найденная площадь пересечения равна максимальной (с учётом погрешности), то это и есть решающие лучи
                    if (Math.abs(new Polygon(sort(w1.intersection(w2))).Square() - polygon.Square()) <= 0.0001){
                        w1.IsSolution = true;
                        w2.IsSolution = true;
                        // IsSolution - поле класса, отвечающее за цвет отрисовываемого "широкого луча"
                        break;
                    }
                }
            }
            if (w1.IsSolution){ //Если решающие лучи найдены, то нет смысла продолжать их искать
                break;
            }
        }
    }

    /**
     * Конструктор класса задачи
     */
    public Problem() {
        beams = new ArrayList<>();
    }

    public void addBeam(double x, double y, double x2, double y2) {
        WideBeams beam = new WideBeams(new Vector(x, y), new Vector(x2, y2));
        beams.add(beam);
    }
    /**
     * Загрузить задачу из файла
     */
    public void loadFromFile() {
        beams.clear();
        try {
            File file = new File(FILE_NAME);
            Scanner sc = new Scanner(file);
            // пока в файле есть непрочитанные строки
            while (sc.hasNextLine()) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                double x2 = sc.nextDouble();
                double y2 = sc.nextDouble();
                sc.nextLine();
                WideBeams beam = new WideBeams(new Vector(x, y), new Vector(x2, y2));
                beams.add(beam);
            }
        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: " + ex);
        }
    }

    /**
     * Сохранить задачу в файл
     */
    public void saveToFile() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME));
            for (WideBeams beam : beams) {
                out.printf("%.2f %.2f %.2f %.2f \n", beam.a.x, beam.a.y, beam.b.x, beam.b.y);
            }
            out.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файл: " + ex);
        }
    }

    /**
     * Добавить заданное число случайных широких лучей
     *
     * @param n кол-во лучей
     */
    public void addRandomBeams(int n) {
        for (int i = 0; i < n; i++) {
            WideBeams w = WideBeams.getRandomWideBeams();
            beams.add(w);
        }
    }

    /**
     * Очистить задачу
     */
    public void clear() {
        beams.clear();
        polygon = null;
    }

    /**
     * Нарисовать задачу
     *
     * @param gl переменная OpenGL для рисования
     */
    public void render(GL2 gl) {
        for (WideBeams beam : beams) {
            beam.render(gl);
        }
        if (polygon != null) {
            polygon.render(gl);
        }
    }

    /**
     * Метод для сортировки точек в массиве по часовой стрелке
     */
    public Vector[] sort(Vector[] arr){
        Vector[] vertex = new Vector[12];
        vertex[0] = arr[0];
        Line l;
        boolean solution = false;
        int c = 0;
        int p = 0;
        for (Vector v : arr){
            if (v != null){
                p++;
            }
        }
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == null || c + 1 == p){
                break;
            }
            for (int j = 0; j < arr.length; j++){
                if (arr[j] == null || c == 8){
                    break;
                }
                l = new Line(vertex[c], arr[j]);
                for (int k = 0; k < arr.length; k++){
                    if (arr[k] == null){
                        solution = true;
                        break;
                    }
                    for (int r = 0; r < arr.length; r++) {
                        if (arr[r] == null){
                            break;
                        }
                        if (!l.oneSide(new Point(arr[k].x, arr[k].y), new Point(arr[r].x, arr[r].y))) {
                            solution = true;
                            break;
                        }
                    }
                    if (solution){
                        solution = false;
                        break;
                    }
                }
                if (c != 0) {
                    if (solution && (arr[j] != vertex[c - 1]) && arr[j] != vertex[c]) {
                        c++;
                        if (c == 8){
                            break;
                        }
                        vertex[c] = arr[j];
                    }
                }
                else if (solution && (arr[j] != vertex[c])) {
                    c++;
                    vertex[c] = arr[j];
                }
                solution = false;
            }
        }
        return vertex;
    }
}