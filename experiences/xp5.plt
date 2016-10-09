set output "C:/Users/Pierrick/workspace/SMTWTP/experiences/xp5.png"
set title "Déviation de chaque instance avec 3 voisinages différents, solution initiale MDD et stratégie best"
set ylabel "Déviation (%)"
set xlabel "Instances"
set xrange [0:124]
plot "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/best_insert_mdd.dat" using 0:1 with lines title "insert", "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/best_swap_mdd.dat" using 0:1 with lines title "swap", "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/best_interchange_mdd.dat" using 0:1 with lines title "interchange"