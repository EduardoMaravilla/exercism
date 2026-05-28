class Matrix {
    private String[] datos;
    Matrix(String matrixAsString) {
        this.datos = matrixAsString.split("\n");        
    }
    int[] getRow(int rowNumber) {
        String[] fila = datos[rowNumber-1].split(" ");
        int[] num = new int[fila.length];
        for (int i = 0; i < fila.length; i++) {
            num[i] = Integer.parseInt(fila[i]);
        }
        return num;
    }
    int[] getColumn(int columnNumber) {
        int[] column = new int[datos.length];
        for (int i = 0; i < datos.length; i++) {
            String[] fila = datos[i].split(" ");
            column[i] = Integer.parseInt(fila[columnNumber-1]);
        }
        return column;
    }
}