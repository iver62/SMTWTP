set title "Temps d'exécution de chaque instance avec 3 solutions initiales différentes, voisinage insert et stratégie best"
set ylabel "Temps (ms)"
set xlabel "Instance"
set xrange [0:124]
plot "../data/results/hc/BEST_IMPROVEMENT_INSERT_MDD.dat" using 0:2 with lines title "MDD" linetype 6, "../data/results/hc/BEST_IMPROVEMENT_INSERT_EDD.dat" using 0:2 with lines title "EDD" linetype 7, "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/BEST_IMPROVEMENT_INSERT_RND.dat" using 0:2 with lines title "RND" linetype 4