set title "temps d'exécution de chaque instance avec la stratégie first improvement et 2 solutions initiales différentes"
set ylabel "Temps (ms)"
set xlabel "Instance"
set xrange [0:124]
plot "../data/results/vnd/FIRST_IMPROVEMENT_MDD_v1.dat" using 0:2 with lines title "MDD" linetype 7, "../data/results/vnd/FIRST_IMPROVEMENT_RND_v1.dat" using 0:2 with lines title "RND" linetype 6