package application.main;

import application.direction.ArrayShifts;
import application.direction.Direction;
import application.manual.KeyBoardManual;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Field {
    private final int[][] field;
    private boolean isStillPlay;

    public Field() {
        this.field = new int[4][4];
        this.isStillPlay = true;
    }

    private int get2Or4(){
        int chance = (int)(Math.random() * 10);
        return chance >= 7 ? 4 : 2;
    }

    private void setElementRandomPlace(){
        ArrayList<String> coordinates = getCoordinatesEmptyPlace();
        int randomPlace = (int)(Math.random() * coordinates.size());
        try {
            String[] coordinate = coordinates.get(randomPlace).split("_");
            int y = Integer.parseInt(coordinate[0]);
            int x = Integer.parseInt(coordinate[1]);

            this.field[y][x] = get2Or4();
        }catch (IndexOutOfBoundsException e){
            System.err.println("Game over!");
            this.isStillPlay = false;
        }

    }

    private ArrayList<String> getCoordinatesEmptyPlace(){
        ArrayList<String> coordinates = new ArrayList<>();

        for(int i = 0; i < this.field.length; i++){
            for(int j = 0; j < this.field[i].length; j++){
                int digitEnd = 2048;
                if(this.field[i][j] == digitEnd){
                    System.err.println("Win!!");
                    this.isStillPlay = false;
                }
                if(this.field[i][j] == 0){
                    coordinates.add(i + "_" + j);
                }
            }
        }

        return coordinates;
    }


    private void run(){
        KeyBoardManual manual = new KeyBoardManual();
        manual.start();
        this.setElementRandomPlace();
        this.printField();

        while (this.isStillPlay){
            if(manual.hasKeyEvents()) {

                KeyEvent keyEvent;

                while (isKey(keyEvent = manual.getEventFromTop()))

                if (keyEvent.getKeyChar() == 'q') {
                    System.err.println("Game was stop!");
                    return;
                }

                switch (keyEvent.getKeyCode()){
                    case KeyEvent.VK_DOWN:
                        updateField(Direction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        updateField(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        updateField(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        updateField(Direction.UP);
                        break;
                }

                this.setElementRandomPlace();
                this.printField();
            }
        }
    }

    private boolean isKey(KeyEvent keyEvent){
        boolean is = false;

        try {
            if (keyEvent.getKeyChar() == 'q') {
                return !is;
            }
        }catch (NullPointerException e){
            System.err.println("Game was stop!");
            System.exit(1);
        }

        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_DOWN:
                is = true;
                break;
            case KeyEvent.VK_LEFT:
                is = true;
                break;
            case KeyEvent.VK_RIGHT:
                is = true;
                break;
            case KeyEvent.VK_UP:
                is = true;
                break;
        }

        return !is;
    }

    private void updateField(Direction direction){
        switch (direction){
            case RIGHT:
                ArrayShifts.shiftRight(field, direction);
                break;
            case LEFT:
                ArrayShifts.shiftLeft(field, direction);
                break;
            case DOWN:
                ArrayShifts.shiftDown(field, direction);
                break;
            case UP:
                ArrayShifts.shiftUp(field, direction);
                break;
        }
    }


    private void printField(){
        System.out.println();
        System.out.println();

        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field[i].length; j++){
                int fieldResult = field[i][j];
                String delimiter = getSpaceLine(' ', countSpaces(fieldResult));
                System.out.print(delimiter + fieldResult);
            }
            System.out.println();
            System.out.println();
        }
    }

    private int countSpaces(int fieldResult){
        int result = 4;
        String line = String.valueOf(fieldResult);
        if(line.length() > 1){
            int cut = line.length() - 1;
            result = result - cut;
        }

        return result;
    }

    private String getSpaceLine(char symbol, int countSymbols){
        StringBuilder result = new StringBuilder();
        result.append(String.valueOf(symbol).repeat(Math.max(0, countSymbols)));
        return result.toString();
    }


    public static void main(String[] args){
        Field game = new Field();
        game.run();
    }
}
