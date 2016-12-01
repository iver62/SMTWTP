set title "Evaluation de chaque instance et comparaison par rapport à la meilleure solution connue"
set ylabel "Evaluation"
set xlabel "Instance"
set xrange [0:124]
plot "../data/wtbest100b.txt" using 0:1 with lines title "BEST" linetype 8, "../data/results/rnd.dat" using 0:1 with lines title "RND" linetype 4, "../data/results/edd.dat" using 0:1 with lines title "EDD" linetype 7, "../data/results/mdd.dat" using 0:1 with lines title "MDD" linetype 6