set title "Temps d'exécution de chaque instance avec solution initiale MDD, population de 20 individus et 2 algos"
set ylabel "Temps (ms)"
set xlabel "Instance"
set xrange [0:124]
plot "../data/results/ma/20_100_MDD.dat" using 0:2 with lines title "Memetic Algo" linetype 7, "../data/results/ga/20_1000_MDD.dat" using 0:2 with lines title "Genetic Algo" linetype 6