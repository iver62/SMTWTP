set output "C:/Users/Pierrick/workspace/SMTWTP/experiences/xp2.png"
set title "Déviation de chaque instance avec solution initiale MDD, voisinage insert et 2 stratégies différentes"
set ylabel "Déviation (%)"
set xlabel "Instances"
set xrange [0:124]
plot "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/best_insert_mdd.dat" using 0:1 with lines title "best", "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/first_insert_mdd.dat" using 0:1 with lines title "first"