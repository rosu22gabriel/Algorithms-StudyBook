
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StdIn {
    // presupune Unicode UTF-8 ca incodare
    private static final String CHARSET_NAME = "UTF-8";

    // presupune language = English, country = US pentru consistenta cu System.out.
    private static final Locale LOCALE = Locale.US;

    // separatorul de token-uri implicit; se pastreaza invarianta
    // ca aceasta valoare de tinuta de delimitatorul scanner-ului intre apeluri
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

    // face whitespace-ul siginifiant
    private static final Pattern EMPTY_PATTERN = Pattern.compile("");

    // folosit pentru a citi intregul input
    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");

    private static Scanner scanner;

    // nu are sens ca aceasta clasa sa fie instantiata.
    private StdIn() { }

    /**
     * Intoarce true daca inputul standard e gol (cu posibila exceptie pentru whitespace)
     * Foloseste aceasta metoda ca sa stie daca urmatorul apel la {@link #readString()},
     * {@link #readDouble()}, etc. va avea succes.
     * @return {@code true} daca inputul standard este gol (cu exceptie posibila la whitespace);
     * {@code false} altfel
     */
    public static boolean isEmpty() {
        return !scanner.hasNext();
    }


    /**
     * Intoarce true daca inputul standard are o linie urmatoare
     * Foloseste aceasta metoda pentru a sti daca urmatorul apel la {@link #readLine()} va avea succes.
     * Aceasta metoda este echivalenta cu {@code hasNextChar()}.
     * @return {@code true} daca inputul standard are o linie urmatoare; 
     *         {@code false} altfel
     * 
     */
    public static boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * Intoarce true daca inputul standard are un caracter urmator
     * Foloseste aceasta metoda pentru a sti daca urmatorul apel la {@link #readChar()} va avea succes.
     * Aceasta metoda este echivalenta cu {@code hasNextLine()}.
     * @return {@code true} daca inputul standard are un caracter urmator; 
     *         {@code false} altfel
    */

    public static boolean hasNextChar() {
        scanner.useDelimiter(EMPTY_PATTERN);
        boolean result = scanner.hasNext();
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }


    /**
     * Citeste si intoarce urmatoarea linie, excludand separatorul de linii daca este prezent
     * 
     * @return urmatoarea linie, excludand separatorul de linii daca este prezent;
     * {@code} if no such line
     */
    public static String readLine() {
        String line;
        try {
            line = scanner.nextLine();
        } catch (NoSuchElementException e) {
            line = null;
        }
        return line;
    }
    

    public static char readChar(){
        try {
            scanner.useDelimiter(EMPTY_PATTERN);
            String ch = scanner.next();
            assert ch.length() == 1 : "Internal (Std)In.readChar() error!"
                + " Please contact the authors.";
            return ch.charAt(0);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'char' value from standard input, "
                                            +"but no more tokens available");
        }
    }


    /**
     * Citeste si intoarce restul din input, ca string.
     * @return restul din input, ca string
     * @throws NoSuchElementException daca inputul standard e gol
     */
    public static String readAll() {
        if (!scanner.hasNextLine())
            return "";
        String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
        // nu e asa important ca delimitatorul sa fie resetat, din moment ce scannerul este gol
        scanner.useDelimiter(WHITESPACE_PATTERN); // dar hai s o facem oricum
        return result;
    }

    /**
     * Read the next token from standard input and returns it as a {@code String}.
     * 
     * @return the next {@code String}
     * @throws NoSuchElementException if standard input is empty
     */
    public static String readString() {
        try {
            return scanner.next();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'String' value from standard input, "
                                            +"but no tokens are available");
        }
    }

    /**
     * Citeste urmatorul token din standard input, il parseaza ca intreg, apoi intoarce acel intreg.
     * 
     * @return urmatorul intreg din standard input
     * @throws NoSuchElementException daca standard input e gol
     * @throws InputMismatchException daca urmatorul token nu poate fi parsat ca {@code double}
     */
    public static int readInt() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read an 'int' value from standard input, "
                                            +"but the next token is \"" + token +"\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read an 'int' value from standard input, "
                                            +"but no more tokens are available");
        }
    }

    
    /**
     * Citeste urmatorul token din standard input, il parseaza ca double, apoi intoarce acel double.
     * 
     * @return urmatorul double din standard input
     * @throws NoSuchElementException daca standard input e gol
     * @throws InputMismatchException daca urmatorul token nu poate fi parsat ca {@code double}
     */
    public static double readDouble() {
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read a 'double' value from standard input, "
                                            + "but the next token is \"" + token +"\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'double' value from standard input, "
                                            + "but no more tokens are available");
        } 
    }


    /**
     * Citete urmatorul token din standard input, il parseaza ca 'float', apoi intoarce acel 'float'
     * 
     * @return urmatorul 'float din standard input'
     * @throws NoSuchElementException daca standard input este gol
     * @throws InputMismatchException daca urmatorul token nu poate fi parsat ca {@code float}
     */
    public static float readFloat() {
        try {
            return scanner.nextFloat();
        } catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read a 'float' value from standard input, "
                                            + "but the next token is \"" + token + "\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'float' value from standard input, "
                                            + "but there no more tokens are available");
        }
    }


    /**
     * Citeste urmatorul token din standard input, il parseaza ca 'long integer', si intoarce acel 'long integer'.
     * 
     * @return the next long integer on standard input
     * @throws NoSuchElementException if standard input is empty
     * @throws InputMismatchException if the next token cannot be parsed as a {@code long}
     */
    
    public static long readLong() {
        try {
            return scanner.nextLong();
        } catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read a 'long' value from standard input, "
                                            + "but the next token is \"" + token + "\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'long' value from standard input, "
                                            + "but no more tokens are available");
        }
    }



    /**
     * Citeste urmatorul token din standard input, il parseaza ca 'short integer', si intoarce acel 'short integer'.
     * 
     * @return urmatorul 'short integer' on standard input
     * @throws NoSuchElementException daca standard input e gol
     * @throws InputMismatchException daca urmatorul token nu poate fi parsat ca {@code short}
     */
    public static short readShort() {
        try {
            return scanner.nextShort();
        } catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts ot read a 'short' value from standard input, "
                                            + "but the next token is \"" + token +"\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'short' value from standard input, "
                                            + "but no more tokens are available");
        }
    }


    /**
     * Citeste urmatorul token din standard input, il parseaza ca 'byte', and returns the byte
     * 
     * @return urmatorul byte din standard input
     * @throws NoSuchElementException daca standard input e gol
     * @throws InputMismatchException daca urmatorul token nu poate fi parsat ca {@code byte}
     *      
     * */
    public static byte readByte() {
        try {
            return scanner.nextByte();
        } catch (InputMismatchException e) { 
            String token = scanner.next();
            throw new InputMismatchException("attemtps to read a 'byte' value from standard input, "
                                            + "but the next token is \"" + token + "\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'byte' value from standard input, "
                                            + "but no more tokens are available");
        }
    }


    /**
     * Citeste urmatorul token din standard input, il parseaza ca boolean,
     * si intoarce boolean-ul
     * 
     * @return urmatorul boolean din standard input
     * @throws NoSuchElementException daca standard input e gol
     * @throws InputMismatchException daca urmatorul token nu poate fi parsat ca {@code boolean}:
     * {@code true} or {@code 1} for truem and {@code false} or {@code 0} pentru false,
     * ignorand case-ul
     */

    public static boolean readBoolean() {
        try {
            String token = readString();
            if ("true".equalsIgnoreCase(token))     return true;
            if ("false".equalsIgnoreCase(token))    return false;
            if ("1".equals(token))                  return true;
            if ("0".equals(token))                  return false;
            throw new InputMismatchException("attempts to read a 'boolean' value from standard input, "
                                            + "but the next token is \"" + token + "\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'boolean' value from standard input, "
                                            + "but no more tokens are available");
        }
    }


    /**
     * Citeste restul de token-uri din standard input si intoarce acele token-uri ca un array de string-uri
     * 
     * @return toate token-urile ramase din standard input, ca un array de string-uri
     */
    public static String[] readAllStrings() {
        // am putea folosi readAll.trim().split(), dar nu ar fi consistent
        // pentru ca trim() foloseste caractere 0x00..0x20 ca whitespace
        String[] tokens = WHITESPACE_PATTERN.split(readAll());
        if (tokens.length == 0 || tokens[0].length() > 0)
            return tokens;

        // nu include primul token daca e precede whitespace
        String[] decapitokens = new String[tokens.length-1];
        for (int i = 0; i < tokens.length - 1; i++)
            decapitokens[i] = tokens[i + 1];
        return decapitokens;
    }


    /**
     * Citeste toate token-urile ramase din standard input si intoarce acele token-uri ca un array de string-uri
     * @return toate token-urile ramase din standard input, ca un array de string-uri 
     */
    public static String[] readAllLines() {
        ArrayList<String> lines = new ArrayList<String>();
        while (hasNextLine()) {
            lines.add(readLine());
        }
        return lines.toArray(new String[0]);
    }

    /**
     * Citeste toate token-urile ramase din standard input si intoarce acele token-uri ca un array de int-uri
     * @return toate token-urile ramase din standard input, ca un array de int-uri
     * @throws InputMismatchException daca un token nu poate fi parsat ca {@code int}
     */
    public static int[] readAllInts() {
        String[] fields = readAllStrings();
        int[] vals = new int[fields.length];
        for (int i = 0; i < fields.length; i++)
            vals[i] = Integer.parseInt(fields[i]);
        return vals;
    }

    




}
