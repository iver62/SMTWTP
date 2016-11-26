set title "Déviation de chaque instance avec 3 voisinages différents, solution initiale MDD et stratégie best"
set ylabel "Déviation (%)"
set xlabel "Instances"
set xrange [0:124]
plot "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/BEST_IMPROVEMENT_INSERT_MDD.dat" using 0:1 with lines title "insert" linetype 7, "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/BEST_IMPROVEMENT_SWAP_MDD" using 0:1 with lines title "swap" linetype 6, "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/BEST_IMPROVEMENT_INTERCHANGE_MDD" using 0:1 with lines title "interchange" linetype 2