(; ( let  prixHt 200 )( let  prixTtc ( /  ( *  prixHt 119 )100 )))
DATA SEGMENT
   prixHt  DD
   prixTtc  DD
DATA ENDS
CODE SEGMENT
deux feuilles   -       mov eax, 200 
    mov prixHt , eax
    mov eax, prixHt 
    push eax
feuille a gauche   -   feuille a droite   -   deux feuilles   -       mov eax, prixHt 
    mul eax, 119 
CODE ENDS
