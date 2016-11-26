set title "Evaluation de chaque instance et comparaison par rapport à la meilleure solution connue"
set ylabel "Evaluation"
set xlabel "Instance"
set xrange [0:124]
plot "C:/Users/Pierrick/workspace/SMTWTP/data/wtbest100b.txt" using 0:1 with lines title "BEST" linetype 8, "C:/Users/Pierrick/workspace/SMTWTP/data/results/rnd.dat" using 0:1 with lines title "RND" linetype 4, "C:/Users/Pierrick/workspace/SMTWTP/data/results/edd.dat" using 0:1 with lines title "EDD" linetype 7, "C:/Users/Pierrick/workspace/SMTWTP/data/results/mdd.dat" using 0:1 with lines title "MDD" linetype 6