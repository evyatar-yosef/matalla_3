# matalla_3

## general: 
this assignment is about threads and threadpools, in this assignment we got two part:
## part_a:
In this assignment we asked to create several text files and calculate the total number of lines
in these files. we use 4 methods to execute the assignment:
### 1) createTextFiles:
 this method roll is to create the text files whose lines we want to count,
This method accepts 3 parameters: int n - the number of files we want to create, int seed,bound - variables whose role is to define a "random" number of lines in each text file
### 2) getNumOfLines:
 a simple method that uses a loop to go through the files and counts the number of lines in each file. 
### 3)  getNumOfLinesThreads:
use threads so each threads receives one text and counts the number of lines in it. 
the method use GetLineThread class that implements Runable interface and implements run method(where the counting take place), and getlines method(return the number of lines).
### 4) getNumOfLinesThreadPool:
use threadpool So that each thread receives one text to count its lines and when it finishes it frees up and is ready to receive a new task to perform.
the method use GetLinePoolThread that implements Callable interface and implements call method that make the calulate and return the result.

* each method also calculate the amount of time it took for the counting to preformed.

## Comparison of times:
<img width="385" alt="זמנים 3" src="https://user-images.githubusercontent.com/117945522/211626154-943a0bc0-c518-4d5c-9b5c-6263d9a92a14.png">
we can see that the methods getNumOfLinesThreads and getNumOfLinesThreadPool got almost the same execution time and the method  getNumOfLines took much more time, There could be several reasons for this: the reason why getNumOfLines is slower
than the others is could be because when using a single thread, all the operations within that task must be completed sequentially, one after the other, On the other hand, when a task is executed using multiple threads, the operations within that task can be divided up and executed in parallel.
getNumOfLinesThreads and getNumOfLinesThreadPool are about the same time that could happen If the tasks are relatively short-lived and don't involve a lot of blocking,then the overhead of creating and managing threads may not have a significant impact on performance. In this case, using a thread pool might not provide much of a performance boost over using individual threads.
Additionally, it also can be depends on how we implement our thread pool.
######In summary:
the performance difference between using threads and a thread pool will depend on the nature of the tasks we executing, the number of threads we using, and how we implement our thread pool.

## diagram:
![diagram part a task 3](https://user-images.githubusercontent.com/117945522/211389290-fcaabfea-f3c3-4c91-9819-e0709a33d43d.png)





