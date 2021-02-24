public class ThreadCalc extends Thread {
    int matrix[][];
    int startIndex;
    int endIndex;
    int multiResult[];
    int N;

    public ThreadCalc(int matrix[][], int startIndex, int endIndex, int N) {
        this.matrix = matrix;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.multiResult = new int[N];
        this.N = N;
    }


    @Override
    public void run() {
        for (int j = startIndex; j < endIndex; j++) {
            int temp = 1;
            for (int i = 0; i < N; i++) {
                temp *= matrix[i][j];
            }
            GlobalVars.multiResultParallel[j] = temp;
        }

    }
}
