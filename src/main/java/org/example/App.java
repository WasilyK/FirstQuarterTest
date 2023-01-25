package org.example;

import java.util.Scanner;

public class App
{
    public static final int INITIAL_SIZE = 5;
    public static final String EXIT_COMMAND = "exit";
    public static final int FILTER_WORD_LENGTH = 3;
    public static final int STEP_BY_INCREASE_ARRAY_SIZE = 5;

    public static void main( String[] args ) {
        System.out.println("Программа отфильтрует введенный массив по длине слов " +
                "(равным " + FILTER_WORD_LENGTH + ") и выведет результат на экран");

        printArray(filterByLength(createArrayFromUserInputs(), FILTER_WORD_LENGTH));
    }

    private static String getInputFromUser(Scanner scanner) {
        String input;
        String msg = "Введите слово и нажмите enter (для выхода \"" + EXIT_COMMAND + "\"): ";
        do {
            System.out.print(msg);
            input = scanner.nextLine();
        } while (input.isEmpty() || input.isBlank());
        return input;
    }

    private static String[] createArrayFromUserInputs() {
        String[] inputs = new String[INITIAL_SIZE];
        int inputCount = 0;
        try(Scanner scanner = new Scanner(System.in)) {
            String input;
            do {
                if(inputCount == inputs.length) {
                    inputs = increaseArraySize(inputs, STEP_BY_INCREASE_ARRAY_SIZE);
                }
                input = getInputFromUser(scanner);
                if(!input.equals(EXIT_COMMAND)) {
                    addToArray(inputs, input);
                    inputCount++;
                }
            } while(!input.equals(EXIT_COMMAND));
        }
        if(inputs.length > inputCount) {
            inputs = compactArray(inputs);
        }
        return inputs;
    }

    private static String[] filterByLength(String[] arr, int length) {
        if(length < 0) {
            throw new IllegalArgumentException("Длина слова не может быть меньше 0.");
        }
        if(arr == null) {
            return null;
        }
        int size = arr.length;
        String[] res = new String[size];

        for (String str : arr) {
            if (str.length() <= length) {
                addToArray(res, str);
            }
        }

        return compactArray(res);
    }

    private static void addToArray(String[] arr, String str) {
        int size = arr.length;
        for(int i = 0; i < size; i++) {
            if(arr[i] == null) {
                arr[i] = str;
                return;
            }
        }
    }

    /**
     * Возвращает строковый массив, усеченный с конца на null-вые элементы
     * до первого не null-ого элемента
     */
    private static String[] compactArray(String[] arr) {
        if(arr == null) {
            return null;
        }
        int size = arr.length;
        String[] res = null;
        for(int i = size - 1; i >= 0; i--) {
            if(arr[i] != null) {
                if(res == null) {
                    res = new String[i + 1];
                }
                res[i] = arr[i];
            }
        }
        return res;
    }

    /**
     * Копирует принятый массив и возвращает массив с увеличенным на by размером
     */
    private static String[] increaseArraySize(String[] arr, int by) {
        if(arr == null) {
            return null;
        }
        if(by < 0) {
            throw new IllegalArgumentException("Can't be increased by a negative number");
        }
        if(by == 0) {
            return arr;
        }
        int size = arr.length;
        String[] res = new String[size + by];
        for(int i = 0; i < size; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    private static void printArray(String[] arr) {
        System.out.print("[");
        if(arr != null) {
            int size = arr.length;
            for(int i = 0; i < size; i++) {
                if(i == size - 1) {
                    System.out.print(arr[i]);
                } else {
                    System.out.printf("%s, ", arr[i]);
                }
            }
        }
        System.out.print("]");
    }
}
