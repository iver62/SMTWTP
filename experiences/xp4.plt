set output "C:/Users/Pierrick/workspace/SMTWTP/experiences/xp4.png"
set title "Déviation de chaque instance avec 2 solutions initiales différentes et stratégie best"
set ylabel "Déviation (%)"
set xlabel "Instances"
set xrange [0:124]
plot "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/best_insert_mdd.dat" using 0:1 with lines title "MDD", "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/best_insert_edd.dat" using 0:1 with lines title "EDD"