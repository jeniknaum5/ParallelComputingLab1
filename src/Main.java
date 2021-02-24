

//Заповнити квадратну матрицю випадковими числами. На побічній діагоналі розмістити добуток елементів, які лежать в тому ж стовпці.

public class Main {
    public static int N = 3000, M = 3000;
    public static int NUMBER_THREADS = 10;


    public static void main(String[] args) throws InterruptedException{
        int[][] matrixParallel = new int[N][M];
        int rand_min =1, rand_max = 50;

        for(int i=0; i<N; i++){//заполняем матрицу случайными числамы в определённом промежутке
            for (int j=0; j<M; j++){
                matrixParallel[i][j] = rand_min + (int) (Math.random() * rand_max);
            }
        }

        ThreadCalc[] ThreadArray = new ThreadCalc[NUMBER_THREADS];


        long sred=0;

        for(int test = 0; test <100; test++){
            long startTimeParallel = System.nanoTime();
            for(int i=0;i<NUMBER_THREADS; i++){//разбиваем на потоки
                ThreadArray[i] = new ThreadCalc(matrixParallel, N/NUMBER_THREADS*i,
                        i==(NUMBER_THREADS-1)?N:N/NUMBER_THREADS *(i+1), N);
                ThreadArray[i].start();
            }
            for(int i=0;i< NUMBER_THREADS; i++)//завершение потоков
                ThreadArray[i].join();

            for(int i=0; i<N; i++){
                matrixParallel[N-i-1][i] = GlobalVars.multiResultParallel[i];
            }
            long finishTimeParallel = System.nanoTime();
            long durationParallel = (finishTimeParallel - startTimeParallel)/1000; //время работы потоков в микросекундах
            if(test==0)
                continue;
            //System.out.println("Время работы " +NUMBER_THREADS+" потока(-ов): " + durationParallel);
            sred+=durationParallel;
        }
        System.out.println("Среднее время " + sred/99);
    }

    public static void printMatrix(int[][] matrix){
        for(int i=0;i< N;i++){
            for (int j=0; j< M; j++){
                System.out.print(matrix[i][j]+ "\t");
            }
            System.out.println();
        }
    }
}
