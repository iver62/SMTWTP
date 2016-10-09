set output "C:/Users/Pierrick/workspace/SMTWTP/experiences/xp1.png"
set title "Evaluation de chaque instance et comparaison par rapport aux meilleures solutions connues"
set ylabel "Evaluation"
set xlabel "Instances"
set xrange [0:124]
plot "C:/Users/Pierrick/workspace/SMTWTP/data/wtbest100b.txt" using 0:1 with lines title "BEST", "C:/Users/Pierrick/workspace/SMTWTP/data/results/rnd.dat" using 0:1 with lines title "RND", "C:/Users/Pierrick/workspace/SMTWTP/data/results/edd.dat" using 0:1 with lines title "EDD", "C:/Users/Pierrick/workspace/SMTWTP/data/results/mdd.dat" using 0:1 with lines title "MDD"