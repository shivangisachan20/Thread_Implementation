package MergeSort;

import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class Client
{


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<Integer>arrayToSort=List.of( 7,5,4,1,2,6,3,8);
        MergeSorter mergeSorter=new MergeSorter(arrayToSort);

        ExecutorService executorService = newCachedThreadPool();
        Future<List<Integer>>sortedArrayFutures= executorService.submit(mergeSorter);

        List<Integer> sortedArray=sortedArrayFutures.get();
        System.out.println(sortedArray);

    }
}

