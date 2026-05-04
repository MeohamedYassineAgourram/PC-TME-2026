# Q1:

voir le fichier MandlebrotTask et les modifications sur MandelbrotCalculator

# Q2:

Les Tests sont éffectué avec Main.java :

Initalement:

-------------------------------------avec compute -------------------------------------------------

int width = 800;
int height = 600;
int maxIterations = 5000;

Rendered image in 2442 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 2601ms




-------------------------------------avec parCompute -------------------------------------------------

int width = 800;
int height = 600;
int maxIterations = 5000;

Rendered image in 419 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 536ms






===============================================================================================

Changement sur la taille de l'image :


-------------------------------------avec compute -------------------------------------------------

int width = 1200;
int height = 1200;
int maxIterations = 5000;

Rendered image in 7020 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 7292ms




-------------------------------------avec parCompute -------------------------------------------------

int width = 1200;
int height = 1200;
int maxIterations = 5000;

Rendered image in 935 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 1121ms






===============================================================================================

Modification sur maxIterations

-------------------------------------avec compute -------------------------------------------------

int width = 1200;
int height = 1200;
int maxIterations = 15000;

Rendered image in 20541 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 20756ms




-------------------------------------avec parCompute -------------------------------------------------

int width = 1200;
int height = 1200;
int maxIterations = 15000;

Rendered image in 2631 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 2805ms


===============================================================================================





Modification sur nombre de thread pour parCompute :

pool defaut de java 
ForkJoinPool pool = new ForkJoinPool(2);
ForkJoinPool pool = new ForkJoinPool(4);
ForkJoinPool pool = new ForkJoinPool(6);
ForkJoinPool pool = new ForkJoinPool(8);


-------------------------------------avec parCompute -------------------------------------------------

int width = 1200;
int height = 1200;
int maxIterations = 15000;

Rendered image in 2631 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 2805ms

Rendered image in 9790 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 10020ms

Rendered image in 5414 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 5607ms

Rendered image in 4262 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 4559ms

Rendered image in 3058 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 3290ms



===============================================================================================

Modification de la partie de l'image :

Les anciennes configurations: BoundingBox bbox = new BoundingBox(-2.5, 1, -1, 1, width, height);
Les nouveles configurations à tester : 

-BoundingBox bbox = new BoundingBox(-5, 5, -1, 1, width, height);
-BoundingBox bbox = new BoundingBox(-1.0, -0.5, -0.25, 0.25, width, height);
-BoundingBox bbox = new BoundingBox(-0.8, -0.7, 0.05, 0.15, width, height);


-------------------------------------avec compute -------------------------------------------------

int width = 1200;
int height = 1200;
int maxIterations = 15000;

Rendered image in 6637 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 6837ms

Rendered image in 76229 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 76409ms

Rendered image in 61628 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 61894ms


-------------------------------------avec parCompute -------------------------------------------------

int width = 1200;
int height = 1200;
int maxIterations = 15000;

Rendered image in 1051 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 1259ms

Rendered image in 9568 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 9757ms

Rendered image in 7174 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 7385ms


# Q3 :

voir fichier QuickSortParallèle

# Q4 : 

voir partie question 4 du fichier QuickSort + le main pour voir données obtenues.

Sur mon pc portable j'ai les résultats :

Temps séquentiel : 166 ms
Temps parallèle avec 1 threads : 218 ms
Temps parallèle avec 2 threads : 97 ms
Temps parallèle avec 4 threads : 118 ms
Temps parallèle avec 8 threads : 64 ms
Temps parallèle avec thread pool : 45 ms

# Q5 :

THRESHOLD = la taille du tableau si supérieur à ce seuil on divise pour règner sinon on fait directement le tri :

-> 10 :

Temps séquentiel : 166 ms
Temps parallèle avec 1 threads : 218 ms
Temps parallèle avec 2 threads : 97 ms
Temps parallèle avec 4 threads : 118 ms
Temps parallèle avec 8 threads : 64 ms
Temps parallèle avec thread pool : 45 ms


-> 50 :

Temps séquentiel : 175 ms
Temps parallèle avec 1 threads : 210 ms
Temps parallèle avec 2 threads : 97 ms
Temps parallèle avec 4 threads : 79 ms
Temps parallèle avec 8 threads : 66 ms
Temps parallèle avec thread pool : 51 ms


-> 100 :

Temps séquentiel : 200 ms
Temps parallèle avec 1 threads : 245 ms
Temps parallèle avec 2 threads : 137 ms
Temps parallèle avec 4 threads : 74 ms
Temps parallèle avec 8 threads : 76 ms
Temps parallèle avec thread pool : 51 ms


-> 1000 :

Temps séquentiel : 176 ms
Temps parallèle avec 1 threads : 180 ms
Temps parallèle avec 2 threads : 100 ms
Temps parallèle avec 4 threads : 68 ms
Temps parallèle avec 8 threads : 56 ms
Temps parallèle avec thread pool : 56 ms


-> 10000 :

Temps séquentiel : 193 ms
Temps parallèle avec 1 threads : 184 ms
Temps parallèle avec 2 threads : 87 ms
Temps parallèle avec 4 threads : 78 ms
Temps parallèle avec 8 threads : 60 ms
Temps parallèle avec thread pool : 69 ms


-> 100000 :

Temps séquentiel : 183 ms
Temps parallèle avec 1 threads : 168 ms
Temps parallèle avec 2 threads : 101 ms
Temps parallèle avec 4 threads : 70 ms
Temps parallèle avec 8 threads : 57 ms
Temps parallèle avec thread pool : 48 ms

