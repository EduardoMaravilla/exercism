import java.util.Arrays;

public class CircularBuffer<T> {
    int size;
    int elementAdd;
    Object[] values;
    int pointInicial ;
    int pointFinal ;
    CircularBuffer(final int size) {
    this.size = size;
    this.values = new Object[size];
    this.pointInicial = 0;
    this.pointFinal = 0;
    this.elementAdd = 0;
    }

    T read() throws BufferIOException {
        if (isEmpty()){
            throw new BufferIOException("Tried to read from empty buffer");
        }
        T value = (T) this.values[this.pointInicial];
        newPointInicial();
        elementAdd--;
        return value;
    }

    void write(T data) throws BufferIOException {
        if (isFull()){
            throw new BufferIOException("Tried to write to full buffer");
        }
        this.values[calculatePositionOfNewElement()]= data;
        this.elementAdd++;
        if (this.pointFinal < this.size){
            this.pointFinal++;
        }else {
            this.pointFinal = 0;
        }
    }

    void overwrite(T data) throws BufferIOException {
        if (isFull()) {
            this.values[pointInicial] = data;
            if (pointInicial == pointFinal) {
                if (pointFinal < size - 1) {
                    pointFinal++;
                } else {
                    pointFinal = 0;
                }
            }
            if (pointInicial < size - 1) {
                pointInicial++;
            } else {
                pointInicial = 0;
            }
        } else {
            write(data); 
        }
    }


    void clear() {
        Arrays.fill(values,null);
        elementAdd =0;
    }

    private void newPointInicial(){
        this.pointInicial = (this.pointInicial + 1) % this.size;
    }

    private int calculatePositionOfNewElement(){
        return (this.pointInicial + this.elementAdd) % this.size;
    }

    private boolean isEmpty(){
        return this.elementAdd == 0;
    }
    private boolean isFull(){
        return this.elementAdd == this.size;
    }

}
