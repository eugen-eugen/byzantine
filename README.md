# The Byzantine Generals Problem
This Problem is described in the paper of L. Lamport et. all, which is available for example here: [http://lamport.azurewebsites.net/pubs/byz.pdf](http://lamport.azurewebsites.net/pubs/byz.pdf)

There is an alogirithm, how to reach consens  of nodes in a network, if up to 1/3 of nodes are malicious. It is recursive and called  OM-algorithm.

###  Why did I write this program?
I wanted to see the OM in action. I actually didn't plan to write a program on my own, but after a couple hours looking up on internet I haven't found a ready-to-use demo. I don't know why, finally it is a famous problem, which is often discussed.

Anyway, I did and you can also use my program to demonstrate the OM-algorithm of L. Lamport. 

###  What does the program do?
Like im paper there are a (byzantine) commander and some lieutnants, in program defined by constant `nLieutnants`. The commander issues the order to *attack* to all his lieutnants. Since the lieutnants aren't sure, whether the commander loyal or not, they send the order among themselve. It's why a traitorious commander could issue a contradictory order to different lietnants.
Some of lieutnants, however,  in program defined by constant `nTraitors` also traitors and replace the order whith another one for *retreat*.

Unter the condition `nLieutnants + 1 > nTraitors/3` the loyal lieutnants would receive the correct order to attack despite of traitors. Plus 1 is because of the commander. This condition is proven in the paper of L. Lamport. Try it out!

Please note, that in my scenario the commander is loyal. The OM algorithm, however, is also valid even if not. But the program should be modified to see it.

###  How to use?
My program has not a fancy UI or command line interface. You should set the constants `nLieutnants` and `nTraitors` to values you want and rund the class as whith `java de.moldawski.byz.ByzantineFault`. The output shows then all lieutnants and order, which they received.

###  Feedback
I would be glad to receive a feedback, if you like the program or have some more ideas to improve it. Thank You!




