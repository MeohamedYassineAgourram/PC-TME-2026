# TME 1 : Rappels String, List, Map et complexité

## 1.1 Fréquence de mots

### Question 1

L'exécution du programme:
| Preparing to parse data/WarAndPeace.txt (mode=list), containing 3300667 bytes
| Total words: 565527
| Unique words: 20332
| Total runtime (wall clock) : 6007 ms for mode list

Complexité du mode "list" : L'opération est en O(n × d). Pour chaque mot du texte (n), on parcourt toute la liste des mots uniques déjà trouvés (taille d) avec la méthode contains.

Si d est proche de n alors tous les mots du texte sont différents (comme dans un dictionnaire), alors d est presque égal à n. La complexité devient O(n × n), c'est-à-dire O(n²) (quadratique). C'est catastrophique pour les grands textes, car le temps de calcul explose !

### Question 2

L'exécution du programme:
| Preparing to parse data/WarAndPeace.txt (mode=listfreq), containing 3300667 bytes
| Total words: 565527
| Unique words: 20332
| 34562 the
| 22148 and
| 16709 to
| 14990 of
| 10513 a
| Total runtime (wall clock) : 13530 ms for mode listfreq

Complexité du mode "listfreq" : Remplissage : Comme pour "list", on parcourt la liste existante pour chaque mot lu. Cela coûte O(n × d). Tri : Le tri final coûte O(d log d). Le total est O(n×d + d×log(d)). Mais comme n est beaucoup plus grand que log(d), c'est la partie O(n × d) qui gagne
La complexité totale est dominée par le remplissage : O(n × d).

Si d est proche de n alors chaque mot est unique, la complexité devient O(n²). C'est extrêmement lent car la liste grandit à chaque mot et on doit la parcourir entièrement à chaque fois.

### Question 3

L'exécution du programme:
| Preparing to parse data/WarAndPeace.txt (mode=tree), containing 3300667 bytes
| Total words: 565527
| Unique words: 20332
| 34562 the
| 22148 and
| 16709 to
| 14990 of
| 10513 a
| Total runtime (wall clock) : 1278 ms for mode tree

Complexité du mode "tree" : Le TreeMap est un arbre binaire de recherche équilibré. Remplissage : Chaque insertion ou recherche coûte O(log d) (où d est le nombre de mots distincts). Comme on le fait pour les n mots du texte, cela coûte O(n log d). Tri final : Trier les entrées coûte O(d log d).
Total : O(n log d) (car n est plus grand que d)

Comparaison : C'est beaucoup plus efficace que le mode "list" ! Si d est proche de n (tous les mots différents), la complexité est O(n log n), ce qui est très rapide comparé au O(n²) catastrophique des listes.

### Question 4

L'exécution du programme:
| Preparing to parse data/WarAndPeace.txt (mode=hash), containing 3300667 bytes
| Total words: 565527
| Unique words: 20332
| 34562 the
| 22148 and
| 16709 to
| 14990 of
| 10513 a
| Total runtime (wall clock) : 1127 ms for mode hash

Complexité du mode "hash" : Le HashMap utilise une table de hachage. Remplissage : L'accès (put/get) est en temps constant O(1) en moyenne. Pour les n mots du texte, le coût total du remplissage est donc O(n). Tri final : Il faut toujours trier les mots uniques à la fin, ce qui coûte O(d log d).
Total : O(n + d log d).

Comparaison : C'est théoriquement la méthode la plus rapide pour la phase de comptage. Même si d est proche de n (tous les mots différents), la complexité reste O(n) pour le comptage (plus le tri), ce qui bat toutes les autres méthodes.

## 1.2 String vs StringBuilder

### Question 5
Complexité de repeatNaive En Java, les String sont immuables. L'opérateur += ne rajoute pas un caractère, il crée une nouvelle String et recopie tout le contenu précédent dedans.
Pour générer une chaîne de taille N :
    À l'itération i, on doit copier i caractères.
    Le nombre total de copies est la somme : 1 + 2 + ... + n.
    C'est une suite arithmétique dont la somme vaut (n(n+1) / 2).
La complexité est donc en O(n²) (Quadratique). C'est très inefficace pour créer une chaîne de 100 000 caractères, l'ordinateur doit faire environ 5 milliards de copies !

### Question 6
Test avec N = 100000
Naive Elapsed time: 1004ms
Default Elapsed time: 6ms
Capacity Elapsed time: 3ms

Commentaire : La version naive (String) est très lente car elle crée une nouvelle chaîne de caractères à chaque tour de boucle (complexité quadratique O(n²)). Les versions StringBuilder modifient la chaîne en mémoire sans la recopier à chaque fois (complexité linéaire O(n)). La version repeatCapacity est théoriquement la plus optimisée car elle évite d'avoir à agrandir le tableau interne en cours de route, mais la version Default est déjà excellente.

### Question 7
1 Version repeatCapacity
    Fonctionnement : On alloue directement un tableau interne de taille n.
    Nombre de réallocations : 0 et le tableau a la bonne taille dès le début.
    Nombre de caractères copiés (hors remplissage) : 0 et aucune copie intermédiaire n'est nécessaire.
    Complexité : On effectue simplement n opérations d'ajout. La complexité est donc linéaire O(n).

2 Version repeatDefault
Fonctionnement : La capacité initiale est de 16. À chaque fois que le tableau est plein, Java crée un nouveau tableau deux fois plus grand et recopie les anciens caractères.
Les étapes (pour atteindre n) :
    Capacités successives : 16, 32, 64, 128, ..., n/2 (jusqu'à dépasser n).
    Nombre de réallocations : Il correspond au nombre de fois qu'on doit doubler 16 pour atteindre n, soit environ log_2(n). (C'est très peu : pour 10^5, c'est environ 13 réallocations).
Nombre de caractères copiés :
    À chaque réallocation, on copie tout ce qu'on a déjà écrit.
    Total des copies un peu prêt 16 + 32 + 64 + ... + n/2.
    C'est une suite géométrique de raison 2. La somme est environ égale à n. (Mais toujours plus petit que n)
Complexité :
    Coût total = (n ajouts) + (~n copies lors des agrandissements).
    Total ~2n opérations.
    En notation grand O, les constantes sans supprimées : la complexité reste linéaire O(n).

--> Conclusion :
Les deux versions ont la même complexité théorique asymptotique O(n). C'est pour cela que la différence de temps mesurée (Question 6) est très faible (quelques millisecondes). Cependant, "repeatCapacity" reste légèrement plus performant car il évite le travail caché de réallocation mémoire.

### Question 8

Observations basées sur benchmark.pdf :

1 Méthode Naive (Courbe Verte - Page 1) :
    La courbe suit une trajectoire parabolique parfaite.
    La légende indique un "fit" en O(n^2).
    Cela confirme sans appel que la concaténation de String est de complexité quadratique. C'est inutilisable pour de grandes données.

2 Méthode StringBuilder (Courbes Bleue et Orange - Page 2) :
    Les deux courbes sont des droites (Linéaires O(n)).
    Comparaison Default vs Capacity :
        builder_capacity (Orange) est la plus performante (~9.89 ns/char).
        builder_default (Bleue) est plus lente (~18.49 ns/char) et présente des irrégularités (pics).
    Ces résultats confirment la théorie : builder_capacity est plus rapide (presque 2x ici) car elle évite le surcoût des réallocations mémoire et des copies intermédiaires visibles sur la courbe bleue.

Conclusion :
L'expérience valide totalement les prédictions théoriques des questions 5 et 7.