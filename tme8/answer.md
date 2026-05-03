Objectif du TME8/ Les problèmes à résoudre :

• Il ne gère qu’un seul niveau de profondeur, i.e. il ramasse les pdf de la page de départ et ceux
des pages référencées par la page de départ, mais c’est tout.
• Il ne gère pas les cycles de liens; en effet il est important de ne pas crawler plusieurs fois la
même page.
• Il est séquentiel
L’objectif est de paralléliser le programme.


# Q1 Parallélisation  + Q2:

d'après la phrase et le thème de TME :

"On va créer une file de paires (url, profondeur) à traiter. Cette file est partagée par plusieurs
threads et bloquante si vide." => BlockingQueue qui est une file thread-safe avec opérationq bloquantes.

voir fichier WebCrawlerParallel

Résumé du code :

pour n thread dans le treadpool, ils traite de manière thread-safe les tâches (profondeur + url) dans BlockingQueue dans un while(true).
On donne un max de profondeur à traité donc en partant de racine et max lien de lien de la racine, et chaque url non traité on les ajoute dasn les taches à traiter. 


# Q3 : 

Voir fichier ActivityMonitor 


# Q4 :

Voir fichier WebCrawlerParallel_Q4

# Q5 :

Utiliser le fichier WebCrawlerParallel_Q5
Refresh le package tme8 pour avoir le rendu résultats_q5.txt