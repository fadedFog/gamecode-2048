package application.direction;

public class ArrayShifts {

    private static void shiftAboutDirection(int[][] field, Direction direction){
        switch (direction){
            case UP:
                for(int count = 0; count < field.length; count++) {
                    for (int i = 0; i < field[0].length; i++) {
                        for (int j = 0; j < field.length; j++) {
                            if (j != field.length - 1) {
                                if (field[j][i] == 0) {
                                    field[j][i] = field[j + 1][i];
                                    field[j + 1][i] = 0;
                                }
                            }
                        }
                    }
                }
                break;
            case DOWN:
                for(int count = 0; count < field.length; count++) {
                    for (int i = 0; i < field[0].length; i++) {
                        for (int j = field.length-1; j >= 0; j--) {
                            if (j != 0) {
                                if (field[j][i] == 0) {
                                    field[j][i] = field[j - 1][i];
                                    field[j - 1][i] = 0;
                                }
                            }
                        }
                    }
                }
                break;
            case LEFT:
                for(int count = 0; count < field.length; count++){
                    for(int i = 0; i < field.length; i++){
                        for(int j = 0; j < field[i].length; j++){
                            if(j != field[i].length-1){
                                if(field[i][j] == 0){
                                    field[i][j] = field[i][j + 1];
                                    field[i][j + 1] = 0;
                                }
                            }
                        }
                    }
                }
                break;
            case RIGHT:
                for(int count = 0; count < field.length; count++){
                    for(int i = 0; i < field.length; i++){
                        for(int j = field[i].length-1; j >= 0; j--){
                            if(j != 0){
                                if(field[i][j] == 0){
                                    field[i][j] = field[i][j - 1];
                                    field[i][j - 1] = 0;
                                }
                            }
                        }
                    }
                }
                break;
        }
    }

    public static void shiftUp(int[][] field, Direction direction){
            shiftAboutDirection(field, direction);
            for (int j = 0; j < field[0].length; j++) {
                for (int i = 0; i < field.length; i++) {
                    if (i != field.length - 1) {
                        if (field[i][j] == field[i + 1][j]) {
                            field[i][j] *= 2;
                            field[i + 1][j] = 0;
                            i += 1;
                        }
                    }
                }
            }
        shiftAboutDirection(field, direction);

    }

    public static void shiftDown(int[][] field, Direction direction){
        shiftAboutDirection(field, direction);
        for (int j = 0; j < field[0].length; j++) {
            for (int i = field.length - 1; i >= 0; i--) {
                if (i != 0) {
                    if (field[i][j] == field[i - 1][j]) {
                        field[i][j] *= 2;
                        field[i - 1][j] = 0;
                        i -= 1;
                    }
                }
            }
        }
        shiftAboutDirection(field, direction);
    }

    public static void shiftLeft(int[][] field, Direction direction) {
        shiftAboutDirection(field, direction);
        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field[i].length; j++){
                if(j != field[i].length-1){
                    if(field[i][j] == field[i][j + 1]){
                        field[i][j] *= 2;
                        field[i][j + 1] = 0;
                        j += 1;
                    }
                }
            }
        }
        shiftAboutDirection(field, direction);
    }

    public static void shiftRight(int[][] field, Direction direction){
        shiftAboutDirection(field, direction);
        for(int i = 0; i < field.length; i++){
            for(int j = field[i].length-1; j >= 0; j--){
                if(j != 0){
                    if(field[i][j] == field[i][j - 1]){
                        field[i][j] *= 2;
                        field[i][j - 1] = 0;
                        j -= 1;
                    }
                }
            }
        }
        shiftAboutDirection(field, direction);
    }

}
