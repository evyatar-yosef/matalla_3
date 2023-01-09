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
<img width="425" alt="צילום מסך 2023-01-09 212819" src="https://user-images.githubusercontent.com/117945522/211394406-9abc1383-91d0-42a0-8c02-5a11129f21d0.png">



## diagram:
![diagram part a task 3](https://user-images.githubusercontent.com/117945522/211389290-fcaabfea-f3c3-4c91-9819-e0709a33d43d.png)





