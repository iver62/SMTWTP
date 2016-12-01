set title "Temps d'exécution de chaque instance avec voisinage insert, solution initiale MDD et 2 stratégies différentes"
set ylabel "Temps (ms)"
set xlabel "Instance"
set xrange [0:124]
plot "../data/results/hc/BEST_IMPROVEMENT_INSERT_MDD.dat" using 0:2 with lines title "best improvement" linetype 7, "../data/results/hc/FIRST_IMPROVEMENT_INSERT_MDD.dat" using 0:2 with lines title "first improvement" linetype 6