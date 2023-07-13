package next.reflection;

public class ElapsedTimeTest {
    @ElapsedTime
    void bubbleSort() {
        int[] a = new int[100000];
        for(int i = 1; i < 100000; i++) {
            for(int j = 0; j < 100000 - i; j++) {
                if(a[j] > a [j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                }
            }
        }
    }

    @ElapsedTime
    void sort() {

    }
}
