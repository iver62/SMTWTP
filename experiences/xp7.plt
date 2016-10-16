set output "C:/Users/Pierrick/workspace/SMTWTP/experiences/xp7.png"
set title "comparaison ts et vnd first mdd"
set ylabel "Déviation (%)"
set xlabel "Instances"
set xrange [0:124]
plot "C:/Users/Pierrick/workspace/SMTWTP/data/results/ts/first_mdd.dat" using 0:2 with lines title "TS", "C:/Users/Pierrick/workspace/SMTWTP/data/results/vnd/first_mdd.dat" using 0:2 with lines title "VND"