# vrachtwagen-service
Dit is de vrachtwagen-service back-end voor het project Advanced Programming Topics van Jules Debbaut en Walid Amhaini uit 3ITF. Deze service behandelt voornamelijk de gegevens van een vrachtwagen, bijvoorbeeld nummerplaat, het bedrijf waartoe een vrachtwagen behoort en bovendien het merk, model en bouwjaar van de vrachtwagen. 
De back-end database maakt hier gebruik van MongoDB. Deze microservice voorziet van 6 GET-endpoints, 1 POST-endpoint, 1 PUT-endpoint en 1 DELETE-endpoint. 

#GET /vrachtwagens
Door deze request te maken haal je alle vrachtwagens op die in de database zitten.

#GET /vrachtwagens/nummerplaat/{nummerplaat}
Door een nummerplaat in deze request mee te geven, haal je 1 vrachtwagen op met exact die nummerplaat. 

#GET /vrachtwagens/bouwjaar/{bouwjaar}
Door in deze request een bouwjaar mee te geven, zal u alle vrachtwagens meekrijgen met het gegeven bouwjaar.

#GET /vrachtwagens/merk/{merk}
Door in deze request een merk mee te geven, zal u alle vrachtwagens meekrijgen met een gegeven merk.

#GET /vrachtwagens/model/{model}
Door in deze request een model van een vrachtwagen mee te geven, zal u alle vrachtwagens van dit soort te zien krijgen.

#POST /vrachtwagens
Deze request wordt gebruikt om vrachtwagens aan de back-end toe te voegen.

#PUT /vrachtwagens/nummerplaat/{nummerplaat}
De PUT request gebeurt hier op basis van het nummerplaat van een vrachtwagen, aangezien deze uniek is voor elke vrachtwagen. U geeft hier dus gewoon een nummerplaat mee voor de vrachtwagen die u wilt bewerken.

#DELETE /vrachtwagens/nummerplaat/{nummerplaat}
Ook de DELETE request gebeurt op dezelfde manier. U geeft hier dus de nummerplaat van de vrachtwagen mee die u uit het systeem wilt verwijderen.
