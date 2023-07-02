import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static AtomicInteger counter3 = new AtomicInteger();
    static AtomicInteger counter4 = new AtomicInteger();
    static AtomicInteger counter5 = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
            Thread palindrome = new Thread( () ->
            {
                for (String text : texts) {
                    if (istPalindrome(text) && !sameLetter(text))
                        counter(text.length());

            }
            });
            palindrome.start();

        Thread sameLetters = new Thread(() -> {
            for (String text: texts) {
                if (sameLetter(text))
                        counter(text.length());

            }
        });
        sameLetters.start();

        Thread ascendingOrder = new Thread(() -> {
            for (String text : texts) {
                if (inAscendingOrder(text) && !sameLetter(text))
                    counter(text.length());
            }
        });
        ascendingOrder.start();



        palindrome.join();
        sameLetters.join();
        ascendingOrder.join();


        System.out.println("Красивых слов с длиной 3: " + counter3);
        System.out.println("Красивых слов с длиной 4: " + counter4);
        System.out.println("Красивых слов с длиной 5: " + counter5);

    }
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
    public static boolean istPalindrome(String text) {
        return text.equals(new StringBuilder(text).reverse().toString());
    }
    public static boolean sameLetter(String text) {
        for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) != text.charAt(i - 1));
        }
        return false;
    }
    public static boolean inAscendingOrder(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) < text.charAt(i - 1));
        }
        return false;
    }
    public static void counter(int textLength) {
        if (textLength == 3)
            counter3.getAndIncrement();
        else if (textLength == 4)
            counter4.getAndIncrement();
        else
            counter5.getAndIncrement();
        }
    }
