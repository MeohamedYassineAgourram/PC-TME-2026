Complétez avec vos réponses.

# Q1

 blabla décrire l'interface du jeu, voir énoncé
 
# Q2

data race entre le updater et le refresher, si updater sleep 0 alors refresher arrive pas à suivre updater, donc sauté étape, sinon inversement image static changement dans longtemps

Si on met 0 pour le screen refresh delay alors on ne se converge pas sur l'image par défault

# Q4

Code : LifeModelSync et Mtsafemode

# Q5
si grid update delay est à 0 et screen refresh delay sans bouger alors on a un focntionnement correct et de même inversement car l'image se converge vers l'image par défault. Donc ils sont threads-safe, par ailleurs on peut également oberser une baisse de fps, des cout de lag du au sleep, le refresh et le update ne s'exécutent pas mutuellement voir même  ça ne garanti pas l'alternance entre update et refresh 


