Complétez avec vos réponses.

# Q1

En mode 'naive' et les sliders par défaut, la scène évolue de manière chaotique, et elle se stabilise à la fin avec des structure stables avec les oscillateurs comme nous indique la figure 2. 

Quand on modifie les sliders, on remarque que la vitesse d'affichage, la fluidité en depend fortement, plus on les augmente plus ça freeze, moins c'est fluide.

Cependant on distingue 2 différence lorsqu'on défile le slider Grid Update delay vers 0ms on remarque que l'affichage est très rapide mais on arrive a obtenir quelque chose de stable comme la figiure 2.

Tandis que pour slider Screen Refresh, si on le défile vers 0 donc no sleep, l'évolution de celle ci devient alors complètement chaotique, elle ne se stabilise pas mais en plus on ne peut plus refresh. L'évolution de celle semble donc incorrect car la figure 2 nous indique qu'un code correct devrait normalement se converger vers la figure (une stabilisation) or ce n'est pas le cas 

 
# Q2

En analysant l'extrait, on sait que l'Udapter et le Refresher s'exécute en parallèle, le updater ecrit dans next et le refresher lit le next via current

Et on observe un typique data race entre le updater et le refresher, le refresher pourrait lire quand le udapter est en train d'écrire, ou peut louper des lectures quand udapter a finit d'écrire quand on met un sleep trop élevé ou slider de update trop élevé.

Si on met 0 pour le screen refresh delay alors on a pas de sleep donc ça augmente la chance de lire quelque chose que le refresher est en train d'écrire ce qui falsifie notre scène et mène un cercle visieu (un résultat chaotique) car on lit quelque chose d'incomplet et cette erreur est repris par refresher dans les calules et ainsi de suite ce qui mène à un cercle vicieux

# Q4

Codes de fichers : LifeModelSync et Mtsafemode

# Q5

Avec l'ajout de zynchronized, les udaptaters et refreshers ne peux peuvent plus s'exécuter en même temps ce qui résoud en quelque sort la data race évoqué dans la question 2. 
Si on met le slider de refresh à 0 et udapte intacte on voit que le problème chaotique de la question 2 est maintenant résolu. Ce qui veut dire que le programme semble être correct.
Cependant contraiement au cas de la question 2, quand on met le slider de udapter à 0 le programme est correct mais on ressens le coup de lag, que ce n'est pas très fluide cela peut être dû à des udapte non lu par le refresher donc par exemple 2 update se fait par la suite.

# Q6

Voir fichier LifeModelBlock

# Q7 

Voir fichier AlternateMode


# Q8 

Le sleep servait de base pour controller le prise de travaille des 2 threads (pour que le udapter puisse plus lire que refresher et vice versa). Cependant comme on a mis l'écoute while + wait, cette régularisation des threads devient inutile car l'oodre est déjà controllé avec l'implémentation de while + wait. Donc les sleeps en soit ne sert juste à ralentir pour avoir un effet visuel puisque si on met les 2 slider à 0 l'affichage sera quasi instantanné, mais pour cela on a juste à garder le sleep du refresher.

=> j'ai mis en commentaire pour enlever la fonctionnalité du sleep udapter


# Q9

Voir fichier Turn dans le package life.sync

# Q10

Voir fichier ExternalMode, le problème visuel est bien résolu, si nécessaire on peut mettre en commentaire pour désactiver le fonctionnement du slider Update que je n'ai pas fait ici.


# Q11 

Je confirme que ça explose l'affichage :'(
(Hypothèse : car c'est comme si on avait divisé l'affichage en 4, si refresh lit les 4 parties qui sont encore en cours alors la présence d'erreur augmente)

# Q13

Voir fichier SimpleSemaphore dans le package life.sync

# Q14 

Voir fichier TwoSemaphoreMode

En tirant le les slider vers 0 pour Update je ressens un peu des coups de lag et parfois ça ne bouge plus alors qu'il sont censé comparé au anciennes comportements des anciennes questions, et pour le cas de Refresh ça a résolu le cas de comportement complètement chaotique mais pas complètement, comparé à ce qu'on avait avec 1 seul Thread d'update, le résultat semble être un peu différent de ce qu'on avait obtenue dans les précédante questions, parfois des affichage chaotique qui revient par exemple.

# Q15 (Bonus) 

Les udapters prennent des jetons dans le même sémaphore (ready), donc si un udapter est très rapide, il peut alors prendre plusieurs fois des jetons et faire plusieurs morceaux de calcul pendant qu'un autre udapter n'a pas encore travaillé, ce qui explique pourquoi on avait quelque chose qui ne correspondait pas à ce qu'on avait obtenu.


# Q16

Voir fichier SemaphoreMode 

On a pas de problème chaotique et de fluidité


