package ua.khpi.oop.alekseenko01;

public class Program {

    public static int FifthExample ( int BookNumber ) {
        int result = 26 % ( BookNumber - 1 );
        return ++result;
    }

    public static char SixthExample ( int result ) {
        return Character.toUpperCase((char) (((result - 1) % 26)+'a'));
    }

    private static int pairedDec = 0;
    private static int unpairedDec = 0;

    private static int FindedOnes = 0;

    public static void Parity ( int BookNumber ,
                         long PhoneNumber,
                         int Last2Phone,
                         int Last4Phone,
                         int BookNumberTransact,
                         char SymbolByNum ) {

        if ( BookNumber % 2 == 1 ) {
            unpairedDec++;
        } else pairedDec++;

        if ( PhoneNumber % 2 == 1 ) {
            unpairedDec++;
        } else pairedDec++;

        if ( Last2Phone % 2 == 1 ) {
            unpairedDec++;
        } else pairedDec++;

        if ( Last4Phone % 2 == 1 ) {
            unpairedDec++;
        } else pairedDec++;

        if ( BookNumberTransact % 2 == 1 ) {
            unpairedDec++;
        } else pairedDec++;

        if ( SymbolByNum % 2 == 1 ) {
            unpairedDec++;
        } else pairedDec++;
    }

    public static int FindOne ( int BookNumber ,
                         long PhoneNumber,
                         int Last2Phone,
                         int Last4Phone,
                         int BookNumberTransact,
                         char SymbolByNum ) {

        // переводим все числа в двоичную систему
        // соединяем в одну строку
        // ищем единицы в цикле

        String result = Integer.toBinaryString(BookNumber) +
                        Long.toBinaryString(PhoneNumber) +
                        Integer.toBinaryString(Last2Phone) +
                        Integer.toBinaryString(Last4Phone) +
                        Integer.toBinaryString(BookNumberTransact) +
                        Integer.toBinaryString(SymbolByNum);

        for ( int i = 0; i < result.length(); i++ ) {
            if ( result.charAt(i) == '1' ) {
                FindedOnes++;
            }
        }

        return FindedOnes;
    }


    public static void main(String[] args) {

        // TASK 1

        int BookNumber = 0xA;                                   // task 1.1
        long PhoneNumber = 380507180034L;                       // task 1.2
        int Last2Phone = 0B100010;                              // task 1.3
        int Last4Phone = 0100010;                               // task 1.4
        int BookNumberTransact = FifthExample( 15 );    // task 1.5
        char SymbolByNum = SixthExample(BookNumberTransact);        // task 1.6

        // TASK 2

        Parity( BookNumber, PhoneNumber, Last2Phone, Last4Phone, BookNumberTransact, SymbolByNum );

        // TASK 3

        int count = FindOne( BookNumber, PhoneNumber, Last2Phone, Last4Phone, BookNumberTransact, SymbolByNum );
        System.out.println(count);
    }
}
