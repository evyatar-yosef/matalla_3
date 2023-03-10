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
** for this comparison we create 1000 files when seed = 200 , bound = 100000.

we can see that the methods getNumOfLinesThreads and getNumOfLinesThreadPool got almost the same execution time and the method  getNumOfLines took much more time, There could be several reasons for this: the reason why getNumOfLines is slower
than the others is could be because when using a single thread, all the operations within that task must be completed sequentially, one after the other, On the other hand, when a task is executed using multiple threads, the operations within that task can be divided up and executed in parallel.
getNumOfLinesThreads and getNumOfLinesThreadPool are about the same time that could happen If the tasks are relatively short-lived and don't involve a lot of blocking,then the overhead of creating and managing threads may not have a significant impact on performance. In this case, using a thread pool might not provide much of a performance boost over using individual threads.
Additionally, it also can be depends on how we implement our thread pool.
######In summary:
the performance difference between using threads and a thread pool will depend on the nature of the tasks we executing, the number of threads we using, and how we implement our thread pool.

## diagram:
<img width="429" alt="דיאגרמה חלק א" src="https://user-images.githubusercontent.com/117945522/211858812-cda442a8-6a80-46f7-9ed9-9140fba86c45.png">


## part_b:
## general:
in this part we asked to make a new type that provides an asynchronous task with priority and a ThreadPool type that supports tasks priority. in this class we got several classes:
### TaskType class:
is an enum we recieve with the assignment that describes the task type (computational/O/I access/unknown) and its priority based on the value The number of the task type.
### Task class:
this class represents an operation that can be run asynchronously and can return a value of some type.
### CustomExecutor:
Represents a new type of ThreadPool that supports a queue of priority tasks (all
the task in a queue is of type Task.) CustomExecutor will execute the tasks according to their priority.
### AdapterToTask:
this class wrapping a Callable object with FutureTask and adding ability to sort it based on the priority of the task.
the class extends FutureTask and implements Comparable<AdapterToTask>,Runnable.( use adapter design pattern).
 
 ### diagram:
 <img width="385" alt="image" src="https://user-images.githubusercontent.com/117945522/211857787-90bf91c5-2967-4eff-a0a0-bb5002833ea2.png">





