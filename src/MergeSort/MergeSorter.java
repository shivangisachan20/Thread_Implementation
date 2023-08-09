package MergeSort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MergeSorter implements Callable<List<Integer>>{
    List<Integer> arrayToSort;
    MergeSorter(List<Integer> arrayToSort)
    {
        this.arrayToSort=arrayToSort;
    }

   public List<Integer> call() throws Exception
   {
       if(arrayToSort.size()<=1) return arrayToSort;
       int mid=arrayToSort.size()/2;
       List<Integer>leftArray = new ArrayList<>();
       for (int i=0;i<mid;i++)
       {
           leftArray.add(arrayToSort.get(i));
       }
       List<Integer>rightArray=new ArrayList<>();
       for (int i=mid;i< arrayToSort.size();i++)
       {
         rightArray.add(arrayToSort.get(i));
       }

       // Sorting of 2 half is independent task and can be done into parallel
        MergeSorter leftMergeSorter=new MergeSorter(leftArray);
        MergeSorter rightMergeSorter=new MergeSorter(rightArray);

        List<Integer>sortedLeft=leftMergeSorter.call();
       List<Integer>sortedRight=rightMergeSorter.call();

        //merge the 2 sorted part of the array

       int i=0,j=0;
       List<Integer>sortedArray=new ArrayList<>();
       while(i<sortedLeft.size() && j<sortedRight.size())
       {
           if(sortedLeft.get(i)< sortedRight.get(j))
           {
               sortedArray.add(sortedLeft.get(i));
               i++;
           }
           else {
               sortedArray.add( sortedRight.get(j));
               j++;
           }
       }
       while(i<sortedLeft.size())
       {
           sortedArray.add(sortedRight.get(i));;
           i++;

       }
       while(j<sortedRight.size())
       {
           sortedArray.add(sortedRight.get(j));;
           j++;

       }
       return sortedArray;
   }

}
