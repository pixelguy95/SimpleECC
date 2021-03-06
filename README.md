# SimpleECC
A simple implementation of the elliptic curve cryptography principles. All in java

## Background
I got interested in Elliptic curve cryptography after reading the book **Mastering bitcoin** by  Andreas M. Antonopoulos. 
After trying to find some learning material online on how to make your own implementation I found the results lacking. 
So I decided to implement my own version of Elliptic curve math and key generation. I tried to make the implementation 
as clear as possible. 

I am no security expert by any means. So This repository is mostly meant to be used for learning by those who
 are interested and not for any serious implementation (as if anyone would use this crap anyway).
 
 
 ## Math and stuff
Currently this implementations supports elliptic curves of this form:

<a href="https://www.codecogs.com/eqnedit.php?latex=\dpi{100}&space;\fn_jvn&space;\large&space;$&space;y^2&space;=&space;x^3&space;&plus;&space;ax&space;&plus;&space;b$" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\dpi{100}&space;\fn_jvn&space;\large&space;$&space;y^2&space;=&space;x^3&space;&plus;&space;ax&space;&plus;&space;b$" title="\large $ y^2 = x^3 + ax + b$" /></a>

Also in current implementation the curve has to be over a field.

### Elliptic curve addition
 <a href="https://www.codecogs.com/eqnedit.php?latex=\dpi{100}&space;\fn_jvn&space;\large&space;$&space;Q&space;&plus;&space;P&space;=&space;R&space;\rightarrow&space;(X_q,&space;Y_q)&space;&plus;&space;(X_p,&space;Y_p)&space;=&space;(X_r,&space;Y_r)$" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\dpi{100}&space;\fn_jvn&space;\large&space;$&space;Q&space;&plus;&space;P&space;=&space;R&space;\rightarrow&space;(X_q,&space;Y_q)&space;&plus;&space;(X_p,&space;Y_p)&space;=&space;(X_r,&space;Y_r)$" title="\large $ Q + P = R \rightarrow (X_q, Y_q) + (X_p, Y_p) = (X_r, Y_r)$" /></a>
 
 The first part of the equation is different depending on the points you are adding.
 
 #### If Q and P are coincident
 <a href="https://www.codecogs.com/eqnedit.php?latex=\dpi{100}&space;\fn_jvn&space;\large&space;$\lambda=(3X_p^2&space;&plus;&space;a)*(2Y_p)^{-1}\;\;mod\;\;p" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\dpi{100}&space;\fn_jvn&space;\large&space;$\lambda=(3X_p^2&space;&plus;&space;a)*(2Y_p)^{-1}\;\;mod\;\;p" title="\large $\lambda=(3X_p^2 + a)*(2Y_p)^{-1}\;\;mod\;\;p" /></a>

### Otherwise
<a href="https://www.codecogs.com/eqnedit.php?latex=\dpi{100}&space;\fn_jvn&space;\large&space;$\lambda=(Y_q-&Y_p)*(X_q-X_p)^{-1}\;\;mod\;\;p" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\dpi{100}&space;\fn_jvn&space;\large&space;$\lambda=(Y_q-&Y_p)*(X_q-X_p)^{-1}\;\;mod\;\;p" title="\large $\lambda=(Y_q-&Y_p)*(X_q-X_p)^{-1}\;\;mod\;\;p" /></a>

### The rest of the equation
<a href="https://www.codecogs.com/eqnedit.php?latex=\dpi{100}&space;\fn_jvn&space;\large&space;X_r=\lambda^2-X_p-X_q\\&space;Y_r=\lambda(X_p-X_r)-Y_p" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\dpi{100}&space;\fn_jvn&space;\large&space;X_r=\lambda^2-X_p-X_q\\&space;Y_r=\lambda(X_p-X_r)-Y_p" title="\large X_r=\lambda^2-X_p-X_q\\ Y_r=\lambda(X_p-X_r)-Y_p" /></a>
