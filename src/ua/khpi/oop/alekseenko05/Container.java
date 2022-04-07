package ua.khpi.oop.alekseenko05;

public class Container{
    private int InputTextLength = 0;
    private String[] InputText;

    public void DEBUG () {
        for ( String i : InputText ) {
            System.out.println("-" + i + "-");
        }
        System.out.println();
    }

    public String textToString() {
        StringBuilder result = new StringBuilder();

        if ( InputText == null ) {
            return "";
        }

        for ( String i : InputText ) {
            result.append(i).append(" ");
        }

        return result.toString();
    }

    public void add(String text_line) {
        if ( InputTextLength == 0 ) {
            InputTextLength++;
            InputText = new String[InputTextLength];
            InputText[0] = text_line;
        } else {
            String[] temp = new String[InputTextLength];
            temp = InputText;
            InputTextLength++;
            InputText = new String[InputTextLength];
            for ( int i = 0; i < InputTextLength - 1; i++ ) {
                InputText[i] = temp[i];
            }
            InputText[InputTextLength-1] = text_line;
        }
    }

    public void clear() {
        InputText = new String[0];
        InputTextLength = 0;
    }

    public boolean remove(String text_line) {
        for ( int i = 0; i < InputTextLength; i++ ) {
            if ( InputText[i].equals(text_line) ) {
                // удаление со сдвигом+

                String[] temp = new String[InputTextLength-1];

                //arraycopy(1 массив, исходная позиция в 1 массиве, 2 массив, исходная позиция во 2 массиве, длина)

                System.arraycopy(InputText, 0, temp, 0, i);
                System.arraycopy(InputText, i + 1, temp, i, InputTextLength - i - 1);

                InputText = temp;
                InputTextLength--;
                return true;
            }
        }
        return false;
    }

    public Object[] toArray() {
        Object[] objects = new Object[2];
        objects[0] = InputText;
        objects[1] = InputTextLength;
        return objects;
    }

    public boolean contains(String text_line) {
        for ( int i = 0; i < InputTextLength; i++ ) {
            if ( InputText[i].equals(text_line) ) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(Container container) {
        for ( int i = 0; i < InputTextLength; i++ ) {
            if ( container.InputText == InputText
                    && container.InputTextLength == InputTextLength ) {
                return true;
            }
        }
        return false;
    }

    public int getSize () {
        return InputTextLength;
    }
    public void setSize ( int number ) { InputTextLength = number; }

    public Iterator getIterator() {
        return new ArrayIterator();
    };

    public class ArrayIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            return index < InputTextLength;
        }

        public String next() {
            if ( hasNext() ) {
                return InputText[index++];
            }
            return null;
        }

        public void remove() {
            for ( int i = 0; i < InputTextLength; i++ ) {
                if ( i == index ) {
                    String[] temp = new String[InputTextLength-1];

                    //arraycopy(1 массив, исходная позиция в 1 массиве, 2 массив, исходная позиция во 2 массиве, длина)

                    System.arraycopy(InputText, 0, temp, 0, i);
                    System.arraycopy(InputText, i + 1, temp, i, InputTextLength - i - 1);

                    InputText = temp;
                    InputTextLength--;
                }
            }
        }

        public void reset() {
            index = 0;
        }
    }
}
