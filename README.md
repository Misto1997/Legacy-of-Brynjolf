# Legacy-of-Brynjolf

## Introduction
Brynjolf is a legendary thief known to escape even the most secured rooms and this project simulates Brynjolf's mind to escape room in the presence of moving guards.


## Tech used
1. Java 8
2. Junit5
3. Mockito


## Running the zip file with Intellij
1. Download and unzip the file.
2. In Intellij, File -> Open -> browse to project location -> Click on pom.xml ->Open as project.
3. goto src -> main -> java.
4. Run Main class.


## Legends

#### Objects
1. O(capital O) - empty space
2. G(capital G) - guard
3. E(capital E) - exit gate
4. B(capital B) - brynjolf
5. X(capital X) - wall

#### Moves
1. L(capital L) - move left
2. R(capital R) - move right
3. U(capital U) - move up
4. D(capital D) - move down

**All legends should be stirctly capital letter only**


## Rules
1. Brynjolf can run either left(L), right(R), up(U) or down(D).
2. He can keep going through empty spaces until he hits a wall or reaches exit.
3. If he reaches the exit he wins.
4. The guards copy brynjolf's direction and run in the same direction except they dont go through exit, they treat it as wall.
5. If brynjolf and guard meet's at any point then brynjolf gets caught and he lose.
6. If no move left for Brynjolf and he cannot move any further then the result would be undecided.


## Input format
1. room.txt file inside src -> main -> resource will contain above mentioned room blueprint in N x N format.
2. this file should contain square(N x N) format only with given restriction where N is a positive integer.
3. you can edit file to try different room structure's.
4. two options would be given to you: 1. Establishment 2. Enlightement
5. enter any one option(1 or 2) in console.

## Walkthrough

#### 1. Establishment
- input sequence(example: LLR) needed to be passed(compulsory) via commandline input.
- it will print the state i.e (**WIN, LOOSE, UNDECIDED**) after executing given sequence.

##### Example
```
input: RRUU
O O O O          O O O O          O O O O
X G O O    R     X O G O    R     X O O G
O B O E    -->   O O B E    -->   O O O E  (WIN)
O O G O          O O O G          O O O G
output: win: executed 2 moves of 4

input: UURR
O O O O          O O O O     
X G O O    U     X G O O    
O B O E    -->   O O O E  (LOSE)  
O O G O          O O G O
output: loose: executed 1 move of 4

input: LR
O O O O          O O O O          O O O O
X G O O    L     X G O O    R     X O G O
O B O E    -->   B O O E    -->   O B O E  (UNDECIDED)
O O G O          O G O O          O O G O
outpur: undecided: executed 2 moves of 2
```
  
#### 2. Enlightement
- input sequence(example: LLR) needed to be passed(Optional) via commandline input.
- if input sequence is there it will run the sequence and if state is **UNDECIDED** after running input sequence then it will  run winning sequence.
- if input sequence is not there then it will simply print **winning** sequence.


##### Example
```
input: R
O O O O          O O O O          O O O O
X G O O    R     X O G O    R     X O O G
O B O E    -->   O O B E    -->   O O O E  (WIN)
O O G O          O O O G          O O O G
output: win: R

input:<no input>
O O O O          O O O O          O O O O
X G O O    R     X O G O    R     X O O G
O B O E    -->   O O B E    -->   O O O E  (WIN)
O O G O          O O O G          O O O G
output: win: RR

input: D
O O O O          O O O O  
X G O O    D     X O O O  
O B O E    -->   O G O E  (STUCK)
O O G O          O B G O  
output: stuck: no way to win
```

## Assumptions
1. If Brynjolf step's into position where guard stands then game over there and thats the final room blueprint.
2. guard copies brynjolf moves after brynjolf moves, both will not move in parallel.


## Limitation
~~As BFS shortest path algorithm is being used here so it will try to explore all path from pertcular point to get shortest distance and hence it work's great with small matrix size, till 7x7 size to be precise. Performace will keeps on decreasing if matrix size increases.~~
**Algorithm is now optimized and working perfect(tested till 15x15 size)**

## In progress work
1. Make dependencies loosely coupled.
2. Code refactoring and more test cases to cover left over edge cases if any.
   